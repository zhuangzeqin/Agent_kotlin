package com.eeepay.zzq.lambda

/**
 * 描述：声明函数类型变量和对外提供的回调方法（与接口回调功能保持一致），并实现接口内回调方法
 * 作者：zhuangzeqin
 * 时间: 2021/3/16-17:45
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class ResponseCallbackListener<T> : IResponseCallback<T> {
    //1 通过高阶函数声明函数类型变量（参数与接口互调函数一致）
    lateinit var next: (data: T?) -> Unit
    lateinit var error: (code: Int, message: String) -> Unit

    //2 声明回调方法（与接口回调功能一致），参数为对应的函数类型变量
    fun OnNext(nextListener: (data: T?) -> Unit) {
        this.next = nextListener
    }
    fun OnError(errorListener: (code: Int, message: String) -> Unit) {
        this.error = errorListener
    }

    fun setCallback(
        nextListener:  (data: T?)  -> Unit,
        errorListener: (code: Int, message: String) -> Unit) {
        this.next = nextListener
        this.error = errorListener
    }
    //实现接口内回调方法,调用对应函数变量的invoke(param)方法实现回调
    override fun nextFun(data: T?) {
        //函数类型的值可以通过其 invoke(……) 操作符调用：f.invoke(x) 或者直接 f(x)。
        this.next.invoke(data)
        //this.next(data)
    }
    override fun errorFun(code: Int, message: String) {
        this.error.invoke(code, message)
    }
}