package com.eeepay.zzq.agent_kotlin

import com.eeepay.zzq.base.BaseMvpActivity
import com.eeepay.zzq.mvp.presenter.login.ILoginView
import com.eeepay.zzq.mvp.presenter.login.LoginPresenter
import com.eeepay.zzq.presenter.base.CreatePresenter
import kotlinx.android.synthetic.main.activity_login.*

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2020/8/5-11:27
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */

@CreatePresenter(presenter = [LoginPresenter::class])
class LoginAct : BaseMvpActivity<LoginPresenter>(), ILoginView {

    override fun eventOnClick() {
        btn_login.setOnClickListener { view ->
            getPresenter().login(this, "18681490423", "123456")
        }
    }

    override fun getContentView(): Int {
        return R.layout.activity_login
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun onLoginSuccess(msg: String) {
        showError(msg)
        goActivity(this, MainActivity::class.java)
    }

}