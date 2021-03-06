package com.meng.blockchain;

import com.meng.blockchain.model.*;
import com.meng.blockchain.security.CryptoUtil;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * 挖矿并一对一简单交易
 */
public class BlockchainTest {

    @Before
    public void setup() throws Exception{

    }

    @Test
    public void testBlockChain() throws Exception {
        //创建一个空的区块链
        List<Block> blockChain = new ArrayList<>();
        //初始化创始区块  默认index为1 交易数为0等
        Block beginBlock = new Block(1, System.currentTimeMillis(), new ArrayList<>(), 1, "1", "1");
        //将创始区块加入到区域链
        blockChain.add(beginBlock);
        System.out.println(JSON.toJSONString(blockChain));
        digMine(blockChain);
    }

    /**
     * 挖矿
     * @param blockChain
     */
    private void digMine(List<Block> blockChain) {
        System.out.println("------开始挖矿------");

        //创建交易集合
        List<Transaction> transactionList = new ArrayList<>();
        //加入系统奖励
        Transaction sysTx = new Transaction();
        transactionList.add(sysTx);
        //加入当前交易集合
        Transaction transaction1 = new Transaction();
        //transaction1 set方法无意义，只是为了配合transaction2
        transaction1.setId(CryptoUtil.UUID());
        transaction1.setTxOut(new TransactionOutput(0, CryptoUtil.UUID()));
        Transaction transaction2 = new Transaction();
        transactionList.add(transaction1);
        transactionList.add(transaction2);

        transaction2.setId(CryptoUtil.UUID());

        //交易发起方
        Wallet walletSender = Wallet.generateWallet();
        //交易接收方
        Wallet walletReciptent = Wallet.generateWallet();

        TransactionInput txIn = new TransactionInput(transaction1.getId(), 10, null,walletSender.getPublicKey());
        transaction2.setTxIn(txIn);
        TransactionOutput txOut = new TransactionOutput(10,walletReciptent.getHashPubKey());
        transaction2.setTxOut(txOut);

        //假设transaction1已经被打包进区块，也就是已经被记录到账本了
        transaction2.sign(walletSender.getPrivateKey(),transaction1);


        //获取当前区块链的最后一个区块的hash值
        long nonce = 1;
        String hash = "";
        Block latestBlock = blockChain.get(blockChain.size() - 1);
        while (true) {

            hash = CryptoUtil.SHA256(latestBlock.getHash() + JSON.toJSONString(transactionList) + nonce);
            if(hash.startsWith("00000")){
                System.out.println("=====计算结果正确，计算次数为：" +nonce+ ",hash:" + hash);
                break;
            }
            nonce ++;
        }
        //创建新空区块
        Block newBlock = new Block(latestBlock.getIndex() + 1, System.currentTimeMillis(), transactionList, nonce, hash, latestBlock.getPreviousHash());
        blockChain.add(newBlock);

        System.out.println("挖矿后的区块链：" + JSON.toJSONString(blockChain));
    }


    @Test
    public void testGenWallet() throws Exception {
        Wallet wallet = Wallet.generateWallet();
        System.out.println(JSON.toJSON(wallet));
    }
}
