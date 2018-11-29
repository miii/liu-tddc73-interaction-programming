package com.example.jacob.tddb84project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jacob.tddb84project.PasswordStrengthMeter.PasswordStrengthMeter;
import com.example.jacob.tddb84project.PasswordStrengthMeter.algorithm.BasicStrengthValidator;
import com.example.jacob.tddb84project.PasswordStrengthMeter.visualization.ProgressBarVisualization;
import com.example.jacob.tddb84project.PasswordStrengthMeter.visualization.TextVisualization;

public class TestPSMActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create basic layout
        LinearLayout layout = new LinearLayout(this);
        layout.setPadding(32, 32, 32, 32);
        layout.setOrientation(LinearLayout.VERTICAL);

        setTitle("Password Strength Meter");
        setContentView(layout);

        // Add instructions
        TextView t = new TextView(this);
        t.setText("Validera lösenord");
        layout.addView(t);

        // Add strength meter
        PasswordStrengthMeter meter = new PasswordStrengthMeter(this);
        layout.addView(meter);

        // Set validator
        meter.setValidator(new BasicStrengthValidator());

        // Add visualizers
        meter.addVisualizer(new TextVisualization(this));
        meter.addVisualizer(new ProgressBarVisualization(this));
    }

}
