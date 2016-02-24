package com.scyy.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
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
}
