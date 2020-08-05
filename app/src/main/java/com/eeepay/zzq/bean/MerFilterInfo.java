package com.eeepay.zzq.bean;

import java.util.List;

/**
 * 描述：筛选-商户模糊搜索实体类
 * 作者：ChinzLee
 * 时间：2020/4/26 16:21
 * 邮箱：ljq@eeepay.cn
 * 备注:
 */
public class MerFilterInfo {

    /**
     * code : 200
     * message :
     * data : [{"merchant_no":"258121000032023","merchant_name":"陈测敏感词优化"},{"merchant_no":"258121000031666","merchant_name":"陈测欢乐返零激活"},{"merchant_no":"258121000031664","merchant_name":"陈测欢乐返零激活验证"},{"merchant_no":"258121000031656","merchant_name":"陈测欢乐返零元激活"},{"merchant_no":"258121000031655","merchant_name":"陈测欢乐返激活零元返"},{"merchant_no":"258121000031653","merchant_name":"陈测欢乐返激活零元"}]
     * count : 0
     * success : true
     */

    private int code;
    private String message;
    private int count;
    private boolean success;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * merchant_no : 258121000032023
         * merchant_name : 陈测敏感词优化
         */

        private String merchant_no;
        private String merchant_name;

        public String getMerchant_no() {
            return merchant_no;
        }

        public void setMerchant_no(String merchant_no) {
            this.merchant_no = merchant_no;
        }

        public String getMerchant_name() {
            return merchant_name;
        }

        public void setMerchant_name(String merchant_name) {
            this.merchant_name = merchant_name;
        }
    }
}
