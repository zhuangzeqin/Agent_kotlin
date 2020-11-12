package com.eeepay.zzq.utils

/**
 * 描述：密封类是一个有特定数量子类的类，看上去和枚举有点类似，所不同的是，在枚举中，我们每个枚举常量都是该枚举类对象实例；而在密封类中，每个枚举都是密封类的子类，且该子类都能存在多个实例。
密封类的所有子类都必须与密封类在同一文件中
密封类的子类的子类可以定义在任何地方，并不需要和密封类定义在同一个文件中
密封类没有构造函数，不可以直接实例化，只能实例化内部的子类
使用密封类的关键好处在于使用 when 表达式 的时候，如果能够 验证语句覆盖了所有情况，就不需要为该语句再添加一个 else 子句了。
 * 作者：zhuangzeqin
 * 时间: 2020/11/11-17:32
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
sealed class ViewSealed {
    object UP : ViewSealed()
    object DOWN : ViewSealed()
    object RIGHT : ViewSealed()
    object LEFT : ViewSealed()
//    class spin(angle: Int) : ViewSealed()
}