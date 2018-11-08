package com.example.jacob.tddc73_lab1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class lab1_3 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Laboration 1.3");

        setContentView(R.layout.lab1_3);
        // this.createGUI();
    }

    private void createGUI() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(24, 24,24,24);

        // Question 1
        TextView t1 = new TextView(this);
        t1.setText("Hur trivs du på LiU?");
        t1.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.addView(t1);

        LinearLayout l1 = new LinearLayout(this);
        l1.setOrientation(LinearLayout.HORIZONTAL);

        CheckBox c11 = new CheckBox(this);
        c11.setText("Bra");
        l1.addView(c11);

        CheckBox c12 = new CheckBox(this);
        c12.setText("Mycket bra");
        l1.addView(c12);

        CheckBox c13 = new CheckBox(this);
        c13.setText("Jättebra");
        c13.setChecked(true);
        l1.addView(c13);

        layout.addView(l1);

        // Question 2
        TextView t2 = new TextView(this);
        t2.setText("Läser du på LiTH?");
        t2.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.addView(t2);

        LinearLayout l2 = new LinearLayout(this);
        l2.setOrientation(LinearLayout.HORIZONTAL);

        CheckBox c21 = new CheckBox(this);
        c21.setText("Ja");
        l2.addView(c21);

        CheckBox c22 = new CheckBox(this);
        c22.setText("Nej");
        c22.setChecked(true);
        l2.addView(c22);

        layout.addView(l2);

        // Question 3
        ImageView im = new ImageView(this);
        im.setImageResource(R.drawable.liu);
        im.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                500
        ));
        layout.addView(im);

        TextView t3 = new TextView(this);
        t3.setText("Är detta LiUs logotyp?");
        t3.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.addView(t3);

        LinearLayout l3 = new LinearLayout(this);
        l3.setOrientation(LinearLayout.HORIZONTAL);

        CheckBox c31 = new CheckBox(this);
        c31.setText("Ja");
        c31.setChecked(true);
        l3.addView(c31);

        CheckBox c32 = new CheckBox(this);
        c32.setText("Nej");
        l3.addView(c32);

        layout.addView(l3);

        // Submit button
        Button b = new Button(this);
        b.setText("SKICKA IN");
        layout.addView(b);

        setContentView(layout);
    }
}
