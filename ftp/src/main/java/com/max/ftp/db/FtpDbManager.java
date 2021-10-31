package com.max.ftp.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@ComponentScan(value="com.max.core.db")
public class FtpDbManager {

    private static final Logger logger = LoggerFactory.getLogger(FtpDbManager.class);

    @Autowired
    FtpDAO db;

    @Transactional
    public String updateDb(int cobDate, int feedId) {
        String id = "-1";
        try {
            db.inactiveRecords(cobDate, feedId);
            db.insertOneNewJobRecord(cobDate, feedId);
            id = db.selectOneActiveRecord();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return id;
    }

}
