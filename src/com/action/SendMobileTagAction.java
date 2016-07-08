package com.action;

import com.bean.UserBean;
import com.webSocket.GetTagWS;
import com.webSocket.WebSocket;

/**
 * Created by drc on 16-7-6.
 */
public class SendMobileTagAction {
    private String tagNum;
    private String info;

    public void setTagNum(String tagNum) {
        this.tagNum = tagNum;
    }

    public String execute(){
        String mobileID = UserBean.getCurrentUser().getId();
        String userID = null;
        if(mobileID.endsWith("M")){
            userID = mobileID.substring(0, mobileID.length()-1);
        }else {
            info = "loginError";
        }
        UserBean user = UserBean.getUserByID(userID);
        if(user != null){
            WebSocket webSocket = user.getWebSocket();
            if(webSocket != null && webSocket instanceof GetTagWS){
                webSocket.send(tagNum);
                info = "success";
            }else {
                info = "pageError";
            }
        }else {
            info = "userError";
        }
        return "success";
    }

    public String getInfo() {
        return info;
    }
}
