package com.example.jacob.tddb84project.PasswordStrengthMeter;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.jacob.tddb84project.PasswordStrengthMeter.Algorithm.StrengthValidatorInterface;
import com.example.jacob.tddb84project.PasswordStrengthMeter.Visualization.VisualizationInterface;

import java.util.ArrayList;
import java.util.List;

public class PasswordStrengthMeter extends LinearLayout {
    // Support single validator
    private StrengthValidatorInterface validator = null;
    // Support multiple visualizers
    private List<VisualizationInterface> visualizers = new ArrayList<>();
    // Listeners
    private List<OnPasswordUpdateListener> listeners = new ArrayList<>();

    public PasswordStrengthMeter(Context context) {
        super(context);

        // Setup layout
        LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        setLayoutParams(params);
        setOrientation(VERTICAL);

        // Create textbox
        EditText editText = new EditText(context);
        editText.setLayoutParams(params);
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        editText.setTransformationMethod(new PasswordTransformationMethod());
        editText.setHint("••••••••");
        addView(editText);

        // Observe text changes
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Validate new data and update visualization
                update(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void setValidator(StrengthValidatorInterface v) {
        validator = v;
    }

    public void addVisualizer(VisualizationInterface v) {
        visualizers.add(v);

        // Add to layout
        View view = v.getView(getContext());
        addView(view);
    }

    public void removeVisualizer(VisualizationInterface v) {
        visualizers.remove(v);

        // Remove from layout
        View view = v.getView(getContext());
        removeView(view);
    }

    public void addOnUpdateListener(OnPasswordUpdateListener listener) {
        listeners.add(listener);
    }

    public void removeOnUpdateListener(OnPasswordUpdateListener listener) {
        listeners.remove(listener);
    }

    private void update(String text) {
        // Do nothing if no validator has been given
        if (validator == null)
            return;

        // Calculate validation score
        Double score = validator.validate(text);

        // Update visualizers
        for (VisualizationInterface visualizer : visualizers)
            visualizer.onUpdate(score);

        for (OnPasswordUpdateListener listener : listeners)
            listener.onUpdate(score);
    }
}
