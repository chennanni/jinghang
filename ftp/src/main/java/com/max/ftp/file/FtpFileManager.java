package com.max.ftp.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FtpFileManager {

    @Autowired
    RiskFileGenerator riskFileGenerator;

    @Autowired
    TradeFileGenerator tradeFileGenerator;

    public Map<String, String> generateFile(int cobDate, int feedId, String feedName) {
        //TODO generate file based on batch / feedId
        Map<String, String> result = new HashMap<>();
        String tradeFilePath = tradeFileGenerator.generateFile(cobDate, feedId, feedName);
        String riskFilePath = riskFileGenerator.generateFile(cobDate, feedId, feedName);
        result.put("TRADE_FILE", tradeFilePath);
        result.put("RISK_FILE", riskFilePath);
        return result;
    }

}
