package com.example.pizzeria;

import android.content.SharedPreferences;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_order);

        // 1. XML se RecyclerView ko connect karo
        rvOrders = findViewById(R.id.rvOrdersList);
        rvOrders.setLayoutManager(new LinearLayoutManager(this));

        // 2. SharedPreferences se data load karne wala function call karo
        loadDataFromSharedPreferences();

        // 3. Agar list khali nahi hai, toh adapter set karo
        if (orderList != null) {
            // Latest orders ko top par dikhane ke liye list reverse karo
            Collections.reverse(orderList);

            adapter = new OrderAdapter(orderList);
            rvOrders.setAdapter(adapter);
        }
    }

    private void loadDataFromSharedPreferences() {
        // Paypal activity mein jo name use kiya tha wahi "PizzeriaPrefs" yahan use karna hai
        SharedPreferences sp = getSharedPreferences("PizzeriaPrefs", MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sp.getString("orders_list", null);

        // JSON string ko wapas ArrayList<Order> mein badalne ke liye
        Type type = new TypeToken<ArrayList<Order>>() {}.getType();

        if (json == null) {
            orderList = new ArrayList<>();
        } else {
            orderList = gson.fromJson(json, type);
        }
    }
}