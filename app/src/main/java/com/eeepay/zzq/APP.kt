package com.eeepay.zzq

import android.app.Application
import android.content.Context
import android.view.Gravity
import androidx.lifecycle.ProcessLifecycleOwner
import com.eeepay.zzq.base.RxHttpManager
import com.eeepay.zzq.mvp.ui.lifecycle.ApplicationLifecycleObserver
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
class APP : Application() {
    val tag = APP::class.java.simpleName
    //lateinit 不能用来修饰基本数据类型，因为基本类型的属性在类加载后的准备阶段都会被初始化为默认值
    //lateinit不能修饰val变量，只能修饰可变的属性
    lateinit var context: Context
    override fun onCreate() {
        super.onCreate()
        context = this
        Utils.init(this)
        FastSharedPreferencesTools.getInstance().init(this)
        RxHttpManager.init()
        //初始化ToastUtils
        ToastUtils.setGravity(Gravity.CENTER, 0, 0)
        //注册App生命周期观察者
        ProcessLifecycleOwner.get().getLifecycle().addObserver(ApplicationLifecycleObserver())

//        ForegroundCallbacks().get(this)
//        ForegroundCallbacks().get(this)!!.addListener(object : ForegroundCallbacks.Listener {
//                override fun onBecameForeground() {
////                    L.d("当前程序切换到前台")
//                    Log.d(tag,"${"当前程序切换到前台"}")
//                }
//
//                override fun onBecameBackground() {
////                    L.d("当前程序切换到后台")
//                    Log.d(tag,"${"当前程序切换到后台"}")
//                }
//            })

    }
}