package com.eeepay.zzq.mvp.model.login

import androidx.lifecycle.LifecycleOwner
import com.eeepay.zzq.Api
import com.eeepay.zzq.bean.LoginktInfo
import com.eeepay.zzq.mvp.model.IModelContract
import com.eeepay.zzq.mvp.model.base.BaseModel
import com.eeepay.zzq.mvp.model.base.OnCallbackImpl2
import com.eeepay.zzq.parse.ErrorInfo
import com.eeepay.zzq.utils.EncRSA
import com.eeepay.zzq.utils.FastSharedPreferencesTools
import com.rxjava.rxlife.lifeOnMain
import rxhttp.RxHttp

/**
 * 描述：登录请求的Model
 * 作者：zhuangzeqin
 * 时间: 2020/8/6-12:25
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class LoginModel3(owner: LifecycleOwner) : BaseModel(owner),
    IModelContract.ILoginModel2<LoginktInfo.Data> {
    override fun reqLogin(
        userName: String,
        password: String,
        onCallBack: OnCallbackImpl2<LoginktInfo.Data>?
    )
    {
        checkNotNull(onCallBack) { "=== resultCallBack is null===" }
        val encBase64Pass = EncRSA.EncBase64Pass(password) //RSA 加密密码
        /** ------注释说明-参数的封装-------  */
        mParams.run {
            put("userName", userName)
            put("password", encBase64Pass) //RSA加密后的密码
            val lib_team_id = "110010"//组织id
            put("agentOem", lib_team_id) //代理商oem值 盛代宝为200010
        }
        RxHttp.postJson(Api.API_LOGIN_URL).addAll(mParams)
            .asResultCallBack(LoginktInfo.Data::class.java).lifeOnMain(this).subscribe(
                { t -> with(t) {
                    //保存本地
                    FastSharedPreferencesTools.getInstance().putSerializable("LoginInfo", this)
                    onCallBack.onSucess(this)
                }
                }, { error ->
                    val errorInfo = ErrorInfo(error)//错误信息
                    onCallBack.onFailure(errorInfo.errorCode.toString(), errorInfo.errorMsg)
                }
            )

    }


}