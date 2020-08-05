package com.eeepay.zzq.utils

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2020/8/5-17:32
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
object ToolUtils {
    fun <T1, T2> ifNotNull(value1: T1?, value2: T2?, bothNotNull: (T1, T2) -> (Unit)) {
        if (value1 != null && value2 != null) {
            bothNotNull(value1, value2)
        }
    }
}