package com.max.parser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CalService01 {

    private static final Logger logger = LoggerFactory.getLogger(CalService01.class);

    // mock time consuming processing
    public void process() {
        int interval = 10;
        try {
            for (int i = 0; i<interval; i++) {
                logger.info("time consuming process, going to take some time...");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
