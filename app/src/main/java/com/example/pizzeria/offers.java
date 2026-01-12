package com.example.pizzeria;

import android.content.Intent;
import android.content.SharedPreferences; // Added
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast; // Added
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.button.MaterialButton;

public class offers extends AppCompatActivity {

    private ImageView btnBack;
    private MaterialButton btnBuy1, btnBuy2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_offers);

        if (findViewById(R.id.main) != null) {
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        btnBack = findViewById(R.id.btnBack);
        btnBuy1 = findViewById(R.id.btnBuy1);
        btnBuy2 = findViewById(R.id.btnBuy2);

        btnBack.setOnClickListener(v -> finish());

        // Buy Button Click logic with Login Check
        btnBuy1.setOnClickListener(v -> checkLoginAndOpen("Cheese Lover", 12.00));
        btnBuy2.setOnClickListener(v -> checkLoginAndOpen("Farmhouse Special", 9.99));
    }

    private void checkLoginAndOpen(String name, double price) {
        // SharedPrefs se session check karein
        SharedPreferences sp = getSharedPreferences("LoginStatus", MODE_PRIVATE);
        boolean isLoggedIn = sp.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            // Agar login hai toh buying page pe bhejo
            openBuyingPage(name, price);
        } else {
            // Agar login nahi hai toh login page pe bhejo
            Toast.makeText(this, "Please Login to purchase!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(offers.this, login.class));
        }
    }

    private void openBuyingPage(String name, double price) {
        Intent intent = new Intent(offers.this, buying.class);
        intent.putExtra("PIZZA_NAME", name);
        intent.putExtra("PIZZA_PRICE", price);
        startActivity(intent);
    }
}