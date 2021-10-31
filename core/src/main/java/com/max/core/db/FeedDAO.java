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
public class FeedDAO {

    private static final Logger logger = LoggerFactory.getLogger(FeedDAO.class);
    private static final String SELECT_SYSTEM_ID_SQL = "SELECT system_id FROM feed WHERE id = %id%";
    private static final String SELECT_FEED_NAME_SQL = "SELECT name FROM feed WHERE id = %id%";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional
    protected int getSystemIdFromFeedId(int feedId) {
        String sql = SELECT_SYSTEM_ID_SQL.replaceAll("%id%", String.valueOf(feedId));
        List<String> names = jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString(1);
            }
        });
        if (!names.isEmpty() && names.size() == 1) {
            String systemId = names.get(0);
            logger.debug("get system_id: " + systemId + "; feed_id: " + feedId);
            return Integer.parseInt(systemId);
        }
        return 0;
    }

    @Transactional
    protected String getFeedNameFromFeedId(int feedId) {
        String sql = SELECT_FEED_NAME_SQL.replaceAll("%id%", String.valueOf(feedId));
        List<String> names = jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString(1);
            }
        });
        if (!names.isEmpty() && names.size() == 1) {
            String feedName = names.get(0);
            logger.debug("get name: " + feedName + "; feed_id: " + feedId);
            return feedName;
        }
        return "";
    }

}