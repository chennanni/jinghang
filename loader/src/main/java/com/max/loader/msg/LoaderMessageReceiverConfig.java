package com.max.loader.msg;

import com.max.core.msg.MessageSender;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(value="com.max.core.msg", excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {MessageSender.class})})
public class LoaderMessageReceiverConfig {
}