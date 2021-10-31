package com.max.parser.mapper;

import com.max.core.file.FileTemplate;
import com.max.core.file.SourceFile;
import com.max.parser.entity.Context;
import com.max.parser.entity.Risk;
import com.max.parser.entity.Trade;
import com.max.parser.service.CalService01;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Mapper03 implements MapperCore {

    private static final Logger logger = LoggerFactory.getLogger(Mapper03.class);

    // todo later: get it from DB
    private Map<String, Integer> infotypeMap;

    @Autowired
    FileTemplate fileTemplate;

    @Autowired
    CalService01 calService01;

    public Mapper03() {
        init();
    }

    private void init() {
        infotypeMap = new HashMap<>();
        infotypeMap.put("info_pnl",1);
        infotypeMap.put("info_vod0",2);
        infotypeMap.put("info_risk_3",3);
        infotypeMap.put("info_risk_4",4);
    }

    @Override
    public List<Trade> parseTrade(String filePath, Context context) {
        logger.info("start to parse trade: " + filePath);
        if (StringUtils.isEmpty(filePath)) return null;
        List<Trade> trades = new ArrayList<>();
        // read source file
        SourceFile sourceFile = fileTemplate.read(filePath);
        Map<String, Integer> header = sourceFile.getHeaderMap();
        List<String[]> contents = sourceFile.getContents();
        // deal with contents
        for (String[] content : contents) {
            String date = content[header.get("date")];
            String tradeUid = content[header.get("trade")];
            String tradeAction = content[header.get("trade_action")];
            String tradeType = content[header.get("trade_type")];
            Trade t = new Trade();
            t.setUid(tradeUid);
            t.setTradeAction(tradeAction);
            t.setTradeType(tradeType);
            t.setDatasetId(context.getDatasetId());
            t.setSystemId(context.getSystemId());
            t.setAccountUid(0);
            t.setInstrumentUid(0);
            trades.add(t);
        }
        // service call
        calService01.process();
        logger.info("COMPLETE - parse trade, records count: " + trades.size());
        return trades;
    }

    @Override
    public List<Risk> parseRisk(String filePath, Context context) {
        logger.info("start to parse risk: " + filePath);
        if (StringUtils.isEmpty(filePath)) return null;
        List<Risk> risks = new ArrayList<>();
        // read source file
        SourceFile sourceFile = fileTemplate.read(filePath);
        Map<String, Integer> header = sourceFile.getHeaderMap();
        List<String[]> contents = sourceFile.getContents();
        // deal with contents
        for (String[] content : contents) {
            String date = content[header.get("date")];
            String tradeUid = content[header.get("trade")];
            String riskType = content[header.get("risk_type")];
            String riskAmount = content[header.get("risk_amount")];
            Risk r = new Risk();
            r.setTradeUid(tradeUid);
            r.setInfotypeId(infotypeMap.get(riskType));
            r.setAmount(Double.parseDouble(riskAmount));
            r.setDatasetId(context.getDatasetId());
            risks.add(r);
        }
        // service call
        calService01.process();
        logger.info("COMPLETE - parse risk, records count: " + risks.size());
        return risks;
    }

}