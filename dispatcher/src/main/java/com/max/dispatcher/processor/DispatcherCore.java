package com.max.dispatcher.processor;

import com.max.core.msg.MessageSender;
import com.max.core.processor.CoreProcessor;
import com.max.dispatcher.db.DispatcherDbManager;
import com.max.dispatcher.zk.RegistryConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

@Component
public class DispatcherCore implements CoreProcessor {

    private static final Logger logger = LoggerFactory.getLogger(DispatcherCore.class);

    @Value("${service.name}")
    private String serviceName;

    @Autowired
    DispatcherDbManager dispatcherDbManager;

    @Autowired
    MessageSender messageSender;

    @Autowired
    RegistryConsumer parserRegistry;

    @Override
    public void processDataset(Map<String, String> input) {

        // get msg from dispatcher's priority queue

        // parse msg input
        String datasetId = input.get("dataset_id");
        int cobDate = Integer.valueOf(input.get("cob_date"));
        int feedId = Integer.valueOf(input.get("feed_id"));
        String tradeFile = input.get("trade_file");
        String riskFile = input.get("risk_file");
        Map<String, String> filePath = new HashMap<>();
        filePath.put("TRADE_FILE", tradeFile);
        filePath.put("RISK_FILE", riskFile);

        // record status: start to process
        boolean isGoodToGo = dispatcherDbManager.startDispatcher(datasetId, cobDate, feedId);
        if (!isGoodToGo) {
            logger.warn("This batch job is invalid, NO need to proceed, dataset_id: " + datasetId);
            return;
        }

        // distribute msg to parsers's priority queue
        String instanceId = "";
        try {
            List<String> services = parserRegistry.pullServiceList();
            Optional.ofNullable(services).ifPresent(list -> {
                logger.info("size: " + list.size());
                list.stream().forEach(s -> logger.info("parserRegistry available instance_id: " + s));
            });
            // distribution alg
            if (services!=null && services.size()>0) {
                Random rand = new Random(System.currentTimeMillis());
                instanceId = services.get(rand.nextInt(services.size()));
                logger.info("picking instance_id: " + instanceId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StringUtils.hasText(instanceId)) {
            String[] parserQueue = messageSender.getQueue().split("_");
            messageSender.setQueue(parserQueue[0] + "_" + instanceId);
            messageSender.sendCompleteMessage(cobDate, feedId, datasetId, filePath);

        } else {
            // note: should handle this scenario better and add retry-later function
            logger.error("no available parsers, discard this msg");
        }


        // record status: complete to process
        dispatcherDbManager.completeDispatcher(datasetId);

    }
}
