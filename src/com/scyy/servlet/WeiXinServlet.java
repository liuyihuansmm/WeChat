package com.scyy.servlet;

import com.scyy.util.CheckConnectUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by LYH on 2016/2/24.
 * 描述：微信主Servlet
 * 功能：接入微信服务器
 */
public class WeiXinServlet extends HttpServlet{

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        String echostr = req.getParameter("echostr");

        PrintWriter out = resp.getWriter();
        if(CheckConnectUtil.isConnect(timestamp,nonce,signature)) {
            out.print(echostr);
        }
    }
}
