package com.max.receiver.msg;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value="com.max.core.msg")
public class ReceiverMessageConfig {
    // init MessageListener
    // init MessageSender
}
