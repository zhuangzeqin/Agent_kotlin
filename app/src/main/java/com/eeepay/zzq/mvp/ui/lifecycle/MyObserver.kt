package com.eeepay.zzq.mvp.ui.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent


/**
 * 描述：LifecycleObserver用于标记一个类是生命周期观察者
 * 作者：zhuangzeqin
 * 时间: 2021/2/25-12:01
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class MyObserver:LifecycleObserver {
    val TAG = MyObserver::class.java.simpleName
    //connectListener()会在ON_RESUME时执行
    @OnLifecycleEvent(value = Lifecycle.Event.ON_RESUME)
    fun connect() {
        Log.i(TAG, "connect: ")
    }
    //disconnectListener()会在ON_PAUSE时执行。
    @OnLifecycleEvent(value = Lifecycle.Event.ON_PAUSE)
    fun disConnect() {
        Log.i(TAG, "disConnect: ")
    }
    //当然注解中的value你也写成其它 你关注的任何一个生命周期，例如Lifecycle.Event.ON_DESTROY。

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreated(owner: LifecycleOwner?) {
//            owner.getLifecycle().addObserver(anotherObserver);
//            owner.getLifecycle().getCurrentState();
    }

    /**
     * onAny是每个生命周期都会调用到一次。其他的话，就是正常调用。
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onAny(
        owner: LifecycleOwner?,
        event: Lifecycle.Event?
    ) {
//            event.name()
        Log.i(TAG, "event.name(): "+event!!.name)
    }
}