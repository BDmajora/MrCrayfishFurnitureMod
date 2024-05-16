/*
 * Decompiled with CFR 0.152.
 */
package com.mrcrayfish.furniture.api;

import java.util.HashMap;

public class Parser {
    private static Parser instance = new Parser();
    private HashMap<String, String> values = new HashMap();

    public static Parser getInstance() {
        return instance;
    }

    public void parseLine(String line, boolean removeSpaces) {
        String[] keys;
        this.values.clear();
        if (removeSpaces) {
            line = line.replace(" ", "");
        }
        for (String key : keys = line.split(",")) {
            String[] keyData = key.split("=");
            String key_id = keyData[0];
            String key_value = keyData[1];
            this.values.put(key_id, key_value);
        }
    }

    public String getValue(String key_id, String d3fault) {
        if (this.values.get(key_id) != null) {
            return this.values.get(key_id);
        }
        return d3fault;
    }

    public HashMap<String, String> getMap() {
        return this.values;
    }
}

