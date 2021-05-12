package org.com;

import org.fisco.bcos.sdk.abi.datatypes.generated.Int256;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

public class util {
    public static byte[] inputData2bytes(String inputData) {
        byte[] res;
        byte[] bs = inputData.getBytes();
        res = bs;
        return res;
    }

    public static String getDateString() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
        return format.format(date);
    }

    public static byte[] int2bytes(int input) {
        byte[] output = new byte[32];
        int i;

        for (i = 0; i < 32; i++) {
            output[i] = (byte) (input);
        }
        return output;
    }

    public static byte[] hexString2bytes(String hexString) {
        int len = hexString.length();
        int i = len % 2;
        int j = 0;
        int k;
        byte[] res;
        if (hexString.charAt(1) == 'x') {//0x is head
            res = new byte[(len + i - 2) / 2];
            for (j = 0; j < res.length ; j++) {
                int m = hexString.charAt(2 * j + 2);
                int n;
                try {
                    n = hexString.charAt(2 * j + 1 +2);
                }catch (java.lang.StringIndexOutOfBoundsException e){
                    n = 0x30;
                }
                if (m >= '0' && m <= '9') {
                    k = 16 * (m - 0x30);
                } else if (m >= 'a' && m <= 'f') {
                    k = 16 * (m - 0x60 + 9);
                } else if (m >= 'A' && m <= 'F') {
                    k = 16 * (m - 0x40 + 9);
                } else return new byte[0];

                if (n >= '0' && n <= '9') {
                    k += (n - 0x30);
                } else if (n >= 'a' && n <= 'f') {
                    k += (n - 0x60 + 9);
                } else if (n >= 'A' && n <= 'F') {
                    k += (n - 0x40 + 9);
                } else return new byte[0];

                res[j] = (byte) k;
                k = 0;
            }
        }
        else {
            res = new byte[(len + i) / 2];
            for (j = 0; j < res.length; j++) {
                int m = hexString.charAt(2 * j);
                int n;
                try {
                    n = hexString.charAt(2 * j + 1);
                }catch (java.lang.StringIndexOutOfBoundsException e){
                    n = 0x30;
                }
                if (m >= '0' && m <= '9') {
                    k = 16 * (m - 0x30);
                } else if (m >= 'a' && m <= 'f') {
                    k = 16 * (m - 0x60 + 9);
                } else if (m >= 'A' && m <= 'F') {
                    k = 16 * (m - 0x40 + 9);
                } else return new byte[0];

                if (n >= '0' && n <= '9') {
                    k += (n - 0x30);
                } else if (n >= 'a' && n <= 'f') {
                    k += (n - 0x60 + 9);
                } else if (n >= 'A' && n <= 'F') {
                    k += (n - 0x40 + 9);
                } else return new byte[0];

                res[j] = (byte) (k & 0xff);
                k = 0;
            }

        }
        System.out.println(res);
        return res;
    }
    public static String bytes2hexString(byte[] input){
        int len = input.length;
        StringBuffer str = new StringBuffer(2*len + 2);
        str.append("0x");
        int b,res;
        for(int i = 0 ; i < len ; i++){
            b = input[i];
            res = 0;
            res = (b & 0xf0) >> 4;
            if(res >= 0x00 && res <= 0x09){
                str.append((char)(res + 0x30));
            }
            else {
                str.append((char)(res - 10 + 0x41));// a b c d e f
            }
            res = b & 0x0f;
            if(res >= 0x00 && res <= 0x09){
                str.append((char)(res + 0x30));
            }
            else {
                str.append((char)(res - 10 + 0x41));// a b c d e f
            }
        }
        System.out.println(str);
        return str.toString();
    }
}
