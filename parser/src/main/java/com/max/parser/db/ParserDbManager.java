package com.max.parser.db;

import com.max.core.db.DbTemplate;
import com.max.core.enums.DatasetStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(value="com.max.core.db")
public class ParserDbManager {

    private static final Logger logger = LoggerFactory.getLogger(ParserDbManager.class);

    @Autowired
    DbTemplate dbTemplate;

    public boolean startParser(String datasetId, int cobDate, int feedId) {
        return dbTemplate.startProcessDataset(datasetId, cobDate, feedId, DatasetStatus.PARSER_START);
    }

    public void completeParser(String datasetId) {
        dbTemplate.completeProcessDataset(datasetId, DatasetStatus.PARSER_END);
    }

}
