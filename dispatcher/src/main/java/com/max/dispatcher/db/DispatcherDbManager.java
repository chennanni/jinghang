package com.max.dispatcher.db;

import com.max.core.db.DbTemplate;
import com.max.core.enums.DatasetStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(value="com.max.core.db")
public class DispatcherDbManager {

    private static final Logger logger = LoggerFactory.getLogger(DispatcherDbManager.class);

    @Autowired
    DbTemplate dbTemplate;

    public boolean startDispatcher(String datasetId, int cobDate, int feedId) {
        return dbTemplate.startProcessDataset(datasetId, cobDate, feedId, DatasetStatus.DISPATCHER_START);
    }

    public void completeDispatcher(String datasetId) {
        dbTemplate.completeProcessDataset(datasetId, DatasetStatus.DISPATCHER_END);
    }

}
