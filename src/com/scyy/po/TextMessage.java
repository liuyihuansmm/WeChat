package com.scyy.po;

/**
 * Created by LYH on 2016/2/24.
 *  说明：文本消息实体类
 */
public class TextMessage extends BaseMessage {

    private  String Content;
    private  String MsgId;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}
