package com.example.jacob.tddb84project.PasswordStrengthMeter.Visualization;

import android.content.Context;
import android.view.View;

public interface VisualizationInterface {
    /**
     * Get visualizer view
     */
    View getView(Context context);

    /**
     * On score update callback
     */
    void onUpdate(Double score);
}
