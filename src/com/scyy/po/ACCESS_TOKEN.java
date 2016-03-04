package com.scyy.po;

/**
 * Created by LYH on 2016/3/1.
 * 说明：全局票据实体类
 */
public class ACCESS_TOKEN {

    private  String access_token;
    private  int    expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
