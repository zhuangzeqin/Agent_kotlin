package com.eeepay.zzq.mvp.ui.Countdown

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eeepay.zzq.agent_kotlin.R
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_countdown.*
import java.util.concurrent.TimeUnit


/*
  * ================================================
  * 描述：倒计时
  * 作者：zhuangzeqin
  * 时间: 2021/2/24-9:56
  * 邮箱：zzq@eeepay.cn
  * 备注:
  * ================================================
  */
class CountdownAct : AppCompatActivity() {
    val countTime = 10L//倒计时10s
    var subscribe: Disposable? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countdown)
        tv_countdown.setOnClickListener {
            //点击后置为不可点击状态
//            setTimer(countTime)
            setTimer2(countTime)
        }
    }

    /**
     * 通过RxJava实现的倒计时
     */
    fun setTimer(count: Long) {
        tv_countdown.setEnabled(false)
        /**
         * intervalRange 发送固定个数的事件
         * 第一个参数：开始值
         * 第二个参数：事件数量
         * 第三个参数：开始发送时的延迟时间
         * 第四个参数：时间间隔
         * 第五个参数：时间单元
         */
        subscribe = Flowable.intervalRange(0, count + 1, 0, 1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).doOnNext {
                tv_countdown.setText(String.format("重新获取(%s)", count - it))
            }.doOnComplete {
                //倒计时完毕置为可点击状态
                tv_countdown.setEnabled(true)
                tv_countdown.setText("获取验证码")
            }.subscribe()
    }

    fun setTimer2(count: Long) {
        tv_countdown.setEnabled(false)
        //interval 是会无限发送事件的。第一个参数开始的延时事件，二个参数为时间间隔，三个时间间隔单元（单位）

        //interval 是会无限发送事件的。第一个参数开始的延时事件，二个参数为时间间隔，三个时间间隔单元（单位）
        Observable.interval(
            0,
            1,
            TimeUnit.SECONDS
        ) //因为interval是无限发送的，所以使用take操作符，只会发送10个数据
            //因为interval发送是从0开始的，而我们要使用的是10 - value 所以用map做一下转换
            .take(count).map { along -> count - along }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<Long> {
                override fun onComplete() {
                    //倒计时完毕置为可点击状态
                    tv_countdown.setEnabled(true)
                    tv_countdown.setText("获取验证码")
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(it: Long) {
                    tv_countdown.setText(String.format("重新获取(%s)", it))
                }


                override fun onError(e: Throwable) {

                }
            })

    }

    override fun onDestroy() {
        super.onDestroy()
        subscribe?.let { it.dispose() }
    }
}