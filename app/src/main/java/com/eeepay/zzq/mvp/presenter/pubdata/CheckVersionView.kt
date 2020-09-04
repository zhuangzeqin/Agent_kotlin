package com.eeepay.zzq.mvp.presenter.pubdata

import com.eeepay.zzq.mvp.presenter.base.interfaces.IBaseView

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2020/8/31-11:36
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
interface CheckVersionView :IBaseView{
    /**
     * progress 下载进度回调,0-100，仅在进度有更新时才会回调
     * currentSize 当前已下载的字节大小
     * totalSize 要下载的总字节大
     */
    fun onCheckVersionProgress(progress: Int,currentSize:Long,totalSize:Long)

    /**
     * 完成
     */
    fun onCompleteProgress(msg:String)
}