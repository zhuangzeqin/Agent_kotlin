package com.eeepay.zzq.mvp

/**
 * 描述：Builder 构建模式
 * 作者：zhuangzeqin
 * 时间: 2020/12/18-14:25
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class SimpleBuilder private constructor(mBuilder: Builder) {
    //private constructor(mBuilder: Builder) 私有的构造函数 放在类后面
    private var mTag: String? = null// TAG 标签
    private var mResultCallBack: ResultCallBack? = null//回调接口

    /**
     * 初始化操作
     */
    init {
        mTag = mBuilder.tag
        mResultCallBack = mBuilder.resultCallBack
    }

    /**
     * Builder 类
     */
    class Builder {
        var tag: String? = null// TAG 标签
        var resultCallBack: ResultCallBack? = null//回调接口

        /**
         * 设置tag
         */
        fun setTag(tag: String?): Builder {
            this.tag = tag
            return this
        }

        /**
         * 设置回调接口
         */
        fun setResultCallBack(resultCallBack: ResultCallBack?): Builder {
            this.resultCallBack = resultCallBack
            return this
        }

        /**
         * 静态内部类调用外部类的构造函数，来构造外部类
         * Builder类中的成员函数返回Builder对象自身的另一个作用就是让它支持链式调用，使代码可读性大大增强。
         * @return
         */
        fun build(): SimpleBuilder {
            return SimpleBuilder(this)
        }
    }

    /**
     * 开始请求数据
     */
    fun start() {
        checkNotNull(mResultCallBack) { "===ResultCallBack is null,you can must implement.===" }
        println("mTag = ${mTag}")
        mResultCallBack!!.onSucceed(mTag, mTag)
        mResultCallBack!!.onFailure(mTag, mTag)
    }

    /**
     * 将结果返回给外部调用者使用
     */
    interface ResultCallBack {
        //外围实现这个接口的时候
        fun onSucceed(tag: String?, data: String?) //成功
        fun onFailure(tag: String?, msg: String?) //失败
    }
}