package com.eeepay.zzq.bean

import java.io.Serializable

/**
 * 描述：kotlin 生成的实体bean
 * 作者：zhuangzeqin
 * 时间: 2020/8/4-10:24
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class LoginktInfo : Serializable {
    //lateinit 不支持基本数据类型，例如Int. 针对基本数据类型实现方式你可以使用委托(delegate)类
//    private var mNumber: Int by Delegates.notNull<Int>()
//    Kotlin 允许使用 ` ` 把一个不合法的标识符强行合法化，当然本身是合法的也能用。 一般我们都只用于关键字冲突的情形，可你们一定没想到有这种妙用吧。

    data class LoginBean(
        val code: Int,
        val count: Int,
        val `data`: Data,
        val message: String,
        val success: Boolean
    ):Serializable

    data class Data(
        val agentLevel: Int,
        val agentName: String,
        val agentNo: String,
        val agentNode: String,
        val agentOem: String,
        val lockTime: Any,
        val loginToken: String,
        val manage: String,
        val oneAgentNo: String,
        val oneLevelId: String,
        val parentId: String,
        val password: String,
        val phone: String,
        val teamId: String,
        val userId: String,
        val userName: String,
        val wrongPasswordCount: Int
    ):Serializable
}

