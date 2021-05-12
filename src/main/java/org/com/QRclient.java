package org.com;

import org.com.fisco.QR;

import java.math.BigInteger;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class QRclient<isend> {
    public QRclient(QR qrSol){
        boolean isend = false;
        while(!isend){
            System.out.println("--------c-o-n-s-o-l-o--------");
            System.out.println("-1,addAdministrator()");
            System.out.println("-2,getQRCoinInform(hash,note)");
            System.out.println("-3,createNewCoin(inputData)");
            System.out.println("-4,logoutQRCoin(hash)");
            Scanner scan = new Scanner(System.in);
            String input = scan.next();
            switch (input){
                case "1" :{
                    System.out.println("add "+sol.addAdministrator(qrSol) + " as admin");
                    break;
                }
                case "2" :{
                    byte[] hash = inputHash();
                    String note = inputString("note");
                    //
                    String output = sol.QRCoinInform.getQRCoinInform(qrSol, hash,util.getDateString().getBytes(), note);

                    System.out.println(output);
                    break;
                }
                case "3" :{
                    byte[] inputData = util.inputData2bytes(inputString("data"));

                    System.out.println(sol.createNewCoin(qrSol,inputData));
                    break;
                }
                case "4" :{
                    byte[] hash = inputHash();
                    System.out.println(sol.logoutQRCoin(qrSol,hash));
                    break;
                }
                default:break;
            }
        }
    }
//    private BigInteger inputHash(){
//        Scanner scan = new Scanner(System.in);
//        char c = 'n';
//        BigInteger hash;
//        do{
//            System.out.println("please input hash like \"0xaad...\" .");
//            hash = scan.nextBigInteger();
//
//            String str =String.format("%64s",hash.toByteArray());
//            System.out.println("your hash input is 0x"+ str);
//            System.out.println("enter\"y\"for next step, or enter\"n\" to reenter.");
//            c = scan.next().charAt(0);
//        }while (c != 'y');
//        return hash;
//    }
    public static byte[] inputHash(){//todo 改到util类里面，待添加输入检测（长度，输入除0-f的数），异常抓取（java.lang.StringIndexOutOfBoundsException）
        Scanner scan = new Scanner(System.in);
        char c = 'n';
        byte[] hash;
        String hashStr ;
        do{
            System.out.println("please input hash like \"0xaad...\" .");
            hashStr = scan.next();
            System.out.println(hashStr);
            hash =   util.hexString2bytes(hashStr);
            System.out.println(Arrays.toString(hash));
            String str = util.bytes2hexString(hash);
            System.out.println("your hash input is 0x"+ str);
            System.out.println("enter\"y\"for next step, or enter\"n\" to reenter.");
            c = scan.next().charAt(0);
        }while (c != 'y');
        return hash;
    }
    private String inputString(String strName){
        Scanner scan = new Scanner(System.in);
        String inputData;
        char c = 'n';
        do{
            System.out.println("please enter " +strName +" used \"enter\" as end.");
            inputData = scan.nextLine();
            if(inputData.length() == 0){
                inputData = scan.nextLine();
            }
            System.out.println("your " + strName +" input is \""+ inputData + "\"");
            System.out.println("enter\"y\"for next step, or enter\"n\" to reenter.");
            c = scan.next().charAt(0);
        }while (c != 'y');
        return inputData;
    }
    private String inputString(){
        Scanner scan = new Scanner(System.in);
        String inputData;
        char c = 'n';
        do{
            System.out.println("please enter data used \"enter\" as end.");
            inputData = scan.nextLine();
            System.out.println("your data input is \""+ inputData + "\"");
            System.out.println("enter\"y\"for next step, or enter\"n\" to reenter.");
            c = scan.next().charAt(0);
        }while (c != 'y');
        return inputData;
    }


}
