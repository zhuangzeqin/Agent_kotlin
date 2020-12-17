package com.eeepay.zzq.mvp.presenter.login

import androidx.lifecycle.LifecycleOwner
import com.eeepay.zzq.mvp.model.base.OnCallbackImpl2
import com.eeepay.zzq.mvp.model.login.LoginModel3
import com.eeepay.zzq.mvp.presenter.IPresenterContract.ILoginPresenter
import com.eeepay.zzq.mvp.presenter.base.BasePresenter

/**
 * 描述：登录的LoginPresenter
 * 作者：zhuangzeqin
 * 时间: 2020/8/5-13:44
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class LoginPresenter2 : BasePresenter<ILoginView>(), ILoginPresenter {
    lateinit var loginModel: LoginModel3 //model
    override fun login(owner: LifecycleOwner, username: String, password: String) {
        //View是否绑定 如果没有绑定，就不执行网络请求
        if (!isAttachView) return
        mView.showLoading()
        loginModel = LoginModel3(owner)
        loginModel.run {
            reqLogin(
                username,
                password,
                OnCallbackImpl2(onSuccessF = {
                    mView.hideLoading()
                    mView.onLoginSuccess(it.toString())
                }, onErrorF = { code, msg ->
                    mView.hideLoading()
                    mView.showError(msg)
                })
            )
        }
    }


//    OnCallbackImpl2({ reponse->
//        mView.hideLoading()
//        mView.onLoginSuccess(reponse.toString())
//    }) { code, msg ->
//        mView.hideLoading()
//        mView.showError(code + msg)
//    }
}