package com.max.dispatcher.msg;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value="com.max.core.msg")
public class DispatcherMessageConfig {
    // init MessageListener
    // init MessageSender
}
