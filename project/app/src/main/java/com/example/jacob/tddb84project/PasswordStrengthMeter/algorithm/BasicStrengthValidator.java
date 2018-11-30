package com.example.jacob.tddb84project.PasswordStrengthMeter.Algorithm;

/**
 * Example implementation of the StrengthValidatorInterface
 */
public class BasicStrengthValidator implements StrengthValidatorInterface {

    @Override
    public double validate(String password) {
        if (password.length() > 0)
            return (1 / 16f) * Math.min(16, password.length());

        return 0;
    }
}
