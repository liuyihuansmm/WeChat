package com.scyy.util;

import com.scyy.beans.SkuBean;
import com.scyy.po.NewsItem;
import com.scyy.po.NewsMessage;
import com.scyy.po.TextMessage;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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
    public static final String MESSAGE_NEWS       = "news";           //文本消息
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
     * 说明：帮助菜单
     */
    public static  String helpMenuText() {
        StringBuilder helpMenu = new StringBuilder();
        helpMenu.append("欢迎您的关注，请按提示进行操作：\n");
        helpMenu.append("1. 公司介绍\n");
        helpMenu.append("2. 药品查询\n");
        helpMenu.append("3. 回复？调出此菜单");
        return  helpMenu.toString();
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

    /**
     *
     * @param newsMessage
     * @return
     * 说明：新闻消息转成xml
     */
    public static  String newsMessageToXml(NewsMessage newsMessage) {
        XStream xStream = new XStream();
        xStream.alias("xml",newsMessage.getClass());
        xStream.alias("item",new NewsItem().getClass());
       return xStream.toXML(newsMessage);
    }


    /**
     *
     * @param p_fromUserName
     * @param p_toUserName
     * @return
     * 说明：包装成新闻消息
     */
    public  static  String  toNewsMessage(String p_fromUserName, String p_toUserName) {

        List<NewsItem> list = new ArrayList<NewsItem>();

        NewsItem item1 = new NewsItem();
        item1.setTitle("测试");
        item1.setDescription("1234");
        item1.setPicUrl("http://lyh.ngrok.natapp.cn/WeChat/resources/images/11.jpg");
        item1.setUrl("http://www.baidu.com");

        NewsItem item21 = new NewsItem();
        item21.setTitle("国控四川");
        item21.setDescription("SCYY");
        item21.setPicUrl("http://lyh.ngrok.natapp.cn/WeChat/resources/images/21.jpg");
        item21.setUrl("http://www.scyy.com");

        NewsItem item22 = new NewsItem();
        item22.setTitle("百度");
        item22.setDescription("搜索引擎");
        item22.setPicUrl("http://lyh.ngrok.natapp.cn/WeChat/resources/images/22.jpg");
        item22.setUrl("http://www.baidu.com/");

        list.add(item1);
        list.add(item21);
        list.add(item22);

        NewsMessage message = new NewsMessage();
        message.setFromUserName(p_toUserName);
        message.setToUserName(p_fromUserName);
        message.setCreateTime(new Date().getTime());
        message.setArticleCount(list.size());
        message.setArticles(list);
        message.setMsgType(MESSAGE_NEWS);

        return newsMessageToXml(message);
    }


    /**
     *
     * @param p_fromUserName
     * @param p_toUserName
     * @param list
     * @return
     * 说明：药品查询结果转TextMessage格式的Stirng
     */
    public static  String skuToTextMessage(String p_fromUserName, String p_toUserName,List<SkuBean> list) {
        TextMessage text = new TextMessage();
        text.setToUserName(p_fromUserName);
        text.setFromUserName(p_toUserName);
        text.setCreateTime(new Date().getTime());
        text.setMsgType(MESSAGE_TXT);

        StringBuffer content = new StringBuffer();
        if(list.size()==0) {
            content.append("查无此药");
        }else{
            for(SkuBean temp:list) {
                content.append("======================"+"\n");
                content.append(temp.toString()+"\n");
            }
        }

        text.setContent(content.toString());
        return  textMessageToXml(text);
    }
}
