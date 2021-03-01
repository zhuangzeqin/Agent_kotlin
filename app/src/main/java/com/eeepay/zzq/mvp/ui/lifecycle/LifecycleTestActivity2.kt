package com.eeepay.zzq.mvp.ui.lifecycle

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.eeepay.zzq.agent_kotlin.R

/*
  * ================================================
  * 描述：lifecycleRegistry实例则是在onCreate创建，
  * 并且在各个生命周期内调用markState()方法完成生命周期事件的传递。这就完成了LifecycleOwner的自定义，
  * 也即LifecycleTestActivity2变成了LifecycleOwner，然后就可以和 实现了LifecycleObserver的组件配合使用了。
  * 作者：zhuangzeqin
  * 时间: 2021/2/25-11:53
  * 邮箱：zzq@eeepay.cn
  * 备注:
  * ================================================
  */
class LifecycleTestActivity2 : Activity(),LifecycleOwner {
    private val TAG= LifecycleTestActivity2::class.java.simpleName
    lateinit var  lifecycleRegistry:LifecycleRegistry
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle_test)
        lifecycleRegistry= LifecycleRegistry(this)
        lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED)
        //Lifecycle 生命周期
        lifecycleRegistry.addObserver(MyObserver())
        Log.i(TAG, "onCreate: ")
    }

    override fun onResume() {
        super.onResume()
        lifecycleRegistry.setCurrentState(Lifecycle.State.RESUMED)
        Log.i(TAG, "onResume: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleRegistry.setCurrentState(Lifecycle.State.DESTROYED)
    }

    /**
     * Returns the Lifecycle of the provider.
     *
     * @return The lifecycle of the provider.
     */
    override fun getLifecycle(): Lifecycle {
      return lifecycleRegistry
    }
}