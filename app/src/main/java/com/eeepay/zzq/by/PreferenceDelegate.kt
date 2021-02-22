package com.eeepay.zzq.by

import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * 描述：使用：
var valueInSP: String by PreferenceDelegate(this, "test", "init")

Log.d("xys", valueInSP)
valueInSP = "new value"
Log.d("xys", valueInSP)
out:
D/xys: init
D/xys: new value
 * 作者：zhuangzeqin
 * 时间: 2021/2/2-17:01
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class PreferenceDelegate<T>(
    private val context: Context,
    private val propName: String,
    private val defaultValue: T
) : ReadWriteProperty<Any, T> {

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(
            "SP_NAME",
            Context.MODE_PRIVATE
        )
    }

    /**
     * Sets the value of the property for the given object.
     * @param thisRef the object for which the value is requested.
     * @param property the metadata for the property.
     * @param value the value to set.
     */
    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        value?.let { putSPValue(propName, value) }
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return getSPValue(propName, defaultValue) ?: defaultValue
    }

    private fun <T> getSPValue(name: String, defaultValue: T): T? = with(sharedPreferences) {
        val result = when (defaultValue) {
            is String -> getString(name, defaultValue)
            is Int -> getInt(name, defaultValue)
            is Long -> getLong(name, defaultValue)
            is Float -> getFloat(name, defaultValue)
            is Boolean -> getBoolean(name, defaultValue)
            else -> null
        }
        result as T
    }

    private fun <T> putSPValue(name: String, value: T) = with(sharedPreferences.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> null
        }
    }?.apply()
}