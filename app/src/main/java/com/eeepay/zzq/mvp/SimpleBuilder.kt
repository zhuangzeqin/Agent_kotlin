package com.eeepay.zzq.mvp

/**
 * 描述：Builder 构建模式
 * 作者：zhuangzeqin
 * 时间: 2020/12/18-14:25
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
typealias intSucceedFun = (String?, Any?) -> Unit?//别名 放在类头部
typealias intFailureFun = (String?, String?) -> Unit?//别名放在类头部

class SimpleBuilder private constructor(mBuilder: Builder) {
    //private constructor(mBuilder: Builder)
    //typealias intSucceedFun = (String?, Any?) -> Unit?//别名 放在类头部
    //typealias intFailureFun = (String?, String?) -> Unit?//别名放在类头部
    //将结果返回给外部调用者使用 使用lambda表达式替代匿名内部类实现。
    private var onSucceed: intSucceedFun = { tag: String?, T: Any? -> }
    private var onFailure: intFailureFun = { tag: String?, msg: String? -> }
    private var mTag: String? = null// TAG 标签

    /**
     * 初始化操作
     */
    init {
        this.mTag = mBuilder.tag
        this.onSucceed = mBuilder.onSucceedVel
        this.onFailure = mBuilder.onFailureVel
    }

    /**
     * Builder 类
     */
    class Builder {
        var tag: String? = null// TAG 标签

        //使用lambda表达式替代匿名内部类实现。
        var onSucceedVel: intSucceedFun = { tag: String?, T: Any? -> }
        var onFailureVel: intFailureFun = { tag: String?, msg: String? -> }

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
        fun setResultCallBack(
            onSucceedVal: intSucceedFun,
            onFailureVal: intFailureFun
        ): Builder {
            this.onSucceedVel = onSucceedVal
            this.onFailureVel = onFailureVal
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
        checkNotNull(onSucceed) { "===onSucceed is null,you can must implement.===" }
        checkNotNull(onFailure) { "===onFailure is null,you can must implement.===" }
        println("mTag = ${mTag}")
        onSucceed(mTag, mTag)
        onFailure(mTag, mTag)
    }

}