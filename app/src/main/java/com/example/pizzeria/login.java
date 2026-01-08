package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class login extends AppCompatActivity {

    private TextInputEditText etEmail, etPassword;
    private MaterialButton btnLogin;
    private TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // System Bars Padding
        if (findViewById(R.id.main) != null) {
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        // 1. Initialize Views
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        // 2. Login Button Logic
        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }

            // --- ADMIN LOGIN LOGIC ---
            else if (email.equals("admin@pizza.com") && pass.equals("admin123")) {
                Toast.makeText(this, "Admin Login: Viewing All Orders 📋", Toast.LENGTH_SHORT).show();
                // Admin ko seedha Orders list (item_order) par bhej rahe hain
                Intent intent = new Intent(login.this, item_order.class);
                startActivity(intent);
                finish();
            }

            // --- NORMAL USER LOGIN LOGIC ---
            // Aap yahan apni database ya static credentials ka check badha sakte hain
            else if (email.equals("user@pizza.com") && pass.equals("12345")) {
                Toast.makeText(this, "Welcome to Pizzeria! 🍕", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(login.this, MainActivity.class));
                finish();
            }

            else {
                Toast.makeText(this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
            }
        });

        // 3. Redirect to Registration
        tvRegister.setOnClickListener(v -> {
            Toast.makeText(this, "Opening Registration...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(login.this, registration.class);
            startActivity(intent);
        });
    }
}