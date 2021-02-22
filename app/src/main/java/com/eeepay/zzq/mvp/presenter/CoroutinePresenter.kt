package com.eeepay.zzq.mvp.presenter

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2021/2/20-17:30
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class CoroutinePresenter() {
    //多数情况下，Presenter 的方法也会被 Activity 直接调用，因此也可以将 Presenter 的方法生命成 suspend 方法，然后用 coroutineScope 嵌套作用域，这样 MainScope 被取消后，嵌套的子作用域一样也会被取消，进而达到取消全部子协程的目的：
   suspend fun getUserData()=coroutineScope{
       launch {
           delay(3000L)
           println("World!")
       }
        println("aaaa!")
   }
}