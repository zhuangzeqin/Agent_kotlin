package com.eeepay.zzq.mvp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eeepay.zzq.agent_kotlin.R
import com.eeepay.zzq.mvp.presenter.CoroutinePresenter
import com.eeepay.zzq.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_coroutine.*
import kotlinx.coroutines.*

/*
  * ================================================
  * 描述：协程是一种并发设计模式，可以在 Android 平台上使用它来简化异步执行的代码
  * kotlin 协程相关知识点回归
  * 作者：zhuangzeqin
  * 时间: 2021/2/20-14:33
  * 邮箱：zzq@eeepay.cn
  * 备注:
  * ================================================
  */
class CoroutineActivity : AppCompatActivity() {
    val mainScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)
        //在接收目的地的代码中，请使用 getArguments() 方法来检索 Bundle 并使用其内容：
        val amount= intent.extras!!.getInt("amount", 0)
        ToastUtils.showShort("" + amount)
        //runBlocking 顶层函数(在Kotlin中，函数站在了类的位置，我们可以直接把函数放在代码文件的顶层，让它不从属于任何类)
        //方法一通常适用于单元测试的场景，而业务开发中不会用到这种方法，因为它是线程阻塞的
        btn_runBlocking.setOnClickListener {
            goRunBlocking()
        }

        btn_GlobalScope.setOnClickListener {
           this.goGlobalScope()
        }

        btn_CoroutineScope.setOnClickListener {
//            goCoroutine()
            mainScope.launch {
                getrequest()
            }

        }
    }

    fun goRunBlocking() {
        /**
         * runBlocking 方法，可以在普通的阻塞线程中开启一个新的协程以用于运行挂起函数，并且可以在协程中通过调用 launch 方法，开启一个子协程，用于运行后台阻塞任务。
         */
        runBlocking {
            //launch 方法，开启一个子协程，用于运行后台阻塞任务
            launch {
                // 在后台启动一个新的协程并继续
                delay(3000L)
                println("World!")
            }
        }
        println("Hello,")
    }

    fun goGlobalScope() {
//        方法二，使用 GlobalScope 单例对象
//            可以直接调用 launch 开启协程
        // runBlocking 的区别在于不会阻塞线程。
        // 但在 Android 开发中同样不推荐这种用法，因为它的生命周期会和 app 一致，且不能取消
      val job= GlobalScope.launch {
            // 在后台启动一个新的协程并继续
            delay(3000L)
            println("World!")
        }
        println("Hello,")
        job.cancel()//取消一个协程
    }

    fun goCoroutine() {
        //1**********************************************
        //协程最常用的功能是并发，而并发的典型场景就是多线程。可以使用 Dispatchers.IO 参数把任务切到 IO 线程执行
        //也可以使用 Dispatchers.Main 参数切换到主线程
        CoroutineScope(Dispatchers.IO).launch {
            // 在后台启动一个新的协程并继续
            delay(3000L)
            println("World!")
        }
        println("Hello,")
        //2****************************************************
        CoroutineScope(Dispatchers.Main).launch {   // 在主线程开启协程
//            val user = api.getUser() // IO 线程执行网络请求
//            nameTv.text = user.name  // 主线程更新 UI
        }

        //3****************************************************
        CoroutineScope(Dispatchers.Main).launch {       // 开始协程：主线程
//            val token = api.getToken()                  // 网络请求：IO 线程
//            val user = api.getUser(token)               // 网络请求：IO 线程
//            nameTv.text = user.name                     // 更新 UI：主线程
        }
        //4****************************************************
        CoroutineScope(Dispatchers.Main).launch {
            // async 函数之后再讲
            val avatar = async { /*api.getAvatar(user)*/ }    // 获取用户头像
            val logo = async { /*api.getCompanyLogo(user)*/ } // 获取用户所在公司的 logo
//            val merged = suspendingMerge(avatar, logo)    // 合并结果
//            show(merged) // 更新 UI
        }

        /**
         * withContext 。这个函数可以切换到指定的线程，并在闭包内的逻辑执行结束之后，自动把线程切回去继续执行
         */
        CoroutineScope(Dispatchers.Main).launch  {      // 在 UI 线程开始
            val image = withContext(Dispatchers.IO) {  //  切换到 IO 线程，并在执行完成后切回 UI 线程
//                getImage(imageId)                      //  将会运行在 IO 线程
            }
            val image2 = withContext(Dispatchers.IO) {  //  切换到 IO 线程，并在执行完成后切回 UI 线程
//                getImage(imageId)                      //  将会运行在 IO 线程
            }
            val image3 = withContext(Dispatchers.IO) {  //  切换到 IO 线程，并在执行完成后切回 UI 线程
//                getImage(imageId)                      //  将会运行在 IO 线程
            }
//            avatarIv.setImageBitmap(image)             //  回到 UI 线程更新 UI
        }


        CoroutineScope(Dispatchers.Main).launch {              // 👈 在 UI 线程开始
            val image = getImage(1)
//            avatarIv.setImageBitmap(image)     // 👈 执行结束后，自动切换回 UI 线程
        }

        MainScope()
        CoroutineScope(Dispatchers.Main).launch {
            //                      👇  async 函数启动新的协程
            val avatar = async { /*api.getAvatar(user)*/ }    // 获取用户头像
            val logo = async { /*api.getCompanyLogo(user) */} // 获取用户所在公司的 logo
            //            获取返回值
            avatar.await()
            logo.await()
//            show(avatar.await(), logo.await())                     // 更新 UI
        }

    }
//   withContext 是一个 suspend 函数，它需要在协程或者是另一个 suspend 函数中调用。
   suspend fun getImage(imageId: Int) = withContext(Dispatchers.IO) {
        //
    }

    suspend fun getrequest(): Unit {
        CoroutinePresenter().getUserData()
    }

    override fun onDestroy() {
        mainScope.cancel()
        super.onDestroy()
    }
}