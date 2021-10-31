package com.max.loader.db;

import com.max.core.db.DbTemplate;
import com.max.core.enums.DatasetStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(value="com.max.core.db")
public class LoaderDbManager {

    private static final Logger logger = LoggerFactory.getLogger(LoaderDbManager.class);

    @Autowired
    DbTemplate dbTemplate;

    @Autowired
    LoaderDAO loaderDAO;

    public boolean startLoader(String datasetId, int cobDate, int feedId) {
        return dbTemplate.startProcessDataset(datasetId, cobDate, feedId, DatasetStatus.LOADER_START);
    }

    public void completeLoader(String datasetId) {
        dbTemplate.completeProcessDataset(datasetId, DatasetStatus.LOADER_END);
    }

    public void loadTradeData(String path) {
        loaderDAO.loadTradeData(path);
    }

    public void loadRiskData(String path) {
        loaderDAO.loadRiskData(path);
    }

}
