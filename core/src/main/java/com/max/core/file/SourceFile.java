package com.max.core.file;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SourceFile {

    private Map<String, Integer> headerMap;
    private List<String[]> contents;

    public SourceFile() {
        this.headerMap = new HashMap<>();
        this.contents = new ArrayList<>();
    }

    public Map<String, Integer> getHeaderMap() {
        return headerMap;
    }

    public void setHeaderMap(Map<String, Integer> headerMap) {
        this.headerMap = headerMap;
    }

    public List<String[]> getContents() {
        return contents;
    }

    public void setContents(List<String[]> contents) {
        this.contents = contents;
    }

}
