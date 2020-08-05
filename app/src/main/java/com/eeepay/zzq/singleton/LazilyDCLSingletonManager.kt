package com.eeepay.zzq.lib_common.singleton

import com.squareup.otto.Bus
import com.squareup.otto.ThreadEnforcer
import java.io.Serializable

/**
 * 描述：支持线程安全DCL的单例
 * 作者：zhuangzeqin
 * 时间: 2020/7/28-11:56
 * 邮箱：zzq@eeepay.cn
 * 备注:Companion Object + lazy属性代理
 */
class LazilyDCLSingletonManager private constructor() : Bus(ThreadEnforcer.ANY), Serializable {
    //private constructor()构造器私有化
    private fun readResolve(): Any {
        return mInstance
    }

    companion object {
        @JvmStatic
        //使用lazy属性代理，并指定LazyThreadSafetyMode为SYNCHRONIZED模式保证线程安全
        val mInstance: LazilyDCLSingletonManager by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { LazilyDCLSingletonManager() }
    }
}