package com.eeepay.zzq.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager


/**
 * 描述：主tab adapter 适配器
 * 作者：zhuangzeqin
 * 时间: 2020/8/10-17:55
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class MainAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm),
    ViewPager.OnPageChangeListener {
    lateinit var mFragments: List<Fragment>//Fragment 实例
    lateinit var mTitle: List<String>//标题
    //接口
    var mPageSelectClickEvent:OnPageSelectClickEvent?= null
    //构造函数
    constructor(
        subListFragment: List<Fragment>,
        title: List<String>,
        fm: FragmentManager
    ) : this(fm) {
        this.mFragments = subListFragment
        this.mTitle = title;
    }

    override fun getItem(position: Int): Fragment = mFragments[position]

    /**
     * Return the number of views available.
     */
    override fun getCount(): Int = mFragments.size


    /**
     * Called when the scroll state changes. Useful for discovering when the user
     * begins dragging, when the pager is automatically settling to the current page,
     * or when it is fully stopped/idle.
     *
     * @param state The new scroll state.
     * @see ViewPager.SCROLL_STATE_IDLE
     *
     * @see ViewPager.SCROLL_STATE_DRAGGING
     *
     * @see ViewPager.SCROLL_STATE_SETTLING
     */
    override fun onPageScrollStateChanged(state: Int) {
        TODO("Not yet implemented")
    }

    /**
     * This method will be invoked when the current page is scrolled, either as part
     * of a programmatically initiated smooth scroll or a user initiated touch scroll.
     *
     * @param position Position index of the first page currently being displayed.
     * Page position+1 will be visible if positionOffset is nonzero.
     * @param positionOffset Value from [0, 1) indicating the offset from the page at position.
     * @param positionOffsetPixels Value in pixels indicating the offset from position.
     */
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        TODO("Not yet implemented")
    }

    /**
     * This method will be invoked when a new page becomes selected. Animation is not
     * necessarily complete.
     *
     * @param position Position index of the new selected page.
     */
    override fun onPageSelected(position: Int) {
        mPageSelectClickEvent?.let { mPageSelectClickEvent!!.getCurrentTab(position)  }
    }

    /**
     * 使用这个方式，让页面不缓存，能够在清除fragment的时候对其做了删除
     *
     * @param object
     * @return
     */
//    override fun getItemPosition(`object`: Any): Int {
//        return PagerAdapter.POSITION_NONE;
//    }

    interface OnPageSelectClickEvent
    {
        fun getCurrentTab(index: Int)
    }
}