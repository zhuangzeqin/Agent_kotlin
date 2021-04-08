package com.eeepay.zzq.koin

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2021/3/29-10:26
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class WeatherData(val normalData: NormalData) {
    fun printData(string: String) {
        normalData.printInfo(string)
    }
}