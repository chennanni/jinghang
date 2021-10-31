package com.max.parser.mapper;

import com.max.parser.entity.Context;
import com.max.parser.entity.Risk;
import com.max.parser.entity.Trade;

import java.util.List;

public interface MapperCore {
    public List<Trade> parseTrade(String filePath, Context context);
    public List<Risk> parseRisk(String filePath, Context context);
}
