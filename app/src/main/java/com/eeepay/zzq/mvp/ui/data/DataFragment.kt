package com.eeepay.zzq.mvp.ui.data

import android.os.Bundle
import com.eeepay.zzq.UrlConfitkt
import com.eeepay.zzq.agent_kotlin.R
import com.eeepay.zzq.base.BaseMvpFragment
import com.eeepay.zzq.mvp.presenter.base.BasePresenter
import com.eeepay.zzq.mvp.presenter.base.interfaces.IBaseView
import kotlinx.android.synthetic.main.fragment_coroutines.*
import kotlinx.android.synthetic.main.fragment_home.home_txt_msg
import kotlinx.coroutines.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * 描述：Kotlin 协程中，
 * 关于 runBlocking， launch ，withContext ，async，doAsync 之间的简单区别
 * 作者：zhuangzeqin
 * 时间: 2020/8/11-16:34
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class DataFragment : BaseMvpFragment<BasePresenter<IBaseView>>() {
    /**
     *  创建实例
     */
    fun newInstance(title: String): DataFragment {
        val bundle = Bundle()
        bundle.putString(UrlConfitkt.BUNDLE_TITLE, title)
        val fragment = DataFragment()
        fragment.arguments = bundle
        return fragment
    }

    /**
     * 获取布局
     */
    override fun getLayoutId(): Int = R.layout.fragment_coroutines

    /**
     * 初始化
     */
    override fun init() {
        /* ------注释说明--- lauch 与 runBlocking都能在全局开启一个协程，但 lauch 是非阻塞的 而 runBlocking 是阻塞的----- */
        val title = mBundle!!.getString(UrlConfitkt.BUNDLE_TITLE)
        home_txt_msg.text = title
        btn_lauch.setOnClickListener {
            //lauch 是非阻塞的  通过CoroutineScope.launch开启一个协程，协程体里的任务时就会先挂起（suspend），让CoroutineScope.launch后面的代码继续执行，直到协程体内的方法执行完成再自动切回来所在的上下文回调结果。
            CoroutineScope(Dispatchers.Main).launch {
                delay(500)
                println("1.执行CoroutineScope.... [当前线程为：${Thread.currentThread().name}]")
            }
            println("2.执行CoroutineScope.... [当前线程为：${Thread.currentThread().name}]")
        }
        /* ------注释说明-- runBlocking里的任务如果是非常耗时的操作时，会一直阻塞当前线程，在实际开发中很少会用到runBlocking。 由于runBlocking 接收的 lambda 代表着一个 CoroutineScope，所以 runBlocking 协程体内可继续通过launch来继续创建一个协程，避免了lauch所在的线程已经运行结束而切不回来的情况。------ */
        btn_runBlocking.setOnClickListener {
            runBlocking {
                delay(500)
                println("1.执行CoroutineScope.... [当前线程为：${Thread.currentThread().name}]")
            }
            println("2.执行CoroutineScope.... [当前线程为：${Thread.currentThread().name}]")
        }
        /**
         * withContext 与 async 都可以返回耗时任务的执行结果。
         * 一般来说，多个 withContext 任务是串行的， 且withContext 可直接返回耗时任务的结果。
         * 多个 async 任务是并行的，async 返回的是一个Deferred<T>，需要调用其await()方法获取结果。
         */
        btn_withContext.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                /**
                 * 多个withConext是串行执行，如上代码执行顺序为先执行task1再执行task2，共耗时两个任务的所需时间的总和。这是因为withConext是个 suspend 函数，当运行到 withConext 时所在的协程就会挂起，直到withConext执行完成后再执行下面的方法。所以withConext可以用在一个请求结果依赖另一个请求结果的这种情况。
                 */
                val time1 = System.currentTimeMillis()
                val task1 = withContext(Dispatchers.IO) {
                    delay(2000)
                    println("1.执行CoroutineScope.... [当前线程为：${Thread.currentThread().name}]")
                    ""
                }
                val task2 = withContext(Dispatchers.IO)
                {
                    delay(1000)
                    println("2.执行CoroutineScope.... [当前线程为：${Thread.currentThread().name}]")
                    ""
                }
                println("task1 = $task1  , task2 = $task2 , 耗时 ${System.currentTimeMillis()-time1} ms  [当前线程为：${Thread.currentThread().name}]")

            }
        }
        /**
         * 如果同时处理多个耗时任务，且这几个任务都无相互依赖时，可以使用 async ...  await() 来处理，将上面的例子改为 async 来实现如下 ：
         */
        btn_async.setOnClickListener {
            GlobalScope.launch {
                val time1 = System.currentTimeMillis()
                val task1 = async {
                    delay(2000)
                    println("1.执行CoroutineScope.... [当前线程为：${Thread.currentThread().name}]")
                    "111"
                }

                val task2= async {
                    delay(1000)
                    println("2.执行CoroutineScope.... [当前线程为：${Thread.currentThread().name}]")
                    "222"
                }
                println("task1 = ${task1.await()}  , task2 = ${task2.await()} , 耗时 ${System.currentTimeMillis()-time1} ms  [当前线程为：${Thread.currentThread().name}]")
            }
        }
        /* ------注释说明---await() 只有在 async 未执行完成返回结果时，才会挂起协程。若 async 已经有结果了，await() 则直接获取其结果并赋值给变量，此时不会挂起协程。----- */
        btn_async2.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val time1 = System.currentTimeMillis()
                /* ------注释说明---await() 只有在 async 未执行完成返回结果时，才会挂起协程。若 async 已经有结果了，await() 则直接获取其结果并赋值给变量，此时不会挂起协程。----- */
                val task1 = async {
                    delay(2000)
                    println("1.执行CoroutineScope.... [当前线程为：${Thread.currentThread().name}]")
                    "111"
                }.await()

                val task2= async {
                    delay(1000)
                    println("2.执行CoroutineScope.... [当前线程为：${Thread.currentThread().name}]")
                    "222"
                }.await()
                println("task1 = ${task1}  , task2 = ${task2} , 耗时 ${System.currentTimeMillis()-time1} ms  [当前线程为：${Thread.currentThread().name}]")

            }

        }

        btn_doAsync.setOnClickListener {
            // doAsync 的源码它的实现都是基于Java的 Future  类进行异步处理和通过Handler进行线程切换 ，从而封装的一个扩展函数方便线程切换。
            //doAsync创建了一个线程池，所有耗时方法可以放在 doAsync 上，等获取结果后可以通过 uiThread { } 来切换会主线程。
            // 使用 doAsync 进行线程切换让代码看上去像同步的方式实现异步的请求。
                doAsync {
                    println("1.执行CoroutineScope.... [当前线程为：${Thread.currentThread().name}]")
//                    val sum = (1..5).asFlow()
//                        .map { it * it } // 数字 1 至 5 的平方
//                        .reduce { a, b -> a + b } // 求和（末端操作符）
//                    println(sum)
                    uiThread {
                        println("2.执行CoroutineScope.... [当前线程为：${Thread.currentThread().name}]")
                    }
                }
        }
    }
}