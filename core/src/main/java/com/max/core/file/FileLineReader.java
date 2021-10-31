package com.max.core.file;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class FileLineReader {

    protected SourceFile read(String path) {
        SourceFile sourceFile = new SourceFile();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path));

            // read the first line - header
            if (reader.ready()) {
                String header = reader.readLine();
                String[] headerArray = header.split(",");
                for (int i = 0; i < headerArray.length; i++) {
                    sourceFile.getHeaderMap().put(headerArray[i], i);
                }
            }
            // read the contents
            while (reader.ready()){
                String line = reader.readLine();
                String[] contentArray = line.split(",");
                sourceFile.getContents().add(contentArray);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }
        return sourceFile;
    }

}
