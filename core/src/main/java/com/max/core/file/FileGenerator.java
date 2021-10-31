package com.max.core.file;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public abstract class FileGenerator {

    // TODO: create folder if not exits

    private static final Logger logger = LoggerFactory.getLogger(FileGenerator.class);

    public abstract List<String> produceContent();

    protected void generateFileTemplate(String path) {
        //logger.info("going to generate file for : " + path);
        File file = new File(path);
        if (file.exists()) file.delete();
        try {
            writeFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("COMPLETE - generate file for : " + path);
    }

    private void writeFile(File file) throws IOException {
        file.createNewFile();
        FileWriterWithEncoding fw = new FileWriterWithEncoding(file, "utf-8", true);
        BufferedWriter bw = new BufferedWriter(new FileWriterWithEncoding(file, "utf-8", true));
        try {
            List<String> lines = produceContent();
            Optional.ofNullable(lines).ifPresent(itelines -> itelines.stream().forEach(line -> {
                try {
                    bw.write(line);
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }));
            bw.flush();
            fw.flush();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            try {
                bw.close();
                fw.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
