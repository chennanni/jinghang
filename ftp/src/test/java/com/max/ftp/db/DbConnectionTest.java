package com.max.ftp.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = DummyFtpApplicationForDb.class)
@RunWith(SpringRunner.class)
public class DbConnectionTest {

   @Autowired
   FtpDAO db;

   @Test
   public void dbConnectionTest() {
      System.out.println("RESULT: " + db.testConnection());
   }
}
