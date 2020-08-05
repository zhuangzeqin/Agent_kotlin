package com.eeepay.zzq.lib_common.parse

/**
 * 描述：抽象出后台返回的数据结构格式；通过泛型T 数据 解析
 * 作者：zhuangzeqin
 * 时间: 2020/7/23-16:57
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class ResultCallBack<T> {
    var code: Int = 0// 标识码  类型为 Int, 默认实现了 getter 和 setter
    var message: String? = ""//错误提示语
    var data: T? = null//泛型T 数据
    var count: Int = 0//数据数量
    var success: Boolean = false//是否成功； true 代表成功； false 代表不成功
}