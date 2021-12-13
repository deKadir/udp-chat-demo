package com.chat.utils;

public class StringBuilder {
    //converting byte array to String
    public static String data(byte[] a)
    {

        if (a == null)
            return null;
        java.lang.StringBuilder ret = new java.lang.StringBuilder();
        int i = 0;
        while (a[i] != 0)
        {
            ret.append((char) a[i]);
            i++;
        }

        return ret.toString();
    }

}
