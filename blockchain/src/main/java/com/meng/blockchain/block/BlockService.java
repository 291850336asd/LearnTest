package com.meng.blockchain.block;

import com.alibaba.fastjson.JSON;
import com.meng.blockchain.model.*;
import com.meng.blockchain.security.CryptoUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
