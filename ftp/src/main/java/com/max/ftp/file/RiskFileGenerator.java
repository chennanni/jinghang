package com.max.ftp.file;

import com.max.core.file.FileGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RiskFileGenerator extends FileGenerator {

    private static final Logger logger = LoggerFactory.getLogger(RiskFileGenerator.class);

    @Value("${risk.input.path}")
    private String riskFileParentPath;

    public String generateFile(int cobDate, int feedId, String feedName) {
        String riskFileFullPath = riskFileParentPath + feedId + "_" + feedName + "_risk.txt";
        super.generateFileTemplate(riskFileFullPath);
        return riskFileFullPath;
    }

    @Override
    public List<String> produceContent() {
        ArrayList result = new ArrayList<String>();
        result.add("date,trade,risk_type,risk_amount\n");
        for (int i=0; i<10; i++) {
            result.add("20210901,1003,info_pnl,100\n");
        }
        return result;
    }

}
