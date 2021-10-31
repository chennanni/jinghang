package com.max.ftp.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.max.ftp.com.max.loader.db"})
public class DummyFtpApplicationForDb {

    public static void main(String[] args) {
        SpringApplication.run(DummyFtpApplicationForDb.class, args);
    }

}
