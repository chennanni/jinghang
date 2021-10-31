package com.max.core.msg;

import com.max.core.processor.CoreProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * receive message
 */
@Component
@EnableJms
public class MessageListener {

   private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);

   @Autowired
   MessageTemplate msgTempalte;

   @Autowired
   CoreProcessor processor;

   @JmsListener(destination = "${listen.to.queue}_${instance.id}")
   public void receiveStartMessage(String message) {
      logger.info("***********************NEW MESSAGE RECEIVED*********************** ");
      Map<String, String> msgMap = msgTempalte.deformat(message);
      msgTempalte.printMap(msgMap);
      processor.processDataset(msgMap);
      logger.info("***********************DONE MESSAGE PROCESS *********************** ");
   }

}
