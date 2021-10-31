package com.max.parser.entity;

public class Context {

    private String datasetId;
    private String batchName;
    private int cobDate;
    private int feedId;
    private int systemId;
    private String tradeInputFile;
    private String riskInputFile;
    private String tradeOutputFile;
    private String riskOutputFile;

    public String getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(String datasetId) {
        this.datasetId = datasetId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public int getCobDate() {
        return cobDate;
    }

    public void setCobDate(int cobDate) {
        this.cobDate = cobDate;
    }

    public int getFeedId() {
        return feedId;
    }

    public void setFeedId(int feedId) {
        this.feedId = feedId;
    }

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public String getTradeInputFile() {
        return tradeInputFile;
    }

    public void setTradeInputFile(String tradeInputFile) {
        this.tradeInputFile = tradeInputFile;
    }

    public String getRiskInputFile() {
        return riskInputFile;
    }

    public void setRiskInputFile(String riskInputFile) {
        this.riskInputFile = riskInputFile;
    }

    public String getTradeOutputFile() {
        return tradeOutputFile;
    }

    public void setTradeOutputFile(String tradeOutputFile) {
        this.tradeOutputFile = tradeOutputFile;
    }

    public String getRiskOutputFile() {
        return riskOutputFile;
    }

    public void setRiskOutputFile(String riskOutputFile) {
        this.riskOutputFile = riskOutputFile;
    }

}
