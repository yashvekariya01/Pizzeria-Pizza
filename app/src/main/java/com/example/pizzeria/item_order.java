package com.example.pizzeria;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView; // ListView import kiya

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

public class item_order extends AppCompatActivity {

    ListView lvOrders; // ListView variable
    OrderAdapter adapter;
    ArrayList<Order> orderList;
    Button btnClearOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_order);

        // Connect views
        lvOrders = findViewById(R.id.lvOrdersList);
        btnClearOrders = findViewById(R.id.btnClearOrders);

        // Load data
        loadDataFromSharedPreferences();

        // Set adapter
        if (orderList != null) {
            Collections.reverse(orderList); // latest order top par dikhane ke liye
            adapter = new OrderAdapter(this, orderList); // Context (this) pass kiya
            lvOrders.setAdapter(adapter);
        }

        // Clear All Button Click
        btnClearOrders.setOnClickListener(v -> clearAllOrders());
    }

    private void loadDataFromSharedPreferences() {
        SharedPreferences sp = getSharedPreferences("PizzeriaPrefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString("orders_list", null);

        Type type = new TypeToken<ArrayList<Order>>() {}.getType();

        if (json == null) {
            orderList = new ArrayList<>();
        } else {
            orderList = gson.fromJson(json, type);
        }
    }

    private void clearAllOrders() {
        SharedPreferences sp = getSharedPreferences("PizzeriaPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("orders_list");
        editor.apply();

        orderList.clear();
        adapter.notifyDataSetChanged(); // List ko update karne ke liye
    }
}