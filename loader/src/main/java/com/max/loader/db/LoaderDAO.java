package com.max.loader.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LoaderDAO {

    private static final Logger logger = LoggerFactory.getLogger(LoaderDAO.class);

    private static String LOAD_TRADE_SQL = "load data infile %trade_file% into table `db01`.`trade` \n" +
            "fields terminated by ';' \n" +
            "lines terminated by '\\n'\n" +
            "ignore 1 lines\n" +
            "(`uid`,`action`,`trade_type`,`dataset_id`,`system_id`,`account_uid`,`instrument_uid`);";

    private static String LOAD_RISK_SQL = "load data infile %risk_file% into table `db01`.`risk` \n" +
            "fields terminated by ';' \n" +
            "lines terminated by '\\n'\n" +
            "ignore 1 lines\n" +
            "(`trade_uid`,`infotype_id`,`amount`,`dataset_id`);";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional
    protected void loadTradeData(String filePath) {
        String sql = LOAD_TRADE_SQL.replaceAll("%trade_file%", formatPath(filePath));
        //logger.info("execute loadTradeData SQL: " + sql);
        jdbcTemplate.execute(sql);
        logger.info("COMPLETE - load trade data");
    }

    @Transactional
    protected void loadRiskData(String filePath) {
        String sql = LOAD_RISK_SQL.replaceAll("%risk_file%", formatPath(filePath));
        //logger.info("execute loadRiskData SQL: " + sql);
        jdbcTemplate.execute(sql);
        logger.info("COMPLETE - load risk data");
    }

    private String formatPath(String input) {
        String result = "\"" + input.replaceAll("\\\\", "\\\\\\\\\\\\\\\\") + "\"";
        //logger.info("format path: " + input + " -> " + result);
        return result;
    }

}
