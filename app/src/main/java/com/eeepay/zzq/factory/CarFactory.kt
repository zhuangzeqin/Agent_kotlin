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


    interface Fruits {
        fun showName()
    }

    class Lemon(private val strType: String) : Fruits {
        override fun showName() {
            println("当前构建对象是：$strType")
        }
    }

    class Pear(private val strType: String) : Fruits by Lemon(strType)
    class Watermelon(private val strType: String) : Fruits by Lemon(strType)

    /**
     * 创建工厂
     */
    class FruitsFactory {
        fun createType(type: String): Fruits? {
            return when (type) {
                "Pear" -> {
                    Pear(type)
                }
                "Watermelon" -> {
                    Watermelon(type)
                }
                "Lemon" -> {
                    Lemon(type)
                }
                else -> null
            }
        }
    }


    fun main2() {
        val lemon = FruitsFactory().createType("Lemon")
        lemon?.showName()
        val watermelon = FruitsFactory().createType("Watermelon")
        watermelon?.showName()
        val pear = FruitsFactory().createType("Pear")
        pear?.showName()
    }


}