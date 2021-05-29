package org.com;

import  org.com.test.testSerializable;
import org.com.SqlControl.SqlControl;
import org.com.test.testSQL;
import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.client.protocol.response.BlockNumber;
import org.fisco.bcos.sdk.config.exceptions.ConfigException;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.com.fisco_test.*;
import org.com.fisco.*;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.sql.SQLException;

public class BcosSDKTest
{
    QR qrSol;
    // 获取配置文件路径
    //public final String configFile = org.com.BcosSDKTest.class.getClassLoader().getResource("config-example.toml").getPath();
    public void testClient() throws ConfigException, ContractException {
        // 初始化BcosSDK
        //BcosSDK sdk =  BcosSDK.build(configFile);
        BcosSDK sdk = BcosSDK.build("config-example.toml");
        // 为群组1初始化client
        Client client = sdk.getClient(Integer.valueOf(1));

        // 获取群组1的块高
//        BlockNumber blockNumber = client.getBlockNumber();
//        BigInteger i = blockNumber.getBlockNumber();
//        System.out.println(i);


        // 向群组1部署HelloWorld合约
        CryptoKeyPair cryptoKeyPair = client.getCryptoSuite().getCryptoKeyPair();
        HelloWorld helloWorld = HelloWorld.deploy(client, cryptoKeyPair);

        // 调用HelloWorld合约的get接口
        String getValue = helloWorld.get();
        System.out.println(getValue);
        // 调用HelloWorld合约的set接口
        //TransactionReceipt receipt = helloWorld.set("Hello, fisco");
    }
    private void testLog(){
        BcosSDK sdk = BcosSDK.build("config-example.toml");
        // 为群组1初始化client
        Client client = sdk.getClient(Integer.valueOf(1));

        // 获取群组1的块高
        BlockNumber blockNumber = client.getBlockNumber();
        BigInteger i = blockNumber.getBlockNumber();
        System.out.println(i);

    }



    public static void main(String[] args) throws ConfigException, ContractException, UnsupportedEncodingException, SQLException, ClassNotFoundException {

        BcosSDKTest bst = new BcosSDKTest();
//        bst.testClient();
//        System.out.println("test");
//
//        bst.qrSol = sol.initSol();
//
        String contractAddr;
        contractAddr = bst.qrSol.getContractAddress();
        System.out.println(contractAddr);
        QRclient client = new QRclient(bst.qrSol);
//
    }
}

