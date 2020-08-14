package com.eeepay.zzq.base

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.TextView
import androidx.annotation.UiThread
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.eeepay.zzq.agent_kotlin.R
import com.eeepay.zzq.event.AppBus
import com.eeepay.zzq.listener.NoRepeatClickListener
import com.eeepay.zzq.mvp.presenter.base.BasePresenter
import com.eeepay.zzq.mvp.presenter.base.PresenterDispatch
import com.eeepay.zzq.mvp.presenter.base.PresenterProviders
import com.eeepay.zzq.mvp.presenter.base.interfaces.IBaseView
import com.eeepay.zzq.utils.ActivityStackManager
import com.eeepay.zzq.utils.ToastUtils
import com.eeepay.zzq.utils.statusbarstool.StatusBarUtil
import com.eeepay.zzq.view.DialogHelper
import pub.devrel.easypermissions.EasyPermissions
import rxhttp.wrapper.annotations.NonNull

/**
 * 描述：mvp 抽象类基类
 * 作者：zhuangzeqin
 * 时间: 2020/8/4-15:27
 * 邮箱：zzq@eeepay.cn
 * 备注:
 * //out即java中的<? extends T> 意为仅可作为返回值，返回值类型是T或T的子类
  //in即java中的<? super T> 意为仅可作为参数传入，传入的参数类型是T或T的子类
 */
abstract class BaseMvpActivity<out P : BasePresenter<out IBaseView>> : AppCompatActivity(), IBaseView {
    protected val TAG = BaseMvpActivity::class.java.simpleName//TAG 标签贴

    /**
     * ------presenter 提供者 解析用到的注解以及完成绑定和解绑 View 等一些公共的 Presenter 操作--------
     **/
    private var mPresenterProviders: PresenterProviders? = null

    /**
     * ------presenter 的调度器--------
     **/
    private var mPresenterDispatch: PresenterDispatch? = null

    /* ------注释说明---页面弹框控件对象----- */
    private var mProgressDialog: ProgressDialog? = null

    /* ------注释说明---上下文对象----- */
    protected var mContext: Context? = null

    /* ------注释说明----Bundle---- */
    protected var mBundle: Bundle? = null

    /***获取Toolbar */
    protected var mToolbar: Toolbar? = null

    /***获取title */
    protected var mTitle: TextView? = null

    /***获取右边的标题 */
    protected var mRightTitle: TextView? = null

    /***获取右边中间标题 */
    protected var mRightCenterTitle: TextView? = null

    /***获取返回的图标 */
    protected var mBack: TextView? = null

    /**
     * 初始化
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        /** ------不可横屏幕--------  */
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(getContentView())
        mContext = this
        /** 设置添加到ActivityManager 管理类 */
        ActivityStackManager.getInstance().push(this)
        AppBus.getInstance().register(this) //订阅事件
        /* ------注释说明---mBundle对象----- */
        if (intent.extras != null) mBundle = intent.extras
        /** ------注释说明--------  */
        mPresenterProviders = PresenterProviders.inject(this)
        mPresenterDispatch = PresenterDispatch(mPresenterProviders)
        //绑View的操作
        mPresenterDispatch!!.attachView<P>(mContext, this)
        mPresenterDispatch!!.onCreatePresenter<P>(savedInstanceState)
        /** 设置状态栏问题颜色（黑/白）  */
        setStatusBar(0)
        /** 设置初始化 ToolBar **/
        if (!TextUtils.isEmpty(setTitle())) {
            initToolBar(setTitle())
        }
        /** 设置初始化 View **/
        initView()
        /** 设置点击事件操作**/
        eventOnClick()
        /** 初始化数据**/
        initData()
    }

    /**
     * 销毁操作
     */
    override fun onDestroy() {
        //解绑View的操作
        mPresenterDispatch!!.detachView<P>()
        mPresenterDispatch!!.onDestroyPresenter<P>()
        ActivityStackManager.getInstance().remove(this)
        AppBus.getInstance().unregister(this) //订阅事件
        super.onDestroy()
    }

    override fun showLoading() {
        showWaitDialog(getString(R.string.loading))
    }

    override fun showNetworkError(code: Int, message: String?) {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        if (!isFinishing && mProgressDialog != null) {
            try {
                mProgressDialog!!.dismiss()
                mProgressDialog = null
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    override fun showError(error: String?) {
        error?.let { ToastUtils.showShort(it)}
    }

    /**
     * 显示等待对话框
     */
    @UiThread
    open fun showWaitDialog(message: String?): ProgressDialog? {
        if (!isFinishing) {
            if (mProgressDialog == null) {
                mProgressDialog = DialogHelper.getProgressDialog(this, message)
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
        if (mProgressDialog != null && !this.isFinishing) {
            mProgressDialog!!.dismiss()
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        mPresenterDispatch!!.onSaveInstanceState<P>(outState)
    }

    //后面定义的泛型 P 主要是为了一个 Presenter 的时候使用 getPresenter() 方法时用到的，index =0,数组只有1个
    protected open fun getPresenter(): P {
        return mPresenterProviders!!.getPresenter(0)
    }

    /**
     * 重写onRequestPermissionsResult，用于接受请求结果
     *
     * @param requestCode  请求的唯一code
     * @param permissions  一些列的请求权限
     * @param grantResults 用户授权的结果
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        ////将请求结果传递EasyPermission库处理
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
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
     * ------获取 presenter 提供者--------
     */
    open fun getPresenterProviders(): PresenterProviders? {
        return mPresenterProviders
    }

    /**
     * 设置系统状态栏； 默认是白色底
     *
     * @param colorId
     */
    protected open fun setStatusBar(colorId: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //白色状态栏
            if (colorId == 0) {
//                StatusBarUtil.setStatusBarColor(this, android.R.color.white)
                StatusBarUtil.setStatusBarColor(this, R.color.unify_bg)
                //true 字体颜色为黑色，false为白色
                StatusBarUtil.setLightStatusBar(this, false, true)
            } else {
                StatusBarUtil.setStatusBarColor(this, colorId)
                //true 字体颜色为黑色，false为白色
                StatusBarUtil.setLightStatusBar(this, false, true)
            }
        }
    }

    /**
     * 设置标题
     *
     * @param title
     */
    open fun setTitle(title: String) {
        var title = title
        if (!TextUtils.isEmpty(title)) {
            if (title.length >= 15) {
                title = title.substring(0, 15) + "..."
            }
            mTitle!!.text = title
        }
    }

    /**
     * 设置标题右边图标和点击事件
     *
     * @param resid
     * @param onClickListener
     */
    open fun setTitleRightIcon(
        resid: Int,
        onClickListener: View.OnClickListener
    ) {
        val drawable = mContext!!.resources.getDrawable(resid)
        drawable.setBounds(
            0,
            0,
            drawable.minimumWidth,
            drawable.minimumHeight
        ) //必须设置图片大小，否则不显示
        mTitle!!.setCompoundDrawables(null, null, drawable, null)
        mTitle!!.compoundDrawablePadding = 10 //设置图片和text之间的间距
        mTitle!!.setOnClickListener(object : NoRepeatClickListener() {
            override fun onSingleClick(view: View?) {
                onClickListener.onClick(view)
            }
        })
    }

    /**
     * 初始化 ToolBar
     *
     * @param title
     */
    protected open fun initToolBar(title: String?) {
        mToolbar = findViewById<Toolbar>(R.id.toolbar)
        if (mToolbar != null) {
            setSupportActionBar(mToolbar)
            val actionBar: ActionBar? = supportActionBar
            actionBar!!.setTitle("")
            mTitle = findViewById(R.id.tv_title)
            if (!TextUtils.isEmpty(title) && mTitle != null) {
                mTitle!!.text = title
            } else {
                /** ------当没有标题的时候； 隐藏掉toolbar--------  */
                mToolbar!!.visibility = View.GONE
            }
            mBack = findViewById(R.id.iv_back)
            mRightTitle = findViewById(R.id.tv_rightTitle)
            if (mBack != null) {
                mBack!!.setOnClickListener { finish() }
            }
            mRightCenterTitle = findViewById(R.id.tv_rightCenterTitle)
        } else {
            Log.d(TAG, "mToolbar 控件为空")
        }
    }
    /**
     * 设置右边中间标
     *
     * @param resid
     */
    open fun setRightCenterResource(
        resid: Int,
        onClickListener: View.OnClickListener
    ) {
        mRightCenterTitle!!.visibility = View.VISIBLE
        val drawable = mContext!!.resources.getDrawable(resid)
        drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
        mRightCenterTitle!!.setCompoundDrawables(null, null, drawable, null)
        mRightCenterTitle!!.setOnClickListener(object : NoRepeatClickListener() {
            override fun onSingleClick(view: View?) {
                onClickListener.onClick(view)
            }
        })
    }
    /**
     * 设置标题右边图标和点击事件
     *
     * @param resid
     */
    open fun setTitleRightIcon(resid: Int) {
        val drawable = mContext!!.resources.getDrawable(resid)
        drawable.setBounds(
            0,
            0,
            drawable.minimumWidth,
            drawable.minimumHeight
        ) //必须设置图片大小，否则不显示
        mTitle!!.setCompoundDrawables(null, null, drawable, null)
        mTitle!!.compoundDrawablePadding = 10 //设置图片和text之间的间距
    }
    /**
     * 是否显示返回图标
     *
     * @param isVisible
     */
    open fun isShowBack(isVisible: Boolean) {
        if (isVisible) {
            mBack!!.visibility = View.VISIBLE
        } else {
            mBack!!.visibility = View.GONE
        }
    }
    /**
     * 是否显示Toolbar
     *
     * @param isVisible
     */
    open fun isShowToolbar(isVisible: Boolean) {
        if (isVisible) {
            mToolbar!!.visibility = View.VISIBLE
        } else {
            mToolbar!!.visibility = View.GONE
        }
    }
    /**
     * 设置左边图标
     *
     * @param resid
     */
    open fun setBackResource(resid: Int) {
        mBack!!.visibility = View.VISIBLE
        val drawable = mContext!!.resources.getDrawable(resid)
        drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
        mBack!!.setCompoundDrawables(null, null, drawable, null)
    }
    /**
     * 设置右边图标
     *
     * @param resid
     */
    open fun setRightResource(resid: Int) {
        mRightTitle!!.visibility = View.VISIBLE
        val drawable = mContext!!.resources.getDrawable(resid)
        drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
        mRightTitle!!.setCompoundDrawables(null, null, drawable, null)
    }
    /**
     * 设置右边图标
     *
     * @param resid
     */
    open fun setRightResource(
        resid: Int,
        onClickListener: View.OnClickListener
    ) {
        mRightTitle!!.visibility = View.VISIBLE
        val drawable = mContext!!.resources.getDrawable(resid)
        drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
        mRightTitle!!.setCompoundDrawables(null, null, drawable, null)
        mRightTitle!!.setOnClickListener(object : NoRepeatClickListener() {
            override fun onSingleClick(view: View?) {
                onClickListener.onClick(view)
            }
        })
    }
    /**
     * 设置右边图标
     *
     * @param txt
     */
    open fun setRightTitle(
        txt: String?,
        onClickListener: View.OnClickListener
    ) {
        if (!TextUtils.isEmpty(txt)) {
            mRightTitle!!.visibility = View.VISIBLE
            mRightTitle!!.text = txt
            mRightTitle!!.setOnClickListener(object : NoRepeatClickListener() {
                protected override fun onSingleClick(view: View?) {
                    onClickListener.onClick(view)
                }
            })
        }
    }

    /**
     * 布局id
     */
    protected abstract fun getContentView(): Int

    /**
     * 初始化操作
     */
    protected abstract fun initView()

    /**
     * 初始化点击事件操作
     */
    protected abstract fun eventOnClick()

    /**
     * 初始化数据
     */
    protected abstract fun initData()

    /**
     * 抽象的设置的标题的方法 子类实现
     */
    protected abstract fun setTitle(): String?


}