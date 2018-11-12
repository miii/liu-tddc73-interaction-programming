package com.example.jacob.tddb84project.PasswordStrengthMeter.algorithm;

public class BasicStrengthValidator implements StrengthValidatorInterface {

    @Override
    public float validate(String password) {
        if (password.length() > 0)
            return (100 / 16f) * Math.min(16, password.length());

        return 0;
    }
}
