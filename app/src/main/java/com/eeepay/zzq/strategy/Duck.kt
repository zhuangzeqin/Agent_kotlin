package com.eeepay.zzq.strategy

/**
 * 描述：Kotlin设计模式实现之策略模式
 * 策略模式定义了算法族，分别封装起来，让他们之间可以相互替换，此模式让算法的变化独立于使用算法的客户。
 * 作者：zhuangzeqin
 * 时间: 2021/4/12-17:25
 * 邮箱：zzq@eeepay.cn
 * 备注:两种鸭子都是声明的子类指向了父类，子类实例化的动作不需要在代码中进行硬编码，而是在运行时才指定具体实现的对象
 *      val mallardDuck : Duck = MallardDuck()
        mallardDuck.display()
        mallardDuck.performFly()
        mallardDuck.performQuack()
***************************************************************************
        val modelDuck : Duck = ModelDuck()
        modelDuck.display()
        modelDuck.performFly()
        modelDuck.performQuack()
        modelDuck.swim()
两种鸭子都是声明的子类指向了父类，子类实例化的动作不需要在代码中进行硬编码，而是在运行时才指定具体实现的对象
 */
abstract class Duck(
    var flyBehavior: FlyBehavior? = null,
    var quackBehavior: QuackBehavior? = null
) {
    fun swim() {
        println("所有的鸭子都会游泳哦，所以直接写在了抽象类")
    }

    //执行飞行操作
    fun performFly() {
        flyBehavior?.fly()
    }

    //执行鸭子叫操作
    fun performQuack() {
        quackBehavior?.quack()
    }

    //显示
    abstract fun display()
}