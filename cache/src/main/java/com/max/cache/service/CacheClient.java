package com.max.cache.service;

import com.max.cache.entity.RefData;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CacheClient {

    private static final Logger logger = LoggerFactory.getLogger(CacheClient.class);

    public void put() throws Exception {
        // connect to the locator using default port 10334
        ClientCache cache = new ClientCacheFactory().addPoolLocator("127.0.0.1", 10334)
                .set("log-level", "WARN").create();

        // create a local region that matches the server region
        Region<Integer, String> RefDataRegion =
                cache.<Integer, String>createClientRegionFactory(ClientRegionShortcut.PROXY)
                        .create("RefData");

        // put some data
        RefData data = new RefData(RefDataRegion);
        logger.info("---insert---");
        data.insertValues(10);
        logger.info("---end---");
        // close the client
        cache.close();
    }

    public void get() throws Exception {
        // connect to the locator using default port 10334
        ClientCache cache = new ClientCacheFactory().addPoolLocator("127.0.0.1", 10334)
                .set("log-level", "WARN").create();

        // create a local region that matches the server region
        Region<Integer, String> RefDataRegion =
                cache.<Integer, String>createClientRegionFactory(ClientRegionShortcut.PROXY)
                        .create("RefData");

        // put some data
        RefData data = new RefData(RefDataRegion);
        logger.info("---get---");
        data.getValues().forEach(key -> logger.info(String.format("%d:%s", key, data.getRegion().get(key))));
        logger.info("---end---");
        // close the client
        cache.close();
    }
}
