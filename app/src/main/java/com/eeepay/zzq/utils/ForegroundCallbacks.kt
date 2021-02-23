package com.eeepay.zzq.utils

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import java.util.concurrent.CopyOnWriteArrayList


/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2021/2/22-16:24
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class ForegroundCallbacks : Application.ActivityLifecycleCallbacks {
    val CHECK_DELAY: Long = 500
    val TAG = ForegroundCallbacks::class.java.name
    var isWhitelist = false//是否是白名单

    interface Listener {
        fun onBecameForeground()
        fun onBecameBackground()
    }

    private var instance: ForegroundCallbacks? = null
    private var foreground = false
    private var paused = true
    private val handler: Handler = Handler()
    private val listeners: MutableList<Listener> =
        CopyOnWriteArrayList<Listener>()
    private var check: Runnable? = null
    open fun init(application: Application): ForegroundCallbacks? {
        if (instance == null) {
            instance = ForegroundCallbacks()
            application.registerActivityLifecycleCallbacks(instance)
        }
        return instance
    }

    operator fun get(application: Application): ForegroundCallbacks? {
        if (instance == null) {
            init(application)
        }
        return instance
    }

    operator fun get(ctx: Context): ForegroundCallbacks? {
        if (instance == null) {
            val appCtx: Context = ctx.getApplicationContext()
            if (appCtx is Application) {
                init(appCtx as Application)
            }
            throw IllegalStateException(
                "Foreground is not initialised and " +
                        "cannot obtain the Application object"
            )
        }
        return instance
    }

    fun get(): ForegroundCallbacks? {
        checkNotNull(instance) {
            "Foreground is not initialised - invoke " +
                    "at least once with parameterised init/get"
        }
        return instance
    }

    fun isForeground(): Boolean {
        return foreground
    }

    fun isBackground(): Boolean {
        return !foreground
    }

    fun addListener(listener: Listener) {
        listeners.add(listener)
    }

    fun removeListener(listener: Listener?) {
        listeners.remove(listener)
    }

    override fun onActivityResumed(activity: Activity?) {
        paused = false
        val wasBackground = !foreground
        foreground = true
        if (check != null) handler.removeCallbacks(check)
        if (wasBackground) {
//            L.d("went foreground")
            for (l in listeners) {
                try {
                    l.onBecameForeground()//前台
                } catch (exc: Exception) {
//                    L.d("Listener threw exception!:$exc")
                }
            }
        } else {
//            L.d("still foreground")
        }
    }

    override fun onActivityPaused(activity: Activity?) {
        paused = true
        Log.d("APP", activity.hashCode().toString());
        isWhitelist = WhitleActivityStackManager.getInstance().contains(activity)
        Log.d("APP", isWhitelist.toString())
        if (check != null) handler.removeCallbacks(check)
        handler.postDelayed(Runnable {
            if (foreground && paused) {
                foreground = false
//                L.d("went background")
                for (l in listeners) {
                    try {
                        if (!isWhitelist) {
                            Log.d("APP","不是白名单")
                            l.onBecameBackground()
                        }
                        else {
                            Log.d("APP","白名单")
                            WhitleActivityStackManager.getInstance().pop()//出栈
                        }

                    } catch (exc: Exception) {
//                        L.d("Listener threw exception!:$exc")
                    }
                }
            } else {
//                L.d("still foreground")
            }
        }.also { check = it }, CHECK_DELAY)
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {}
    override fun onActivityStarted(activity: Activity?) {}
    override fun onActivityStopped(activity: Activity?) {}
    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {}
    override fun onActivityDestroyed(activity: Activity?) {}

}