package org.dync.testrecyclerview.entry;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/5/28 14:39
 * 描述:
 */
public class ChatModel {
    public int type;
    public String name;
    public String mMsg;
    public UserType mUserType;

    public ChatModel(){

    }

    public ChatModel(String msg, UserType userType, int type) {
        mMsg = msg;
        mUserType = userType;
        this.type = type;
    }

    public enum UserType {
        From, To
    }
}