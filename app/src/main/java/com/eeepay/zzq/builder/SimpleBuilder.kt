package com.example.myapplication

import android.util.Log

/**
 * 描述：Kotlin Builder模式
 * 作者：zhuangzeqin
 * 时间: 2021/4/3-14:24
 * 邮箱：zzq@eeepay.cn
 * 备注: SimpleBuilder.builder().setAge(31).setName("zzq").build().start()
 */
class SimpleBuilder(var name: String, var age: Int) {
    constructor(builder: Builder) : this(builder.name, builder.age)
    companion object {
        fun builder(): Builder {
            return Builder()
        }
    }
    class Builder {
        var name: String = ""
        var age: Int = 0

        fun setName(name: String): Builder {
            this.name = name
            return this
        }

        fun setAge(age: Int): Builder {
            this.age = age
            return this
        }

        fun build(): SimpleBuilder {
            return SimpleBuilder(this)
        }
    }

    fun start(): Unit {
        //具体的业务逻辑
        Log.d("tag",name)
        Log.d("tag",age.toString())
    }
}