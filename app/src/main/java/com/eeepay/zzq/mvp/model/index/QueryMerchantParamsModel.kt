package com.eeepay.zzq.mvp.model.index

import androidx.lifecycle.LifecycleOwner
import com.eeepay.zzq.Api
import com.eeepay.zzq.mvp.model.IModelContract
import com.eeepay.zzq.mvp.model.base.BaseModel
import com.eeepay.zzq.mvp.model.base.IBaseContract
import com.eeepay.zzq.parse.ErrorInfo
import com.rxjava.rxlife.lifeOnMain
import rxhttp.RxHttp

/**
 * 描述：获取商户查询需要的参数
 * 作者：zhuangzeqin
 * 时间: 2020/8/25-15:40
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class QueryMerchantParamsModel(owner: LifecycleOwner) : BaseModel(owner),
    IModelContract.IQueryMerchantParamsModel<String> {
    override fun reqQueryMerchantParams(resultCallBack: IBaseContract.IResultCallBack<String>?) {
        checkNotNull(resultCallBack, { "=== resultCallBack is null===" })
        RxHttp.get(Api.API_QUERYMERCHANTPARAMS_URL).asString().lifeOnMain(this)
            .subscribe({ response ->
                resultCallBack.onSucess(response)
            }, { error ->
                val errorInfo = ErrorInfo(error)//错误信息
                resultCallBack.onFailure(errorInfo.errorCode.toString(), errorInfo.errorMsg)
            })
    }
}