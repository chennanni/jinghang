package com.max.ftp.db;

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
import java.util.Map;

@Component
public class FtpDAO {

    private static final Logger logger = LoggerFactory.getLogger(FtpDAO.class);
    private static final String INSERT_NEW_JOB_SQL = "INSERT INTO dataset (cob_date,feed_id,status,active,creation_time,last_update_time) VALUES (%cobDate%,%feedId%,'F','Y',current_timestamp,current_timestamp)";
    private static final String INACTIVE_SQL = "UPDATE dataset SET active = 'N', last_update_time = current_timestamp WHERE cob_date = %cobDate% AND feed_id = %feedId%;";
    private static final String SELECT_INSERT_ID_SQL = "SELECT LAST_INSERT_ID();";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> testConnection() {
        List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from dataset");
        return result;
    }

    @Transactional
    public String selectOneActiveRecord() throws Exception {
        String sql = SELECT_INSERT_ID_SQL;
        List<String> ids = jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString(1);
            }
        });
        if (ids.isEmpty() || ids.size()==0) {
            throw new Exception("Error - get 0 insert ids!");
        } else if (ids.size() == 1) {
            logger.info("COMPLETE - get insert id");
            return ids.get(0);
        } else {
            throw new Exception("Error - get multiple insert ids!");
        }
    }

    @Transactional
    public void inactiveRecords(int cobDate, int feedId) {
        String sql = INACTIVE_SQL
                .replaceAll("%cobDate%", String.valueOf(cobDate))
                .replaceAll("%feedId%", String.valueOf(feedId));
        jdbcTemplate.execute(sql);
        logger.info("COMPLETE - inactive com.max.loader.db records");
    }

    @Transactional
    public void insertOneNewJobRecord(int cobDate, int feedId) {
        String sql = INSERT_NEW_JOB_SQL
                .replaceAll("%cobDate%", String.valueOf(cobDate))
                .replaceAll("%feedId%", String.valueOf(feedId));
        jdbcTemplate.execute(sql);
        logger.info("COMPLETE - insert a new com.max.loader.db record");
    }

}