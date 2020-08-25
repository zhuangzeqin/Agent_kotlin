package com.eeepay.zzq.mvp.presenter.index

import androidx.lifecycle.LifecycleOwner
import com.eeepay.zzq.mvp.model.base.IBaseContract
import com.eeepay.zzq.mvp.model.index.QueryMerchantParamsModel
import com.eeepay.zzq.mvp.presenter.IPresenterContract
import com.eeepay.zzq.mvp.presenter.base.BasePresenter

/**
 * 描述：查询商户需要的参数
 * 作者：zhuangzeqin
 * 时间: 2020/8/25-15:53
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class QueryMerchantParamsPresenter : BasePresenter<QueryMerchantParamsView>(),
    IPresenterContract.IQueryMerchantParamsPresenter {
    private lateinit var mode: QueryMerchantParamsModel
    override fun getQueryMerchantParams(owner: LifecycleOwner) {
        if (!isAttachView) return
        mView.showLoading()
        mode = QueryMerchantParamsModel(owner)
        mode.reqQueryMerchantParams(object : IBaseContract.IResultCallBack<String> {
            /**
             * 成功将结果回调出去
             */
            override fun onSucess(response: String) {
                mView.hideLoading()
                mView.showQueryMerchantParams(response)
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