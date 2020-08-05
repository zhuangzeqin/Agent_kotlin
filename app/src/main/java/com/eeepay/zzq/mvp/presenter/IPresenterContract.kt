package com.eeepay.zzq.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import rxhttp.wrapper.annotations.NonNull

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2020/8/5-14:10
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
 interface IPresenterContract {
    /**
     * ------注释说明--登录------
     */
    interface ILoginPresenter {
        fun login(@NonNull owner: LifecycleOwner,@NonNull username: String, @NonNull password: String)
    }
}