package com.example.pizzeria;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class paypal extends AppCompatActivity {

    private TextView tvLiveNo, tvLiveNm, tvLiveEx;
    private EditText etNo, etNm, etEx, etTableNumber;
    private ImageView ivCardLogo;
    private MaterialButton btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal);

        // 1. Initialization
        tvLiveNo = findViewById(R.id.tvLiveNumber);
        tvLiveNm = findViewById(R.id.tvLiveName);
        tvLiveEx = findViewById(R.id.tvLiveExpiry);
        ivCardLogo = findViewById(R.id.ivCardLogo);
        etNo = findViewById(R.id.etNumber);
        etNm = findViewById(R.id.etName);
        etEx = findViewById(R.id.etExp);
        etTableNumber = findViewById(R.id.etTableNumber);
        btnSubmit = findViewById(R.id.btnSubmitPay);

        // 2. Card Preview Logic
        etNm.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvLiveNm.setText(s.length() == 0 ? "YOUR NAME" : s.toString().toUpperCase());
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        etNo.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvLiveNo.setText(s.length() == 0 ? "**** **** **** ****" : s.toString());
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        // 3. SUBMIT ACTION
        btnSubmit.setOnClickListener(v -> {
            String name = etNm.getText().toString().trim();
            String table = etTableNumber.getText().toString().trim();

            if (name.isEmpty() || table.isEmpty()) {
                Toast.makeText(this, "Please enter name and table number!", Toast.LENGTH_SHORT).show();
            } else {
                saveOrderData(name, table);

                // Success screen navigation
                Intent intent = new Intent(paypal.this, success.class);
                startActivity(intent);
                Toast.makeText(this, "Order Placed! 🍕", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private void saveOrderData(String name, String table) {
        try {
            SharedPreferences sp = getSharedPreferences("PizzeriaPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            Gson gson = new Gson();

            // Pehle purana data uthao
            String orderDataString = sp.getString("orders_list", null);

            Type type = new TypeToken<ArrayList<Order>>() {}.getType();
            ArrayList<Order> orderList;

            if (orderDataString == null || orderDataString.isEmpty()) {
                orderList = new ArrayList<>();
            } else {
                orderList = gson.fromJson(orderDataString, type);
            }

            // Time add karo
            String currentTime = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Calendar.getInstance().getTime());

            // Check if Order class exists
            orderList.add(new Order(name, table, currentTime));

            // Save karo
            String updatedDataString = gson.toJson(orderList);
            editor.putString("orders_list", updatedDataString);
            editor.apply();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}