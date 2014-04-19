package com.ysb.model.utils;

import com.ysb.model.dao.UserDAOimpl;

import javax.xml.bind.DatatypeConverter;
import java.sql.SQLException;

/**
 * Created by Yarik on 13.04.14.
 */
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
     * helper method, which useful to show exception which wew caught
     * */
    public static void exeption(String where, Exception what) {
        System.out.println("Exception in " + where + " : " + what.getMessage());
    }
}
