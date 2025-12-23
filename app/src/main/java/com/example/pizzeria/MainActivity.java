package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ImageView ivMenuOpen;
    private MaterialButton btnLoginTop, btnOffers, btnAboutUs;
    private TextView menuHome, menuFeedback, menuAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Views Initialization
        drawerLayout = findViewById(R.id.drawer_layout);
        ivMenuOpen = findViewById(R.id.ivMenuOpen);

        menuHome = findViewById(R.id.menuHome);
        menuFeedback = findViewById(R.id.menuFeedback);
        menuAbout = findViewById(R.id.menuAbout);

        btnLoginTop = findViewById(R.id.btnLoginTop);
        btnOffers = findViewById(R.id.btnOffers);
        btnAboutUs = findViewById(R.id.btnAboutUs);

        // SIDE MENU ACTIONS
        ivMenuOpen.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        menuHome.setOnClickListener(v -> drawerLayout.closeDrawer(GravityCompat.START));

        menuFeedback.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, feedback.class));
            drawerLayout.closeDrawer(GravityCompat.START);
        });

        menuAbout.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, about_us.class));
            drawerLayout.closeDrawer(GravityCompat.START);
        });

        // MAIN BUTTONS ACTIONS
        btnLoginTop.setOnClickListener(v -> startActivity(new Intent(this, login.class)));
        btnOffers.setOnClickListener(v -> startActivity(new Intent(this, offers.class)));
        btnAboutUs.setOnClickListener(v -> startActivity(new Intent(this, about_us.class)));

        // --- NEW BACK BUTTON LOGIC (Fixed Error) ---
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    setEnabled(false); // Callback disable karein
                    onBackPressed(); // Purana back behavior trigger karein
                }
            }
        });
    }
}