package com.eeepay.zzq.utils

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View

/**
 * 描述：在项目中，我们常常要定义圆角矩形背景，一般是用自定义drawable实现的
但是圆角矩形的背景与圆角常常会有细微的变化，而一旦变化我们又要新创建一个drawable文件
这样就会导致文件爆炸的问题
我们可以利用kotlin的扩展函数，来实现简单方便的圆角矩形背景
 * 作者：zhuangzeqin
 * 时间: 2021/1/26-9:54
 * 邮箱：zzq@eeepay.cn
 * 备注:对于需要自定义背景的View,直接调用setRoundRectBg即可，简单方便
 */
class ViewRoundBgUtils {
    fun View.setRoundRectBg(color: Int = Color.WHITE, cornerRadius: Int = 15) {
        background = GradientDrawable().apply {
            setColor(color)
            setCornerRadius(cornerRadius.toFloat())
        }
    }
}