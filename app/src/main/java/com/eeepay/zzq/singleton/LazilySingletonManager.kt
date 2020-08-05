package com.eeepay.zzq.lib_common.singleton

import com.squareup.otto.Bus
import com.squareup.otto.ThreadEnforcer
import java.io.Serializable

/**
 * 描述：线程安全的懒汉式单例
 * 作者：zhuangzeqin
 * 时间: 2020/7/28-11:14
 * 邮箱：zzq@eeepay.cn
 * 备注:可是有时候我们并不想当类加载的时候就去创建这个单例实例，而是想当我们使用这个实例的时候才去初始化它。于是乎就有了懒汉式的单例模式
 */
class LazilySingletonManager private constructor() : Bus(ThreadEnforcer.ANY), Serializable {
    companion object {
        private var mInstance: LazilySingletonManager? = null
            get() {
                return field ?: LazilySingletonManager()//如果为空就实例一个对象
            }
        /* ------注释说明---获得实例----- */
        @JvmStatic //通过@JvmStatic注解，使得在Java中调用instance直接是像调用静态函数一样，
        @Synchronized//添加synchronized同步锁
        fun getInstance(): LazilySingletonManager {
            return requireNotNull(mInstance)
        }
    }
    //防止单例对象在反序列化时重新生成对象
    private fun readResolve(): Any {
        return LazilySingletonManager.getInstance()
    }

}