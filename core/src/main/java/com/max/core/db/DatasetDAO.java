package com.max.core.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class DatasetDAO {

    private static final Logger logger = LoggerFactory.getLogger(DatasetDAO.class);
    private static final String SELECT_DATASET_SQL = "SELECT COUNT(*) FROM dataset WHERE ID = %id% AND cob_date = %cobDate% AND feed_id = %feedId% AND active = 'Y' AND status = '%status%';";
    private static final String UPDATE_DATASET_SQL = "UPDATE dataset SET status = '%status%', last_update_time = current_timestamp WHERE id = %id%;";
    private static final String SELECT_BATCH_NAME_SQL = "SELECT f.name FROM dataset d, feed f WHERE d.id = %id% AND d.feed_id = f.id;";
    private static final String SELECT_SYSTEM_ID_SQL = "SELECT system_id FROM feed WHERE id = %id%";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional
    protected boolean isDatasetValid(String datasetId, int cobDate, int feedId, String status) throws Exception {
        String sql = SELECT_DATASET_SQL
                .replaceAll("%id%", datasetId)
                .replaceAll("%cobDate%", String.valueOf(cobDate))
                .replaceAll("%feedId%", String.valueOf(feedId))
                .replaceAll("%status%", String.valueOf(status));
        List<String> counts = jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString(1);
            }
        });
        if (counts.isEmpty() || counts.size()==0 || counts.get(0).equalsIgnoreCase("0")) {
            logger.debug("COMPLETE - get 0 record");
            return false;
        } else if (counts.size() == 1 && counts.get(0).equalsIgnoreCase("1")) {
            logger.debug("COMPLETE - get 1 record");
            return true;
        } else {
            throw new Exception("Error - get multiple records!");
        }
    }

    @Transactional
    protected void updateDatasetStatus(String datasetId, String status) {
        String sql = UPDATE_DATASET_SQL.replaceAll("%id%", datasetId).replaceAll("%status%", status);
        jdbcTemplate.execute(sql);
        logger.info("COMPLETE - update record to [" + status + "]");
    }

    @Transactional
    protected String getBatchNameFromDatasetId(String datasetId) {
        String sql = SELECT_BATCH_NAME_SQL.replaceAll("%id%", datasetId);
        List<String> names = jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString(1);
            }
        });
        if (!names.isEmpty() && names.size() == 1) {
            String batchName = names.get(0);
            logger.debug("get batch_name: " + batchName + "; dataset_id: " + datasetId);
            return batchName;
        }
        return null;
    }

}