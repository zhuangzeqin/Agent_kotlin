package com.eeepay.zzq.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import rxhttp.wrapper.annotations.NonNull

/**
 * 描述：P层需要的接口协议
 * 作者：zhuangzeqin
 * 时间: 2020/8/5-14:10
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
interface IPresenterContract {
    /**
     * ------注释说明--登录------
     */
    interface ILoginPresenter {
        fun login(
            @NonNull owner: LifecycleOwner,
            @NonNull username: String,
            @NonNull password: String
        )
    }

    /* ------注释说明---获取到公共数据----- */
    interface IPubDataPresenter {
        fun getPubDataInfo(@NonNull owner: LifecycleOwner)
    }
    /* ------注释说明---获取到首页数据----- */
    interface ILoadCurrDayDataPresenter {
        fun getLoadCurrDayData(@NonNull owner: LifecycleOwner)
    }
    /* ------注释说明---获取商户需要的参数----- */
    interface IQueryMerchantParamsPresenter {
        fun getQueryMerchantParams(@NonNull owner: LifecycleOwner)
    }

    /**
     * 检查版本升级
     */
    interface ICheckVersionPresenter {
        fun reqCheckVersion(@NonNull owner: LifecycleOwner,@NonNull downloadUrl:String)
    }
}