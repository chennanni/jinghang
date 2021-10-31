package com.max.parser.file;

import com.max.core.file.FileCreator;
import com.max.parser.entity.Risk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RiskOutputFileCreator extends FileCreator<Risk> {

    private static final Logger logger = LoggerFactory.getLogger(RiskOutputFileCreator.class);

    @Override
    public List<String> produceContent(List<Risk> input) {
        List<String> results = new ArrayList<>();
        results.add(Risk.outputHeader()+"\n");
        for (Risk r: input) {
            results.add(r.outputContent()+"\n");
        }
        return results;
    }

}
