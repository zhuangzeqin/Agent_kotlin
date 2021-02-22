package com.eeepay.zzq.by

import android.util.Log

/**
 * 描述：通过SQL进行持久化
 * 作者：zhuangzeqin
 * 时间: 2021/2/2-16:19
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class SQLData : IDataPersistence {
    private val TAG = SQLData::class.java.simpleName
    override fun addData() {
        Log.d(TAG, "addData: ${TAG}")
    }

    override fun delData() {
        Log.d(TAG, "delData: ${TAG}")
    }

    override fun queryData() {
        Log.d(TAG, "queryData: ${TAG}")
    }
}