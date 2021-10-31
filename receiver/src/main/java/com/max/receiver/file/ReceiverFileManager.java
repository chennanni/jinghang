package com.max.receiver.file;

import com.max.core.file.FileTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@ComponentScan(value="com.max.core.file")
public class ReceiverFileManager {

    // TODO: create folder if not exist

    private static final Logger logger = LoggerFactory.getLogger(ReceiverFileManager.class);

    @Autowired
    FileTemplate fileTemplate;

    @Value("${input.file.path}")
    String receivedFilePath;

    public String process(String inputFilePath, int cobDate) {
        // 1. rename, add timestamp
        // 2. move
        String newFilePath = this.addTimestampAndMove(cobDate, inputFilePath, receivedFilePath);
        return newFilePath;
    }

    /**
     * return the newFilePath if success
     * OR null if failure
     * @param cobDate
     * @param filePath
     * @param newFileParentPath
     * @return
     */
    private String addTimestampAndMove(int cobDate, String filePath, String newFileParentPath) {
        if (filePath == null) return null;
        // get file name
        String fileName = fileTemplate.getFileNameWithExtension(filePath);
        // add new parent path and date
        String newFilePath = newFileParentPath + "\\" + cobDate + "_" + fileName;
        // move
        File file = new File(filePath);
        File newFile = new File(newFilePath);
        if (file.exists()) {
            file.renameTo(newFile);
            logger.info("COMPLETE - file moved from [" + filePath + "] to [" + newFilePath + "]");
            return newFilePath;
        } else {
            return null;
        }
    }

}
