package com.eeepay.zzq.mvp.ui

import com.eeepay.zzq.agent_kotlin.R
import com.eeepay.zzq.base.BaseMvpActivity
import com.eeepay.zzq.mvp.presenter.base.BasePresenter
import com.eeepay.zzq.mvp.presenter.base.interfaces.IBaseView

/**
 * 描述：主界面
 * 作者：zhuangzeqin
 * 时间: 2020/8/10-17:50
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class RxMainAct: BaseMvpActivity<BasePresenter<IBaseView>>() {
    /**
     * 布局id
     */
    override fun getContentView(): Int {
       return R.layout.activity_main
    }

    /**
     * 初始化操作
     */
    override fun initView() {
        TODO("Not yet implemented")
    }

    /**
     * 初始化点击事件操作
     */
    override fun eventOnClick() {
        TODO("Not yet implemented")
    }

    /**
     * 初始化数据
     */
    override fun initData() {
        //标题
        val title = listOf<String>("首页", "数据", "应用", "我的")


    }
}