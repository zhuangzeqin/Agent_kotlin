package com.eeepay.zzq.agent_kotlin

import org.junit.Test
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
//@RunWith(AndroidJUnit4::class)
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
//        class MyData : IDataPersistence by SQLData()
//        MyData().addData()
//        MyData().delData()
//        MyData().queryData()


//        SimpleKotlinDemo().main("")multiplyByTwo
        println("start======")
//        multiplyByTwo(5) {
//            //做一下其它的逻辑操作
//            val sql  = 5//比如我做了一个逻辑操作； 去数据港查询 得到一个数值5
//            val temper = it*sql
//             println("$temper")
//            //如果lamba 里面有返回的记得@语句； 否则会影响程序的执行流程
////            return@multiplyByTwo
//            return@multiplyByTwo
//        }
      /*  LoginLogicImpl.register(
            { data ->
                println("${data.toString()}")
            },
            { code, message ->
                println("${code}+${message}")
            }

        )

        LoginLogicImpl.register2<String> {
            OnNext { data ->
                println("aaa${data}")
            }
            OnError { code, message ->
                println("aaa${code}" + "aaa${message}")
            }

        }*/

      /* val str = LoginLogicImpl.register3 { i, s ->
           println("aaa${i}" + "aaa${s}")
          var string = "aaa${i}" + "aaa${s}"
           "default"
        }
        println(str.toString())*/



        /**
         * 要从arraylist中删除重复项，我们也可以使用java 8 stream api。使用steam的distinct()方法返回一个由不同数据组成的流，通过对象的equals（）方法进行比较。
        收集所有区域数据List使用Collectors.toList()。
        Java程序，用于在不使用Set的情况下从java中的arraylist中删除重复项。
         */
       /* val numbersList = ArrayList(listOf(1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8))
        System.out.println(numbersList)
        val listWithoutDuplicates: List<Int> =
            numbersList.stream().distinct().collect(Collectors.toList())
        println(listWithoutDuplicates)*/

        println("end======")
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
            println("zzq:" + str) // I am xys
        }
        println(str)  // I am zj
    }
}
