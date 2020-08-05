package com.eeepay.zzq.bean;

import java.io.Serializable;

/*
 * 描述：公共数据的实体bean
 * 作者：zhuangzeqin
 * 时间: 2020/6/17-10:44
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class PubDataInfo implements Serializable {
    private boolean showHotLinePhone;//是否显示拨打电话
    private String hotLinePhone;//电话号码
    private String hotLinePhoneTip;//提示语
    private long toBeSetListSize; //代办代理商设置总条数
    private boolean funCode030;//代理商解绑权限开关
    private boolean funCode044;//代理商进件功能入口
    private boolean funCode052;//代理商敏感数据显示 true显示，false 隐藏
    public boolean isFunCode052() {
        return funCode052;
    }
    public void setFunCode052(boolean funCode052) {
        this.funCode052 = funCode052;
    }

    public boolean isShowHotLinePhone() {
        return showHotLinePhone;
    }

    public void setShowHotLinePhone(boolean showHotLinePhone) {
        this.showHotLinePhone = showHotLinePhone;
    }

    public String getHotLinePhone() {
        return hotLinePhone;
    }

    public void setHotLinePhone(String hotLinePhone) {
        this.hotLinePhone = hotLinePhone;
    }

    public String getHotLinePhoneTip() {
        return hotLinePhoneTip;
    }

    public void setHotLinePhoneTip(String hotLinePhoneTip) {
        this.hotLinePhoneTip = hotLinePhoneTip;
    }

    public long getToBeSetListSize() {
        return toBeSetListSize;
    }

    public void setToBeSetListSize(long toBeSetListSize) {
        this.toBeSetListSize = toBeSetListSize;
    }

    public boolean isFunCode030() {
        return funCode030;
    }

    public void setFunCode030(boolean funCode030) {
        this.funCode030 = funCode030;
    }

    public boolean isFunCode044() {
        return funCode044;
    }

    public void setFunCode044(boolean funCode044) {
        this.funCode044 = funCode044;
    }

    @Override
    public String toString() {
        return "PubDataInfo{" +
                "showHotLinePhone=" + showHotLinePhone +
                ", hotLinePhone='" + hotLinePhone + '\'' +
                ", hotLinePhoneTip='" + hotLinePhoneTip + '\'' +
                ", toBeSetListSize=" + toBeSetListSize +
                ", funCode030=" + funCode030 +
                ", funCode044=" + funCode044 +
                '}';
    }
}
