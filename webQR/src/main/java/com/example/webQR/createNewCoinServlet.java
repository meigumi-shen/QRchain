package com.example.webQR;

import lombok.SneakyThrows;
import org.com.fisco.QR;
import org.com.sol;
import org.com.util;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class createNewCoinServlet extends HttpServlet {
    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String QRHash = req.getParameter("QRHash");
        QR qrsol = null;
        try {
            qrsol = sol.initSol(QRHash);
        }catch (ContractException e){}
        byte[] inputData = util.inputData2bytes(req.getParameter("initQRData"));
        String res = sol.createNewCoin(qrsol,inputData);
//        设置回传
        Writer writer = resp.getWriter();
        writer.write(res);
    }
}
