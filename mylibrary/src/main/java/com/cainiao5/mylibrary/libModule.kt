package com.cainiao5.mylibrary

import org.koin.dsl.module

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2021/3/29-12:50
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
object libModule {
    val theLibModule = module {
        //koin支持多个module注入
        single { LibData() }//这边用single方式注入
    }
}