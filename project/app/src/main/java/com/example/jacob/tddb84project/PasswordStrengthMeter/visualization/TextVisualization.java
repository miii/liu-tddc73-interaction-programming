package com.example.jacob.tddb84project.PasswordStrengthMeter.visualization;

import android.content.Context;
import android.view.View;

/**
 * Example implementation of the VisualizationInterface
 */
public class TextVisualization extends android.support.v7.widget.AppCompatTextView implements VisualizationInterface {
    private String prefix = "Password strength: ";

    public TextVisualization(Context context) {
        super(context);

        setText("No input");
    }

    @Override
    public View getView(Context context) {
        return this;
    }

    @Override
    public void onUpdate(Double score) {
        setText(prefix + Math.round(score * 100) + "%");
    }
}
