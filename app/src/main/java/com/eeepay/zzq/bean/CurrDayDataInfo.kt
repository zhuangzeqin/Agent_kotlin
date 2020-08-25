package com.eeepay.zzq.bean

import java.io.Serializable

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2020/8/25-11:17
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class CurrDayDataInfo : Serializable {
    data class dataBean(
        val dayAddMerCount: Int,
        val dayAddMerOrderAmount: Int,
        val dayIncome: String,
        val dayOrderAmount: Int,
        val dayOrderCount: Int
    ) : Serializable
}