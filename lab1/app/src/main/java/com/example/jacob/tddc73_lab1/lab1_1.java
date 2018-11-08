package com.example.jacob.tddc73_lab1;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class lab1_1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Laboration 1.1");

        // setContentView(R.layout.lab1_1);
        this.createGUI();
    }

    private void createGUI() {
        ConstraintLayout layout = new ConstraintLayout(this);

        Button b1 = new Button(this);
        b1.setId(R.id.button);
        b1.setText("KNAPP");
        layout.addView(b1);

        EditText et1 = new EditText(this);
        et1.setId(R.id.editText);
        et1.setText("Textfält");
        layout.addView(et1);

        RatingBar rb1 = new RatingBar(this);
        rb1.setId(R.id.ratingBar);
        rb1.setNumStars(5);
        layout.addView(rb1);

        EditText et2 = new EditText(this);
        et2.setId(R.id.editText2);
        et2.setText("ett textfält som\nklarar\nav\nflera rader\noch använder det skärmutrymme som\n\nfinns");
        et2.setSingleLine(false);
        layout.addView(et2);

        setContentView(layout);

        ConstraintSet set = new ConstraintSet();

        // Button
        set.constrainWidth(b1.getId(), ConstraintSet.MATCH_CONSTRAINT);
        set.constrainHeight(b1.getId(), ConstraintSet.WRAP_CONTENT);
        set.connect(b1.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 8);
        set.connect(b1.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 8);

        // EditText
        set.constrainWidth(et1.getId(), ConstraintSet.MATCH_CONSTRAINT);
        set.constrainHeight(et1.getId(), ConstraintSet.WRAP_CONTENT);
        set.connect(et1.getId(), ConstraintSet.TOP, b1.getId(), ConstraintSet.BOTTOM);
        set.connect(et1.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 8);
        set.connect(et1.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 8);

        // RatingBar
        set.constrainWidth(rb1.getId(), ConstraintSet.WRAP_CONTENT);
        set.constrainHeight(rb1.getId(), ConstraintSet.WRAP_CONTENT);
        set.connect(rb1.getId(), ConstraintSet.TOP, et1.getId(), ConstraintSet.BOTTOM, 8);
        set.connect(rb1.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 8);
        set.connect(rb1.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 8);

        // Multiline EditText
        set.constrainWidth(et2.getId(), ConstraintSet.MATCH_CONSTRAINT);
        set.constrainHeight(et2.getId(), ConstraintSet.MATCH_CONSTRAINT);
        set.connect(et2.getId(), ConstraintSet.TOP, rb1.getId(), ConstraintSet.BOTTOM, 8);
        set.connect(et2.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 8);
        set.connect(et2.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 8);
        set.connect(et2.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 8);

        set.applyTo(layout);
    }
}
