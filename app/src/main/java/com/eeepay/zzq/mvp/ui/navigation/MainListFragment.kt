package com.eeepay.zzq.mvp.ui.navigation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.eeepay.zzq.agent_kotlin.R
import com.eeepay.zzq.utils.ToastUtils
import kotlinx.android.synthetic.main.fragment_mainlist.*

/**
 * 描述：主界面的Fragmnet
 * 作者：zhuangzeqin
 * 时间: 2021/2/22-11:40
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class MainListFragment: Fragment(R.layout.fragment_mainlist) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val string = arguments?.let {  it.getString("name")}
        ToastUtils.showLong(string)
        //当我们从A路由到B，B路由到C后，通过popBackStack返回，指定要返回到的Fragment的id，即可直接返回到指定位置，第二个参数inclusive，代表返回操作是否包含指定的Fragment id。
        btn_main_list.setOnClickListener {
            Navigation.findNavController(it).popBackStack(R.id.loginFragment, false)
        }

    }
}