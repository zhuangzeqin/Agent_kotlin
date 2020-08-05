package com.eeepay.zzq.mvp.presenter.login

import androidx.lifecycle.LifecycleOwner
import com.eeepay.zzq.Api
import com.eeepay.zzq.bean.LoginktInfo
import com.eeepay.zzq.mvp.presenter.IPresenterContract.ILoginPresenter
import com.eeepay.zzq.parse.ErrorInfo
import com.eeepay.zzq.presenter.base.BasePresenter
import com.eeepay.zzq.utils.EncRSA
import com.eeepay.zzq.utils.FastSharedPreferencesTools
import com.rxjava.rxlife.lifeOnMain
import rxhttp.RxHttp

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2020/8/5-13:44
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class LoginPresenter : BasePresenter<ILoginView>(), ILoginPresenter {
    override fun login(owner: LifecycleOwner, username: String, password: String) {
        //View是否绑定 如果没有绑定，就不执行网络请求
        if (!isAttachView) return
        mView.showLoading()
        val encBase64Pass = EncRSA.EncBase64Pass(password) //RSA 加密密码
        /** ------注释说明-参数的封装-------  */
        val mParams: HashMap<String, String> = HashMap(2)
        mParams.put("userName", username)
        mParams.put("password", encBase64Pass) //RSA加密后的密码
        val lib_team_id = "110010"//组织id
        mParams.put("agentOem", lib_team_id) //代理商oem值 盛代宝为200010
        RxHttp.postJson(Api.API_LOGIN_URL).addAll(mParams)
            .asResultCallBack(LoginktInfo.Data::class.java).lifeOnMain(owner).subscribe(
                { t -> with(t)
                {
                    mView.hideLoading()
                    mView.onLoginSuccess(toString())
                    FastSharedPreferencesTools.getInstance().putSerializable("LoginInfo", t)
                }

                }, { error ->
                    mView.hideLoading()
                    val errorInfo = ErrorInfo(error)//错误信息
                    mView.showError(errorInfo.errorMsg)
                }
            )
    }
}