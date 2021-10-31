package com.max.receiver.file;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

public class FileManipulatorTest {

    @Test
    public void getFileNameTest() {
        String inputPath = "D:\\tmp\\jh\\totoro01_trade.txt";
        String[] array = inputPath.split("\\\\");
        String fileName = array[array.length-1];
        Assert.assertEquals(fileName, "totoro01_trade.txt");
    }

}