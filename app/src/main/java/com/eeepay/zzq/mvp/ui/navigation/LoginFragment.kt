package com.eeepay.zzq.mvp.ui.navigation

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.eeepay.zzq.agent_kotlin.R
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * 描述：登录的loginFragmnet
 * 作者：zhuangzeqin
 * 时间: 2021/2/22-11:40
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class LoginFragment: Fragment(R.layout.fragment_login) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val tip = get().getString("zzq", "default")
//       val tip = this.requireArguments().getString("zzq","aaa")
//        ToastUtils.showLong(tip)
        btn_login.setOnClickListener {
            //在Fragment中，可以通过NavController来进行路由，
            Navigation.findNavController(it).navigate(R.id.action_loginFragment3_to_registerFragment)
        }

        btn_act.setOnClickListener {
            //使用 Bundle 对象在目的地之间传递参数
            //在接收目的地的代码中，请使用 getArguments() 方法来检索 Bundle 并使用其内容：
            val amount  = 200
            val bundle = bundleOf("amount" to amount)
            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_coroutineActivity,bundle)
        }
    }
}