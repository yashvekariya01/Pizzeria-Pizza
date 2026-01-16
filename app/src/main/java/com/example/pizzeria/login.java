package com.example.pizzeria;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class login extends AppCompatActivity {

    private TextInputEditText etEmail, etPassword;
    private MaterialButton btnLogin;
    private TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
            // 1. ADMIN LOGIN
            else if (email.equals("admin@gmail.com") && pass.equals("admin@123")) {
                // Admin session start (Optional but good practice)
                setLoginSession(true, "admin");
                startActivity(new Intent(login.this, item_order.class));
                finish();
            }
            // 2. USER LOGIN
            else {
                SharedPreferences sp = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                String savedPassword = sp.getString(email, null);

                if (savedPassword != null && savedPassword.equals(pass)) {
                    // User session start
                    setLoginSession(true, "user");
                    startActivity(new Intent(login.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, "Invalid Email or Password!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvRegister.setOnClickListener(v -> startActivity(new Intent(login.this, registration.class)));
    }

    // Session save karne ka function
    private void setLoginSession(boolean isLoggedIn, String role) {
        SharedPreferences sp = getSharedPreferences("LoginStatus", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isLoggedIn", isLoggedIn);
        editor.putString("role", role);
        editor.apply();
    }
}