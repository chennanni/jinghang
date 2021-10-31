package com.max.parser.processor;

import com.max.core.db.DbTemplate;
import com.max.core.file.FileTemplate;
import com.max.core.msg.MessageSender;
import com.max.core.processor.CoreProcessor;
import com.max.parser.db.ParserDbManager;
import com.max.parser.entity.Context;
import com.max.parser.entity.Risk;
import com.max.parser.entity.Trade;
import com.max.parser.mapper.MapperController;
import com.max.parser.mapper.MapperCore;
import com.max.parser.output.Outputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ParserCore implements CoreProcessor {

    private static final Logger logger = LoggerFactory.getLogger(ParserCore.class);

    @Autowired
    MessageSender messageSender;

    @Autowired
    FileTemplate fileTemplate;

    @Autowired
    DbTemplate dbTemplate;

    @Autowired
    ParserDbManager parserDbManager;

    @Autowired
    MapperController mapperController;

    @Autowired
    Outputter outputter;

    @Override
    public void processDataset(Map<String, String> input) {
        Context context = new Context();
        // parse msg input
        context.setDatasetId(input.get("dataset_id"));
        context.setCobDate(Integer.valueOf(input.get("cob_date")));
        context.setFeedId(Integer.valueOf(input.get("feed_id")));
        context.setTradeInputFile(input.get("trade_file"));
        context.setRiskInputFile(input.get("risk_file"));

        // record status: start to process
        boolean isGoodToGo = parserDbManager.startParser(context.getDatasetId(), context.getCobDate(), context.getFeedId());
        if (!isGoodToGo) {
            logger.warn("This batch job is invalid, NO need to proceed, dataset_id: " + context.getDatasetId());
            return;
        }

        // get additional info
        context.setBatchName(dbTemplate.getBatchNameFromDatasetId(context.getDatasetId()));
        context.setSystemId(dbTemplate.getSystemIdFromFeedId(context.getFeedId()));

        // file processing
        MapperCore mapper = mapperController.getMapper(context.getBatchName());
        List<Trade> trades = mapper.parseTrade(context.getTradeInputFile(), context);
        List<Risk> risks = mapper.parseRisk(context.getRiskInputFile(), context);
        String tradeFileName = fileTemplate.getFileNameWithoutExtension(context.getTradeInputFile());
        String riskFileName = fileTemplate.getFileNameWithoutExtension(context.getRiskInputFile());

        // file output
        context.setTradeOutputFile(outputter.outputTrade(trades, tradeFileName));
        context.setRiskOutputFile(outputter.outputRisk(risks, riskFileName));
         Map<String, String> outputFilePath = new HashMap<>();
        outputFilePath.put("TRADE_FILE", context.getTradeOutputFile());
        outputFilePath.put("RISK_FILE", context.getRiskOutputFile());

        // record status: complete to process
        parserDbManager.completeParser(context.getDatasetId());

        // send msg to parser
        messageSender.sendCompleteMessage(context.getCobDate(), context.getFeedId(), context.getDatasetId(), outputFilePath);
    }
}
