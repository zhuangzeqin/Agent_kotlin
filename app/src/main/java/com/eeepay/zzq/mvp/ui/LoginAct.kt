package com.eeepay.zzq.mvp.ui

import com.eeepay.zzq.agent_kotlin.R
import com.eeepay.zzq.base.BaseMvpActivity
import com.eeepay.zzq.bean.PubDataktInfo
import com.eeepay.zzq.mvp.presenter.base.CreatePresenter
import com.eeepay.zzq.mvp.presenter.base.PresenterVariable
import com.eeepay.zzq.mvp.presenter.login.ILoginView
import com.eeepay.zzq.mvp.presenter.login.LoginPresenter
import com.eeepay.zzq.mvp.presenter.pubdata.IPublicDataView
import com.eeepay.zzq.mvp.presenter.pubdata.PubDataPresenter
import kotlinx.android.synthetic.main.activity_login.*

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2020/8/5-11:27
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */

@CreatePresenter(presenter = [LoginPresenter::class, PubDataPresenter::class])
class LoginAct : BaseMvpActivity<LoginPresenter>(), ILoginView, IPublicDataView {
    @PresenterVariable
    var mPubDataPresenter: PubDataPresenter? = null

    override fun eventOnClick() {
        btn_login.setOnClickListener { view ->
            getPresenter().login(this, "18681490423", "123456")
        }
        btn_getPub.setOnClickListener { view ->
            mPubDataPresenter?.getPubDataInfo(this)
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
        goActivity(this, RxMainAct::class.java)
    }

    override fun showPubDataInfo(pubDataInfo: PubDataktInfo.Data) {
        showError(pubDataInfo.toString())
    }

    /**
     * 抽象的设置的标题的方法 子类实现
     */
    override fun setTitle(): String? {
        return null
    }

}