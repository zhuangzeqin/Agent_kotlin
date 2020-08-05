package com.eeepay.zzq.lib_common.event

import java.io.Serializable

/**
 * 描述：数据类必须满足以下要求：
主构造函数需要至少有一个参数；
主构造函数的所有参数需要标记为 val 或 var；
数据类不能是抽象、开放、密封或者内部的；
（在1.1之前）数据类只能实现接口。
 * 作者：zhuangzeqin
 * 时间: 2020/7/28-14:17
 * 邮箱：zzq@eeepay.cn
 * 备注:在申明一个 data class 有一些需要注意的事项。
主构造函数必须要至少有一个参数
主构造函数中的所有参数必须被标记为val或者var
数据类不能有以下修饰符：abstract，inner,open,sealed
data class只能实现接口（Kotlin1.1以前的规则），现在也可以继承其它类
自动生成如下方法
equals()/hashCode()
toString()方法
componentN()方法
copy()方法
 */
data class AppEventData(var index: Int, var mesage: String) : Serializable {
    var marks: String? = null
}