package com.eeepay.zzq.mvp.presenter.pubdata

import androidx.lifecycle.LifecycleOwner
import com.eeepay.zzq.bean.PubDataktInfo
import com.eeepay.zzq.mvp.model.base.IBaseContract
import com.eeepay.zzq.mvp.model.pubdata.PubDataModel
import com.eeepay.zzq.mvp.presenter.IPresenterContract
import com.eeepay.zzq.mvp.presenter.base.BasePresenter
import rxhttp.wrapper.annotations.NonNull

/**
 * 描述：获取公共数据
 * 作者：zhuangzeqin
 * 时间: 2020/8/6-17:33
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class PubDataPresenter:BasePresenter<IPublicDataView>(), IPresenterContract.IPubDataPresenter {
    private lateinit var pubDataModel: PubDataModel//model
    override fun getPubDataInfo(@NonNull owner: LifecycleOwner) {
        //View是否绑定 如果没有绑定，就不执行网络请求
        if (!isAttachView) return
        pubDataModel = PubDataModel(owner)
        pubDataModel.reqPublicData(object :IBaseContract.IResultCallBack<PubDataktInfo.Data>{
            /**
             * 成功将结果回调出去
             */
            override fun onSucess(response: PubDataktInfo.Data) {
                mView.showPubDataInfo(response)
            }

            /**
             * 失败时将结错误信息回调出去
             */
            override fun onFailure(code: String?, message: String?) {
                mView.showError(message)
            }
        })
    }
}