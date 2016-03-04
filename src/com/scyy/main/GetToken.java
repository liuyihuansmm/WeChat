package com.scyy.main;

import com.scyy.menu.Menu;
import com.scyy.po.ACCESS_TOKEN;
import com.scyy.util.WeiXinUtil;
import net.sf.json.JSONObject;

/**
 * Created by LYH on 2016/3/1.
 */
public class GetToken {
    public static  void main(String[] args) {
        ACCESS_TOKEN token = WeiXinUtil.getToken();
        System.out.println(token.getAccess_token());
        Menu menu = WeiXinUtil.initMenu();
        String menuStr = JSONObject.fromObject(menu).toString();
        WeiXinUtil.createMenu(token.getAccess_token(),menuStr);
    }
}
