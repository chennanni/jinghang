package com.max.ftp.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.max.ftp.file"})
public class DummyFtpApplicationForFile {

    public static void main(String[] args) {
        SpringApplication.run(DummyFtpApplicationForFile.class, args);
    }

}
