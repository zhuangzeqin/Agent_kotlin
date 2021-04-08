package com.eeepay.zzq.koin

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2021/3/26-16:34
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class UserInfo {
    var name:String? = "zhuangzeqin"
    var age:Int?=32
    var address:String? = "深圳市白石龙一区"
    fun sayHello() {
         println(this.hashCode().toString())
         println("${name}+${age}+${address}")
    }
}