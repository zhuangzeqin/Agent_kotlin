package com.eeepay.zzq.lib_common.singleton

/**
 * 描述：枚举单例实现
 * 作者：zhuangzeqin
 * 时间: 2020/7/28-14:08
 * 邮箱：zzq@eeepay.cn
 * 备注:KEnumSingleton.INSTANCE.doSomeThing()
 */
enum class KEnumSingleton {
    INSTANCE;

    fun doSomeThing(){
        print("do some thing")
    }
}