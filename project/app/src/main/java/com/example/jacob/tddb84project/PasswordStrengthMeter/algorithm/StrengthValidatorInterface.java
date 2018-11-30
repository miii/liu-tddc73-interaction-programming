package com.example.jacob.tddb84project.PasswordStrengthMeter.Algorithm;

public interface StrengthValidatorInterface {
    /**
     * Validation score
     * Should return a value between 0-1
     */
    double validate(String password);
}
