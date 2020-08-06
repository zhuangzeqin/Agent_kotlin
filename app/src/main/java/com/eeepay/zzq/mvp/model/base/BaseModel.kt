package com.eeepay.zzq.mvp.model.base

import androidx.lifecycle.LifecycleOwner
import com.rxjava.rxlife.BaseScope
import rxhttp.wrapper.annotations.NonNull

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2020/8/6-10:24
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
abstract class BaseModel(@NonNull owner: LifecycleOwner) : BaseScope(owner){
    //参数列表
    protected var mParams:Map<String,Any> = HashMap()
}