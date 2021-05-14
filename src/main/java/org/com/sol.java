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
    public static String addAdministrator(QR qrsol){
        TransactionReceipt txRec = qrsol.addAdministrator();
        log.txReceiptLog(txRec);
        String str = util.objectToString(txRec);

        String hash = txRec.getOutput();
        return hash;
    }
    public static class addAdminCallback extends TransactionCallback{
        @Override
        public void onResponse(TransactionReceipt receipt) {
            log.txReceiptLog(receipt);

        }
    }
    public static void addAdministratorAsync(QR qrsol){
        qrsol.addAdministrator();
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

    public static class QRCoinInform{
        private static boolean isExist;
        private static byte[] lasthash;
        private static byte[] thishash;
        private static byte[] nexthash;
        private static byte[] newhash;
        private static int usedcount;
        private static String note;
        private static TransactionReceipt TxRec;

        /**
         *三个构造器
         *1,将TransactionReceipt类型的getoutput方法输出的字符串作为输入初始化
         *2,TransactionReceipt做参初始化
         *3，QR hash data note 作参获取txrec后初始化
         * @param output
         */
        //将getCoinInform返回的长字符串拆分解码
        public QRCoinInform(String output){
            decodeTxRecOutput(output);
        }

        public QRCoinInform(TransactionReceipt txRec){
            TxRec = txRec;
            decodeTxRecOutput();
        }
        public QRCoinInform(QR qrSol , byte[] hash, byte[] data , String note){
            TxRec = getQRCoinInformTxRec(qrSol,hash,data,note);
            decodeTxRecOutput();
        }

        /**
         * 同步获取回执的函数
         * @param qrSol
         * @param hash
         * @param data
         * @param note
         * @return
         */
        public static TransactionReceipt getQRCoinInformTxRec(QR qrSol , byte[] hash, byte[] data , String note){
//TODO 不产生新hash，需要查原因，初步判断是程序，不是合约，但是合约需要改进成能输出错误码
            TransactionReceipt t =  qrSol.getQRCoinInform(hash,data,note);//todo 所有和合约交互的方法都要改用异步函数，不然可能会有函数来不及
            TxRec = t;
            log.txReceiptLog(t);
            //return outputString.toString();
            return t;
        }

        /**
         *继承了transactionCallback，实现抽象类
         *该抽象类作为异步获取消息的主要处理函数
         *功能在这里面实现
         * 功能：异步获取回执，并对QRinform对象赋值
         */
        public static class QRCallback extends TransactionCallback{
            public void onResponse(TransactionReceipt trRec){
                TxRec = trRec;
                decodeTxRecOutput();
            }
        }
        public static void getQRCoinInformTxRecAsync(QR qrSol , byte[] hash, byte[] data , String note){
            QRCallback callback = new QRCallback();
            qrSol.getQRCoinInform(qrSol , hash, data , note , callback);
        }

        /**
         * 功能：解码txrex。output，将其元素信息赋予QRinform类的元素。
         * @param output TransactionReceipt.getoutput方法返回的一长串16进制交易回执的字符串作为输入
         *
         */
        public static void decodeTxRecOutput(String output){
            int i = 0;
            StringBuffer str;//作用:作为字符串类型中间变量，临时存储字符串并转化为byte或直接输出
            //以32byte 64个16进制数作为一个参数

            //isExist
            isExist = (output.charAt(2 + 63) == '1') ? true : false;

            //lasthash
            str = new StringBuffer();//对str new一个新的对象，旧对象自动被回收，省去了重新置零的麻烦；
            for(i = 0 ; i < 64 ; i++){
                str.append(output.charAt(2 + 64 * 1 + i));
            }
            lasthash = util.hexString2bytes(str.toString());

            //thishash
            str = new StringBuffer();
            for(i = 0 ; i < 64 ; i++){
                str.append(output.charAt(2 + 64 * 2 + i));
            }
            thishash = util.hexString2bytes(str.toString());

            //nexthash
            str = new StringBuffer();
            for(i = 0 ; i < 64 ; i++){
                str.append(output.charAt(2 + 64 * 3 + i));
            }
            nexthash = util.hexString2bytes(str.toString());

            //newHash
            str = new StringBuffer();
            for(i = 0 ; i < 64 ; i++){
                str.append(output.charAt(2 + 64 * 4 + i));
            }
            newhash = util.hexString2bytes(str.toString());

            //usedcount
            usedcount = output.charAt(2 + 64 * 5 + 63);

            //note
            str = new StringBuffer();
            int noteLength = output.charAt(2 + 64 *7 +63);
            noteLength -= 0x30;
            if(noteLength == 0){
                note = "";
            }
            else if(noteLength > 0) {
                int a = 0, b = 0;
                for (i = 0; i < noteLength; i++) {//str转ascii码
                    a = output.charAt(2 + 64 * 8 + 2 * i) - 0x30;
                    a *= 16;
                    b = output.charAt(2 + 64 * 8 + 2 * i + 1) - 0x30;
                    a += b;
                    str.append(a);
                }
                note = str.toString();
            }
        }

        public static void decodeTxRecOutput(TransactionReceipt t){
            decodeTxRecOutput(t.getOutput());
        }

        public static void decodeTxRecOutput(){
            decodeTxRecOutput(TxRec.getOutput());
        }


        public TransactionReceipt getTxRec(){
            return TxRec;
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
