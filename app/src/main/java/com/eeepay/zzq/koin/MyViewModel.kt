package com.eeepay.zzq.koin

import androidx.lifecycle.ViewModel

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2021/3/26-17:57
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class MyViewModel:ViewModel() {
    var NumData :Int = 0
    /**
     * This method will be called when this ViewModel is no longer used and will be destroyed.
     *
     *
     * It is useful when ViewModel observes some data and you need to clear this subscription to
     * prevent a leak of this ViewModel.
     */
    override fun onCleared() {
         println("销毁的函数")
        super.onCleared()
    }
}