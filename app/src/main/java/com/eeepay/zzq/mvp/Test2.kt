package com.eeepay.zzq.mvp

/**
 * 描述：SAM 转换，即 Single Abstract Method Conversions，
 * 就是对于只有单个非默认抽象方法接口的转换 —— 对于符合这个条件的接口（称之为 SAM Type ），
 * 在 Kotlin 中可以直接用 Lambda 来表示 —— 当然前提是 Lambda 的所表示函数类型能够跟接口的中方法相匹配。
   在Kotlin1.4之前，Kotlin是不支持Kotlin的SAM转换的，只支持Java SAM转换，官方给出的的解释是：是 Kotlin 本身已经有了函数类型和高阶函数，不需要在去SAM转化。 这个解释开发者并不买账，如果你用过Java Lambda和Fuction Interface。当你切换到Kotlin时，就会很懵逼。看来Kotlin是意识到了这个，或者是看到开发者的反馈，终于支持了。
   在1.4之前，只能传递一个对象，是不支持Kotlin SAM的，而在1.4之后，可以支持Kotlin SAM,但是用法有一丢丢变化。interface需要使用fun关键字声明。使用fun关键字标记接口后，只要将此类接口作为参数，就可以将lambda作为参数传递。
 * 作者：zhuangzeqin
 * 时间: 2021/1/26-10:38
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class Test2 {
    // 注意需用fun 关键字声明
    /*fun interface Action {
        fun run()
    }

    fun runAction(a: Action) = a.run()

    fun main() {
        // 1.4之前，只能使用object
        runAction(object : Action {
            override fun run() {
                println("run action")
            }
        })
        // 1.4-M1支持SAM,OK
        runAction {
            println("Hello, Kotlin 1.4!")
        }
    }*/
}