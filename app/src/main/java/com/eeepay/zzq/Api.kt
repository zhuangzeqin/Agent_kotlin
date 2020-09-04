package com.eeepay.zzq

/**
 * 描述：静态常量类
 * 作者：zhuangzeqin
 * 时间: 2020/8/4-14:41
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class Api {
    companion object{
        const val API_LOGIN_URL = "login"//登录
        const val API_GETPUBLICDATA_URL = "publicData/getPublicData"//公共数据接口
        const val API_LOADCURRDAYDATA_URL = "index/loadCurrDayData"//首页->今日业绩接口
        const val API_QUERYMERCHANTPARAMS_URL = "merchant/queryMerchantParams"//首页->查询商户需要的信息接口
        const val API_CHECKVERSION_URL = "http://cs-ys-agentapi2.51ydmw.com/agentApi2/checkVersion"//检查版本升级
    }
}