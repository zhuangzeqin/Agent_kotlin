package com.eeepay.common.lib.mvp.model.base.kt

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.util.concurrent.locks.ReadWriteLock
import java.util.concurrent.locks.ReentrantReadWriteLock



/*
  * ================================================
  * 描述：抽象的订阅者Subscriber
 *  当的有接口不按照一定的格式来时；可以使用该类获取所有的后台字段信息【返回所有的数据字段】
 * 不管成功或者是失败都将所有的字段进行返回，上层根据自己的业务需求进行取相应的字段
  * 作者：zhuangzeqin
  * 时间: 2021年3月17日11:16:06
  * 邮箱：zzq@eeepay.cn
  * 备注: 【不管成功或者是失败都将所有的字段进行返回，上层根据自己的业务需求进行取相应的字段】
  * ----------------------------------------------------------------
  * You never know what you can do until you try !
  *      _              _           _     _   ____  _             _ _
  *     / \   _ __   __| |_ __ ___ (_) __| | / ___|| |_ _   _  __| (_) ___·
  *    / _ \ | '_ \ / _` | '__/ _ \| |/ _` | \___ \| __| | | |/ _` | |/ _ \
  *   / ___ \| | | | (_| | | | (_) | | (_| |  ___) | |_| |_| | (_| | | (_) |
  *  /_/   \_\_| |_|\__,_|_|  \___/|_|\__,_| |____/ \__|\__,_|\__,_|_|\___/
  *
  * 签名：最痛苦的事不是我失败了，而是我本可以.--zzq
  * ----------------------------------------------------------------
  * ================================================
  * kotlin 用了就回不去的一门语言--zzq
  */
 class BaseAllObserverkt<T> @JvmOverloads constructor(call: ResponseCallbackListener<T>.() -> Unit, tag: String = "") :
    Observer<com.eeepay.zzq.lambda.kt.Result<T>> {
    //读写锁 ReentrantReadWriteLock：读写锁，分为读锁和写锁，多个读锁不互斥，读锁与写锁互斥，写锁与写锁互斥，由JVM控制。()
    private val readWriteLock: ReadWriteLock = ReentrantReadWriteLock()
    private var mTag: String? = null//请求标识

    //实例化回调接口实现类---延迟初始化 而且当且仅当变量被第一次调用的时候，委托方法才会执行
    private val callBack: ResponseCallbackListener<T> by lazy { ResponseCallbackListener<T>() }

    //1 常量定义
    companion object {
        const val NETWORKERRORCODE = -1001 //没有网络code
        const val UNKNOWNCODE = -1002 //未知错误
        private const val LOGIN_PATH = "/config/path/login"//登录
    }

    //次构造函数
//    constructor() : this("")
    init {
/*        if (TextUtils.isEmpty(tag))
            mTag = Utils.getUUID() + System.currentTimeMillis().toString() //默认以时间戳为请求标识
        else
            mTag = Utils.getUUID() + System.currentTimeMillis().toString() + tag //默认以时间戳为请求标识tag;*/
        //将参数内的回调函数与实例化对象绑定
        callBack.call()
    }


    override fun onComplete() {
        //解除订阅的地方调用 disposable.dispose()
        cancel()
    }


    override fun onSubscribe(d: Disposable) {
        //上层调用时只关心成功和失败即可无需关心网络情况
        /*if (!NetworkUtil.isNetworkAvailable(SuperApplication.getApplicationInstance().applicationContext)) {
            Logger.d("当前网络不可用，请检查网络情况")
            callBack.onFailureFun(mTag, NETWORKERRORCODE, "当前网络不可用，请检查网络情况", null, 0)
            // 最好主动调用下面这一句,取消本次Subscriber订阅
            if (!d.isDisposed) d.dispose()
            return
        }
        if (!TextUtils.isEmpty(mTag)) {
            RxActionManagerImpl.getInstance().add(mTag, d)
        }*/
    }


    override fun onNext(body: com.eeepay.zzq.lambda.kt.Result<T>) {
        kotlin.runCatching {
            if (body != null) {
                val code = body.code //code 标识码 401
                val message = body.message //错误提示语
                val count = body.count //数据数量总数
                // T data = body.data;//返回数据
                //* ------注释说明---检查token 是否已经401 失效；如果失效跳转登录页面；关闭所有请求----- *//*
                if (checkTokenInvalid(code)) return
                //不成功的情况
                if (!body.isSuccess) {
                    callBack.onFailureFun(mTag, code, message, body, count)
                } else {
                    //返回数据
                    callBack.onSuccessFun(mTag, code, message, body, count)
                }
            } else {
                callBack.onFailureFun(mTag, UNKNOWNCODE, "暂时无法获取数据；请稍后重试", null, 0)
            }
        }.onSuccess {
            //runCatching 如果执行成功，那么onSuccess方法会被调用。
        }.onFailure {
            //如果代码块抛出异常，那么onFailure方法就会被调用，并且伴随异常信息
            it.printStackTrace()
            callBack.onFailureFun(mTag, UNKNOWNCODE, "网络错误,请稍后重试:$mTag", null, 0)
        }
    }


    override fun onError(e: Throwable) {
        cancel()
       /* Logger.d(e.message)
        if (e is ApiException) {
            val exception = e
            val code = exception.code //错误码
            val msg = exception.msg //错误信息
            *//* ------注释说明---检查token 是否已经401 失效；如果失效跳转登录页面；关闭所有请求----- *//*
            if (checkTokenInvalid(code)) return
            callBack.onFailureFun(mTag, code, msg, null, 0)
        } else {
            callBack.onFailureFun(mTag, UNKNOWNCODE, "未知错误", null, 0)
        }*/
    }

    /**
     * 检查token 是否已经401 失效；如果失效跳转登录页面；关闭所有请求
     * @param code
     * @return
     */
    private fun checkTokenInvalid(code: Int): Boolean {
       /* if (HttpURLConnection.HTTP_UNAUTHORIZED == code) { //401
            readWriteLock.readLock().lock() // 读数据，上读锁
            val status_code = PreferenceUtils.getIntParam(ABConfig.KEY_STATUS_CODE, 0) //默认是0也就是正常的状态，登录的时候把这个状态置为0
            readWriteLock.readLock().unlock()
            if (HttpURLConnection.HTTP_UNAUTHORIZED == status_code) { //401
                return true
            }
        }
        if (HttpURLConnection.HTTP_UNAUTHORIZED == code) {
            readWriteLock.writeLock().lock() // 写数据上写锁
            PreferenceUtils.saveParam(ABConfig.KEY_STATUS_CODE, code) //首次遇到401 将保存本地
            readWriteLock.writeLock().unlock()
            RxActionManagerImpl.getInstance().cancel(mTag) //取消本次请求
            RxActionManagerImpl.getInstance().cancelAll() //取消所有的请求
            // 标识码 401 被登录拦截了下来了
            Logger.d("<----401 被登录拦截了下来了,需要调转到登录页面---->")
            //退出app
            ActivityStackManager.getInstance().finishAllActivity()
            // 需要调转到登录页面，把参数跟被登录拦截下来的路径传递给登录页面，登录成功后再进行跳转被拦截的页面
            ARouter.getInstance().build(LOGIN_PATH).withFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).navigation()
            return true
        }*/
        return false
    }

    /**
     * 手动取消请求
     */
    fun cancel() {
       /* if (!TextUtils.isEmpty(mTag)) {
            RxActionManagerImpl.getInstance().cancel(mTag)
        }*/
    }

    /**
     * 是否已经处理
     */
    fun isDisposed(): Boolean {
       /* return if (TextUtils.isEmpty(mTag)) {
            true
        } else RxActionManagerImpl.getInstance().isDisposed(mTag)*/
         return  false
    }

}