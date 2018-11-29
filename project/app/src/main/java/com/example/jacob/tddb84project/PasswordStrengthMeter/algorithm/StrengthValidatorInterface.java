package com.example.jacob.tddb84project.PasswordStrengthMeter.algorithm;

public interface StrengthValidatorInterface {
    /**
     * Validation score
     * Should return a value between 0-1
     */
    double validate(String password);
}
