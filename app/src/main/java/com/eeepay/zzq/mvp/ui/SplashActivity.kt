package com.eeepay.zzq.mvp.ui

import com.eeepay.zzq.agent_kotlin.LoginAct
import com.eeepay.zzq.agent_kotlin.R
import com.eeepay.zzq.base.BaseMvpActivity
import com.eeepay.zzq.mvp.presenter.base.BasePresenter
import com.eeepay.zzq.mvp.presenter.base.interfaces.IBaseView
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * 描述：当不适用mvp 的时候可以继承BaseMvpActivity
 * 作者：zhuangzeqin
 * 时间: 2020/8/6-17:53
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class SplashActivity:BaseMvpActivity<BasePresenter<IBaseView>>() {
    /**
     * 布局id
     */
    override fun getContentView(): Int = R.layout.activity_splash

    /**
     * 初始化操作
     */
    override fun initView() {
        splash_iv.postDelayed( {
            goActivity(this,LoginAct::class.java)
        },2000)
    }

    /**
     * 初始化点击事件操作
     */
    override fun eventOnClick() {
    }

    /**
     * 初始化数据
     */
    override fun initData() {
    }


}