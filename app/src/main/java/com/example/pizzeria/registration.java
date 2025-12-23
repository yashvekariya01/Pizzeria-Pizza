package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class registration extends AppCompatActivity {

    private TextInputEditText etRegName, etRegEmail, etRegPhone, etRegPass;
    private MaterialButton btnRegister;
    private TextView tvBackToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etRegName = findViewById(R.id.etRegName);
        etRegEmail = findViewById(R.id.etRegEmail);
        etRegPhone = findViewById(R.id.etRegPhone);
        etRegPass = findViewById(R.id.etRegPass);
        btnRegister = findViewById(R.id.btnRegister);
        tvBackToLogin = findViewById(R.id.tvBackToLogin);

        btnRegister.setOnClickListener(v -> {
            String name = etRegName.getText().toString().trim();
            String email = etRegEmail.getText().toString().trim();
            String phone = etRegPhone.getText().toString().trim();
            String pass = etRegPass.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) ||
                    TextUtils.isEmpty(phone) || TextUtils.isEmpty(pass)) {
                Toast.makeText(this, "Please fill all details!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Registration Successful!", Toast.LENGTH_LONG).show();
                // Direct Login page pe bhej do
                finish();
            }
        });

        tvBackToLogin.setOnClickListener(v -> {
            finish(); // Current page close karke vapas purane page (Login) pe chala jayega
        });
    }
}