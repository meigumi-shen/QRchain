package com.example.webQR;

import lombok.SneakyThrows;
import org.com.fisco.QR;
import org.com.sol;
import org.com.util;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class getQRCoinInformServlet extends HttpServlet {

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String QRHash = req.getParameter("QRHash");
        byte[] hash = util.hexString2bytes(req.getParameter("getQRcoinHash"));
        byte[] data = util.getDateString().getBytes();
        String note = req.getParameter("getQRcoinNote");
        QR qrsol = null;
        try {
            qrsol = sol.initSol(QRHash);
        }catch (ContractException e){}
        TransactionReceipt txrec = sol.QRCoinInform.getQRCoinInformTxRec(qrsol,hash,data,note);
        String res = txrec.toString();
//        设置回调
        Writer writer = resp.getWriter();
        writer.write(res);
    }


}
