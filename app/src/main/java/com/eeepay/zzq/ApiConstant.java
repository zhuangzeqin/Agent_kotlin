package com.eeepay.zzq;

/**
 * 描述：api 接口常量类
 * 作者：zhuangzeqin
 * 时间: 2020/4/16-9:31
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public final class ApiConstant {
    /**
     * ------注释说明--登录接口------
     **/
    public static final String API_LOGIN_URL = "login";
    /**
     * ------注释说明--首页->今日业绩接口------
     **/
    public static final String API_LOADCURRDAYDATA_URL = "index/loadCurrDayData";
    /**
     * ------注释说明--首页->本月业绩接口------
     **/
    public static final String API_LOADCURRMONTHDATA_URL = "index/loadCurrMonthData";
    /**
     * ------注释说明---首页->今日业绩问号说明-----
     **/
    public static final String API_EXPLAINOFMARK_URL = "index/explainOfMark";
    /**
     * ------注释说明--收入明细(按月/按日统计下发数据)接口 增加分页的功能------
     **/
    public static final String API_INCOMEDETAIL_URL = "income/incomeDetail/%s/%s";
    /**
     * ------注释说明---业绩明细(按月/按日统计下发数据)接口 增加分页的功能-----
     **/
    public static final String API_ACHIEVEMENTDETAIL_URL = "order/achievementDetail/%s/%s";
    /**
     * ------注释说明---数据->新增商户趋势、交易量趋势、新增代理商趋势图接口-----
     **/
    public static final String API_DATATREND_URL = "data/viewDataTrend";
    /**
     * ------注释说明---数据->收入趋势趋势图接口-----
     **/
    public static final String API_INCOMETREND_URL = "data/viewIncomeTrend";
    /**
     * ------注释说明---公共数据接口；比如拨打电话-----
     **/
    public static final String API_GETPUBLICDATA_URL = "publicData/getPublicData";
    /**
     * ------注释说明---根据关键字模糊匹配商户列表接口-----
     **/
    public static final String API_LISTAGENTINFO_URL = "agentInfo/listAgentInfo/%s/%s";
    /**
     * ------注释说明---数据->我的收入接口-----
     **/
    public static final String API_INCOME_CENSUS_URL = "income/myIncomeCensus";
    /**
     * ------注释说明---数据->数据统计接口-----
     **/
    public static final String API_DATA_CENSUS_URL = "order/dataCensus";
    /**
     * ------注释说明---数据->活动商户明细-----
     **/
    public static final String API_ACT_MER_DETAILS_URL = "activityData/activityDataCountDetailQuery/%s/%s";
    /**
     * ------注释说明---活动商户明细-> 筛选联动条件查询-----
     **/
    public static final String API_ACT_DATA_TYPE_QUERY_URL = "activityData/activityDataTypeQuery";
    /**
     * ------注释说明---数据活动数据汇总-----
     **/
    public static final String API_ACTIVITYDATACOUNTQUERY_URL = "activityData/activityDataCountQuery";
    /**
     * ------注释说明---商户汇总->产品汇总-----
     **/
    public static final String API_MERCHANT_SUMMARY_URL = "merchant/merchantSummary";
    /**
     * ------注释说明---商户汇总->代理商汇总-----
     **/
    public static final String API_AGENT_SUMMARY_URL = "merchant/countByDirectAgent/%s/%s";

    /**
     * ------注释说明---根据关键字模糊匹配商户列表，供前端下拉框选择-----
     **/
    public static final String API_WILD_MER_URL = "merchant/wildcardMerList";
    /* ------注释说明--代理管理代理商分页查询下级------ */
    public static final String API_GETAGENTLOWERLEVELALLLIST_URL = "agentLowerLevel/getAgentLowerLevelAllList/%s/%s";
    /* ------注释说明----分页查询代理商所有待设置下级---- */
    public static final String API_GETAGENTLOWERLEVELTOBESETLIST_URL = "agentLowerLevel/getAgentLowerLevelToBeSetList/%s/%s";
    /* ------注释说明----待设置代理商忽略操作---- */
    public static final String API_SETAGENTTOBESETIGNORE_URL = "agentLowerLevel/setAgentToBeSetIgnore/%s";
    /* ------注释说明---代理商详情设置数据下发----- */
    public static final String API_GETAGENTDETAILEDIT_URL = "agentLowerLevel/getAgentDetailEdit/%s";

    /**
     * ------注释说明---三方数据模块-----
     **/
    public static final String API_THREE_DATAS_URL = "threeData/threeDataHome";
    /* ------注释说明--获取组织下拉列表------ */
    public static final String API_TEAM_SELECT_LIST_URL = "threeData/teamSelectList";
    /* ------注释说明--三方数据明细------ */
    public static final String API_THREE_DATAS_DETAIL_URL = "threeDataDetail/%s/%s";
    /* ------注释说明--获取直属下级代理商------ */
    public static final String API_THREE_DATAS_AGENT_URL = "threeData/getChildrenAgent/%s/%s";
    /* ------注释说明--三方数据代理商汇总------ */
    public static final String API_THREE_DATAS_AGENT_CENSUS_URL = "threeData/agentCensus";
    /* ------注释说明--三方数据交易趋势------ */
    public static final String API_THREE_DATAS_TREND_URL = "threeData/trend";

    /* ------注释说明---首页->首页获取待办事项显示状态----- */
    public static final String API_GETTODOSTATUS_URL = "index/getTodoStatus";
    /* ------注释说明----本级绑定结算卡下发数据---- */
    public static final String API_GETBINDINGSETTLEMENTCARDBEFOREDATA_URL = "agentLowerLevel/getBindingSettlementCardBeforeData";
    /* ------注释说明----本级绑定结算卡---- */
    public static final String API_SETBINDINGSETTLEMENTCARD_URL = "agentLowerLevel/setBindingSettlementCard";
    /* ------注释说明----修改时-校验业务产品服务费率设置---- */
    public static final String API_CHECKBPSERVICEEDIT_URL = "agentLowerLevel/checkbpServiceEdit/%s";
    /* ------注释说明----新增下级代理商-代理商基础信息校验---- */
    public static final String API_CHECK_AGENT_BASE_URL = "agentLowerLevel/checkAgentBase";
    /* ------注释说明----新增下级代理商-结算卡校验---- */
    public static final String API_CHECK_AGENT_CARD_URL = "agentLowerLevel/checkAgentCard";
    /* ------注释说明----新增下级代理商下发前置数据---- */
    public static final String API_AGENT_DETAIL_URL = "agentLowerLevel/getAgentDetailAdd";
    /* ------注释说明----新增下级代理商-校验业务产品服务费率设置---- */
    public static final String API_CHECK_BP_SERVICE_URL = "agentLowerLevel/checkbpServiceAdd";
    /* ------注释说明----新增下级代理商---- */
    public static final String API_ADD_AGENT_LOWER_LEVEL_URL = "agentLowerLevel/addAgentLowerLevel";
    /* ------注释说明----代理商详情修改服务费率和活动数据保存---- */
    public static final String API_EDITAGENTLOWERLEVEL_URL = "agentLowerLevel/editAgentLowerLevel/%s";
    /* ------注释说明--商户查询的参数------ */
    public static final String API_QUERYMERCHANTPARAMS_URL = "merchant/queryMerchantParams";
    /* ------注释说明---列出代理商代理的业务产品----- */
    public static final String API_LISTBUSINESSPRODUCT_URL = "merchant/listBusinessProduct";
    /* ------注释说明---获取代理商所属产品----- */
    public static final String API_GETAGENTTEAMS_URL = "merchantInfo/getAgentTeams/%s";
    /* ------注释说明---商户查询列表信息----- */
    public static final String API_LISTMERCHANTINFO_URL = "merchant/listMerchantInfo/%s/%s";
    /* ------注释说明---根据商户号查询商户详情----- */
    public static final String API_GETMERCHANTDETAILS_URL = "merchant/getMerchantDetails/%s";
    /* ------注释说明---查询可替换的业务产品信息----- */
    public static final String API_LISTCANREPLACEBPINFO_URL = "merchant/listCanReplaceBpInfo/%s";
    /* ------注释说明---替换业务产品----- */
    public static final String API_REPLACEBUSINESSPRODUCT_URL = "merchant/replaceBusinessProduct";


    /**
     * ------注释说明---机具管理模块-----
     **/
    /* ------注释说明----机具管理-列表---- */
    public static final String API_DEV_MANAGE_LIST_URL = "machinemanage/getAllByCondition";
    /* ------注释说明----机具管理-回收/下发---- */
    public static final String API_DEV_MANAGE_URL = "machinemanage/manageTerminal";
    /* ------注释说明----机具管理-解绑---- */
    public static final String API_DEV_MANAGE_UNBIND_URL = "machinemanage/terminalRelease";
    //进件查询的列表接口
    public static final String API_DEV_MERCHANT_INFO_LIST_URL = "merchantInfo/getMerchantInfoList";
    //GET /merchantInfo/getAgentBusiness/{agent_no}  获取代理商业务产品的接口
    public static final String API_DEV_AGENT_BUSINESS_URL = "merchantInfo/getAgentBusiness/%s";
    //GET /merchantInfo/getHardProduct/{agent_no} 硬件类型 的接口
    public static final String API_DEV_HARDPRODUCT_URL = "merchantInfo/getHardProduct/%s";
    /* ------注释说明----查询欢乐返子类型---- */
    public static final String API_DEV_MANAGE_ACT_TYPE_URL = "machinemanage/getActivityTypes";
    /* ------注释说明---获取图片验证码----- */
    public static final String API_LOADCAPTCHA_URL = "setting/loadCaptcha";
    /* ------注释说明---找回登录密码第一步-验证短信验证码----- */
    public static final String API_SEEKLOGINPWDFIRST_URL = "setting/seekLoginPwdFirst";
    /* ------注释说明--找回登录密码第二步-设置登录密码------ */
    public static final String API_SEEKLOGINPWDSECOND_URL = "setting/seekLoginPwdSecond";
    /* ------注释说明--发送短信验证码------ */
    public static final String API_SENDSMSVALIDATECODE_URL = "setting/sendSmsValidateCode";

}
