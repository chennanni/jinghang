package com.max.parser.entity;

public class Risk implements FileOutput{

    private String tradeUid;
    private int infotypeId;
    private double amount;
    private String datasetId;

    public static String outputHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("TRADE_UID");
        sb.append(";").append("INFOTYPE_ID");
        sb.append(";").append("RISK_AMOUNT");
        sb.append(";").append("DATASET_ID");
        return sb.toString();
    }

    @Override
    public String outputContent() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getTradeUid());
        sb.append(";").append(this.getInfotypeId());
        sb.append(";").append(this.getAmount());
        sb.append(";").append(this.getDatasetId());
        return sb.toString();
    }

    public String getTradeUid() {
        return tradeUid;
    }

    public void setTradeUid(String tradeUid) {
        this.tradeUid = tradeUid;
    }

    public int getInfotypeId() {
        return infotypeId;
    }

    public void setInfotypeId(int infotypeId) {
        this.infotypeId = infotypeId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(String datasetId) {
        this.datasetId = datasetId;
    }

}
