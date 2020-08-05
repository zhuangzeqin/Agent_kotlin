package com.eeepay.zzq.bean;

import android.util.Log;

import com.eeepay.zzq.utils.FastSharedPreferencesTools;

import java.io.Serializable;

/**
 * Created by zw on 2016/3/3 0003.
 * 登陆的用户信息以及信息保存
 */
public class UserInfo implements Serializable {
    public final static String TAG = UserInfo.class.getSimpleName();
    private static UserInfo userInfo;

    private String userNo;//用户编号
    private String agentNo;//代理商编号
    private String agentNode;//代理商结点
    private String agentName;
    private String userName;//用户名称
    private String role;//角色   1为管理员   0为销售员
    private String phone;
    private String oneAgentNo;//一级代理商编号
    private String agentLevel;//代理商级别
    private String authorities;//权限集合
    private String handle;// 为0时代表该登录管理员用户不可更改销售员不可设置状态为无效。(管理员才下发此字段)
    private String safePhone;//安全手机号
    boolean hasSafePhone;//是否设置安全手机号
    boolean safePassword;//是否设置安全资金密码
    /**
     * ------注释说明-APP首页点击“拓展代理”时直接提示：请先联系上级代理为您配置正确的分润及活动参数在代理商管理页面点击开设下级时提示：请先联系上级代理为您配置正确的分润及活动参数-------
     **/
    boolean rightShareActivity;
    private String lockTime;// 锁住时间
    private String loginToken;//token
    private String oneLevelId;//
    private String parentId;//
    private String password;//
    private String teamId;//
    private int wrongPasswordCount;//

    public PubDataInfo getPubDataBean() {
        return pubDataBean;
    }

    public void setPubDataBean(PubDataInfo pubDataBean) {
        this.pubDataBean = pubDataBean;
    }

    //公共数据各种开关
    private PubDataInfo pubDataBean;


    public String getLockTime() {
        return lockTime;
    }

    public void setLockTime(String lockTime) {
        this.lockTime = lockTime;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public String getOneLevelId() {
        return oneLevelId;
    }

    public void setOneLevelId(String oneLevelId) {
        this.oneLevelId = oneLevelId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public int getWrongPasswordCount() {
        return wrongPasswordCount;
    }

    public void setWrongPasswordCount(int wrongPasswordCount) {
        this.wrongPasswordCount = wrongPasswordCount;
    }


    public boolean isRightShareActivity() {
        return rightShareActivity;
    }

    public void setRightShareActivity(boolean rightShareActivity) {
        this.rightShareActivity = rightShareActivity;
    }

    public String getSafePhone() {
        return safePhone;
    }

    public void setSafePhone(String safePhone) {
        this.safePhone = safePhone;
    }


    public boolean isHasSafePhone() {
        return hasSafePhone;
    }

    public void setHasSafePhone(boolean hasSafePhone) {
        this.hasSafePhone = hasSafePhone;
    }

    public boolean isSafePassword() {
        return safePassword;
    }

    public void setSafePassword(boolean safePassword) {
        this.safePassword = safePassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAgentNode() {
        return agentNode;
    }

    public void setAgentNode(String agentNode) {
        this.agentNode = agentNode;
    }

    public String getAgentNo() {
        return agentNo;
    }

    public void setAgentNo(String agentNo) {
        this.agentNo = agentNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getOneAgentNo() {
        return oneAgentNo;
    }

    public void setOneAgentNo(String oneAgentNo) {
        this.oneAgentNo = oneAgentNo;
    }

    public String getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(String agentLevel) {
        this.agentLevel = agentLevel;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public static UserInfo getInstance() {
        if (userInfo == null) {
            userInfo = new UserInfo();
        }
        return userInfo;
    }

    private UserInfo() {
    }


    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userNo='" + userNo + '\'' +
                ", agentNo='" + agentNo + '\'' +
                ", agentNode='" + agentNode + '\'' +
                ", agentName='" + agentName + '\'' +
                ", userName='" + userName + '\'' +
                ", role='" + role + '\'' +
                ", phone='" + phone + '\'' +
                ", oneAgentNo='" + oneAgentNo + '\'' +
                ", agentLevel='" + agentLevel + '\'' +
                ", authorities='" + authorities + '\'' +
                ", handle='" + handle + '\'' +
                '}';
    }

    /**
     * 保存用户信息到本地
     */
    public static void saveUserInfo() {
        if (userInfo != null) {
            //序列化
            FastSharedPreferencesTools.getInstance().putSerializable(TAG, userInfo);
//            String serialize = SerializeUtils.serialize(userInfo);//序列化
//            SPUtils.saveString(SPUtils.USER_INFO, serialize);
        } else {
            Log.d(TAG, "保存用户信息失败");
        }
    }

    /**
     * 从本地获得保存的用户信息
     *
     * @return
     */
    public static UserInfo getUserInfo2SP() {
        if (userInfo == null) {
            userInfo = (UserInfo)FastSharedPreferencesTools.getInstance().getSerializable(TAG,null);
//            String infoStr = SPUtils.getString(SPUtils.USER_INFO);
//            userInfo = (UserInfo) SerializeUtils.deSerialization(infoStr);//反序列化
        }
        return userInfo;
    }

    /**
     * 删除用户信息
     */
    public static void removeUserInfo() {
        FastSharedPreferencesTools.getInstance().removeKey(TAG);
//        SPUtils.removeHistory(SPUtils.USER_INFO);
    }
}
