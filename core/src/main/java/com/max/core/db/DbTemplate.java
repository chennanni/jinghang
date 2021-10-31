package com.max.core.db;

import com.max.core.enums.DatasetStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * use this template to:
 * - start/end process
 * - get batch_name from dataset_id
 * - get system_id from feed_id
 */
@Component
public class DbTemplate {

    private static final Logger logger = LoggerFactory.getLogger(DbTemplate.class);

    @Autowired
    DatasetDAO datasetDAO;

    @Autowired
    FeedDAO feedDAO;

    public boolean startProcessDataset(String datasetId, int cobDate, int feedId, String nextStatus) {
        try {
            String currentStatus = DatasetStatus.getPreviousStatus(nextStatus);
            boolean isValid = datasetDAO.isDatasetValid(datasetId, cobDate, feedId, currentStatus);
            if (isValid) {
                datasetDAO.updateDatasetStatus(datasetId,nextStatus);
                return true;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public void completeProcessDataset(String datasetId, String completeStatus) {
        datasetDAO.updateDatasetStatus(datasetId, completeStatus);
    }

    public String getBatchNameFromDatasetId(String datasetId) {
        return datasetDAO.getBatchNameFromDatasetId(datasetId);
    }

    public int getSystemIdFromFeedId(int feedId) {
        return feedDAO.getSystemIdFromFeedId(feedId);
    }

    public String getFeedNameFromFeedId(int feedId) {
        return feedDAO.getFeedNameFromFeedId(feedId);
    }

}
