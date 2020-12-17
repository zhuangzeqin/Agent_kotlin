package com.eeepay.zzq.mvp.model.base

/**
 * 描述：Kotlin使用高阶函数实现多方法回调
 * 作者：zhuangzeqin
 * 时间: 2020/12/17-9:42
 * 邮箱：zzq@eeepay.cn
 * 备注:Kotlin使用高阶函数实现多方法回调
 */
class OnCallbackImpl2<T>(
    private val onSuccessF: (T) -> Unit,
    private val onErrorF: (code: String?, message: String?) -> Unit
) : IResponseCallback<T> {
    /**
     * 成功将结果回调出去
     */
    override fun onSucess(response: T) {
        onSuccessF?.let { it(response) }
    }

    /**
     * 失败时将结错误信息回调出去
     */
    override fun onFailure(code: String?, message: String?) {
        onErrorF?.let { it(code, message) }
    }

}