package com.example.jacob.tddb84project.PasswordStrengthMeter.Visualization;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

/**
 * Example implementation of the VisualizationInterface
 */
public class ProgressBarVisualization implements VisualizationInterface {
    ProgressBar progressBar;

    public ProgressBarVisualization(Context context) {
        // Create progress bar
        progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressBar.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
    }

    @Override
    public View getView(Context context) {
        return progressBar;
    }

    @Override
    public void onUpdate(Double score) {
        // Convert to rounded percentage
        int percent = (int) Math.round(score * 100);

        // Set progress bar value
        progressBar.setProgress(percent);

        // Add colors to progress bar
        int color;
        if (percent > 70)
            color = Color.rgb(0, 200, 0);
        else if (percent > 40)
            color = Color.rgb(240, 140, 0);
        else
            color = Color.RED;

        progressBar.getProgressDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }
}
