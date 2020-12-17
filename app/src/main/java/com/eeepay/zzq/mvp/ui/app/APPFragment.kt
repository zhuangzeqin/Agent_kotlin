package com.eeepay.zzq.mvp.ui.app

import android.os.Bundle
import com.eeepay.zzq.UrlConfitkt
import com.eeepay.zzq.agent_kotlin.R
import com.eeepay.zzq.base.BaseMvpFragment
import com.eeepay.zzq.mvp.presenter.base.BasePresenter
import com.eeepay.zzq.mvp.presenter.base.interfaces.IBaseView
import kotlinx.android.synthetic.main.fragment_app.*
import kotlinx.android.synthetic.main.fragment_home.home_txt_msg

/**
 * 描述：应用
 * 作者：zhuangzeqin
 * 时间: 2020/8/11-16:34
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class APPFragment : BaseMvpFragment<BasePresenter<IBaseView>>() {
    /**
     *  创建实例
     */
    fun newInstance(title: String): APPFragment {
        val bundle = Bundle();
        bundle.putString(UrlConfitkt.BUNDLE_TITLE, title)
        val fragment = APPFragment()
        fragment.arguments = bundle
        return fragment
    }

    /**
     * 获取布局
     */
    override fun getLayoutId(): Int = R.layout.fragment_app

    /**
     * 初始化
     */
    override fun init() {
        val title = mBundle!!.getString(UrlConfitkt.BUNDLE_TITLE)
        home_txt_msg.text = title
        app_btn_lambda1.setOnClickListener {
            //lambda的调用有两种方式，一种是通过()来进行调用，另一种是通过invoke()函数进行调用，两种方式没有区别。
//            hello.invoke()
//            hello2()
//            //::sum表示sum函数的引用，cal(2, 3, ::sum)这一句就相当于执行了sum(2, 3)，所以输出结果为5。
//            val result = cal(2, 3, ::sum)
//            println("result = $result")

//            val test = Test()
            // 常规写法 传入函数
            //t.doTest { test -> test.doSomething() }
            // 使用引用函数(Test::doSomething实际上是对lambda表达式{test -> test.doSomething()}的简化)
            //t.doTest(Test::doSomething)
//            test.doTest { test.doSomething() }
//            test.doTest(Test::doSomething)


//            // 通过()来执行匿名函数sum
//            val add = sum(1, 2)
//            println(add)
//            // 通过lambda表达式来完成函数highSum
//            val add2 = highSum(3, 4) { a, b -> a + b }
//            println(add2)
//            // 通过函数引用来完成函数highSum
//            val add3 = highSum(5, 6, ::namedSum)
//            println(add3)


//            val test = listOf(1, 3, 5, 7, 9)
//            // all判断是否全部符合lambda表达式的条件
//            println("是否全部符合>10 ${test.all { it > 10 }}")
//            // any判断是否存在有符合lambda表达式的条件的数据
//            println("是否存在>8 ${test.any { it > 8 }}")
//            // count获取符合lambda表达式条件的数据个数
//            println("大于5的个数 ${test.count { it > 5 }}")
//            // find获取符合lambda表达式条件的第一个数据
//            println("第一个大于5 ${test.find { it > 5 }}")
//            println("最后一个大于5 ${test.findLast { it > 5 }}")

//            //sortedBy()用于根据指定的规则进行顺序排序，如果要降序排序，则需要使用sortedByDescending()，
//            val test2 = listOf(3, 2, 4, 6, 7, 1)
//            println(test2.sortedBy { it })
//
//            //take()和slice()用于进行数据切片，从某个集合中返回指定条件的新集合。类似的还有takeLast()、takeIf()等。
//            val test3 = listOf(3, 2, 4, 6, 7, 1)
//            // 获取前3个元素的新切片
//            println(test3.take(3))
//            // 获取指定index组成的新切片
//            println(test3.slice(IntRange(2, 4)))


//            val test4 = listOf("a", "ab", "b", "bc")
//            // reduce函数将一个集合的所有元素通过传入的操作函数实现数据集合的累积操作效果。
//            println(test4.reduce { acc, name -> "$acc$name" })
//
//
//            val test5 = listOf("a", "ab", "b", "bc")
//
//            // groupBy按照lambda表达式的条件重组数据并分组
//            println("按首字母分组 ${test5.groupBy(String::first)}")
//            // partition按照条件进行分组，该条件只支持Boolean类型条件，first为满足条件的，second为不满足的
//            test5.partition { it.length > 1 }.first.forEach { print("$it、") }
//            println()
//            test5.partition { it.length > 1 }.second.forEach { print("$it、") }
//            println()
//            // flatMap首先按照lambda表达式对元素进行变换，再将变换后的列表合并成一个新列表
//            println(test5.flatMap { it.toList() })


            val list1 = listOf(1, 2, 3, 4)
            val list2 = listOf("kotlin", "Android", "Java", "PHP", "JavaScript")

            // plus() 和 `+`一样
            println(list1.plus(list2))
            println(list1 + list2)

// zip
            println(list1.zip(list2))
            println(list1.zip(list2) {       // 组成的新集合由元素少的原集合决定
                    it1, it2 ->
                it1.toString().plus("-").plus(it2)
            })

// unzip
            val newList =
                listOf(Pair(1, "Kotlin"), Pair(2, "Android"), Pair(3, "Java"), Pair(4, "PHP"))
            println(newList.unzip())

// partition   判断元素是否满足条件把集合拆分为有两个Pair组成的新集合。
            println(list2.partition { it.startsWith("Ja") })
        }

    }

    /**
     * 无参数数的写法
     */
    val hello = { println("hello word") }

    /**
     * 2无参数数的写法
     */
    fun hello2(): Unit {
        println("hello word222")
    }


    /**
     * 有参数的写法
     */
    fun sum1(x: Int, y: Int): Int {
        return x + y
    }

    /**
     * 有参数的写法2
     */
    val sum2: (Int, Int) -> Int = { a, b -> a + b }

    //val 函数名 : (参数1类型, 参数2类型, ...) -> 返回值类型 = { 参数1, 参数2, ... -> 函数体 }
    val sum3 = { a: Int, b: Int -> { a + b } }


//    匿名函数
//    匿名函数形式为：
//    val 函数名 = fun(参数1:类型1, 参数2:类型2, ...): 返回值类型 { 函数体 }
//    示例：
//    val sum = fun(a: Int, b: Int): Int {
//        return a + b
//    }
//    // 等价于函数
//    fun sum(a: Int, b: Int): Int {
//        return a + b
//    }

    //    所谓高阶函数，实际上就是数学中的复合函数的概念，f(g(x))。
    fun cal(a: Int, b: Int, f: (c: Int, d: Int) -> Int): Int {
        return f(a, b)
    }

    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    //在Kotlin中调用高阶函数时，如果最后一个参数为lambda表达式，可以将lambda表达式写在外面，而且如果没有其它参数的话，小括号也是可以省略的。
    val result = cal(2, 3, { a: Int, b: Int -> a + b })

    // 两种写法等价
    val result2 = cal(2, 3) { a: Int, b: Int -> a + b }
//    println("result = $result")
    // result = 5

//    val sumLambda = {a: Int, b: Int -> a + b}
//    var numFun: (a: Int, b: Int) -> Int
//    numFun = {a: Int, b: Int -> a + b}
//    numFun = sumLambda
//    numFun = ::sum
//    numFun(1,2)

    //函数变量
    fun man(): Unit {
        //可以看到这个变量可以等于一个lambda表达式，
        // 也可以等于另一个lambda表达式变量，还可以等于一个普通函数，但是在函数名前需要加上(::)来获取函数引用。
        val sumLambda = { a: Int, b: Int -> a + b }
        var numbFunc: (a: Int, b: Int) -> Int
        numbFunc = { a: Int, b: Int -> a + b }
        numbFunc = sumLambda
        numbFunc = this::sum
        numbFunc(1, 2)


        //    lambda表达式的类型
//    通过下面的例子，可以了解下lambda表达式的类型，代码如下所示。

// 无参，返回String
        val number1: () -> Unit

// 两个整型参数，返回字符串类型
        val number2: (Int, Int) -> String

// 传入了一个lambda表达式和一个整型，返回Int
        val number3: (() -> Unit, Int) -> Int

        //lambda表达式的return
        //除非使用标签指定了返回点，否则return从最近的使用fun关键字声明的函数返回。
        var sum: (Int) -> Unit = tag@{
            print("Test return $it")
            return@tag
        }
        val test = sum(3)
        print("Test return $test")
    }

    // 匿名函数
    val sum = fun(a: Int, b: Int): Int {
        return a + b
    }

    // 具名函数
    fun namedSum(a: Int, b: Int): Int {
        return a + b
    }

    // 高阶函数
    fun highSum(a: Int, b: Int, f: (Int, Int) -> Int): Int {
        return f(a, b)
    }


}