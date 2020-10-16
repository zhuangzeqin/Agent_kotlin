package com.eeepay.zzq.mvp.model.pubdata

import android.annotation.SuppressLint
import androidx.lifecycle.LifecycleOwner
import com.eeepay.zzq.mvp.model.IModelContract
import com.eeepay.zzq.mvp.model.base.BaseModel
import com.eeepay.zzq.mvp.model.base.IBaseContract
import com.eeepay.zzq.parse.ErrorInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import rxhttp.RxHttp
import rxhttp.wrapper.annotations.NonNull
import java.io.File

/**
 * 描述：检查版本升级
 * 作者：zhuangzeqin
 * 时间: 2020/8/31-10:25
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class CheckVersionMode(owner: LifecycleOwner) : BaseModel(owner),
    IModelContract.ICheckVersionMode<String> {

    @SuppressLint("CheckResult")
    override fun reqcheckVersion(
        @NonNull downloadUrl: String,
        resultCallBack: IBaseContract.IResultProgressCallBack<String>?
    ) {
        checkNotNull(resultCallBack, { "=== resultCallBack is null===" })
        val destPath: String = "/sdcard/aaabbb/" + "test.apk"
        val length: Long = File(destPath).length() //已下载的文件长度
        RxHttp.get(downloadUrl)
            .setRangeHeader(length, true)  //设置开始下载位置，结束位置默认为文件末尾
            .asDownload(destPath, AndroidSchedulers.mainThread()) { progress ->
                //下载进度回调,0-100，仅在进度有更新时才会回调
                val progress1 = progress.progress//当前进度 0-100
                val currentSize = progress.currentSize//当前已下载的字节大小
                val totalSize = progress.totalSize//要下载的总字节大
                resultCallBack.onProgress(progress1, currentSize, totalSize)
            }.subscribe({ s ->
                //s为String类型
                //下载成功，处理相关逻辑
                resultCallBack.onSucess(s.toString())
            }, { error ->
                val errorInfo = ErrorInfo(error)//错误信息
                //下载失败，处理相关逻辑
                resultCallBack.onFailure(errorInfo.errorCode.toString(), errorInfo.errorMsg)
            })

    }
}