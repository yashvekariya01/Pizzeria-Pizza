package com.example.pizzeria;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

public class item_order extends AppCompatActivity {

    RecyclerView rvOrders;
    OrderAdapter adapter;
    ArrayList<Order> orderList;
    Button btnClearOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_order);

        // Connect views
        rvOrders = findViewById(R.id.rvOrdersList);
        btnClearOrders = findViewById(R.id.btnClearOrders);

        rvOrders.setLayoutManager(new LinearLayoutManager(this));

        // Load data
        loadDataFromSharedPreferences();

        // Set adapter
        if (orderList != null) {
            Collections.reverse(orderList); // latest order first
            adapter = new OrderAdapter(orderList);
            rvOrders.setAdapter(adapter);
        }

        // Remove All Orders Button
        btnClearOrders.setOnClickListener(v -> clearAllOrders());
    }

    // Load orders from SharedPreferences
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

    // Clear all orders
    private void clearAllOrders() {
        SharedPreferences sp = getSharedPreferences("PizzeriaPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("orders_list");
        editor.apply();

        orderList.clear();
        adapter.notifyDataSetChanged();
    }
}
