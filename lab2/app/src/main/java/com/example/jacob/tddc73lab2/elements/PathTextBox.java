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
                String[] segments = value.replaceAll("^/(.*)/?$", "$1").split("/");

                if (
                    // Path should start
                    !value.startsWith("/") ||
                    // Check if possible group path can be found
                    !mediator.getRootNode().hasChildStartingWith(segments[0]) ||
                    // Check if possible child path can be found
                    (segments.length >= 2 && !mediator.getRootNode().getChild(segments[0]).hasChildStartingWith(segments[1]))
                )
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
