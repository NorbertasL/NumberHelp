package com.example.red_spark.numberhelp.tools;

import com.example.red_spark.numberhelp.Constants;
import com.example.red_spark.numberhelp.Constants.VALUE_TYPE;

/**
 * Created by Red_Spark on 02-Aug-17.
 */

public class ValueConverter {

    public static int toDec(String value, String type){
        switch (type){
            case VALUE_TYPE.HEX:
                return (int)Long.parseLong(value, 16);
            case VALUE_TYPE.OCT:
                return (int)Long.parseLong(value, 8);
            case VALUE_TYPE.BIN:
                return (int)Long.parseLong(value, 2);
            default:
                // TODO: 02-Aug-17
                return -1;
        }
    }


    public static String convertDecTo(int dec, String type){
        switch (type){
            case VALUE_TYPE.HEX:
                return Integer.toHexString(dec);
            case VALUE_TYPE.OCT:
                return Integer.toOctalString(dec);
            case VALUE_TYPE.BIN:
                return Integer.toBinaryString(dec);
            default:
                return dec+"";

        }

    }

}
