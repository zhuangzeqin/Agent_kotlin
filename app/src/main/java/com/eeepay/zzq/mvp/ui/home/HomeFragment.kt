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
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * 描述：首页
 * 作者：zhuangzeqin
 * 时间: 2020/8/11-16:34
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
@CreatePresenter(presenter = [LoadCurrDayDataPresenter::class, QueryMerchantParamsPresenter::class])
class HomeFragment : BaseMvpFragment<LoadCurrDayDataPresenter>(), LoadCurrDayDataView,
    QueryMerchantParamsView {
    @PresenterVariable
    lateinit var mQueryMerchantParamsPresenter:QueryMerchantParamsPresenter
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
}