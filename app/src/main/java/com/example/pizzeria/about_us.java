package com.example.pizzeria;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.button.MaterialButton;

public class about_us extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_about_us);

        // System Bar Padding logic (Edge-to-Edge support ke liye)
        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        // 1. Back Button Logic
        MaterialButton btnBack = findViewById(R.id.btnBackAbout);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        // 2. Call Support Logic
        LinearLayout llCall = findViewById(R.id.llCall);
        if (llCall != null) {
            llCall.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+919876543210")); // Apna number yahan dalein
                startActivity(intent);
            });
        }

        // 3. Email Support Logic
        LinearLayout llEmail = findViewById(R.id.llEmail);
        if (llEmail != null) {
            llEmail.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:support@thepizzeria.com")); // Apni email yahan dalein
                intent.putExtra(Intent.EXTRA_SUBJECT, "Pizza App Support Query");
                startActivity(intent);
            });
        }
    }
}