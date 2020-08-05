package com.eeepay.zzq.lib_common.singleton

import com.squareup.otto.Bus
import com.squareup.otto.ThreadEnforcer
import java.io.Serializable

/**
 * 描述：Kotlin中实现一个饿汉式单例模式
 * 作者：zhuangzeqin
 * 时间: 2020/7/28-10:54
 * 邮箱：zzq@eeepay.cn
 * 备注:只需要定义一个object对象表达式即可，无需手动去设置构造器私有化和提供全局访问点，这一点Kotlin编译器全给你做好了
 */
object SingletonManager: Bus(ThreadEnforcer.ANY),Serializable {//实现Serializable序列化接口，通过私有、被实例化的readResolve方法控制反序列化
    private fun readResolve(): Any {//防止单例对象在反序列化时重新生成对象
        return SingletonManager//由于反序列化时会调用readResolve这个钩子方法，只需要把当前的KSingleton对象返回而不是去创建一个新的对象
    }
}