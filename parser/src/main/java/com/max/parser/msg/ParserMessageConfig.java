package com.max.parser.msg;

import com.max.core.msg.MessageListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//@ComponentScan(value="com.max.core.msg")
@ComponentScan(value="com.max.core.msg", excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {MessageListener.class})})
public class ParserMessageConfig {
    // init MessageSender
    // note: MessageListener is created manually when application start
}
