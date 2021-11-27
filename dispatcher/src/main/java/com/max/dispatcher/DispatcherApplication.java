package com.max.dispatcher;

import com.max.dispatcher.zk.RegistryConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class DispatcherApplication implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(DispatcherApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DispatcherApplication.class, args);
    }

    @Autowired
    RegistryConsumer parserRegistry;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        try {
            List<String> services = parserRegistry.pullServiceListWithoutWatcher();
            Optional.ofNullable(services).ifPresent(list -> list.stream().forEach(s -> logger.info("parserRegistry available instance_id: " + s)));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
