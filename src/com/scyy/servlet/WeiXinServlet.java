package com.scyy.servlet;

import com.scyy.dao.SkuDAO;
import com.scyy.po.TextMessage;
import com.scyy.util.CheckConnectUtil;
import com.scyy.util.MessageUtil;
import org.dom4j.DocumentException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

/**
 * Created by LYH on 2016/2/24.
 * 描述：微信主Servlet
 * 功能：接入微信服务器
 */
public class WeiXinServlet extends HttpServlet{






    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            Map<String,String> map = MessageUtil.xmlToMap(req);
            String toUserName = map.get("ToUserName");
            String fromUserName = map.get("FromUserName");
            String msgType = map.get("MsgType");
            String content = map.get("Content");

            TextMessage text = new TextMessage();
            String message = null;

            /**
             * 说明：不同消息类型或事件类型的处理逻辑
             */
            if(msgType.equals(MessageUtil.MESSAGE_TXT)) {                         //文本消息
                if(content.equals("?")||content.equals("？")){
                    message = MessageUtil.toTextMessgae(fromUserName,toUserName,MessageUtil.helpMenuText());
                }else if(content.equals("1")){
                    message = MessageUtil.toNewsMessage(fromUserName,toUserName);
                }else if(content.equals("2")){
                    message = MessageUtil.toTextMessgae(fromUserName,toUserName,"请输入药品名称：");
                }else {
                    message = MessageUtil.skuToTextMessage(fromUserName,toUserName, SkuDAO.queryDescr(content));
                }
            } else if(msgType.equals(MessageUtil.MESSAGE_EVENT)) {
                String eveType = map.get("Event");
                if(eveType.equals(MessageUtil.MESSAGE_SUBSCRIBE)){               //用户未关注时，进行关注后的事件推送
                    message = MessageUtil.toTextMessgae(fromUserName,toUserName,MessageUtil.welcomeMenuText());
                }if(eveType.equals(MessageUtil.MESSAGE_CLICK)){
                    String eventKey = map.get("EventKey");
                    if(eventKey.equals("help")) {
                        message = MessageUtil.toTextMessgae(fromUserName,toUserName,MessageUtil.helpMenuText());
                    }

                }
            }

            //输出回复
            if(message!=null){
                out.print(message);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }




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
