package com.eeepay.zzq.mvp.ui

import com.eeepay.zzq.agent_kotlin.R
import com.eeepay.zzq.base.BaseMvpActivity
import com.eeepay.zzq.bean.PubDataktInfo
import com.eeepay.zzq.mvp.presenter.base.CreatePresenter
import com.eeepay.zzq.mvp.presenter.base.PresenterVariable
import com.eeepay.zzq.mvp.presenter.login.ILoginView
import com.eeepay.zzq.mvp.presenter.login.LoginPresenter
import com.eeepay.zzq.mvp.presenter.pubdata.CheckVersionPresenter
import com.eeepay.zzq.mvp.presenter.pubdata.CheckVersionView
import com.eeepay.zzq.mvp.presenter.pubdata.IPublicDataView
import com.eeepay.zzq.mvp.presenter.pubdata.PubDataPresenter
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2020/8/5-11:27
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */

@CreatePresenter(presenter = [LoginPresenter::class, PubDataPresenter::class, CheckVersionPresenter::class])
class LoginAct : BaseMvpActivity<LoginPresenter>(), ILoginView, IPublicDataView, CheckVersionView {
    @PresenterVariable
    var mPubDataPresenter: PubDataPresenter? = null

    @PresenterVariable
    var mCheckVersionPresenter: CheckVersionPresenter? = null

    override fun eventOnClick() {
        btn_login.setOnClickListener { view ->
//            getPresenter().login(this, "18681490423", "123456")


            val langs = listOf("C","C++","Java","Python","JavaScript","cg","ca","c4","c1","c8")

            langs
                .filter{ it.startsWith("c")}
                .sortedBy{ it }
                .map{ it.toUpperCase() }
                .forEach{ println(it) }
        }
        btn_getPub.setOnClickListener { view ->
            mPubDataPresenter?.getPubDataInfo(this)
        }
        //升级断点下载测试demo
        btn_download.setOnClickListener { view ->
            val downloadUrl: String =
//                "http://app-client.oss-cn-hangzhou.aliyuncs.com/oem/android/zdb_v1.0.3_20180125_2243.apk"
//            https://app-client.oss-cn-hangzhou.aliyuncs.com/oem/android/sdb_v3.2.7_20200702_1526.apk
                "https://app-client.oss-cn-hangzhou.aliyuncs.com/oem/android/sdb_v3.2.7_20200702_1526.apk"
            mCheckVersionPresenter!!.reqCheckVersion(this, downloadUrl)
        }

    }

    override fun getContentView(): Int {
        return R.layout.activity_login
    }

    override fun initView() {

    }

    override fun initData() {
        GlobalScope.launch {  }

    }

    override fun onLoginSuccess(msg: String) {
        showError(msg)
        goActivity(this, RxMainAct::class.java)
    }

    override fun showPubDataInfo(pubDataInfo: PubDataktInfo.Data) {
        showError(pubDataInfo.toString())
    }

    /**
     * 抽象的设置的标题的方法 子类实现
     */
    override fun setTitle(): String? {
        return null
    }

    /**
     * progress 下载进度回调,0-100，仅在进度有更新时才会回调
     * currentSize 当前已下载的字节大小
     * totalSize 要下载的总字节大
     */
    override fun onCheckVersionProgress(progress: Int, currentSize: Long, totalSize: Long) {
        progress_bar_h.setProgress(progress)
        progress_msg.setText("$progress%")
    }

    /**
     * 完成
     */
    override fun onCompleteProgress(msg: String) {
        showError(msg)
        progress_msg.setText("$msg 下载完成")
    }

}