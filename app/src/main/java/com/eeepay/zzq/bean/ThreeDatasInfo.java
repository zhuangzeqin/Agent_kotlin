package com.eeepay.zzq.bean;

/**
 * 描述：三方数据汇总实体类
 * 作者：ChinzLee
 * 时间: 2020/5/19 10:08
 * 邮箱：ljq@eeepay.cn
 * 备注:
 */
public class ThreeDatasInfo {

    /**
     * code : 0
     * count : 0
     * data : {"currentMonthAddAgentNum":0,"currentMonthAddMerchantNum":0,"currentMonthTradeCount":0,"currentMonthTradeSum":0,"totalAgentNum":0,"totalMerchantNum":0,"yesterdayAddMerchantNum":0,"yesterdayTradeSum":0}
     * message : string
     * success : true
     */

    private int code;
    private int count;
    private DataBean data;
    private String message;
    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * currentMonthAddAgentNum : 0
         * currentMonthAddMerchantNum : 0
         * currentMonthTradeCount : 0
         * currentMonthTradeSum : 0
         * totalAgentNum : 0
         * totalMerchantNum : 0
         * yesterdayAddMerchantNum : 0
         * yesterdayTradeSum : 0
         */

        private String currentMonthAddAgentNum;
        private String currentMonthAddMerchantNum;
        private String currentMonthTradeCount;
        private String currentMonthTradeSum;
        private String totalAgentNum;
        private String totalMerchantNum;
        private String yesterdayAddMerchantNum;
        private String yesterdayTradeSum;

        public String getCurrentMonthAddAgentNum() {
            return currentMonthAddAgentNum;
        }

        public void setCurrentMonthAddAgentNum(String currentMonthAddAgentNum) {
            this.currentMonthAddAgentNum = currentMonthAddAgentNum;
        }

        public String getCurrentMonthAddMerchantNum() {
            return currentMonthAddMerchantNum;
        }

        public void setCurrentMonthAddMerchantNum(String currentMonthAddMerchantNum) {
            this.currentMonthAddMerchantNum = currentMonthAddMerchantNum;
        }

        public String getCurrentMonthTradeCount() {
            return currentMonthTradeCount;
        }

        public void setCurrentMonthTradeCount(String currentMonthTradeCount) {
            this.currentMonthTradeCount = currentMonthTradeCount;
        }

        public String getCurrentMonthTradeSum() {
            return currentMonthTradeSum;
        }

        public void setCurrentMonthTradeSum(String currentMonthTradeSum) {
            this.currentMonthTradeSum = currentMonthTradeSum;
        }

        public String getTotalAgentNum() {
            return totalAgentNum;
        }

        public void setTotalAgentNum(String totalAgentNum) {
            this.totalAgentNum = totalAgentNum;
        }

        public String getTotalMerchantNum() {
            return totalMerchantNum;
        }

        public void setTotalMerchantNum(String totalMerchantNum) {
            this.totalMerchantNum = totalMerchantNum;
        }

        public String getYesterdayAddMerchantNum() {
            return yesterdayAddMerchantNum;
        }

        public void setYesterdayAddMerchantNum(String yesterdayAddMerchantNum) {
            this.yesterdayAddMerchantNum = yesterdayAddMerchantNum;
        }

        public String getYesterdayTradeSum() {
            return yesterdayTradeSum;
        }

        public void setYesterdayTradeSum(String yesterdayTradeSum) {
            this.yesterdayTradeSum = yesterdayTradeSum;
        }
    }
}
