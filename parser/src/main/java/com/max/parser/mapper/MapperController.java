package com.max.parser.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class MapperController {

    private static final Logger logger = LoggerFactory.getLogger(MapperController.class);

    HashMap<String, String> mapping;

    @Autowired
    Mapper01 mapper01;

    @Autowired
    Mapper02 mapper02;

    @Autowired
    Mapper03 mapper03;

    public MapperController() {
        this.init();
    }

    // [ feed - mapper ] mapping
    public void init() {
        mapping = new HashMap<>();
        mapping.put("Totoro_01", MapperEnums.MAPPER_01);
        mapping.put("Totoro_02", MapperEnums.MAPPER_01);
        mapping.put("Euclid_01", MapperEnums.MAPPER_02);
        mapping.put("Euclid_02", MapperEnums.MAPPER_02);
        mapping.put("Spear_01", MapperEnums.MAPPER_03);
        mapping.put("Spear_02", MapperEnums.MAPPER_03);
    }

    public MapperCore getMapper(String batchName) {
        String mapperName = mapping.get(batchName);
        logger.info("use mapper: " + mapperName);
        switch(mapperName){
            case MapperEnums.MAPPER_01: return mapper01;
            case MapperEnums.MAPPER_02: return mapper02;
            case MapperEnums.MAPPER_03: return mapper03;
            default: return null;
        }
    }

}
