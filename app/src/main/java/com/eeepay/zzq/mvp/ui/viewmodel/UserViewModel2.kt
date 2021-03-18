package com.eeepay.zzq.mvp.ui.viewmodel

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 * 描述：iveData与MutableLiveData的其实在概念上是一模一样的.唯一的几个区别如下:
（1）MutableLiveData的父类是LiveData；
（2）LiveData在实体类里可以通知指定某个字段的数据更新；
（3）MutableLiveData则是完全是整个实体类或者数据类型变化后才通知.不会细节到某个字段。
 * 作者：zhuangzeqin
 * 时间: 2021/2/26-11:17
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class UserViewModel2 : ViewModel() {
    //    private val userLiveData: MutableLiveData<String>? = null
//    private val loadingLiveData: MutableLiveData<Boolean>? = null
    //setValue()只能在主线程中调用，postValue()可以在任何线程中调用。
    private var userLiveData: MutableLiveData<String>? = null
    private var loadingLiveData: MutableLiveData<Boolean>? = null

    init {
        //userLiveData用于抛出用户信息，
        userLiveData = MutableLiveData()
        //loadingLiveData用于控制进度条显示。
        loadingLiveData = MutableLiveData()
    }

    /**
     * 获取用户信息,假装网络请求 2s后 返回用户信息
     */
    @SuppressLint("StaticFieldLeak")
    fun getUserInfo() {
        //setValue()只能在主线程中调用，postValue()可以在任何线程中调用。
//        如果同时调用 .postValue(“a”)和.setValue(“b”)，一定是值b被值a覆盖。
//        如果多次调用 .postValue()，只有最后一个值能够被分发（onChanged()被调用）。
        loadingLiveData!!.setValue(true)
//        val value = @SuppressLint("StaticFieldLeak")
        object : AsyncTask<Void, Void, String>() {
            override fun onPostExecute(result: String?) {
                loadingLiveData!!.setValue(false)
                userLiveData!!.setValue(result) //抛出用户信息
            }

            override fun doInBackground(vararg p0: Void?): String {
                try {
                    Thread.sleep(2000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                return "我是zzq，公众号名字也是zzq，欢迎关注~"
            }

        }.execute()

    }

    fun getUserLiveData(): LiveData<String?>? {
        return userLiveData
    }

    fun getLoadingLiveData(): LiveData<Boolean?>? {
        return loadingLiveData
    }

}


