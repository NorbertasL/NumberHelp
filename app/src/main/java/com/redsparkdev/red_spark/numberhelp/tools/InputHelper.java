package com.redsparkdev.red_spark.numberhelp.tools;

/**
 * Created by Red_Spark on 02-Aug-17.
 */

public class InputHelper {

    public static boolean checkIfDec(String value){
       return isDigit(value);
    }
    public static boolean checkIfHex(String value){
        for(int i = 0; i< value.length(); i++) {
            String digit = Character.toString(value.charAt(i));
            if(!isDigit(digit)){
                if(!isHexChar(digit.charAt(0))){
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean checkIfOct(String value){
        if(isDigit(value)){
            for(int i = 0; i< value.length(); i++) {
                //have to use Character.getNumericValue or we get wrong value
                int digit = Integer.valueOf(Character.getNumericValue(value.charAt(i)));
                //check if any of the digits are > or = to 8(Oct is a base of 8)
                //if it is its not a valid number
                if (digit >= 8) {
                   return false;
                }
            }
            return true;
        }
        return false;

    }
    public static boolean checkIfBin(String value){
        if(isDigit(value)){
            for(int i = 0; i< value.length(); i++) {
                //have to use Character.getNumericValue or we get wrong value
                int digit = Integer.valueOf(Character.getNumericValue(value.charAt(i)));
                //check if any of the digits are > or = to 2(Binary is a base of 2)
                //if it is its not a valid number
                if (digit >= 2) {
                    return false;
                }
            }
            return true;

        }
        return false;
    }

    private static boolean isDigit(String value){

        //.matches() check for the patter
        // the \d+ check for digits
        if(!value.matches("\\d+")){
            return false;
        }
        return true;

    }
    private static boolean isHexChar(char value){
        char [] hexChars = {'A', 'B', 'C', 'D', 'E', 'F'};
        for(char c: hexChars){
            if(c == Character.toUpperCase(value)){
                return true;
            }
        }
        return false;
    }
}
