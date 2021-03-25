package com.eeepay.common.lib.mvp.model.base.kt


 /*
   * ================================================
   * 描述：声明函数类型变量和对外提供的回调方法（与接口回调功能保持一致），并实现接口内回调方法
   * 作者：zhuangzeqin
   * 时间: 2021/3/17-16:02
   * 邮箱：zzq@eeepay.cn
   * 备注:
   * ----------------------------------------------------------------
   * You never know what you can do until you try !
   *      _              _           _     _   ____  _             _ _
   *     / \   _ __   __| |_ __ ___ (_) __| | / ___|| |_ _   _  __| (_) ___
   *    / _ \ | '_ \ / _` | '__/ _ \| |/ _` | \___ \| __| | | |/ _` | |/ _ \
   *   / ___ \| | | | (_| | | | (_) | | (_| |  ___) | |_| |_| | (_| | | (_) |
   *  /_/   \_\_| |_|\__,_|_|  \___/|_|\__,_| |____/ \__|\__,_|\__,_|_|\___/
   *
   * 签名：最痛苦的事不是我失败了，而是我本可以.--zzq
   * ----------------------------------------------------------------
   * ================================================
   */
// 使用typealias 别名 简化入参的形式；也能达到统一管理（接口中大量重复的类型）
//tag: String?, code: Int, message: String?, response: Result<T>?, count: Int
typealias intParameterFun<T> = (tag: String?, code: Int, message: String?, response: com.eeepay.zzq.lambda.kt.Result<T>?, count: Int) -> Unit
class ResponseCallbackListener<T> : IResponseCallback<T> {
    //1 通过高阶函数声明函数类型变量（参数与接口互调函数一致）
    lateinit var succeed: intParameterFun<T>
    lateinit var failure: intParameterFun<T>

    //2 接受者声明回调方法（与接口回调功能一致），参数为对应的函数类型变量
    fun  onSuccess(succeedListener: intParameterFun<T>) {
        this.succeed = succeedListener
    }

    fun onFailure(failureListener: intParameterFun<T>) {
        this.failure = failureListener
    }
    //3 业务使用 实现接口内回调方法,调用对应函数变量的invoke(param)方法实现回调
    override fun onSuccessFun(tag: String?, code: Int, message: String?, response: com.eeepay.zzq.lambda.kt.Result<T>?, count: Int) {
        this.succeed.invoke(tag, code, message, response, count)
    }

    override fun onFailureFun(tag: String?, code: Int, message: String?, response: com.eeepay.zzq.lambda.kt.Result<T>?, count: Int) {
        this.failure.invoke(tag, code, message, response, count)
    }
}