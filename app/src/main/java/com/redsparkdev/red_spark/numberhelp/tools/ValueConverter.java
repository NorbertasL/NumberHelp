package com.redsparkdev.red_spark.numberhelp.tools;

import com.redsparkdev.red_spark.numberhelp.Constants.VALUE_TYPE;

import java.math.BigInteger;

/**
 * Created by Red_Spark on 02-Aug-17.
 */

public class ValueConverter {

    public static BigInteger toDec(String value, String type){
        switch (type){
            case VALUE_TYPE.HEX:
                return new BigInteger(value, 16);
            case VALUE_TYPE.OCT:
                return new BigInteger(value, 8);
            case VALUE_TYPE.BIN:
                return new BigInteger(value, 2);
            default:
                return null;
        }
    }


    public static String convertDecTo(BigInteger dec, String type){
        String value = dec.toString();
        switch (type){
            case VALUE_TYPE.HEX:
                return new BigInteger(value).toString(16);
            case VALUE_TYPE.OCT:
                return new BigInteger(value).toString(8);
            case VALUE_TYPE.BIN:
                return new BigInteger(value).toString(2);
            default:
                return value;

        }

    }

}
