package com.eeepay.zzq.lambda

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2021/3/16-17:48
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
object LoginLogicImpl {
    //声明register方法，参数为：函数类型
    fun register(nextListener: (Any?) -> Unit,errorListener: (Int,String) -> Unit) {
//        var callBack = ResponseCallbackListener<String>()//实例化回调接口实现类
//        callBack.OnError()
//        callBack.callback()										  //将参数内的回调函数与实例化对象绑定
        if (false) {
            nextListener.invoke("成功回调aaaaaa")         //成功回调
        } else {
            errorListener.invoke(400, "failure...")   //失败回调
        }
    } 
    
    @Suppress("UNCHECKED_CAST")
    fun<T> register2(call: ResponseCallbackListener<T>.()-> Unit) {
        var callBack = ResponseCallbackListener<T>()//实例化回调接口实现类
        callBack.call()  //将参数内的回调函数与实例化对象绑定
        if (false) {
            callBack.nextFun("成功回调" as T)         //成功回调
        } else {
            callBack.errorFun(400, "failure...")   //失败回调
        }
    }

    fun register3(call: (Int,String) -> String) {
        if (false) {
        } else {
            call.invoke(400,"zzq")
        }
    }

    fun<T> register4(call:IResponseCallback<T>.()->Unit) {
        var IResponseCallback = ResponseCallbackListener<T>()//实例化回调接口实现类
        IResponseCallback.call()  //将参数内的回调函数与实例化对象绑定
        if (false) {
        } else {
            IResponseCallback.errorFun(400, "failure...")
        }
    }
}