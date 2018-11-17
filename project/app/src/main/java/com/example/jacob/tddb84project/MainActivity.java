package com.example.jacob.tddb84project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jacob.tddb84project.Carousel.TestCarouselActivity;
import com.example.jacob.tddb84project.PasswordStrengthMeter.TestPSMActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start activity on button press
        Button password = findViewById(R.id.passwordActivityButton);
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TestPSMActivity.class);
                startActivity(intent);
            }
        });

        // Start activity on button press
        Button carousel = findViewById(R.id.carouselActivityButton);
        carousel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TestCarouselActivity.class);
                startActivity(intent);
            }
        });
    }
}
