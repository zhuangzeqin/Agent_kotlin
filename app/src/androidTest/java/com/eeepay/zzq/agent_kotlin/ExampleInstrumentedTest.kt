package com.eeepay.zzq.agent_kotlin

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.eeepay.zzq.by.IDataPersistence
import com.eeepay.zzq.by.SQLData
import org.junit.Test
import org.junit.runner.RunWith
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

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

//        for (i  in 1..10 step 2)
//        {
//            println("$i")
//        }
//        testRun()
//        val listOf = listOf<String>("1", "2")
//        //由于当条件为 true 时，最终结果返回的是 this，因此可以进行链式操作：
//        //takeIf  :   接收一个判断条件表达式，如果判断表达式为true则返回 对象本身，false返回 null
//       // takeUnless:  与takeIf相反,  如果判断表达式为true则返回 null，false返回 对象本身
//        listOf.takeIf { listOf.isEmpty()}?.let { println("${it.size}") }
//        listOf.takeUnless { listOf.isEmpty()}?.let { println("${it.size}") }

//        val stringToDate = getStringToDate("8")
//        println("stringToDate = ${stringToDate}")


//        val tCar = CarFactory(CarType.BCar)
//        val nCar = CarFactory(CarType.DCar)
//        println("traditional car use ${tCar.name}")
//        println("new-energy car use ${nCar.name}")

//        val myDB = MyData(SharedPreferencesData())
//        myDB.addData()
//        myDB.delData()
//        myDB.queryData()

        //再简单一点，如果你不用传入多种不同的实例，
        // 可以在构造方法中去掉默认参数，
        // 直接在by关键字后面添加具体的接口实现
        class MyData : IDataPersistence by SQLData()
        MyData().addData()
        MyData().delData()
        MyData().queryData()
    }

    //将字符串转换为时间戳
    fun getStringToDate(time: String?): Long {
        val sf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        var date = Date()
        try {
            date = sf.parse(time)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date.getTime()
    }


    //不使用Sequences序列，使用普通的集合操作
    fun computeRunTime(action: (() -> Unit)?) {
        val startTime = System.currentTimeMillis()
        action?.invoke()
        println("the code run time is ${System.currentTimeMillis() - startTime}")
        startTime.takeIf { it.equals("123") }

    }

    fun testRun() {
        var str = "aaaaa"
        run {
             str = "bbbbbb"
            println("zzq:"+str) // I am xys
        }
        println(str)  // I am zj
    }
}
