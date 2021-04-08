package com.eeepay.zzq.koin

import android.view.View

/**
 * 描述：声明进样参数---构造参数从外面传入
 * 作者：zhuangzeqin
 * 时间: 2021/3/29-12:37
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class ViewData(val view: View) {
    fun prinId() {
         println("aa${view.id}")
    }
}