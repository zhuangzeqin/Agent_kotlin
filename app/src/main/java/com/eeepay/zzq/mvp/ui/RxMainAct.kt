package com.eeepay.zzq.mvp.ui

import androidx.fragment.app.Fragment
import com.eeepay.zzq.adapter.MainAdapter
import com.eeepay.zzq.agent_kotlin.R
import com.eeepay.zzq.base.BaseMvpActivity
import com.eeepay.zzq.mvp.presenter.base.BasePresenter
import com.eeepay.zzq.mvp.presenter.base.interfaces.IBaseView
import com.eeepay.zzq.mvp.ui.app.APPFragment
import com.eeepay.zzq.mvp.ui.data.DataFragment
import com.eeepay.zzq.mvp.ui.home.HomeFragment
import com.eeepay.zzq.mvp.ui.my.MyFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 描述：主界面
 * 作者：zhuangzeqin
 * 时间: 2020/8/10-17:50
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class RxMainAct : BaseMvpActivity<BasePresenter<IBaseView>>() {
    /**
     * 布局id
     */
    override fun getContentView(): Int {
        return R.layout.activity_main
    }

    /**
     * 初始化操作
     */
    override fun initView() {
        isShowBack(false)
    }

    /**
     * 初始化点击事件操作
     */
    override fun eventOnClick() {
    }

    /**
     * 初始化数据
     */
    override fun initData() {
        //标题创建一个不可变的list
        val title = listOf<String>("首页", "数据", "应用", "我的")
        val mutableListOf = mutableListOf<Fragment>()
        mutableListOf.add(HomeFragment().newInstance(title[0]))
        mutableListOf.add(DataFragment().newInstance(title[1]))
        mutableListOf.add(APPFragment().newInstance(title[2]))
        mutableListOf.add(MyFragment().newInstance(title[3]))
        val mainAdapter = MainAdapter(mutableListOf, title, supportFragmentManager)
        //设置接口
        mainAdapter.mPageSelectClickEvent = object : MainAdapter.OnPageSelectClickEvent {
            override fun getCurrentTab(index: Int) {
                when (index) {
                    0 -> setTitle("首页")
                    1 -> setTitle("数据")
                    2 -> setTitle("应用")
                    3 -> setTitle("我的")
                    else -> setTitle("")
                }
            }
        }
        mViewPager.adapter = mainAdapter
        mViewPager.addOnPageChangeListener(mainAdapter)
        alphaIndicator.setViewPager(mViewPager)
        alphaIndicator.getTabView(0).showNumber(5)
        alphaIndicator.getTabView(1).showNumber(10089)
        alphaIndicator.getTabView(2).showNumber(88)
        alphaIndicator.getTabView(3).showPoint()
    }

    /**
     * 抽象的设置的标题的方法 子类实现
     */
    override fun setTitle(): String? {
        return "首页"
    }
}