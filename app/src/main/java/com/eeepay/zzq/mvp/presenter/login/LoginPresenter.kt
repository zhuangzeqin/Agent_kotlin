package com.eeepay.zzq.mvp.presenter.login

import androidx.lifecycle.LifecycleOwner
import com.eeepay.zzq.bean.LoginktInfo
import com.eeepay.zzq.mvp.model.base.IBaseContract
import com.eeepay.zzq.mvp.model.login.LoginModel
import com.eeepay.zzq.mvp.presenter.IPresenterContract.ILoginPresenter
import com.eeepay.zzq.mvp.presenter.base.BasePresenter

/**
 * 描述：登录的LoginPresenter
 * 作者：zhuangzeqin
 * 时间: 2020/8/5-13:44
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class LoginPresenter : BasePresenter<ILoginView>(), ILoginPresenter {
    lateinit var loginModel: LoginModel //model
    override fun login(owner: LifecycleOwner, username: String, password: String) {
        //View是否绑定 如果没有绑定，就不执行网络请求
        if (!isAttachView) return
        mView.showLoading()
        loginModel = LoginModel(owner)
        loginModel.reqLogin(
            username,
            password,
            object : IBaseContract.IResultCallBack<LoginktInfo.Data> {
                override fun onSucess(response: LoginktInfo.Data) {
                    mView.hideLoading()
                    mView.onLoginSuccess(response.toString())
                }

                override fun onFailure(code: String?, message: String?) {
                    mView.hideLoading()
                    mView.showError(message)
                }
            })
    }
}