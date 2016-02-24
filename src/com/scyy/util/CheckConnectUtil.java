package com.scyy.util;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * Created by LYH on 2016/2/24.
 * 描述：检查连接工具
 * 功能：校验接入微信服务器是否正确
 */
public class CheckConnectUtil {

    private static  final String token="sinopharmscyy";
    /**
     *
     * @param p_token
     * @param p_timestamp
     * @param p_nonce
     * @param p_signature
     * @return flag(boolean)
     */
    public static  boolean isConnect(String p_timestamp, String p_nonce, String p_signature) {
        boolean flag = false;

        String[] arry = new String[]{p_timestamp,p_nonce,token};
        Arrays.sort(arry);
        StringBuffer sBuffer = new StringBuffer();
        for(String temp:arry) {
            sBuffer.append(temp);
        }

        String reslut = getSha1(sBuffer.toString());
        if(reslut.equals(p_signature) == true) {
            flag = true;
        }

        return  flag;
    }

    /**
     * Sha1加密方法
     * @param str
     * @return
     * 说明：这部分是网上down的，直接拿来用
     */
    public static String getSha1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };

        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }
}
