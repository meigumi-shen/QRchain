package org.com;

import org.com.fisco.QR;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.model.TransactionReceipt;

import java.text.SimpleDateFormat;
import java.util.Date;

public class log {

    public static void txReceiptLog(TransactionReceipt txRec){

        System.out.println(util.getDateString() + "-|-" + txRec.toString());

    }
    public static void blockLog(){
        Client client = sol.initClient();
        System.out.println(util.getDateString() + client.getBlockNumber());
    }


}
