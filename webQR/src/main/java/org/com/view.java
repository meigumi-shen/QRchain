package org.com;

import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.model.TransactionReceipt;

import java.util.Scanner;

public class view {
    public static class show{
        public static void ClientView(){
            System.out.println("--------c-o-n-s-o-l-o--------");
            System.out.println("-1,addAdministrator()");
            System.out.println("-2,getQRCoinInform(hash,note)");
            System.out.println("-3,createNewCoin(inputData)");
            System.out.println("-4,logoutQRCoin(hash)");
        }

        public static String showQRinform(sol.QRCoinInform qrCinfo) {
            StringBuffer outputString = new StringBuffer();
            if (qrCinfo.getisExist()) {
                outputString.append("isExist:ture\n");

                outputString.append("Lasthash is:");
                outputString.append(util.bytes2hexString(qrCinfo.getLasthash()));

                outputString.append("\nthishash is:");
                outputString.append(util.bytes2hexString(qrCinfo.getThishash()));

                outputString.append("\nnexthash is:");
                outputString.append(util.bytes2hexString(qrCinfo.getNexthash()));

                outputString.append("\nnewhash is:");
                outputString.append(util.bytes2hexString(qrCinfo.getNewhash()));

                outputString.append("\nusedcount is:");
                outputString.append(qrCinfo.getUsedcount() - 0x30);//直接输出ascii码的数值，所以需要-30h作转换，无法处理大于10的数
                // TODO 默认10以下，10以上的情况以后修

                outputString.append("\nnote is:");
                outputString.append(qrCinfo.getNote());


            } else {
                outputString.append("isExist:false\n");
            }
            return outputString.toString();
        }
    }

}
