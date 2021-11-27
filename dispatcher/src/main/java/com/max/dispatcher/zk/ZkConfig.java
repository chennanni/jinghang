package com.max.dispatcher.zk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZkConfig {

    @Bean
    RegistryConsumer getParserRegistry() {
        return new RegistryConsumer("parser");
    }

}
