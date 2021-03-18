package com.eeepay.zzq.kotlin

import androidx.collection.SimpleArrayMap
import kotlin.system.measureTimeMillis

/**
 * 描述：kotlin 语法快速掌握
 * 作者：zhuangzeqin
 * 时间: 2021/3/4-9:56
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class SimpleKotlinDemo {
    //region 1 变量
    var a: Int = 0
    var b = 5
    val n = 3  //相当于java final 关键字
    var ss: String? = null
    lateinit var str: String//不能null 必须是引用类型
    val sss: String by lazy { "延迟初始化" }

    //伴生对象--companion object 修饰为伴生对象,伴生对象在类中只能存在一个，类似于java中的静态方法 Java 中使用类访问静态成员，静态方法。
    companion object {
        //常量定义
        const val number = 3.14
    }

    //endregion
    //region 2 函数
    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    //参数可以默认值；如果不传就是1
    fun div(x: Int, y: Float = 1F): Unit {
    }


    //函数也可以是一个表达式返回
    fun move(vararg a: Int, y: Float = 1F) = run {
        a[0] + y + a[1]

    }
    //internal 修饰类的方法，表示这个类方法只适合当前module使用，如果其他module使用的话，会找不到这个internal方法或者报错
    internal fun appleInternalLog(){
         println("${123}")
    }


    //endregion
    //region for when 语法
    fun main(args:String): Unit {
        //统计方法的耗时
        val measureTimeMillis = measureTimeMillis {
            //for 1 遍历出0-10
            for (i in 0..10) {
                println(i)
            }
            //排除10 遍历到0-9
            for (i in 0 until 10) {
                println(i)
            }
            //步数为2 遍历输出 0-2-4-6-8-10
            for (i: Int in 0..10 step 2) {
                println("${i}")
            }
            /**
             * 倒叙输出每次步数为2 输出 10-8-6-4-2-0
             */
            for (i in 10 downTo 0 step 2) {
                println("${i}")
                gradeStudent(i);
            }
        }
         println("函数总耗时：${measureTimeMillis}")
        getruncatch()

    }
    //when 函数语法糖  在kotlin中when表达式类似于Java中的switch语句。
    //10分满分 9分干的不错 8分还可以 7分还需努力 6分刚好及格 其他(需要加油)
    private fun gradeStudent(score:Int):String{
        return when (score) {
            10 -> "满分 ，棒棒的"
            9 -> "干的不错"
            8 -> "还可以"
            7 -> "还需努力"
            6 -> "刚好及格"
            else -> "需要加油"
        }
    }
    //endregion

    //region 可折叠的注释说明
    fun getruncatch(): Unit {
        /**
         * 其中 getOrNull 返回的是 runCatching 中处理的结果，是一个可空类型的，比如这里我返回的是 CollectionBean 类
        getOrDefault 返回的是一个不可空的类型，调用的时候需要传递默认值，为 null 的时候会返回默认值
        getOrThrow 未发生的异常返回的是 runCatching 中处理的结果，如果 runCatching 中发生了异常，调用这个 API 会抛出异常
        getOrElse 返回处理的结果，或者是异常自己处理
         */
        kotlin.runCatching {
            gradeStudent(0)
        }.onSuccess {
             println(it)
            val simpleArrayMap = SimpleArrayMap<String, String>()
            simpleArrayMap.put("str",it)
        }.onFailure {
             println("${it.printStackTrace()}")
        }.getOrDefault("获取失败")
    }
    //endregion

}