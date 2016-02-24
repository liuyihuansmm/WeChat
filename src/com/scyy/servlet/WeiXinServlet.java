package com.scyy.servlet;

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
            String message;

            /**
             * 说明：不同消息类型或事件类型的处理逻辑
             */
            if(msgType.equals(MessageUtil.MESSAGE_TXT)) {                         //文本消息
                text.setToUserName(fromUserName);
                text.setFromUserName(toUserName);
                text.setCreateTime(new Date().getTime());
                text.setContent("你发送的内容是:"+content);
                text.setMsgType(MessageUtil.MESSAGE_TXT);
                message = MessageUtil.textMessageToXml(text);
                out.print(message);
            } else if(msgType.equals(MessageUtil.MESSAGE_EVENT)) {
                String eveType = map.get("Event");
                if(eveType.equals(MessageUtil.MESSAGE_SUBSCRIBE)){               //用户未关注时，进行关注后的事件推送
                    message = MessageUtil.toTextMessgae(fromUserName,toUserName,MessageUtil.welcomeMenuText());
                    out.print(message);
                }else if(eveType.equals(MessageUtil.MESSAGE_UNSUBSCRIBE)){       //用户取消关注后的事件推送
                    message = MessageUtil.toTextMessgae(fromUserName,toUserName,MessageUtil.thanksMenuText());
                    out.print(message);
                }
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
