package com.eeepay.zzq.mvp.ui.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent




/**
 * 描述：/**
 * Application生命周期观察，提供整个应用进程的生命周期
 *
 * Lifecycle.Event.ON_CREATE只会分发一次，Lifecycle.Event.ON_DESTROY不会被分发。
 *
 * 第一个Activity进入时，ProcessLifecycleOwner将分派Lifecycle.Event.ON_START, Lifecycle.Event.ON_RESUME。
 * 而Lifecycle.Event.ON_PAUSE, Lifecycle.Event.ON_STOP，将在最后一个Activit退出后后延迟分发。如果由于配置更改而销毁并重新创建活动，则此延迟足以保证ProcessLifecycleOwner不会发送任何事件。
 *
 * 作用：监听应用程序进入前台或后台
*/
 * 作者：zhuangzeqin
 * 时间: 2021/2/25-15:15
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class ApplicationLifecycleObserver:LifecycleObserver {
    val tag = ApplicationLifecycleObserver::class.java.simpleName
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onAppForeground() {
        Log.d(tag,"${"当前程序切换到前台"}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onAppBackground() {
        Log.d(tag,"${"当前程序切换到后台"}")
    }
}