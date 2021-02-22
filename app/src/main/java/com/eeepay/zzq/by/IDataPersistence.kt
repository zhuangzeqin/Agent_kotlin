package com.eeepay.zzq.by

/**
 * 描述：第一步：创建接口约束，抽象业务场景。
 * 例如下面这个数据持久化的例子，我们通过接口定义了三个数据操作方法。
 * 作者：zhuangzeqin
 * 时间: 2021/2/2-16:17
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
interface IDataPersistence {
    fun addData()
    fun delData()
    fun queryData()
}