package com.max.parser.file;

import com.max.core.file.FileCreator;
import com.max.parser.entity.Trade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TradeOutputFileCreator extends FileCreator<Trade> {

    private static final Logger logger = LoggerFactory.getLogger(TradeOutputFileCreator.class);

    @Override
    public List<String> produceContent(List<Trade> input) {
        List<String> results = new ArrayList<>();
        results.add(Trade.outputHeader()+"\n");
        for (Trade t: input) {
            results.add(t.outputContent()+"\n");
        }
        return results;
    }

}
