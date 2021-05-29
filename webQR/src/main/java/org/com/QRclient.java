package org.com;

import org.com.fisco.QR;

import java.util.Arrays;
import java.util.Scanner;

public class QRclient<isend> {
    public QRclient(QR qrSol){
        boolean isend = false;
        while(!isend){
            view.show.ClientView();
            Scanner scan = new Scanner(System.in);
            String input = scan.next();
            switch (input){
                case "1" :{
                    System.out.println("add "+sol.addAdministrator(qrSol) + " as admin");
                    break;
                }
                case "2" :{
                    byte[] hash = inputHash();
                    String note = util.inputString("note");
                    //
                    String output = sol.QRCoinInform.getQRCoinInformTxRec(qrSol, hash,util.getDateString().getBytes(), note).toString();

                    System.out.println(output);
                    break;
                }
                case "3" :{
                    byte[] inputData = util.inputData2bytes(util.inputString("data"));

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


}
