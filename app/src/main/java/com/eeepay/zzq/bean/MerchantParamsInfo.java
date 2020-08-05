package com.eeepay.zzq.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 描述：查询商户需要的参数(这个接口后续可能需要调)
 * 作者：zhuangzeqin
 * 时间: 2019/6/17-15:07
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class MerchantParamsInfo implements Serializable {


    /**
     * code : 200
     * message :
     * data : {"searchTypes":[{"key":"ALL","value":"全部","description":"包含以下所有类别"},{"key":"QUALITY","value":"优质商户","description":"本月交易金额>=50000元"},{"key":"ACTIVE","value":"活跃商户","description":"近30天交易笔数>=2笔且交易金额>=10元"},{"key":"UNCERTIFIED","value":"未认证商户","description":"身份未认证的商户"},{"key":"SLEEP","value":"休眠商户","description":"入网>=60天且连续>60天无交易"}],"merchantTypes":[{"key":"ALL","value":"全部商户","description":"包含直营及下级所有的商户"},{"key":"OFFICAL","value":"直营商户","description":"仅包含直营推广的商户"},{"key":"CHILDREN","value":"下级商户","description":"仅包含所有下级的商户"}],"sortTypes":[{"key":"DEFAULT_ORDER","value":"默认排序","description":""},{"key":"CUR_MONTH_TRANS_DESC","value":"本月交易量从高到底","description":""},{"key":"CUR_MONTH_TRANS_ASC","value":"本月交易量从低到高","description":""},{"key":"LAST_MONTH_TRANS_DESC","value":"上个月交易量从高到低","description":""},{"key":"LAST_MONTH_TRANS_ASC","value":"上个月交易量从低到高","description":""},{"key":"ALL_TRANS_DESC","value":"累积交易量从高到低","description":""},{"key":"ALL_TRANS_ASC","value":"累积交易量从低到高","description":""}],"mbpStatus":[{"key":"1","value":"待一审","description":""},{"key":"2","value":"待平台审核","description":""},{"key":"3","value":"审核失败","description":""},{"key":"4","value":"正常","description":""},{"key":"5","value":"已转自动审件","description":""},{"key":"0","value":"关闭","description":""}]}
     * count : 0
     * success : true
     */

    private int code;
    private String message;
    private DataBean data;
    private int count;
    private boolean success;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public static class DataBean implements Serializable {
        private List<SearchTypesBean> searchTypes;
        private List<MerchantTypesBean> merchantTypes;
        private List<ActivityMerchantTypesBean> activityMerchantTypes;//商户类型
        private List<RecommendedSourceBean> recommendedSource;//推广来源
        private List<HlfActiveBean> hlfActive;//add by 激活状态
        private List<SortTypesBean> sortTypes;
        private List<RiskStatusBean> riskStatus;//商户冻结状态
        private List<MerchantSearchEchoBean> merchantSearchEcho;
        private List<MbpStatusBean> mbpStatus;

        public List<ActivityMerchantTypesBean> getActivityMerchantTypes() {
            return activityMerchantTypes;
        }

        public void setActivityMerchantTypes(List<ActivityMerchantTypesBean> activityMerchantTypes) {
            this.activityMerchantTypes = activityMerchantTypes;
        }
        public List<SearchTypesBean> getSearchTypes() {
            return searchTypes;
        }

        public void setSearchTypes(List<SearchTypesBean> searchTypes) {
            this.searchTypes = searchTypes;
        }

        public List<MerchantTypesBean> getMerchantTypes() {
            return merchantTypes;
        }

        public void setMerchantTypes(List<MerchantTypesBean> merchantTypes) {
            this.merchantTypes = merchantTypes;
        }

        public List<RecommendedSourceBean> getRecommendedSource() {
            return recommendedSource;
        }

        public void setRecommendedSource(List<RecommendedSourceBean> recommendedSource) {
            this.recommendedSource = recommendedSource;
        }
        public List<HlfActiveBean> getHlfActive() {
            return hlfActive;
        }

        public void setHlfActive(List<HlfActiveBean> hlfActive) {
            this.hlfActive = hlfActive;
        }
        public List<SortTypesBean> getSortTypes() {
            return sortTypes;
        }

        public void setSortTypes(List<SortTypesBean> sortTypes) {
            this.sortTypes = sortTypes;
        }

        public List<RiskStatusBean> getRiskStatus() {
            return riskStatus;
        }

        public void setRiskStatus(List<RiskStatusBean> riskStatus) {
            this.riskStatus = riskStatus;
        }

        public List<MerchantSearchEchoBean> getMerchantSearchEcho() {
            return merchantSearchEcho;
        }

        public void setMerchantSearchEcho(List<MerchantSearchEchoBean> merchantSearchEcho) {
            this.merchantSearchEcho = merchantSearchEcho;
        }

        public List<MbpStatusBean> getMbpStatus() {
            return mbpStatus;
        }

        public void setMbpStatus(List<MbpStatusBean> mbpStatus) {
            this.mbpStatus = mbpStatus;
        }

        public static class SearchTypesBean implements Serializable {
            /**
             * key : ALL
             * value : 全部
             * description : 包含以下所有类别
             */

            private String key;
            private String value;
            private String description;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }

        public static class MerchantTypesBean implements Serializable {
            /**
             * key : ALL
             * value : 全部商户
             * description : 包含直营及下级所有的商户
             */

            private String key;
            private String value;
            private String description;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }

        public static class ActivityMerchantTypesBean implements Serializable {
            /**
             * key : ALL
             * value : 全部商户
             * description : 包含直营及下级所有的商户
             */

            private String key;
            private String value;
            private String description;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }

        /**
         * 推广来源
         */
        public static class RecommendedSourceBean implements Serializable {
            /**
             * key : 0
             * value : 正常注册
             * description : null
             */

            private String key;
            private String value;
            private String description;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }

        //激活状态
        public static class HlfActiveBean implements Serializable
        {
            /**
             * key : 0
             * value : 未激活
             * description : null
             */

            private String key;
            private String value;
            private String description;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }


        public static class SortTypesBean implements Serializable {
            /**
             * key : DEFAULT_ORDER
             * value : 默认排序
             * description :
             */

            private String key;
            private String value;
            private String description;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }

        /**
         * 商户冻结状态
         */
        public static class RiskStatusBean implements Serializable {
            /**
             * key : 1
             * value : 正常
             * description : null
             */

            private String key;
            private String value;
            private String description;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }

        public static class MerchantSearchEchoBean implements Serializable {
            /**
             * key : ALL
             * value : {"agentNo":null,"agentNode":null,"theOpenBpId":null,"teamId":null,"mobilePhone":null,"startTransTime":null,"endTransTime":null,"minTransMoney":null,"maxTransMoney":null,"minOrderNum":null,"maxOrderNum":null,"province":null,"city":null,"district":null,"startCreateTime":null,"endCreateTime":null,"merchantStatus":null,"theUnOpenBpId":null,"queryScope":"ALL","searchType":"ALL","sortType":"DEFAULT_ORDER","warningId":null,"hlfActive":null}
             * description : 包含以下所有类别
             */

            private String key;
            private MerchantSearchEchoBean.ValueBean value;
            private String description;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public MerchantSearchEchoBean.ValueBean getValue() {
                return value;
            }

            public void setValue(MerchantSearchEchoBean.ValueBean value) {
                this.value = value;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public static class ValueBean implements Serializable {
                public String getAgentName() {
                    return agentName;
                }

                public void setAgentName(String agentName) {
                    this.agentName = agentName;
                }

                public String getTeam_name() {
                    return team_name;
                }

                public void setTeam_name(String team_name) {
                    this.team_name = team_name;
                }

                public String getTheOpenBpIdName() {
                    return theOpenBpIdName;
                }

                public void setTheOpenBpIdName(String theOpenBpIdName) {
                    this.theOpenBpIdName = theOpenBpIdName;
                }

                public String getTheUnOpenBpIdName() {
                    return theUnOpenBpIdName;
                }

                public void setTheUnOpenBpIdName(String theUnOpenBpIdName) {
                    this.theUnOpenBpIdName = theUnOpenBpIdName;
                }

                /**
                 * agentNo : null
                 * agentNode : null
                 * theOpenBpId : null
                 * teamId : null
                 * mobilePhone : null
                 * startTransTime : null
                 * endTransTime : null
                 * minTransMoney : null
                 * maxTransMoney : null
                 * minOrderNum : null
                 * maxOrderNum : null
                 * province : null
                 * city : null
                 * district : null
                 * startCreateTime : null
                 * endCreateTime : null
                 * merchantStatus : null
                 * theUnOpenBpId : null
                 * queryScope : ALL
                 * searchType : ALL
                 * sortType : DEFAULT_ORDER
                 * warningId : null
                 * hlfActive : null
                 */
                private String agentName;
                private String agentNo;
                private String agentNode;
                private String theOpenBpId;
                private String theOpenBpIdName;
                private String teamId;
                private String team_name;
                private String mobilePhone;
                private String startTransTime;
                private String endTransTime;
                private double minTransMoney;
                private double maxTransMoney;
                private int minOrderNum;
                private int maxOrderNum;
                private String province;
                private String city;
                private String district;
                private String startCreateTime;
                private String endCreateTime;
                private String merchantStatus;
                private String theUnOpenBpId;
                private String theUnOpenBpIdName;
                private String queryScope;
                private String searchType;
                private String sortType;
                private String warningId;
                private String hlfActive;
                private String merchantName;            // 商户名称
                private String merchantNo;              // 商户编号
                private String recommendedSource;       // 推广来源
                private String riskStatus;               // 冻结状态

                public String getAgentNo() {
                    return agentNo;
                }

                public void setAgentNo(String agentNo) {
                    this.agentNo = agentNo;
                }

                public String getAgentNode() {
                    return agentNode;
                }

                public void setAgentNode(String agentNode) {
                    this.agentNode = agentNode;
                }

                public String getTheOpenBpId() {
                    return theOpenBpId;
                }

                public void setTheOpenBpId(String theOpenBpId) {
                    this.theOpenBpId = theOpenBpId;
                }

                public String getTeamId() {
                    return teamId;
                }

                public void setTeamId(String teamId) {
                    this.teamId = teamId;
                }

                public String getMobilePhone() {
                    return mobilePhone;
                }

                public void setMobilePhone(String mobilePhone) {
                    this.mobilePhone = mobilePhone;
                }

                public String getStartTransTime() {
                    return startTransTime;
                }

                public void setStartTransTime(String startTransTime) {
                    this.startTransTime = startTransTime;
                }

                public String getEndTransTime() {
                    return endTransTime;
                }

                public void setEndTransTime(String endTransTime) {
                    this.endTransTime = endTransTime;
                }

                public double getMinTransMoney() {
                    return minTransMoney;
                }

                public void setMinTransMoney(double minTransMoney) {
                    this.minTransMoney = minTransMoney;
                }

                public double getMaxTransMoney() {
                    return maxTransMoney;
                }

                public void setMaxTransMoney(double maxTransMoney) {
                    this.maxTransMoney = maxTransMoney;
                }

                public int getMinOrderNum() {
                    return minOrderNum;
                }

                public void setMinOrderNum(int minOrderNum) {
                    this.minOrderNum = minOrderNum;
                }

                public int getMaxOrderNum() {
                    return maxOrderNum;
                }

                public void setMaxOrderNum(int maxOrderNum) {
                    this.maxOrderNum = maxOrderNum;
                }

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public String getDistrict() {
                    return district;
                }

                public void setDistrict(String district) {
                    this.district = district;
                }

                public String getStartCreateTime() {
                    return startCreateTime;
                }

                public void setStartCreateTime(String startCreateTime) {
                    this.startCreateTime = startCreateTime;
                }

                public String getEndCreateTime() {
                    return endCreateTime;
                }

                public void setEndCreateTime(String endCreateTime) {
                    this.endCreateTime = endCreateTime;
                }

                public String getMerchantStatus() {
                    return merchantStatus;
                }

                public void setMerchantStatus(String merchantStatus) {
                    this.merchantStatus = merchantStatus;
                }

                public String getTheUnOpenBpId() {
                    return theUnOpenBpId;
                }

                public void setTheUnOpenBpId(String theUnOpenBpId) {
                    this.theUnOpenBpId = theUnOpenBpId;
                }

                public String getQueryScope() {
                    return queryScope;
                }

                public void setQueryScope(String queryScope) {
                    this.queryScope = queryScope;
                }

                public String getSearchType() {
                    return searchType;
                }

                public void setSearchType(String searchType) {
                    this.searchType = searchType;
                }

                public String getSortType() {
                    return sortType;
                }

                public void setSortType(String sortType) {
                    this.sortType = sortType;
                }

                public String getWarningId() {
                    return warningId;
                }

                public void setWarningId(String warningId) {
                    this.warningId = warningId;
                }

                public String getHlfActive() {
                    return hlfActive;
                }

                public void setHlfActive(String hlfActive) {
                    this.hlfActive = hlfActive;
                }

                public String getMerchantName() {
                    return merchantName;
                }

                public void setMerchantName(String merchantName) {
                    this.merchantName = merchantName;
                }

                public String getMerchantNo() {
                    return merchantNo;
                }

                public void setMerchantNo(String merchantNo) {
                    this.merchantNo = merchantNo;
                }

                public String getRecommendedSource() {
                    return recommendedSource;
                }

                public void setRecommendedSource(String recommendedSource) {
                    this.recommendedSource = recommendedSource;
                }

                public String getRiskStatus() {
                    return riskStatus;
                }

                public void setRiskStatus(String riskStatus) {
                    this.riskStatus = riskStatus;
                }
            }
        }

        public static class MbpStatusBean implements Serializable {
            /**
             * key : 1
             * value : 待一审
             * description :
             */

            private String key;
            private String value;
            private String description;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }
    }
}
