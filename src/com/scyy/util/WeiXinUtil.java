package com.scyy.util;


import com.scyy.menu.Button;
import com.scyy.menu.ClickButton;
import com.scyy.menu.Menu;
import com.scyy.menu.ViewButton;
import com.scyy.po.ACCESS_TOKEN;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by LYH on 2016/2/29.
 * 说明：重写post、get方法
 */
public class WeiXinUtil {

    private static final String APPSECRET = "1975ade88c6907b85df5aa28d807612a";
    private static final String APPID = "wx4b1f44af27484917";
    private static final String GET_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private static final String CREATE_MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    public  static JSONObject get(String url) {
        JSONObject   jsObject     = new JSONObject();
        HttpClient   httpClient   = HttpClients.createDefault();
        HttpGet      httpGet      = new HttpGet(url);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            String result = EntityUtils.toString(entity,"UTF-8");
            if(result!=null){
                jsObject = JSONObject.fromObject(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  jsObject;
    }

    public static JSONObject post(String url , String outStr) {
        JSONObject   jsObject     = new JSONObject();
        HttpClient   httpClient   = HttpClients.createDefault();
        HttpPost     httpPost     = new HttpPost(url);

        httpPost.setEntity(new StringEntity(outStr,"UTF-8"));
        try {
            HttpResponse httpResponse = httpClient.execute(httpPost);
            String result = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");

            if(result!=null) {
                jsObject = JSONObject.fromObject(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsObject;
    }

    public static ACCESS_TOKEN getToken() {
        ACCESS_TOKEN token = new ACCESS_TOKEN();
        String url = GET_TOKEN.replace("APPID",APPID).replace("APPSECRET",APPSECRET);
        JSONObject jsStr = get(url);
        token.setAccess_token(jsStr.getString("access_token"));
        token.setExpires_in(jsStr.getInt("expires_in"));
        return  token;
    }

    public static Menu initMenu() {
        Menu menu = new Menu();

        Button but1 = new Button();
        but1.setName("菜单1");

        ViewButton viBut11 = new ViewButton();
        viBut11.setName("菜单11");
        viBut11.setUrl("http://www.baidu.com");
        viBut11.setType("view");

        ClickButton clBut12 = new ClickButton();
        clBut12.setName("菜单12");
        clBut12.setType("click");
        clBut12.setKey("1993829");

        but1.setSub_button(new  Button[]{viBut11,clBut12});

        Button but2 = new Button();
        but2.setName("菜单2");

        ViewButton viBut21 = new ViewButton();
        viBut21.setName("菜单21");
        viBut21.setUrl("http://www.qq.com");
        viBut21.setType("view");

        ClickButton clBut22 = new ClickButton();
        clBut22.setName("菜单22");
        clBut22.setType("click");
        clBut22.setKey("1993829");

        but2.setSub_button(new Button[]{viBut21,clBut22});

        ClickButton but3 = new ClickButton();
        but3.setName("帮助菜单");
        but3.setType("click");
        but3.setKey("help");

        menu.setButton(new Button[]{but1,but2,but3});
        return menu;
    }

    public static  int createMenu(String token,String menu) {
        String url = CREATE_MENU_URL.replace("ACCESS_TOKEN",token);
        JSONObject result = post(url,menu);
        if(result.getInt("errcode")==0) {
            System.out.println("创建成功！");
        }else {
            System.out.println(result.getString("errmsg"));
        }
        return result.getInt("errcode");
    }

}
