package com.eeepay.zzq.mvp.ui

import android.util.Log
import com.eeepay.zzq.agent_kotlin.R
import com.eeepay.zzq.base.BaseMvpActivity
import com.eeepay.zzq.mvp.presenter.base.BasePresenter
import com.eeepay.zzq.mvp.presenter.base.interfaces.IBaseView
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.*

/**
 * 描述：当不适用mvp 的时候可以继承BaseMvpActivity
 * 作者：zhuangzeqin
 * 时间: 2020/8/6-17:53
 * 邮箱：zzq@eeepay.cn
 * 备注:运行的协程就会随着Activity的销毁而销毁。
 */
class SplashActivity:BaseMvpActivity<BasePresenter<IBaseView>>(), CoroutineScope by MainScope() {
    /**
     * 布局id
     */
    override fun getContentView(): Int = R.layout.activity_splash

    /**
     * 初始化操作
     */
    override fun initView() {
        // //rxLifeScope在rxLife-coroutine库中，需要单独依赖
        //通过launch方法开启一个协程
//        val emptyList = emptyList<String>()
//        rxLifeScope.launch {
//            async { goToSplash() }
//        }

//        val launch = GlobalScope.launch(Dispatchers.Main) {//切换到Main线程执行
////            delay(2000)
//            withContext(Dispatchers.IO)
//            {
//                testSync()
//            }
//            withContext(Dispatchers.IO)
//            {
//                testAsync()
//            }
//            //通过withContext函数实现多线程的协作
////            withContext()
////            goToSplash()
//        }

        goToSplash()

    }

     fun goToSplash() {
         splash_iv.postDelayed( {
            goActivity(this, LoginAct::class.java)
        },2000)
    }
    //runBlocking启动的协程任务会阻断当前线程，直到该协程执行结束。
    fun goToLogin()= runBlocking(Dispatchers.IO) {
        val job = launch {
            delay(2000)//在协程里delay方法作用等同于线程里的sleep, 都是休息一段时间, 但不同的是delay不会阻塞当前线程
            goToSplash()
        }
        job.join()
    }


    /**
     * 初始化点击事件操作
     */
    override fun eventOnClick() {
    }

    /**
     * 初始化数据
     */
    override fun initData() {
    }

    /**
     * 抽象的设置的标题的方法 子类实现
     */
    override fun setTitle(): String? {
        return null
    }

    /**
     * 销毁操作
     */
    override fun onDestroy() {
        super.onDestroy()
//        launch.cancel()
    }


    //异步协程launch+async+async
    fun testAsync(){
        //开启两个异步任务；这里只能用async，因为只有async有await()获取结果，并且异步
        val task1 = async {
            repeat(100){
                Log.d("Task1","当前线程：${Thread.currentThread().name}")
            }
            "AsyncTask1"
        }
        val task2 = async {
            repeat(100){
                Log.d("Task2","当前线程：${Thread.currentThread().name}")
            }
            "AsyncTask2"
        }
        //更新UI或async
        launch(Dispatchers.Unconfined) {
            Log.d("UI1","当前线程：${Thread.currentThread().name}")
            //当前UI线程的协程阻塞，但是不会使UI阻塞
//            text1.text = task1.await()
//            text2.text = task2.await()
            Log.d("UI2","当前值：${task1.await()+task2.await()}")
            Log.d("UI2","当前线程：${Thread.currentThread().name}")
        }
    }
    //同步协程launch+async+async
    fun testSync(){
        launch(Dispatchers.Unconfined) {
            Log.d("UI1","当前线程：${Thread.currentThread().name}")
            val res1 = async {
                repeat(100){
                    Log.d("Task1","当前线程：${Thread.currentThread().name}")
                }
                "AsyncTask1"
            }.await()  //挂起
            val res2 = async {
                repeat(100){
                    Log.d("Task2","当前线程：${Thread.currentThread().name}")
                }
                "AsyncTask2"
            }.await()
            Log.d("UI2","当前线程：${Thread.currentThread().name}")
//            text1.text = res1
//            text2.text = res2
            Log.d("UI2","当前值：${res1}")
            Log.d("UI2","当前值：${res2}")
        }
    }
}