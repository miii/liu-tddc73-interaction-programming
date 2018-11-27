package com.example.jacob.tddc73lab2.elements;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.jacob.tddc73lab2.mediator.PathChanger;
import com.example.jacob.tddc73lab2.mediator.PathMediator;

public class PathTextBox extends PathChanger {

    private android.widget.EditText editText;

    public PathTextBox(final PathMediator mediator, View view) {
        super(mediator);

        editText = (EditText) view;
        final PathTextBox context = this;

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Remove slash prefix/suffix and split into array
                String value = charSequence.toString();

                // Search node by key (path)
                boolean pathMatchExists = false;
                for (String path : mediator.getRootNode().getTree().keySet()) {
                    if (path.startsWith(value)) {
                        pathMatchExists = true;
                        break;
                    }
                }

                // Check if path match exists
                if (!pathMatchExists)
                    editText.setBackgroundColor(Color.RED);
                else
                    // Reset background
                    editText.setBackgroundColor(Color.TRANSPARENT);


                mediator.updatePath(charSequence.toString(), context);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    @Override
    public void receive(String path) {
        editText.setText(path);
    }
}
