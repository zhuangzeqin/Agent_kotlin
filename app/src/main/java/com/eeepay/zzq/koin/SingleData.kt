package com.eeepay.zzq.koin

/**
 * 描述：Koin支持调用单例的方法,而且调用起来非常简单,也是在MyApp中的appModule中注入,不过这次注入方式为single
 * 作者：zhuangzeqin
 * 时间: 2021/3/26-17:40
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class SingleData {
    var userName: String? = null
    var age: Int? = null
    fun info() {
        println("用户名:" + userName + "////年龄:" + age)
    }
}