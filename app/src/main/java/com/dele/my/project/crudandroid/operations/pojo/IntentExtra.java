package com.dele.my.project.crudandroid.operations.pojo;

public class IntentExtra {
    private String key;
    private long longValue;
    private int intValue;
    private String stringValue;

    public IntentExtra(String key, long longValue) {
        this.key = key;
        this.longValue = longValue;
    }

    public IntentExtra(String key, int intValue) {
        this.key = key;
        this.intValue = intValue;
    }

    public IntentExtra(String key, String stringValue) {
        this.key = key;
        this.stringValue = stringValue;
    }

    public int getValue(int  key) {
        return this.intValue;
    }

    public String getValue(String key) {
        return stringValue;
    }

    public long getValue(long key) {
        return longValue;
    }

    public String getKey() {
        return key;
    }
}
