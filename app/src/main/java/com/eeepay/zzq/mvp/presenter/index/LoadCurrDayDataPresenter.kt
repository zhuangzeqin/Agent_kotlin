package com.eeepay.zzq.mvp.presenter.index

import androidx.lifecycle.LifecycleOwner
import com.eeepay.zzq.bean.CurrDayDataInfo
import com.eeepay.zzq.mvp.model.base.IBaseContract
import com.eeepay.zzq.mvp.model.index.LoadCurrDayDataModel
import com.eeepay.zzq.mvp.presenter.IPresenterContract
import com.eeepay.zzq.mvp.presenter.base.BasePresenter

/**
 * 描述：获取首页的数据
 * 作者：zhuangzeqin
 * 时间: 2020/8/25-11:36
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class LoadCurrDayDataPresenter:BasePresenter<LoadCurrDayDataView>(),IPresenterContract.ILoadCurrDayDataPresenter {
    private lateinit var mode: LoadCurrDayDataModel//model
    override fun getLoadCurrDayData(owner: LifecycleOwner) {
        if(!isAttachView) return
        mView.showLoading()
        mode = LoadCurrDayDataModel(owner)
        mode.loadCurrDayData(object :IBaseContract.IResultCallBack<CurrDayDataInfo.dataBean>{
            /**
             * 成功将结果回调出去
             */
            override fun onSucess(response: CurrDayDataInfo.dataBean) {
                mView.hideLoading()
                mView.showLoadCurrDayData(response.toString())
            }

            /**
             * 失败时将结错误信息回调出去
             */
            override fun onFailure(code: String?, message: String?) {
                mView.hideLoading()
                mView.showError(message)
            }
        })
    }
}