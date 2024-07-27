package com.example.my_spring_app.model;

public class OffsetTable {
    private String[][] values;

    public OffsetTable() {
        String[] row1 = {"", "Amount", "", "Last updated"};
        String[] row2 = {"Teebana Savings", "", "", "14/7/2024"};
        this.values = new String[][]{row1, row2};
    }

    public String[][] getValues() { return values; }

    public void setValues(String [][] values) { this.values = values; }

}
