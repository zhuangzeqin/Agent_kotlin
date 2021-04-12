package com.eeepay.zzq.strategy

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2021/4/12-17:31
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class ModelDuck : Duck(flyBehavior = FlyWithNoWay(),quackBehavior = Squack()){
    override fun display() {
        println("我是一个假鸭子")
    }
}