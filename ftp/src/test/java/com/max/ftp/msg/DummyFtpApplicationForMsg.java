package com.max.ftp.msg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.max.ftp.msg"})
public class DummyFtpApplicationForMsg {

    public static void main(String[] args) {
        SpringApplication.run(DummyFtpApplicationForMsg.class, args);
    }

}
