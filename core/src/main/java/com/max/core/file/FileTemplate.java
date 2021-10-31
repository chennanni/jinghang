package com.max.core.file;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * use this template to:
 * - read file
 * - read file name
 */
@Component
public class FileTemplate {

    private static final Logger logger = LoggerFactory.getLogger(FileTemplate.class);

    @Autowired
    FileLineReader fileLineReader;

    public SourceFile read(String path) {
        return fileLineReader.read(path);
    }

    public String getFileNameWithExtension(String path) {
        if (StringUtils.isEmpty(path)) return null;
        String[] array1 = path.split("\\\\");
        String fileNameWithExtension = array1!=null ? array1[array1.length-1] : null;
        return fileNameWithExtension;
    }

    public String getFileNameWithoutExtension(String path) {
        String fileNameWithExtension = getFileNameWithExtension(path);
        String[] array2 = fileNameWithExtension!=null ? fileNameWithExtension.split("\\.") : null;
        String fileNameWithoutExtension = (array2!=null && array2.length>=2) ? array2[array2.length-2] : null;
        logger.debug("get file name: " + fileNameWithoutExtension);
        return fileNameWithoutExtension;
    }

}
