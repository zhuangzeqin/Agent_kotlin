package com.eeepay.zzq.mvp.model.base

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2020/12/16-20:09
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
open class Builder<T> : IResponseCallback<T> {
    // 定义一个函数变量
    private lateinit var onSuccessVal: (response: T) -> Unit

    //  定义一个函数变量
    private lateinit var onFailedVal: (errorCode: String?, msg: String?) -> Unit

    // 给函数变量赋值
    open fun onSuccessFun(listener: (response: T) -> Unit) {
        this.onSuccessVal = listener
    }

    // 给函数变量赋值
    open fun onFailedFun(listener: (errorCode: String?, msg: String?) -> Unit) {
        this.onFailedVal = listener
    }


    /**
     * 成功将结果回调出去
     */
    override fun onSucess(response: T) {
        onSuccessVal.invoke(response)

    }

    /**
     * 失败时将结错误信息回调出去
     */
    override fun onFailure(code: String?, message: String?) {
        onFailedVal.invoke(code, message)
    }
}