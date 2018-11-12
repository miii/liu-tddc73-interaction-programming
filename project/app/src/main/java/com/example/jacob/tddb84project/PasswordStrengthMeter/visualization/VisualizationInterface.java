package com.example.jacob.tddb84project.PasswordStrengthMeter.visualization;

import android.content.Context;
import android.view.View;

public interface VisualizationInterface {
    View getView(Context context);
    void onUpdate(Float score);
}
