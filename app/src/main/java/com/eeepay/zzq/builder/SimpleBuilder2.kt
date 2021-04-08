package com.example.myapplication

import android.util.Log

/**
 * 描述：进阶版（推荐）
 * 作者：zhuangzeqin
 * 时间: 2021/4/3-14:33
 * 邮箱：zzq@eeepay.cn
 * 备注: SimpleBuilder2.build {
name="zhuangzeqin"
age = 32
}.start()
 */
class SimpleBuilder2(var name: String, var age: Int) {
    constructor(builder: Builder) : this(builder.name, builder.age)
    companion object {
        inline fun build(block: Builder.() -> Unit): SimpleBuilder2 {
            return Builder().apply(block).build()
        }
    }

    class Builder {
        var name: String = ""
        var age: Int = 0

        fun build(): SimpleBuilder2 {
            return SimpleBuilder2(this)
        }
    }

    fun start(block:(String,String)->Unit,block1:(String,Int)->Unit): Unit {
        //具体的业务逻辑
        Log.d("tag",name)
        Log.d("tag",age.toString())
    }

}