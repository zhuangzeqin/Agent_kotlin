package com.eeepay.zzq.bean;

import java.io.Serializable;

/**
 * 描述：版本检查升级的实体bean
 * 作者：zhuangzeqin
 * 时间: 2019/6/6-14:26
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class CheckVersionInfo implements Serializable {

    /**
     * app_url : http://app-client.oss-cn-hangzhou.aliyuncs.com/oem/android/zdb_v1.0.3_20180125_2243.apk
     * down_flag : 1
     * version : 3.0.0
     * lowest_version : 2.0.0
     * ver_desc : 你好，升级
     */

    private String app_url;//更新地址
    private int down_flag; //1选择更新，2强制更新
    private String version;//版本号
    private String lowest_version;
    private String ver_desc;//更新内容描述
    private String protocolVersion;//隐私协议版本号字段

    public String getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }


    public String getApp_url() {
        return app_url;
    }

    public void setApp_url(String app_url) {
        this.app_url = app_url;
    }

    public int getDown_flag() {
        return down_flag;
    }

    public void setDown_flag(int down_flag) {
        this.down_flag = down_flag;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLowest_version() {
        return lowest_version;
    }

    public void setLowest_version(String lowest_version) {
        this.lowest_version = lowest_version;
    }

    public String getVer_desc() {
        return ver_desc;
    }

    public void setVer_desc(String ver_desc) {
        this.ver_desc = ver_desc;
    }
}
