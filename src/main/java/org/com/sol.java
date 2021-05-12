package org.com;

import org.com.fisco.QR;
import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;



public class sol {
    public static Client initClient(){
        BcosSDK sdk = BcosSDK.build("config-example.toml");
        // 为群组1初始化client
        return sdk.getClient(Integer.valueOf(1));
    }
    public static QR initSol() throws ContractException {
        Client client = initClient();

        //contractAddr , an init contract
        //String contractAddr = "0xa4547571cf750a2a23dbb8d25768829892ec39d9";//ver1001
        String contractAddr = "0xdbff609ca613c5133db5d647272738f7aaaa396e";//ver1002

        // 向群组1部署合约
        CryptoKeyPair cryptoKeyPair = client.getCryptoSuite().getCryptoKeyPair();
        //QR qrSol = QR.deploy(client, cryptoKeyPair);
        QR qrSol = QR.load(contractAddr,client,cryptoKeyPair);
        return qrSol;
    }
    public static String addAdministrator(QR sol){
        TransactionReceipt txRec = sol.addAdministrator();
        log.txReceiptLog(txRec);
        String hash = txRec.getOutput();
        return hash;
    }
    private static byte[] makeCoinData(byte[] data){
        //shi gong zhong
        int i,j,k;
        byte[] bs;
        bs = util.getDateString().getBytes();
        i = bs.length;
        j = data.length;
        byte[] res = new byte[i+j];
        for(k = 0 ;k < i+j ;k++){
            res[k] = (k < i) ? bs[k] : data[k-i];
        }
        return res;

    }
    public static String createNewCoin(QR qrSol ,byte[] data) {
        data = makeCoinData(data);
        TransactionReceipt txRec = qrSol.createQRCoin(data);
        log.txReceiptLog(txRec);
        return txRec.getOutput();
    }
    public static class QRCoinInform extends TransactionCallback{
        private boolean isExist;
        private byte[] lasthash;
        private byte[] thishash;
        private byte[] nexthash;
        private byte[] newhash;
        private int usedcount;
        private String note;

        //将getCoinInform返回的长字符串拆分解码
        public QRCoinInform(String input){
            int i = 0;
            StringBuffer str = new StringBuffer();//作用:作为字符串类型中间变量，临时存储字符串并转化为byte或直接输出
            //以32byte 64个16进制数作为一个参数

            //isExist
            isExist = (input.charAt(2 + 63) == '1') ? true : false;

            //lasthash
            str = new StringBuffer();//对str new一个新的对象，旧对象自动被回收，省去了重新置零的麻烦；
            for(i = 0 ; i < 64 ; i++){
                str.append(input.charAt(2 + 64 * 1 + i));
            }
            lasthash = util.hexString2bytes(str.toString());

            //thishash
            str = new StringBuffer();
            for(i = 0 ; i < 64 ; i++){
                str.append(input.charAt(2 + 64 * 2 + i));
            }
            thishash = util.hexString2bytes(str.toString());

            //nexthash
            str = new StringBuffer();
            for(i = 0 ; i < 64 ; i++){
                str.append(input.charAt(2 + 64 * 3 + i));
            }
            nexthash = util.hexString2bytes(str.toString());

            //newHash
            str = new StringBuffer();
            for(i = 0 ; i < 64 ; i++){
                str.append(input.charAt(2 + 64 * 4 + i));
            }
            newhash = util.hexString2bytes(str.toString());





            //usedcount
            //str = new StringBuffer();
            usedcount = input.charAt(2 + 64 * 5 + 63);

            //str = null;
            //note
            str = new StringBuffer();
            int noteLength = input.charAt(2 + 64 *7 +63);
            noteLength -= 0x30;
            if(noteLength == 0){
                note = "";
            }
            else if(noteLength > 0) {
                int a = 0,b = 0;
                for(i = 0 ; i < noteLength ; i++){//str转ascii码
                    a = input.charAt(2 + 64 * 8 + 2 * i) - 0x30;
                    a *= 16;
                    b = input.charAt(2 + 64 * 8 + 2 * i +1) - 0x30;
                    a += b;
                    str.append(a);
                }
                note = str.toString();
            }
        }
        public static void getQRCoinInformAsync(QR qrSol , byte[] hash, byte[] data , String note){

        }
        public static String getQRCoinInform(QR qrSol , byte[] hash, byte[] data , String note){
//TODO 不产生新hash，需要查原因，初步判断是程序，不是合约，但是合约需要改进成能输出错误码
            TransactionReceipt txRec =  qrSol.getQRCoinInform(hash,data,note);//todo 所有和合约交互的方法都要改用异步函数，不然可能会有函数来不及
            log.txReceiptLog(txRec);
            StringBuffer outputString = new StringBuffer();
            if(txRec.getOutput().length() > 2){
                QRCoinInform qrCinfo = new QRCoinInform(txRec.getOutput());

                if(qrCinfo.getisExist()){
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


                }
                else{
                    outputString.append("isExist:false\n");
                }



            }


            log.txReceiptLog(txRec);
            //return outputString.toString();
            return txRec.toString();
        }
        /*
        *继承了transactionCallback，实现抽象类
        *该抽象类作为异步获取消息的主要处理函数
        *功能在这里面实现
        *
         */
        //public QRCoinInformAsync()
        public void onResponse(TransactionReceipt trRec){

        }

        public boolean getisExist() {
            return isExist;
        }

        public byte[] getLasthash() {
            return lasthash;
        }

        public byte[] getThishash() {
            return thishash;
        }

        public byte[] getNexthash() {
            return nexthash;
        }

        public int getUsedcount() {
            return usedcount;
        }

        public byte[] getNewhash() {
            return newhash;
        }

        public String getNote() {
            return note;
        }
    };


    public static String logoutQRCoin(QR qrSol , byte[] hash){
        TransactionReceipt txRec = qrSol.logoutQRCoin(hash);
        log.txReceiptLog(txRec);
        return txRec.getOutput();
    }
    public static String getTreeNodeInform(QR qrSol , byte[] hash){
        TransactionReceipt txRec = qrSol.getTreeNodeInform(hash);
        log.txReceiptLog(txRec);
        return txRec.getOutput();
    }



}
