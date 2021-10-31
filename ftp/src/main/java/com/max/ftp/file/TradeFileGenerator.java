package com.max.ftp.file;

import com.max.core.file.FileGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TradeFileGenerator extends FileGenerator {

    private static final Logger logger = LoggerFactory.getLogger(TradeFileGenerator.class);

    @Value("${trade.input.path}")
    private String tradeFileParentPath;

    public String generateFile(int cobDate, int feedId, String feedName) {
        String tradeFileFullPath = tradeFileParentPath + feedId + "_" + feedName + "_trade.txt";
        super.generateFileTemplate(tradeFileFullPath);
        return tradeFileFullPath;
    }

    @Override
    public List<String> produceContent() {
        ArrayList result = new ArrayList<String>();
        result.add("date,trade,trade_action,trade_type\n");
        for (int i=0; i<10; i++) {
            result.add("20210901,1003,buy,market\n");
        }
        return result;
    }

}
