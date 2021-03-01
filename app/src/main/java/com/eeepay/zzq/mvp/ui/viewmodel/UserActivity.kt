package com.eeepay.zzq.mvp.ui.viewmodel

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.eeepay.zzq.agent_kotlin.R
import kotlinx.android.synthetic.main.activity_user.*

/*
  * ================================================
  * 描述：
  * ViewModel的使用很简单，作用和原来的Presenter一致。只是要结合LiveData，UI层观察即可。
ViewModel的创建必须通过ViewModelProvider。

注意到ViewModel中没有持有任何UI相关的引用。

旋转手机重建Activity后，数据确实恢复了。

  * 作者：zhuangzeqin
  * 时间: 2021/2/26-16:48
  * 邮箱：zzq@eeepay.cn
  * 备注:
  * ================================================
  */
class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        //获取ViewModel实例
        val userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        //获取ViewModel实例，然后就是 观察ViewModel中的LiveData。 观察 用户信息
        userViewModel.getUserLiveData()?.observe(this, Observer { result ->
            tv_user_info.setText(result)
        })

        userViewModel.getLoadingLiveData()
            ?.observe(
                this,
                Observer { aBoolean -> progressBar.setVisibility(if (aBoolean!!) View.VISIBLE else View.GONE) })


        btn_getUserInfo.setOnClickListener {
            userViewModel.getUserInfo()
        }
    }
}