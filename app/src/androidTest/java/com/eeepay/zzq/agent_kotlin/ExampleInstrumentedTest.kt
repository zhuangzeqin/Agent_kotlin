package com.eeepay.zzq.agent_kotlin

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun useAppContext() {
//        var i :Int?
        // Context of the app under test.
        //val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        //assertEquals("com.eeepay.zzq.agent_kotlin", appContext.packageName)
//        computeRunTime {
//            (0..10000000).asSequence()
//                .map { it + 1 }
//                .filter { it % 2 == 0 }
//                .count { it < 10 }
//                .run {
//                    println("by using list way, result is : $this")
//                }
//        }

//        println("ColorEnum.RED.color${i!!.toString()}")

        for (i  in 1..10 step 2)
        {
            println("$i")
        }
    }

    //不使用Sequences序列，使用普通的集合操作
    fun computeRunTime(action: (() -> Unit)?) {
        val startTime = System.currentTimeMillis()
        action?.invoke()
        println("the code run time is ${System.currentTimeMillis() - startTime}")
    }
}
