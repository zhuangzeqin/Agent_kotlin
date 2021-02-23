package com.eeepay.zzq.mvp.ui.navigation

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.eeepay.zzq.agent_kotlin.R
import kotlinx.android.synthetic.main.fragment_register.*

/**
 * 描述：注册的Fragmnet
 * 作者：zhuangzeqin
 * 时间: 2021/2/22-11:40
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class RegisterFragment: Fragment(R.layout.fragment_register) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_register.setOnClickListener {

            //同时，也可以通过Bundle来进行参数的传递，这跟之前使用Fragment基本类似，代码如下。bundleOf
//            Navigation.findNavController(it).navigate(R.id.action_registerFragment_to_mainListFragment, bundleOf("name" to "xuyisheng"))
            Navigation.findNavController(it).navigate(R.id.action_registerFragment_to_mainListFragment, bundleOf("name" to "xuyisheng"))
        }
    }
}