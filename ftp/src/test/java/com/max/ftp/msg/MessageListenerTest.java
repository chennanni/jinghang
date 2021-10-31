package com.max.ftp.msg;

import com.max.core.msg.MessageSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = DummyFtpApplicationForMsg.class)
@RunWith(SpringRunner.class)
public class MessageListenerTest {

   @Autowired
   MessageSender messageSender;

   @Test
   public void receiveMessageTest() {
      messageSender.send("This is a test msg.");
      try {
         Thread.sleep(1000);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
      System.out.println("test done");
   }
}
