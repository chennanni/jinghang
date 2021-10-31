package com.max.parser.output;

import com.max.parser.entity.Risk;
import com.max.parser.entity.Trade;
import com.max.parser.file.RiskOutputFileCreator;
import com.max.parser.file.TradeOutputFileCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Outputter {

    @Value("${output.file.path}")
    String outputFileParentPath;

    @Value("${output.file.format}")
    String outputFileFormat;

    @Autowired
    TradeOutputFileCreator tradeOutputFileCreator;

    @Autowired
    RiskOutputFileCreator riskOutputFileCreator;

    private static final Logger logger = LoggerFactory.getLogger(Outputter.class);

    public String outputTrade(List<Trade> input, String fileName) {
        String outputFileFullPath = this.outputFileParentPath + "\\" + fileName + "." + this.outputFileFormat;
        tradeOutputFileCreator.generateFileTemplate(outputFileFullPath, input);
        logger.info("COMPLETE - output: " + outputFileFullPath);
        return outputFileFullPath;
    }

    public String outputRisk(List<Risk> input, String fileName) {
        String outputFileFullPath = this.outputFileParentPath + "\\" + fileName + "." + this.outputFileFormat;
        riskOutputFileCreator.generateFileTemplate(outputFileFullPath, input);
        logger.info("COMPLETE - output: " + outputFileFullPath);
        return outputFileFullPath;
    }

}
