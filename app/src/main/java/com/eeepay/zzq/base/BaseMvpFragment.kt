package com.eeepay.zzq.base

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import com.eeepay.zzq.agent_kotlin.R
import com.eeepay.zzq.event.AppBus
import com.eeepay.zzq.mvp.presenter.base.BasePresenter
import com.eeepay.zzq.mvp.presenter.base.PresenterDispatch
import com.eeepay.zzq.mvp.presenter.base.PresenterProviders
import com.eeepay.zzq.mvp.presenter.base.interfaces.IBaseView
import com.eeepay.zzq.utils.ToastUtils
import com.eeepay.zzq.view.DialogHelper
import rxhttp.wrapper.annotations.NonNull

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2020/8/7-10:07
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
abstract class BaseMvpFragment<P : BasePresenter<*>> : Fragment(), IBaseView {
    protected val mTag = BaseMvpFragment::class.java.simpleName//tag 标签
    protected lateinit var mRootView: View//根目录
    protected lateinit var mContext: Context//上下文对象
    protected var mBundle:Bundle? = null//bundle 对象
    protected lateinit var mActivity:Activity//Activity
    protected var inflater: LayoutInflater? = null
    // 标志位 标志已经初始化完成。
    protected var isPrepared = false
    //标志位 fragment是否可见
    protected var isVisibles = false

    /**
     * 解析用到的注解以及完成绑定和解绑 View 等一些公共的 Presenter 操作
     */
    private var mPresenterProviders: PresenterProviders? = null

    /**
     * presenter 的调度器
     */
    private var mPresenterDispatch: PresenterDispatch? = null

    /**
     * 等待加载的对话框
     */
    private var mProgressDialog: ProgressDialog? = null

    /**
     * Called when a fragment is first attached to its context.
     * [.onCreate] will be called after this.
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        mActivity = context as Activity
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mBundle = arguments //参数Bunlder
        }
    }

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        if (mRootView != null) {
            val parent = mRootView.parent as ViewGroup
            parent?.removeView(mRootView)
        } else {
            mRootView = inflater.inflate(getLayoutId(), container, false)
            mActivity = activity!!
            mContext = mActivity
            this.inflater = inflater
        }
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)
        AppBus.getInstance().register(this) //订阅事件
        mPresenterProviders = PresenterProviders.inject(this)
        mPresenterDispatch = PresenterDispatch(mPresenterProviders)
        //绑View的操作
        mPresenterDispatch!!.attachView<P>(mContext, this)
        mPresenterDispatch!!.onCreatePresenter<P>(savedInstanceState)
        isPrepared = true
        init()
        lazyLoad()
    }
    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDetach() {
        //解绑View的操作
        mPresenterDispatch!!.detachView<P>()
        AppBus.getInstance().unregister(this) //订阅事件
        super.onDetach()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState!!)
        mPresenterDispatch!!.onSaveInstanceState<P>(outState)
    }
    //后面定义的泛型 P 主要是为了一个 Presenter 的时候使用 getPresenter() 方法时用到的，index =0,数组只有1个
    protected open fun getPresenter(): P {
        return mPresenterProviders!!.getPresenter(0)
    }
    /**
     * ------获取 presenter 提供者--------
     */
    open fun getPresenterProviders(): PresenterProviders? {
        return mPresenterProviders
    }
    /**
     * 懒加载
     */
      private fun lazyLoad() {
        if (!isPrepared || !isVisible) {
            return
        }
        lazyLoadData()
        isPrepared = false
    }

    /**
     * 懒加载
     */
    @CallSuper
    protected open fun lazyLoadData() {
    }

    /**
     * 可见的情况下
     */
    protected open fun onVisible() {
        lazyLoad() //懒加载
    }

    /**
     * 不可见的情况下
     */
    protected open fun onInvisible() {}

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (userVisibleHint) {
            isVisibles = true
            onVisible()
        } else {
            isVisibles = false
            onInvisible()
        }
    }
    override fun showLoading() {
        showWaitDialog(getString(R.string.loading))
    }

    override fun showNetworkError(code: Int, message: String?) {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        if (mActivity != null && !mActivity.isFinishing && mProgressDialog != null && isVisible && mProgressDialog!!.isShowing()) {
            try {
                mProgressDialog!!.dismiss()
                mProgressDialog = null
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    override fun showError(error: String?) {
        if (isVisible)
        error?.let { ToastUtils.showShort(it)}
    }

    /**
     * 显示等待对话框
     */
    @UiThread
    open fun showWaitDialog(message: String?): ProgressDialog? {
        if (mActivity != null && !mActivity.isFinishing && isVisible)
        {
            if (mProgressDialog == null) {
                mProgressDialog = DialogHelper.getProgressDialog(mActivity, message)
                mProgressDialog!!.setCanceledOnTouchOutside(false)
            }
            if (mProgressDialog != null) {
                mProgressDialog!!.setMessage(message)
                mProgressDialog!!.show()
            }
            return mProgressDialog
        }
        return null
    }

    override fun onPause() {
        super.onPause()
        if (mProgressDialog != null && !mActivity.isFinishing) {
            mProgressDialog!!.dismiss()
        }
    }

    /**
     * 跳转activity
     */
    // Java 并不认识默认参数，这个时候就需要用到 @JvmOverloads 注解
    //在 Kotlin 中可以使用默认参数的方式来代替函数的重载，就像下面这样
    //open关键字方法就可以被重写,类可以被继承； kotlin 默认所有的类都是final 即不可继承
    //let扩展函数的实际上是一个作用域函数，当你需要去定义一个变量在一个特定的作用域范围内，let函数的是一个不错的选择；let函数另一个作用就是可以避免写一些判断null的操作。
    @JvmOverloads
    open fun goActivity(
        @NonNull context: Context,
        @NonNull descClass: Class<*>,
        bundle: Bundle? = null,
        requestCode: Int = -1,
        flags: Int? = -1
    ) {
        val javaClass = context.javaClass
        if (javaClass == descClass) {
            return
        }
        try {
            val intent = Intent()
            intent.setClass(context, descClass)
            //表示object不为null的条件下，才会去执行let函数体
            flags?.let { intent.addFlags(it) }
            bundle?.let { intent.putExtras(it) }
            //API才发现requestCode >= 0才起作用;如果用负值的requestCode和调用startActivity是一样的，所以代码不走startActivityForResult而是startActivity
            (context as Activity).startActivityForResult(intent, requestCode)
            context.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    /**
     * 获取布局
     */
    @LayoutRes abstract fun getLayoutId(): Int

    /**
     * 初始化
     */
    protected abstract fun init()
}