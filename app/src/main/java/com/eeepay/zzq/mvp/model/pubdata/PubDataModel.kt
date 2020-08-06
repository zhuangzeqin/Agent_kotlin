package com.eeepay.zzq.mvp.model.pubdata

import androidx.lifecycle.LifecycleOwner
import com.eeepay.zzq.Api
import com.eeepay.zzq.bean.PubDataktInfo
import com.eeepay.zzq.mvp.model.IModelContract
import com.eeepay.zzq.mvp.model.base.BaseModel
import com.eeepay.zzq.mvp.model.base.IBaseContract
import com.eeepay.zzq.parse.ErrorInfo
import com.rxjava.rxlife.lifeOnMain
import rxhttp.RxHttp

/**
 * 描述：公共数据的model
 * 作者：zhuangzeqin
 * 时间: 2020/8/6-16:47
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class PubDataModel(owner: LifecycleOwner) : BaseModel(owner),
    IModelContract.IPublicDataModel<PubDataktInfo.Data> {
    override fun reqPublicData(resultCallBack: IBaseContract.IResultCallBack<PubDataktInfo.Data>?) {
        checkNotNull(resultCallBack, { "=== resultCallBack is null===" })
        RxHttp.postJson(Api.API_GETPUBLICDATA_URL).asResultCallBack(PubDataktInfo.Data::class.java)
            .lifeOnMain(this).subscribe(
                { t ->
                    with(t) {
                        resultCallBack.onSucess(this)
                    }
                }, { error ->
                    val errorInfo = ErrorInfo(error)//错误信息
                    resultCallBack.onFailure(errorInfo.errorCode.toString(), errorInfo.errorMsg)
                })

    }
}