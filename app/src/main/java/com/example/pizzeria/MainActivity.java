package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
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
    private TextView menuHome, menuFeedback, menuAbout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Main Views Initialization
        drawerLayout = findViewById(R.id.drawer_layout);
        ivMenuOpen = findViewById(R.id.ivMenuOpen);
        btnLoginTop = findViewById(R.id.btnLoginTop);
        btnOffers = findViewById(R.id.btnOffers);
        btnAboutUs = findViewById(R.id.btnAboutUs);
        navigationView = findViewById(R.id.nav_view);

        // --- ERROR FIX START ---
        // Kyunki TextViews NavigationView ke andar wale LinearLayout mein hain,
        // isliye hume unhe navigationView object ke zariye dhundhna hoga.
        menuHome = navigationView.findViewById(R.id.menuHome);
        menuFeedback = navigationView.findViewById(R.id.menuFeedback);
        menuAbout = navigationView.findViewById(R.id.menuAbout);
        // --- ERROR FIX END ---

        // SIDE MENU ACTIONS
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

        // MAIN BUTTONS ACTIONS
        btnLoginTop.setOnClickListener(v -> startActivity(new Intent(this, login.class)));
        btnOffers.setOnClickListener(v -> startActivity(new Intent(this, offers.class)));
        btnAboutUs.setOnClickListener(v -> startActivity(new Intent(this, about_us.class)));

        // BACK BUTTON LOGIC
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
}