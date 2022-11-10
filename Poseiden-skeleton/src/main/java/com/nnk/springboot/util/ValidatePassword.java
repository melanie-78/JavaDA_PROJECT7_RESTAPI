package com.nnk.springboot.util;

import com.nnk.springboot.domain.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatePassword {
    public static Boolean checkPass(User user){
        //access to user's password
        String password = user.getPassword();

        Boolean hasNum=false;
        Boolean hasCap=false;
        char c;

        for(int i=0; i<password.length(); i++) {
            c = password.charAt(i);

            if (Character.isDigit(c)) {
                hasNum = true;
            } else if (Character.isUpperCase(c)) {
                hasCap = true;
            }
        }
            if (hasNum && hasCap) {
                return true;
        }
        return false;
    }

    public static Boolean hasSpecialCharacter(User user){
        String password = user.getPassword();

        Pattern sPattern = Pattern.compile("[a-zA-Z0-9]*");
        Matcher sMatcher = sPattern.matcher(password);

        if(!sMatcher.matches()){
            return true;
        }
        return false;
    }
}
