package com.max.receiver.processor;

import com.max.core.msg.MessageSender;
import com.max.core.processor.CoreProcessor;
import com.max.receiver.db.ReceiverDbManager;
import com.max.receiver.file.ReceiverFileManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ReceiverCore implements CoreProcessor {

    private static final Logger logger = LoggerFactory.getLogger(ReceiverCore.class);

    @Autowired
    ReceiverDbManager receiverDbManager;

    @Autowired
    ReceiverFileManager receiverFileManager;

    @Autowired
    MessageSender messageSender;

    @Override
    public void processDataset(Map<String, String> input) {
        // parse msg input
        String datasetId = input.get("dataset_id");
        int cobDate = Integer.valueOf(input.get("cob_date"));
        int feedId = Integer.valueOf(input.get("feed_id"));
        String tradeFile = input.get("trade_file");
        String riskFile = input.get("risk_file");

        // record status: start to process
        boolean isGoodToGo = receiverDbManager.startReceiver(datasetId, cobDate, feedId);
        if (!isGoodToGo) {
            logger.warn("This batch job is invalid, NO need to proceed, dataset_id: " + datasetId);
            return;
        }

        // file processing
        String newTradeFilePath = receiverFileManager.process(tradeFile, cobDate);
        String newRiskFilePath = receiverFileManager.process(riskFile, cobDate);
        if (StringUtils.isEmpty(newTradeFilePath) || StringUtils.isEmpty(newRiskFilePath)) {
            logger.warn("File process failed, please check manually, dataset_id: " + datasetId);
            return;
        }
        Map<String, String> filePath = new HashMap<>();
        filePath.put("TRADE_FILE", newTradeFilePath);
        filePath.put("RISK_FILE", newRiskFilePath);

        // record status: complete to process
        receiverDbManager.completeReceiver(datasetId);

        // send msg to parser
        messageSender.sendCompleteMessage(cobDate, feedId, datasetId, filePath);
    }
}
