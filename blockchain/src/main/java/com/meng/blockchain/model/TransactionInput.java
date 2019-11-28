package com.meng.blockchain.model;

/**
 * 交易输入
 */
public class TransactionInput {

    /**
     * 前一次交易id
     */
    private String txId;

    /**
     * 交易的金额
     */
    private int value;

    /**
     * 交易签名
     */
    private String signatrue;

    /**
     * 交易发送方的钱包公钥
     */
    private String publicKey;

    public TransactionInput() {
    }

    public TransactionInput(String txId, int value, String signatrue, String publicKey) {
        this.txId = txId;
        this.value = value;
        this.signatrue = signatrue;
        this.publicKey = publicKey;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getSignatrue() {
        return signatrue;
    }

    public void setSignatrue(String signatrue) {
        this.signatrue = signatrue;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
