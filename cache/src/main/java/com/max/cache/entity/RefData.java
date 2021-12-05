package com.max.cache.entity;

import org.apache.geode.cache.Region;

import org.slf4j.Logger;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class RefData {

    private final Region<Integer, String> region;

    public RefData(Region<Integer, String> region) {
        this.region = region;
    }

    public Set<Integer> getValues() {
        return new HashSet<>(region.keySetOnServer());
    }

    public void insertValues(int upperLimit) {
        IntStream.rangeClosed(1, upperLimit).forEach(i -> region.put(i, "value" + i));
    }

    public void printValues(Set<Integer> values, Logger logger) {
        values.forEach(key -> logger.info(String.format("%d:%s", key, region.get(key))));
    }

    public void printValues(Set<Integer> values) {
        values.forEach(key -> System.out.println(String.format("%d:%s", key, region.get(key))));
    }

    public Region<Integer, String> getRegion() {
        return region;
    }

}
