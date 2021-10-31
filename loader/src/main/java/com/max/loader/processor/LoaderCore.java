package com.max.loader.processor;

import com.max.core.processor.CoreProcessor;
import com.max.loader.db.LoaderDbManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LoaderCore implements CoreProcessor {

    private static final Logger logger = LoggerFactory.getLogger(LoaderCore.class);

    @Autowired
    LoaderDbManager loaderDbManager;

    @Override
    public void processDataset(Map<String, String> input) {

        // parse msg input
        String datasetId = input.get("dataset_id");
        int cobDate = Integer.valueOf(input.get("cob_date"));
        int feedId = Integer.valueOf(input.get("feed_id"));
        String tradeFile = input.get("trade_file");
        String riskFile = input.get("risk_file");

        // record status: start to process
        boolean isGoodToGo = loaderDbManager.startLoader(datasetId, cobDate, feedId);
        if (!isGoodToGo) {
            logger.warn("This batch job is invalid, NO need to proceed, dataset_id: " + datasetId);
            return;
        }

        // persist to db
        loaderDbManager.loadTradeData(tradeFile);
        loaderDbManager.loadRiskData(riskFile);

        // record status: complete to process
        loaderDbManager.completeLoader(datasetId);

    }
}
