package com.eeepay.zzq.mvp.ui.home

import android.os.Bundle
import com.eeepay.zzq.UrlConfitkt
import com.eeepay.zzq.agent_kotlin.R
import com.eeepay.zzq.base.BaseMvpFragment
import com.eeepay.zzq.mvp.presenter.base.CreatePresenter
import com.eeepay.zzq.mvp.presenter.base.PresenterVariable
import com.eeepay.zzq.mvp.presenter.index.LoadCurrDayDataPresenter
import com.eeepay.zzq.mvp.presenter.index.LoadCurrDayDataView
import com.eeepay.zzq.mvp.presenter.index.QueryMerchantParamsPresenter
import com.eeepay.zzq.mvp.presenter.index.QueryMerchantParamsView
import com.eeepay.zzq.mvp.presenter.login.ILoginView
import com.eeepay.zzq.mvp.presenter.login.LoginPresenter2
import com.eeepay.zzq.utils.PreferenceUtils
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * 描述：首页
 * 作者：zhuangzeqin
 * 时间: 2020/8/11-16:34
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
@CreatePresenter(presenter = [LoadCurrDayDataPresenter::class, QueryMerchantParamsPresenter::class, LoginPresenter2::class])
class HomeFragment : BaseMvpFragment<LoadCurrDayDataPresenter>(), LoadCurrDayDataView,
    QueryMerchantParamsView, ILoginView {
    @PresenterVariable
    lateinit var mQueryMerchantParamsPresenter:QueryMerchantParamsPresenter
    @PresenterVariable
    lateinit var mLoginPresenter:LoginPresenter2
    /**
     * 创建实例
     */
    fun newInstance(title: String?): HomeFragment {
        val bundle = Bundle()
        bundle.putString(UrlConfitkt.BUNDLE_TITLE, title)
        val fragment: HomeFragment = HomeFragment()
        fragment.arguments = bundle
        return fragment
    }

    /**
     * 获取布局
     */
    override fun getLayoutId(): Int = R.layout.fragment_home


    /**
     * 初始化
     */
    override fun init() {
       val title = mBundle!!.getString(UrlConfitkt.BUNDLE_TITLE)
        home_txt_msg.text = title

        home_btn_data1.setOnClickListener { view ->
            getPresenter().getLoadCurrDayData(this)
            mLoginPresenter.login(this, "18681490423", "123456")
        }
        home_btn_data2.setOnClickListener { view ->
            mQueryMerchantParamsPresenter.getQueryMerchantParams(this)
        }
    }

    override fun showLoadCurrDayData(msg: String) {
        showError(msg)
    }

    override fun showQueryMerchantParams(message: String) {
        showError(message)
    }

    override fun onLoginSuccess(msg: String) {
        val instance = PreferenceUtils.getInstance(mContext)
        PreferenceUtils.saveParam("zzq",msg)
        showError(msg)
    }
}