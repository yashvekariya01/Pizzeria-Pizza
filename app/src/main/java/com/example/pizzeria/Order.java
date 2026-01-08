package com.example.pizzeria;

public class Order {
    String name, table, time;

    public Order(String name, String table, String time) {
        this.name = name;
        this.table = table;
        this.time = time;
    }

    public String getName() { return name; }
    public String getTable() { return table; }
    public String getTime() { return time; }
}