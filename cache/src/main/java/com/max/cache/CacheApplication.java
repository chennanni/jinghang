package com.max.cache;

import com.max.cache.service.CacheClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CacheApplication implements ApplicationRunner {

    @Autowired
    CacheClient cacheClient;

    private static final Logger logger = LoggerFactory.getLogger(CacheApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        cacheClient.put();
        //cacheClient.get();
    }

}
