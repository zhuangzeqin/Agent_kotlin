package com.eeepay.zzq.mvp.presenter.login

import com.eeepay.zzq.presenter.interfaces.IBaseView

/**
 * 描述：登录提供的View
 * 作者：zhuangzeqin
 * 时间: 2020/8/5-13:45
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
interface ILoginView :IBaseView{
    fun onLoginSuccess(msg:String)//登录成功
}