package com.eeepay.zzq.mvp.model.base

/**
 * 描述：基础协议的定义
 * 作者：zhuangzeqin
 * 时间: 2020/8/6-11:26
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
interface IBaseContract {
    /**
     * 定义CallBack将结果返回给外部调用者使用
     */
    interface IResultCallBack<T> {
        /**
         * 成功将结果回调出去
         */
        fun onSucess(response: T)


        /**
         * 失败时将结错误信息回调出去
         */
        fun onFailure(code: String?, message: String?)
    }
    /**
     * 定义CallBack将结果返回给外部调用者使用
     */
    interface IResultProgressCallBack<T> {
        /**
         * 成功将结果回调出去
         */
        fun onSucess(response: T)

        /**
         * progress 下载进度回调,0-100，仅在进度有更新时才会回调
         * currentSize 当前已下载的字节大小
         * totalSize 要下载的总字节大
         */
        fun onProgress(progress: Int,currentSize:Long,totalSize:Long)

        /**
         * 失败时将结错误信息回调出去
         */
        fun onFailure(code: String?, message: String?)
    }

    /**
     * 定义CallBack将结果返回给外部调用者使用
     */
    interface IResultCallBackCount<T> {
        /**
         * 成功将结果回调出去 带总数的接口；
         */
        fun onSucess(tag: String?, response: T, count: Int)

        /**
         * 失败时将结错误信息回调出去
         */
        fun onFailure(tag: String?, message: String?)
    }
}