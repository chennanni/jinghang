package com.max.parser.entity;

public class Trade implements FileOutput {

    private String uid;
    private String tradeType;
    private String tradeAction;
    private String datasetId;
    private int systemId;
    private int accountUid;
    private int instrumentUid;

    public static String outputHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("TRADE_UID");
        sb.append(";").append("TRADE_ACTION");
        sb.append(";").append("TRADE_TYPE");
        sb.append(";").append("DATASET_ID");
        sb.append(";").append("SYSTEM_ID");
        sb.append(";").append("ACCOUNT_UID");
        sb.append(";").append("INSTRUMENT_UID");
        return sb.toString();
    }

    @Override
    public String outputContent() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getUid());
        sb.append(";").append(this.getTradeAction());
        sb.append(";").append(this.getTradeType());
        sb.append(";").append(this.getDatasetId());
        sb.append(";").append(this.getSystemId());
        sb.append(";").append(this.getAccountUid());
        sb.append(";").append(this.getInstrumentUid());
        return sb.toString();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeAction() {
        return tradeAction;
    }

    public void setTradeAction(String tradeAction) {
        this.tradeAction = tradeAction;
    }

    public String getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(String datasetId) {
        this.datasetId = datasetId;
    }

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public int getAccountUid() {
        return accountUid;
    }

    public void setAccountUid(int accountUid) {
        this.accountUid = accountUid;
    }

    public int getInstrumentUid() {
        return instrumentUid;
    }

    public void setInstrumentUid(int instrumentUid) {
        this.instrumentUid = instrumentUid;
    }

}
