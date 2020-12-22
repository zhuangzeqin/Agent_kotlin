package com.eeepay.zzq.factory

/**
 * 描述：工厂类
 * 作者：zhuangzeqin
 * 时间: 2020/12/22-9:43
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
object CarFactory {

    /**
     * 将一个函数标记为重载一个操作符，也就是操作符重载
     */
    operator fun invoke(car: CarType): ICar {
        return when (car) {
            CarType.BCar -> BCar()
            CarType.DCar -> DCar()
        }
    }


    // 测试
    fun main() {
        val tCar = CarFactory(CarType.BCar)
        val nCar = CarFactory(CarType.DCar)
        println("traditional car use ${tCar.name}")
        println("new-energy car use ${nCar.name}")
    }

}