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
public class RiskFileGeneratorTest {

    @Autowired
    RiskFileGenerator riskFileGenerator;

    @Value("${risk.input.path}")
    private String riskFilePath;

    @Test
    public void generateFileTest() {
//        riskFileGenerator.generateFileWithoutPath();
//        Assert.assertTrue(new File(riskFilePath).exists());
    }

}