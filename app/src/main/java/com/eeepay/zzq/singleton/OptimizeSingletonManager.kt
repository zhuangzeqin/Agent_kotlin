package com.eeepay.zzq.lib_common.singleton

import com.squareup.otto.Bus
import com.squareup.otto.ThreadEnforcer
import java.io.Serializable

/*
 * 描述：静态内部类单例实现
 * 作者：zhuangzeqin
 * 时间: 2020/7/28-14:01
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class OptimizeSingletonManager private constructor() : Bus(ThreadEnforcer.ANY), Serializable {
    /* ------注释说明---静态内部类----- */
    private object SingletonHolder {
        val mInstance: OptimizeSingletonManager = OptimizeSingletonManager()
    }

    //防止单例对象在反序列化时重新生成对象
    private fun readResolve(): Any {
        return SingletonHolder.mInstance
    }

    companion object {
        @JvmStatic
        fun getInstance(): OptimizeSingletonManager {//全局访问点
            return SingletonHolder.mInstance
        }
    }
}