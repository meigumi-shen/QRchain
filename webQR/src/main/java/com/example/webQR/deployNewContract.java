package com.example.webQR;

import lombok.SneakyThrows;
import org.com.fisco.QR;
import org.com.sol;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class deployNewContract extends HttpServlet {
    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        QR qrsol = null;
        try {
            qrsol = sol.initSol();
        }catch (ContractException e){}
        String contractHash = qrsol.getContractAddress();

        Writer writer = resp.getWriter();
        writer.write(contractHash);

    }
}
