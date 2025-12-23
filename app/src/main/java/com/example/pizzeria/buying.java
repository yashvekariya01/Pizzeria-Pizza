package com.example.pizzeria;

import android.content.Intent; // Ye add karna hai
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class buying extends AppCompatActivity {

    private TextView tvName, tvPrice;
    private RadioGroup rgSize;
    private CheckBox cbCheese, cbOnion, cbMushroom;
    private MaterialButton btnGoToPaypal; // Button add kiya
    private double basePrice = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buying);

        tvName = findViewById(R.id.tvPizzaNameDetail);
        tvPrice = findViewById(R.id.tvFinalPrice);
        rgSize = findViewById(R.id.rgSize);
        cbCheese = findViewById(R.id.cbExtraCheese);
        cbOnion = findViewById(R.id.cbOnion);
        cbMushroom = findViewById(R.id.cbMushroom);
        btnGoToPaypal = findViewById(R.id.btnGoToPaypal); // XML se connect kiya

        String name = getIntent().getStringExtra("PIZZA_NAME");
        basePrice = getIntent().getDoubleExtra("PIZZA_PRICE", 0.0);

        tvName.setText(name);
        calculateTotal();

        rgSize.setOnCheckedChangeListener((group, checkedId) -> calculateTotal());
        cbCheese.setOnCheckedChangeListener((buttonView, isChecked) -> calculateTotal());
        cbOnion.setOnCheckedChangeListener((buttonView, isChecked) -> calculateTotal());
        cbMushroom.setOnCheckedChangeListener((buttonView, isChecked) -> calculateTotal());

        // --- YEH WALA PART ZAROORI HAI ---
        btnGoToPaypal.setOnClickListener(v -> {
            Intent intent = new Intent(buying.this, paypal.class);
            // Data bhejna taaki PayPal page par price dikhe
            intent.putExtra("TOTAL", tvPrice.getText().toString());
            intent.putExtra("NAME", tvName.getText().toString());
            startActivity(intent);
        });
    }

    private void calculateTotal() {
        double total = basePrice;
        int selectedId = rgSize.getCheckedRadioButtonId();
        if (selectedId == R.id.rbSmall) total -= 2.0;
        else if (selectedId == R.id.rbLarge) total += 4.0;

        if (cbCheese.isChecked()) total += 1.5;
        if (cbOnion.isChecked()) total += 1.5;
        if (cbMushroom.isChecked()) total += 1.5;

        tvPrice.setText("$" + String.format("%.2f", total));
    }
}