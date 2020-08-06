package com.eeepay.zzq.bean

import java.io.Serializable

/**
 * 描述：定义的公共的实体bean
 * 作者：zhuangzeqin
 * 时间: 2020/8/6-17:01
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class PubDataktInfo : Serializable {

    data class PubDataBean(
        val code: Int,
        val count: Int,
        val `data`: Data,
        val message: String,
        val success: Boolean
    ) : Serializable

    data class Data(
        val funCode030: Boolean,
        val funCode044: Boolean,
        val funCode052: Boolean,
        val hotLinePhone: String,
        val hotLinePhoneTip: String,
        val showHotLinePhone: Boolean,
        val toBeSetListSize: Int
    ) : Serializable

}