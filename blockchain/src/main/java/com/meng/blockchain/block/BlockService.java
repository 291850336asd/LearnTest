package com.meng.blockchain.block;

import com.alibaba.fastjson.JSON;
import com.meng.blockchain.model.*;
import com.meng.blockchain.security.CryptoUtil;

import java.util.*;

/**
 * 区块链核心服务
 */
public class BlockService {

    /**
     * 区块链存储结构
     */
    private List<Block> blockChain = new ArrayList<>();

    /**
     * 当前节点钱包集合
     */
    private Map<String, Wallet> myWalletMap = new HashMap<>();

    /**
     * 其它钱包集合，钱包只包含其它钱包的公钥
     */
    private Map<String, Wallet> otherWalletMap = new HashMap<>();

    /**
     * 转账交易集合
     */
    private  List<Transaction> allTransactions = new ArrayList<>();

    /**
     * 已经打包转账交易
     */
    private List<Transaction> packedTransactions = new ArrayList<>();

    public BlockService() {
        //创建创始区块
        Block genesisBlock = new Block(1, System.currentTimeMillis(), new ArrayList<>(), 1,"1", "1");
        blockChain.add(genesisBlock);
        System.out.println("生成创始区块：" + JSON.toJSONString(genesisBlock));
    }

    /**
     * 挖矿
     * @param toAddress
     * @return
     */
    public Block mine(String toAddress) {
        //创建系统奖励交易
        Transaction coinbaseTx = newCoinbaseTx(toAddress);
        allTransactions.add(coinbaseTx);
        List<Transaction> blockTxs = new ArrayList<>(allTransactions);
        //去除已打包区块的交易
        blockTxs.removeAll(packedTransactions);
        verifyAllTransactions(blockTxs);
        String newBlockHash = "";
        long nonce = 1;
        long start = System.currentTimeMillis();
        System.out.println("开始挖矿");
        while (true){
            //计算新区块hash
            newBlockHash = calculateHash(getLastestBlock().getHash(), blockTxs, nonce);
            //校验新区块hash
            if(isValidHash(newBlockHash)){
                System.out.println("挖矿完成，正确的hash值：" + newBlockHash);
                System.out.println("挖矿耗费时间：" + (System.currentTimeMillis() - start) + "ms");
                break;
            }
            nonce ++;
        }
        //创建新区块
        Block block = createNewBlock(nonce, getLastestBlock().getHash(), newBlockHash, blockTxs);
        return block;
    }

    /**
     *  创建新区块
     */
    private Block createNewBlock(long nonce, String previousHash, String hash, List<Transaction> blockTxs){
        Block block = new Block(blockChain.size() + 1, System.currentTimeMillis(), blockTxs, nonce, previousHash, hash);
        if(addBlock(block)){
            return block;
        }
        return null;
    }

    /**
     * 验证所有交易是否正确， 非常重要的一步，可以防止双花
     * @param blockTxs
     */
    private void verifyAllTransactions(List<Transaction> blockTxs){
        List<Transaction> invalidTxs = new ArrayList<>();
        for(Transaction tx : blockTxs){
            if(!verifyTransaction(tx)){
                invalidTxs.add(tx);
            }
        }
        blockTxs.removeAll(invalidTxs);
        //去除无效交易
        allTransactions.removeAll(invalidTxs);
    }

    /**
     * 验证交易是否合法
     * @param tx
     * @return
     */
    private boolean verifyTransaction(Transaction tx){
        if(tx.coinbaseTx()){
            return true;
        }
        Transaction prevTx = findTransaction(tx.getTxIn().getTxId());
        return tx.verify(prevTx);
    }

    /**
     * 根据唯一标识获取交易
     * @param id
     * @return
     */
    private Transaction findTransaction(String id){
        for(Transaction tx : allTransactions){
            if(id.equals(tx.getId())){
                return tx;
            }
        }
        return null;
    }

    /**
     * 生成区块奖励交易
     * @param toAddress
     * @return
     */
    public Transaction newCoinbaseTx(String toAddress){
        TransactionInput txIn = new TransactionInput("0", -1, null, null);
        Wallet wallet = myWalletMap.get(toAddress);
        //指定生成区块的奖励为10BTC
        TransactionOutput txOut = new TransactionOutput(10, wallet.getHashPubKey());
        return new Transaction(CryptoUtil.UUID(), txIn, txOut);
    }

    /**
     * 创建交易
     * @param senderWallet
     * @param recipientWallet
     * @param amount
     * @return
     */
    public Transaction createTransaction(Wallet senderWallet, Wallet recipientWallet, int amount){
        //查找未消费的交易
        List<Transaction> unspentTxs = findUnspentTransactions(senderWallet.getAddress());
        Transaction prevTx = null;
        for(Transaction transaction : unspentTxs){
            //TODO 找零或者 当前金额不一致如何处理
            if(transaction.getTxOut().getValue() != amount){

            } else {
                prevTx = transaction;
                break;
            }
        }
        if(prevTx == null){
            return null;
        }
        TransactionInput txIn = new TransactionInput(prevTx.getId(), amount, null,senderWallet.getPublicKey());
        TransactionOutput txOut = new TransactionOutput(amount, recipientWallet.getHashPubKey());
        Transaction transaction = new Transaction(CryptoUtil.UUID(), txIn, txOut);
        transaction.sign(senderWallet.getPrivateKey(), prevTx);
        allTransactions.add(transaction);
        return transaction;
    }

    /**
     * 查找未消费的交易
     * @param address
     * @return
     */
    private List<Transaction> findUnspentTransactions(String address){
        List<Transaction> unspentsTxs = new ArrayList<>();
        Set<String> spentTxs = new HashSet<>();
        //获取已经花费的交易
        for(Transaction tx : allTransactions){
            if(tx.coinbaseTx()){
                continue;
            }
            if(address.equals(Wallet.getAddress(tx.getTxIn().getPublicKey()))){
                spentTxs.add(tx.getTxIn().getTxId());
            }
        }
        //获取未花费的交易  allTransactions可能包含未打包的交易不能使用
        //只能从区块链的交易集合获取
        for(Block block : blockChain){
            List<Transaction> transactions = block.getTransactions();
            for(Transaction transaction : transactions){
                if(address.equals(CryptoUtil.MD5(transaction.getTxOut().getPublicKeyHash()))){
                    if(!spentTxs.contains(transaction.getId())){
                        unspentsTxs.add(transaction);
                    }
                }
            }
        }
        return unspentsTxs;
    }

    /**
     * 创建钱包
     * @return
     */
    public Wallet createWallet(){
        Wallet wallet = Wallet.generateWallet();
        String address = wallet.getAddress();
        myWalletMap.put(address, wallet);
        return wallet;
    }

    /**
     * 获取钱包余额
     * @param address
     * @return
     */
    public int getWalletBalance(String address){
        List<Transaction> unspentTxs = findUnspentTransactions(address);
        int balance = 0;
        for(Transaction transaction : unspentTxs){
            balance += transaction.getTxOut().getValue();
        }
        return balance;
     }
    /**
     * 返回最后一个区块
     * @return
     */
    public Block getLastestBlock() {
        return blockChain.size() > 0 ? blockChain.get(blockChain.size() - 1) : null;
    }


    /**
     * 添加新区块
     * @param newBlock
     * @return
     */
    public boolean addBlock(Block newBlock) {
        if(isValidNewBlock(newBlock, getLastestBlock())){
            blockChain.add(newBlock);
            //新区块的交易需要加入已打包的交易集合里去
            packedTransactions.addAll(newBlock.getTransactions());
            return true;
        }
        return false;
    }

    /**
     * 验证新区块是否有效
     * @param newBlock
     * @param previousBlock
     * @return
     */
    public boolean isValidNewBlock(Block newBlock, Block previousBlock) {
        if(!previousBlock.getHash().equals(newBlock.getPreviousHash())){
            System.out.println("新区块的前一个区块hash验证不通过");
            return false;
        }
        //验证新区块hash值是否正确
        String hash = calculateHash(newBlock.getPreviousHash(), newBlock.getTransactions(), newBlock.getNonce());
        if(!hash.equals(newBlock.getHash())){
            System.out.println("新区块的hash无效: " + hash + " " + newBlock.getHash());
            return false;
        }
        if(!isValidHash(newBlock.getHash())){
            System.out.println("新区块的hash无效: " + hash );
            return false;
        }
        return true;
    }

    /**
     * 验证整个区块链是否有效
     */
    public boolean isValidChain(List<Block> chain){
        Block block = null;
        Block lastBlock = chain.get(0);
        int currentIndex = 1;
        while (currentIndex < chain.size()){
            block = chain.get(currentIndex);
            if(!isValidNewBlock(block, lastBlock)){
                return false;
            }
            lastBlock = block;
            currentIndex ++;
        }
        return true;
    }

    /**
     * 替换本地区块链
     * @param newBlocks
     */
    public void replaceChain(List<Block> newBlocks){
        if(isValidChain(newBlocks) && newBlocks.size() > blockChain.size()){
            blockChain = newBlocks;
            //清除已打包交易结合
            packedTransactions.clear();
            blockChain.forEach(block ->{
                packedTransactions.addAll(block.getTransactions());
            });
        } else {
            System.out.println("接收的区块链无效");
        }
    }

    /**
     * 计算区块hash
     * @param previousHash
     * @param currentTransactions
     * @param nonce
     * @return
     */
    private String calculateHash(String previousHash, List<Transaction> currentTransactions, long nonce) {
        return CryptoUtil.SHA256(previousHash + JSON.toJSONString(currentTransactions) + nonce);
    }

    /**
     * 验证hash是否满足系统条件
     * @param hash
     * @return
     */
    private boolean isValidHash(String hash){
        return hash.startsWith("0000");
    }

    public List<Block> getBlockChain() {
        return blockChain;
    }

    public void setBlockChain(List<Block> blockChain) {
        this.blockChain = blockChain;
    }

    public Map<String, Wallet> getMyWalletMap() {
        return myWalletMap;
    }

    public void setMyWalletMap(Map<String, Wallet> myWalletMap) {
        this.myWalletMap = myWalletMap;
    }

    public Map<String, Wallet> getOtherWalletMap() {
        return otherWalletMap;
    }

    public void setOtherWalletMap(Map<String, Wallet> otherWalletMap) {
        this.otherWalletMap = otherWalletMap;
    }

    public List<Transaction> getAllTransactions() {
        return allTransactions;
    }

    public void setAllTransactions(List<Transaction> allTransactions) {
        this.allTransactions = allTransactions;
    }

    public List<Transaction> getPackedTransactions() {
        return packedTransactions;
    }

    public void setPackedTransactions(List<Transaction> packedTransactions) {
        this.packedTransactions = packedTransactions;
    }
}
