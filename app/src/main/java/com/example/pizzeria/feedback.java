package com.example.pizzeria;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class feedback extends AppCompatActivity {

    private RatingBar ratingBar;
    private TextInputEditText etFeedbackText;
    private MaterialButton btnSubmitFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        ratingBar = findViewById(R.id.ratingBar);
        etFeedbackText = findViewById(R.id.etFeedbackText);
        btnSubmitFeedback = findViewById(R.id.btnSubmitFeedback);

        btnSubmitFeedback.setOnClickListener(v -> {
            float rating = ratingBar.getRating();
            String feedbackMsg = etFeedbackText.getText().toString();

            if (rating == 0) {
                Toast.makeText(this, "Please select at least 1 star!", Toast.LENGTH_SHORT).show();
            } else {
                // Yahan aap feedback ko database ya server pe bhej sakte hain
                Toast.makeText(this, "Thank you for " + rating + " stars!", Toast.LENGTH_LONG).show();

                // Feedback dene ke baad wapas home screen pe jane ke liye
                finish();
            }
        });
    }
}