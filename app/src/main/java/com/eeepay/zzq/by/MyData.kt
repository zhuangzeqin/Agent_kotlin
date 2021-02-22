package com.eeepay.zzq.by

/**
 * 描述：调用约束接口，即业务方调用，但不用考虑具体的实现。
 * 类委托的语法格式是，<类>:<约束接口> by <实现类的实例>，即通过by关键字，将接口的实现，委托给一个具体的实例来作为自己的实现。
 * 作者：zhuangzeqin
 * 时间: 2021/2/2-16:27
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class MyData(private val delegate:IDataPersistence):IDataPersistence by delegate {
    // val myDB = MyDB(SQL())

//    override fun addData() {}
//
//    override fun delData() {}
//
//    override fun queryData() {}

}