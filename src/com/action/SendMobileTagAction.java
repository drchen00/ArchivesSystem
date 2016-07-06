package com.action;

import com.bean.UserBean;

/**
 * Created by drc on 16-7-6.
 */
public class SendMobileTagAction {
    private String tagNum;

    public void setTagNum(String tagNum) {
        this.tagNum = tagNum;
    }

    public String execute(){
        String mobileID = UserBean.getCurrentUser().getId();
        String userID;
        if(mobileID.endsWith("M")){
            userID = mobileID.substring(0, mobileID.length()-1);
        }else {
            return "error";
        }
        UserBean user = UserBean.getUserByID(userID);
        user.getWebSocket().send(tagNum);
        return "success";
    }
}
