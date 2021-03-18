package com.eeepay.zzq.lambda

/**
 * 描述：定义回调接口
 * 作者：zhuangzeqin
 * 时间: 2021/3/16-17:44
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
interface IResponseCallback<T> {
    fun nextFun(data: T?)

    fun errorFun(code: Int, message: String)
}