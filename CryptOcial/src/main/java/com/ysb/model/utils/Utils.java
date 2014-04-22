package com.ysb.model.utils;

import javax.xml.bind.DatatypeConverter;

public class Utils {

    /** converts base64 String to file
     * helps to show user avatar on jsp
    * */
    public static String bytesToBase64(byte[] file){
        return DatatypeConverter.printBase64Binary(file);
    }


    /** converts file to base64 String
     * helps to save user avatar to db
    * */
    public static byte[] base64toBytes(String file){
        return DatatypeConverter.parseBase64Binary(file);
    }


    /**
     * helper method, which useful to show exception which we caught or write to log file
     * */
    public static void exception(String where, Exception what) {
        System.out.println("Exception in " + where + " : " + what.getMessage());
    }
}
