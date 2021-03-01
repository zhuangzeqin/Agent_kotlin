package com.eeepay.zzq.mvp.ui.lifecycle

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.eeepay.zzq.agent_kotlin.R

/*
  * ================================================
  * 描述：class describe
  * 作者：zhuangzeqin
  * 时间: 2021/2/25-11:53
  * 邮箱：zzq@eeepay.cn
  * 备注:
  * ================================================
  */
class LifecycleTestActivity : AppCompatActivity() {
    private val TAG= LifecycleTestActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle_test)

        //Lifecycle 生命周期
        lifecycle.addObserver(MyObserver())
        Log.i(TAG, "onCreate: ")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause: ")
    }
}