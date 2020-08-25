package com.eeepay.zzq.mvp.model.index

import androidx.lifecycle.LifecycleOwner
import com.eeepay.zzq.Api
import com.eeepay.zzq.bean.CurrDayDataInfo
import com.eeepay.zzq.mvp.model.IModelContract
import com.eeepay.zzq.mvp.model.base.BaseModel
import com.eeepay.zzq.mvp.model.base.IBaseContract
import com.eeepay.zzq.parse.ErrorInfo
import com.rxjava.rxlife.lifeOnMain
import rxhttp.RxHttp

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2020/8/25-11:12
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class LoadCurrDayDataModel(owner: LifecycleOwner) : BaseModel(owner),
    IModelContract.ILoadCurrDayDataModel<CurrDayDataInfo.dataBean> {
    override fun loadCurrDayData(resultCallBack: IBaseContract.IResultCallBack<CurrDayDataInfo.dataBean>?) {
        requireNotNull(resultCallBack, { "=== resultCallBack is null===" })
        RxHttp.postJson(Api.API_LOADCURRDAYDATA_URL)
            .asResultCallBack(CurrDayDataInfo.dataBean::class.java).lifeOnMain(this).subscribe(
                { t ->
                    resultCallBack.onSucess(t)
                },
                { error ->
                    val errorInfo = ErrorInfo(error)//错误信息
                    resultCallBack.onFailure(errorInfo.errorCode.toString(), errorInfo.errorMsg)
                })
    }

}