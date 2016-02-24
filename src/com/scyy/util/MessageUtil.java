package com.scyy.util;

import com.scyy.po.TextMessage;
import com.scyy.servlet.WeiXinServlet;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LYH on 2016/2/24.
 * 描述：消息处理工具
 * 功能：处理消息格式转换之类的事情
 */
public class MessageUtil {

    /**
     * 说明：消息类型、事件类型定义
     */
    public static final String MESSAGE_TXT         = "text";           //文本消息
    public static final String MESSAGE_IMAGE       = "image";
    public static final String MESSAGE_VOICE       = "voice";
    public static final String MESSAGE_VIDEO       = "video";
    public static final String MESSAGE_SHORTVIDEO  = "shortvideo";
    public static final String MESSAGE_LOCATION    = "location";
    public static final String MESSAGE_LINK        = "link";
    public static final String MESSAGE_EVENT       = "event";
    public static final String MESSAGE_SUBSCRIBE   = "subscribe";
    public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
    public static final String MESSAGE_SCAN        = "SCAN";
    public static final String MESSAGE_CLICK       = "CLICK";
    public static final String MESSAGE_VIEW        = "VIEW";


    /**
     *
     * @param request
     * @return map(HashMap<String, String>)
     * @throws IOException
     * @throws DocumentException
     * 说明：从输入流中读取xml数据流，并保存到map中返回该map
     */
    public static Map<String,String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
        Map<String,String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();
        InputStream in = request.getInputStream();
        Document doc = reader.read(in);

        Element element = doc.getRootElement();
        List<Element> list = element.elements();

        for(Element temp:list) {
            map.put(temp.getName(),temp.getText());
        }

        in.close();
        return  map;
    }

    /**
     *
     * @param textMessage
     * @return
     * 说明: 文本消息转成xml格式的String并返回
     */
    public static String textMessageToXml(TextMessage textMessage) {
        XStream xStream = new XStream();
        xStream.alias("xml",textMessage.getClass());
        return xStream.toXML(textMessage);
    }

    /**
     *
     * @return
     * 说明：定义欢迎菜单，当用户首次关注时，向用户返回
     */
    public  static  String welcomeMenuText(){
        StringBuilder welMenu = new StringBuilder();
        welMenu.append("欢迎关注国控四川医药服务号！\n");
        return  welMenu.toString();
    }

    /**
     *
     * @return
     * 说明：定义感谢菜单，当用户取消关注时，向用户返回
     */
    public static  String  thanksMenuText() {
        StringBuilder thanksMenu = new StringBuilder();
        thanksMenu.append("感谢您的关注！\n");
        return  thanksMenu.toString();
    }
    /**
     *
     * @return
     * 说明：将String转成TextMassage
     */
    public static String toTextMessgae(String p_fromUserName, String p_toUserName, String p_content) {
        TextMessage text = new TextMessage();
        text.setToUserName(p_fromUserName);
        text.setFromUserName(p_toUserName);
        text.setCreateTime(new Date().getTime());
        text.setContent(p_content);
        text.setMsgType(MESSAGE_TXT);
        return  textMessageToXml(text);
    }
}
