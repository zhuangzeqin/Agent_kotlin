package com.eeepay.zzq.mvp.presenter.pubdata

import com.eeepay.zzq.bean.PubDataktInfo
import com.eeepay.zzq.mvp.presenter.base.interfaces.IBaseView

/**
 * 描述：公共数据的View
 * 作者：zhuangzeqin
 * 时间: 2020/8/6-17:30
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
interface IPublicDataView : IBaseView {
    fun showPubDataInfo(pubDataInfo: PubDataktInfo.Data)
}