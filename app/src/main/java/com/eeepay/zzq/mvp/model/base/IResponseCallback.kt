package com.eeepay.zzq.mvp.model.base

/**
 * 描述：lambda表达式的类型封装的响应接口的回调
 * 作者：zhuangzeqin
 * 时间: 2020/12/16-18:02
 * 邮箱：zzq@eeepay.cn
 * 备注:使用lambda表达式替代匿名内部类实现
 */
@FunctionalInterface
interface IResponseCallback<T> {
    /**
     * 成功将结果回调出去
     */
    fun onSucess(response: T)


    /**
     * 失败时将结错误信息回调出去
     */
    fun onFailure(code: String?, message: String?)
}