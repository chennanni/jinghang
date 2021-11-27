package com.max.core.msg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * send message
 */
@Component
@EnableJms
public class MessageSender {

   private static final Logger logger = LoggerFactory.getLogger(MessageSender.class);

   @Value("${send.to.queue}")
   private String queue;

   @Autowired
   MessageTemplate messageTemplate;

   @Autowired
   private JmsTemplate jmsTemplate;

   public void sendCompleteMessage(int cobDate, int feedId, String datasetId, Map<String, String> filePath) {
      this.send(messageTemplate.format(cobDate, feedId, datasetId, filePath));
   }

   public void send(String message) {
      jmsTemplate.convertAndSend(queue, message);
      logger.info("COMPLETE - send msg, [queue]: " + queue + "; [msg]: " + message);
   }

   public String getQueue() {
      return queue;
   }

   public void setQueue(String queue) {
      this.queue = queue;
   }

}
