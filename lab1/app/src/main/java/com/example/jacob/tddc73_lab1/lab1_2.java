package com.example.jacob.tddc73_lab1;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class lab1_2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Laboration 1.2");

        // setContentView(R.layout.lab1_2);
        this.createGUI();
    }

    private void createGUI() {
        LinearLayout root = new LinearLayout(this);
        root.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        root.setOrientation(LinearLayout.VERTICAL);

        // Used by horizontal LinearLayouts
        LinearLayout.LayoutParams hLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        hLayoutParams.gravity = LinearLayout.HORIZONTAL;

        // Used by TextViews
        LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        textViewParams.weight = 3;

        // Used by EditTexts etc.
        LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        editTextParams.weight = 1;

        ///////////////////////////////////////////////

        // Row 1
        LinearLayout row1 = new LinearLayout(this);
        row1.setLayoutParams(hLayoutParams);
        root.addView(row1);

        TextView t1 = new TextView(this);
        t1.setId(R.id.textView21);
        t1.setLayoutParams(textViewParams);
        t1.setText("Namn");
        t1.setGravity(Gravity.CENTER_VERTICAL);
        row1.addView(t1);

        EditText ed1 = new EditText(this);
        ed1.setId(R.id.editText21);
        ed1.setLayoutParams(editTextParams);
        ed1.setText("Anders");
        row1.addView(ed1);

        // Row 2
        LinearLayout row2 = new LinearLayout(this);
        row2.setLayoutParams(hLayoutParams);
        root.addView(row2);

        TextView t2 = new TextView(this);
        t2.setId(R.id.textView22);
        t2.setLayoutParams(textViewParams);
        t2.setText("Lösenord");
        t2.setGravity(Gravity.CENTER_VERTICAL);
        row2.addView(t2);

        EditText edt2 = new EditText(this);
        edt2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        edt2.setId(R.id.password1);
        edt2.setLayoutParams(editTextParams);
        edt2.setText("12345678");
        row2.addView(edt2);

        // Row 3
        LinearLayout row3 = new LinearLayout(this);
        row3.setLayoutParams(hLayoutParams);
        root.addView(row3);

        TextView t3 = new TextView(this);
        t3.setId(R.id.textView23);
        t3.setLayoutParams(textViewParams);
        t3.setText("Epost");
        t3.setGravity(Gravity.CENTER_VERTICAL);
        row3.addView(t3);

        EditText edt3 = new EditText(this);
        edt3.setId(R.id.email1);
        edt3.setLayoutParams(editTextParams);
        edt3.setText("anders.froberg@liu.se");
        row3.addView(edt3);

        // Row 4
        LinearLayout row4 = new LinearLayout(this);
        row4.setLayoutParams(hLayoutParams);
        root.addView(row4);

        TextView t4 = new TextView(this);
        t4.setId(R.id.textView24);
        t4.setLayoutParams(textViewParams);
        t4.setText("Ålder");
        t4.setGravity(Gravity.CENTER_VERTICAL);
        row4.addView(t4);

        SeekBar sb = new SeekBar(this);
        sb.setProgress(30);
        sb.setLayoutParams(editTextParams);
        row4.addView(sb);


        setContentView(root);
    }
}
