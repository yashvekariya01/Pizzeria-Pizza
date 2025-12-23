package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
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

        // System Bars Padding (Make sure your top-level layout ID is 'main')
        if (findViewById(R.id.main) != null) {
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        // 1. Initialize Views
        btnBack = findViewById(R.id.btnBack);
        btnBuy1 = findViewById(R.id.btnBuy1);
        btnBuy2 = findViewById(R.id.btnBuy2);

        // 2. Back Button Logic
        btnBack.setOnClickListener(v -> {
            finish(); // Isse user wapas Main Page par chala jayega
        });

        // 3. Buy Button 1 (Cheese Lover)
        btnBuy1.setOnClickListener(v -> {
            openBuyingPage("Cheese Lover", 12.00);
        });

        // 4. Buy Button 2 (Farmhouse Special)
        btnBuy2.setOnClickListener(v -> {
            openBuyingPage("Farmhouse Special", 9.99);
        });
    }

    // Common function to send data to Buying Page
    private void openBuyingPage(String name, double price) {
        Intent intent = new Intent(offers.this, buying.class);
        intent.putExtra("PIZZA_NAME", name);
        intent.putExtra("PIZZA_PRICE", price);
        startActivity(intent);
    }
}