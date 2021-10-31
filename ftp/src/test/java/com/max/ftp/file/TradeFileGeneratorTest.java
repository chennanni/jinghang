package com.max.ftp.file;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.File;

@SpringBootTest(classes = DummyFtpApplicationForFile.class)
@RunWith(SpringRunner.class)
public class TradeFileGeneratorTest {

    @Autowired
    TradeFileGenerator tradeFileGenerator;

    @Value("${trade.input.path}")
    private String tradeFilePath;

    @Test
    public void generateFileTest() {
//        tradeFileGenerator.generateFileWithoutPath();
//        Assert.assertTrue(new File(tradeFilePath).exists());
    }

}