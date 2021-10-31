package com.max.core.msg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * use this template to:
 * - format/de-format jms message
 * - print message obj
 */
@Component
public class MessageTemplate {

    private static final Logger logger = LoggerFactory.getLogger(MessageTemplate.class);

    public String format(int cobDate, int feedId, String datasetId, Map<String, String> filePath) {
        StringBuilder result = new StringBuilder();
        result.append("dataset_id=").append(datasetId);
        result.append(";cob_date=").append(cobDate);
        result.append(";feed_id=").append(feedId);
        result.append(";trade_file=").append(filePath.get("TRADE_FILE"));
        result.append(";risk_file=").append(filePath.get("RISK_FILE"));
        return result.toString();
    }

    public Map<String, String> deformat(String msg) {
        if (msg == null || msg.isEmpty()) return null;
        Map<String, String> result = new HashMap<>();
        String[] itemArray = msg.split(";");
        for(String item: itemArray) {
            String[] keyValuePair = item.split("=");
            result.put(keyValuePair[0], keyValuePair[1]);
        }
        return result;
    }

    public void printMap(Map<String, String> map) {
        Optional.ofNullable(map)
                .ifPresent(m -> m.keySet()
                        .stream()
                        .forEach(k -> logger.info(k + ":" + m.get(k))));
    }

}
