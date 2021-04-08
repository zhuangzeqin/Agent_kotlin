package com.eeepay.zzq.koin

import android.util.Log
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject

/**
 * 描述：实现KoinComponent,普通类中使用注入对象
 * 作者：zhuangzeqin
 * 时间: 2021/3/29-12:45
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
@KoinApiExtension
class CompontData : KoinComponent {
    val appD1 by inject<AppData>()//懒加载模式
    val appD2 = get<AppData>()//非懒加载模式
    fun priInfo() {
        Log.d("CompontData","CompontData中appD1地址:" + appD1.hashCode() + "////appD2地址:" + appD2.hashCode())
    }
}