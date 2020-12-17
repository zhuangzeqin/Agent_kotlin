package com.eeepay.zzq.mvp.ui

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2020/12/16-14:08
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
// 使用typealias 使用typealias给重复申明的lambda表达式设置别名
typealias intFun = (Int) -> Unit
class Test {
    fun doSomething(): Unit {
        println("做点事情")
    }

    fun doTest(f:(Test)->Unit): Unit {
        f(this)
    }

    fun fun1(f: (Int) -> Unit) {
        f(1)
    }

    fun fun2(f: (Int) -> Unit) {
        f(2)
    }



    fun fun3(f: intFun) {
        f(3)
    }

    fun fun4(f: intFun) {
        f(4)
    }


}