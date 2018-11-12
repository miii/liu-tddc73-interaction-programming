package com.example.jacob.tddc73lab3;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate main layout
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayoutCompat layout = (LinearLayoutCompat) inflater.inflate(R.layout.activity_main, null);
        setContentView(layout);

        // Create element from task
        InteractiveSearcher search = new InteractiveSearcher(this);
        search.setThreshold(3);

        // Create random element to prove floating suggestions popup
        TextView t = new TextView(this);
        t.setText("Lite annat innehåll som hamnar bakom förslagen");

        // Add elements to activity view
        layout.addView(search);
        layout.addView(t);
    }
}
