package com.max.receiver.db;

import com.max.core.db.DbTemplate;
import com.max.core.enums.DatasetStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(value="com.max.core.db")
public class ReceiverDbManager {

    private static final Logger logger = LoggerFactory.getLogger(ReceiverDbManager.class);

    @Autowired
    DbTemplate dbTemplate;

    public boolean startReceiver(String datasetId, int cobDate, int feedId) {
        return dbTemplate.startProcessDataset(datasetId, cobDate, feedId, DatasetStatus.RECEIVER_START);
    }

    public void completeReceiver(String datasetId) {
        dbTemplate.completeProcessDataset(datasetId, DatasetStatus.RECEIVER_END);
    }

}
