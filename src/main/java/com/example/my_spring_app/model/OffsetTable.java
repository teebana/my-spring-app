package com.example.my_spring_app.model;

import lombok.ToString;

@ToString
public class OffsetTable {
    private String range;
    private String majorDimension;
    private String[][] values;

    public OffsetTable() {}

    public String getRange() { return range; }

    public void setRange(String range) { this.range = range; }

    public String getMajorDimension() { return majorDimension; }

    public void setMajorDimension(String majorDimension) { this.majorDimension = majorDimension; }

    public String[][] getValues() { return values; }

    public void setValues(String [][] values) { this.values = values; }

}
