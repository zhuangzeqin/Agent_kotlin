package com.eeepay.zzq.kotlin

import com.eeepay.zzq.utils.ToastUtils
import isNotEmptyWithContract
import kotlin.contracts.ExperimentalContracts

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2021/3/10-16:55
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
//inline 关键字应该只用在需要内联特性的函数中，比如高阶函数作为参数和具体化的类型参数时。
@ExperimentalContracts
fun String.toast(msg:String): Unit {
    if (msg.isNotEmpty())
    {

    }

    var bb =  isNotEmptyWithContract(msg)
    ToastUtils.showShort(msg)
}

/**
 * 这个内联函数是传入一个numb *2 返回
 * inline 关键字的作用，是把 inline 方法以及方法中的 lambda 参数在编译期间复制到调用方，
 * 进而减少函数调用以及对象生成。对于有时候我们不想让 inline 关键字对 lambda 参数产生影响，
 * 可以使用 noline 关键字。
 * 如果想 lambda 也被 inline，但是不影响调用方的控制流程，那么就要是用 crossinline。
 */
inline fun multiplyByTwo(num: Int, crossinline block:(Int)->Unit):Int
{
    val temp =num*2
    block.invoke(temp)
    return temp
}