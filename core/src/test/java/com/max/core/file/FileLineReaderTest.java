package com.max.core.file;

import org.junit.Test;

public class FileLineReaderTest {

    @Test
    public void testFileRead(){
        FileLineReader fileLineReader = new FileLineReader();
        String path = "F:\\CODEBASE\\tmp\\jh\\input\\20210930_totoro01_risk.txt";
        fileLineReader.read(path);
    }

}