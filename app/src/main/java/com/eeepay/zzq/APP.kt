package com.eeepay.zzq

import android.app.Application
import android.content.Context
import android.view.Gravity
import com.eeepay.zzq.base.RxHttpManager
import com.eeepay.zzq.utils.FastSharedPreferencesTools
import com.eeepay.zzq.utils.ToastUtils
import com.eeepay.zzq.utils.Utils

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2020/7/28-17:24
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class APP:Application() {
   public var context:Context? = null
    override fun onCreate() {
        super.onCreate()
        context =this
        Utils.init(this)
        FastSharedPreferencesTools.getInstance().init(this)
        RxHttpManager.init()
        //初始化ToastUtils
        ToastUtils.setGravity(Gravity.CENTER, 0, 0)
    }
}