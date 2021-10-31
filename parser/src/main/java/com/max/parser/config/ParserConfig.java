package com.max.parser.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParserConfig {

    @Value("${service.name}")
    private String serviceName;
    @Value("${service.max.instance}")
    private int maxInstance;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getMaxInstance() {
        return maxInstance;
    }

    public void setMaxInstance(int maxInstance) {
        this.maxInstance = maxInstance;
    }

}
