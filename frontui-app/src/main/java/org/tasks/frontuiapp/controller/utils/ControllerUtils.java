package org.tasks.frontuiapp.controller.utils;

import java.util.List;

public class ControllerUtils {

    public static String getPasswordError(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            return "Passwords do not match";
        }
        else if(password.length() < 3) {
            return "Password must be at least 3 characters";
        }
        return null;
    }

    public static boolean isValidPassword(String password, String confirmPassword, List<String> errors) {
        String pswError = ControllerUtils.getPasswordError(password, confirmPassword);
        if (pswError != null) {
            errors.add(pswError);
            return false;
        }
        else {
            errors.clear();
            return true;
        }
    }

}
