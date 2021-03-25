package com.eeepay.common.lib.mvp.model.base.kt
/**
 * 描述：定义回调接口
 * 作者：zhuangzeqin
 * 时间: 2021/3/16-17:44
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
interface IResponseCallback<T> {
    //成功的回调
    fun onSuccessFun(tag: String?, code: Int, message: String?, response: com.eeepay.zzq.lambda.kt.Result<T>?, count: Int)
    //失败的回调
    fun onFailureFun(tag: String?, code: Int, message: String?, response: com.eeepay.zzq.lambda.kt.Result<T>?, count: Int)
}