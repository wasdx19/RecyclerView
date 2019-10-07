package com.example.recyclerviewtest.mock;

public class Mock {
    private String name;
    private int value;

    public Mock() {
    }

    public Mock(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return String.valueOf(value);
    }

    public void setValue(int value) {
        this.value = value;
    }
}
