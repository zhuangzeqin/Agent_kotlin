package com.eeepay.zzq.mvp.presenter.pubdata

import androidx.lifecycle.LifecycleOwner
import com.eeepay.zzq.mvp.model.base.IBaseContract
import com.eeepay.zzq.mvp.model.pubdata.CheckVersionMode
import com.eeepay.zzq.mvp.presenter.IPresenterContract
import com.eeepay.zzq.mvp.presenter.base.BasePresenter

/**
 * 描述：检查版本升级下载断点支持
 * 作者：zhuangzeqin
 * 时间: 2020/8/31-11:39
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class CheckVersionPresenter : BasePresenter<CheckVersionView>(),
    IPresenterContract.ICheckVersionPresenter {
    lateinit var mCheckVersionMode: CheckVersionMode//mode
    override fun reqCheckVersion(owner: LifecycleOwner, downloadUrl: String) {
        if (!isAttachView) return
        mCheckVersionMode = CheckVersionMode(owner)
        mCheckVersionMode.reqcheckVersion(downloadUrl,
            object : IBaseContract.IResultProgressCallBack<String> {
                /**
                 * 成功将结果回调出去
                 */
                override fun onSucess(response: String) {
                    mView.onCompleteProgress(response)
                }

                /**
                 * progress 下载进度回调,0-100，仅在进度有更新时才会回调
                 * currentSize 当前已下载的字节大小
                 * totalSize 要下载的总字节大
                 */
                override fun onProgress(progress: Int, currentSize: Long, totalSize: Long) {
                    mView.onCheckVersionProgress(progress, currentSize, totalSize)
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