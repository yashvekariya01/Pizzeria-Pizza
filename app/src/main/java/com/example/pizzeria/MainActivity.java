package com.example.pizzeria;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ImageView ivMenuOpen;
    private MaterialButton btnLoginTop, btnOffers, btnAboutUs;
    private TextView menuHome, menuFeedback, menuAbout, menuLogout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Views Initialization
        drawerLayout = findViewById(R.id.drawer_layout);
        ivMenuOpen = findViewById(R.id.ivMenuOpen);
        btnLoginTop = findViewById(R.id.btnLoginTop);
        btnOffers = findViewById(R.id.btnOffers);
        btnAboutUs = findViewById(R.id.btnAboutUs);
        navigationView = findViewById(R.id.nav_view);

        // Sidebar Menu Items Initialization (Direct find as discussed)
        menuHome = findViewById(R.id.menuHome);
        menuFeedback = findViewById(R.id.menuFeedback);
        menuAbout = findViewById(R.id.menuAbout);
        menuLogout = findViewById(R.id.menuLogout);

        // --- SIDE MENU ACTIONS ---
        ivMenuOpen.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        if (menuHome != null) {
            menuHome.setOnClickListener(v -> drawerLayout.closeDrawer(GravityCompat.START));
        }

        if (menuFeedback != null) {
            menuFeedback.setOnClickListener(v -> {
                startActivity(new Intent(MainActivity.this, feedback.class));
                drawerLayout.closeDrawer(GravityCompat.START);
            });
        }

        if (menuAbout != null) {
            menuAbout.setOnClickListener(v -> {
                startActivity(new Intent(MainActivity.this, about_us.class));
                drawerLayout.closeDrawer(GravityCompat.START);
            });
        }

        // --- LOGOUT LOGIC ---
        if (menuLogout != null) {
            menuLogout.setOnClickListener(v -> {
                performLogout();
            });
        }

        // --- MAIN PAGE BUTTONS ---
        btnLoginTop.setOnClickListener(v -> startActivity(new Intent(this, login.class)));
        btnOffers.setOnClickListener(v -> startActivity(new Intent(this, offers.class)));
        btnAboutUs.setOnClickListener(v -> startActivity(new Intent(this, about_us.class)));

        // --- SESSION BASED BUTTON CHANGE (Optional) ---
        checkSession();

        // --- BACK BUTTON LOGIC ---
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    setEnabled(false);
                    onBackPressed();
                }
            }
        });
    }

    private void checkSession() {
        SharedPreferences sp = getSharedPreferences("LoginStatus", MODE_PRIVATE);
        boolean isLoggedIn = sp.getBoolean("isLoggedIn", false);
        if (isLoggedIn) {
            btnLoginTop.setText("Logged In"); // Agar user login hai toh button text badal jayega
        }
    }

    private void performLogout() {
        // 1. Session Clear karna (LoginStatus file se)
        SharedPreferences sp = getSharedPreferences("LoginStatus", MODE_PRIVATE);
        sp.edit().clear().apply();

        // 2. User ko Login screen par bhejein aur purani activity memory se hatayein
        Intent intent = new Intent(MainActivity.this, login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}