package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class paypal extends AppCompatActivity {

    private TextView tvLiveNo, tvLiveNm, tvLiveEx;
    private EditText etNo, etNm, etEx;
    private ImageView ivCardLogo;
    private MaterialButton btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal);

        // 1. Views Initialization
        tvLiveNo = findViewById(R.id.tvLiveNumber);
        tvLiveNm = findViewById(R.id.tvLiveName);
        tvLiveEx = findViewById(R.id.tvLiveExpiry);
        ivCardLogo = findViewById(R.id.ivCardLogo);

        etNo = findViewById(R.id.etNumber);
        etNm = findViewById(R.id.etName);
        etEx = findViewById(R.id.etExp);
        btnSubmit = findViewById(R.id.btnSubmitPay);

        // 2. LIVE NAME UPDATE LOGIC
        etNm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    tvLiveNm.setText("FULL NAME");
                } else {
                    tvLiveNm.setText(s.toString().toUpperCase());
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        // 3. LIVE NUMBER & LOGO LOGIC
        etNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvLiveNo.setText(s.length() == 0 ? "**** **** **** ****" : s.toString());

                // Hamesha logo3 set rakhega
                ivCardLogo.setImageResource(R.drawable.logo3);
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        // 4. LIVE EXPIRY UPDATE
        etEx.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvLiveEx.setText(s.length() == 0 ? "MM/YY" : s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        // 5. PAYMENT BUTTON LOGIC (Yahan change kiya hai)
        btnSubmit.setOnClickListener(v -> {
            // Success page par bhejne ka code
            Intent intent = new Intent(paypal.this, success.class);
            startActivity(intent);

            Toast.makeText(this, "Payment Successful! 🍕", Toast.LENGTH_LONG).show();

            // Sirf ye page band hoga, puri app nahi
            finish();
        });
    }
}