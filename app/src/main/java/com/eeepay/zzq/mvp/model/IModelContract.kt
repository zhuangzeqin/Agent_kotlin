package com.eeepay.zzq.mvp.model

import com.eeepay.zzq.mvp.model.base.IBaseContract
import com.eeepay.zzq.mvp.model.base.OnCallbackImpl2
import rxhttp.wrapper.annotations.NonNull

/**
 * 描述：避免model 创建太多的接口类，就统一写在这里；
 * 作者：zhuangzeqin
 * 时间: 2020/8/6-11:56
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
interface IModelContract : IBaseContract {
    //登录model 接口
    interface ILoginModel<T> {
        //登录接口
         fun reqLogin(
            @NonNull userName: String,
            @NonNull password: String,
            @NonNull resultCallBack: IBaseContract.IResultCallBack<T>?
        )
    }

    interface ILoginModel2<T> {
        //登录接口
         fun reqLogin(
            @NonNull userName: String,
            @NonNull password: String,
            @NonNull  callback: OnCallbackImpl2<T>?
        )
    }

    //公共数据接口 model 接口
    interface IPublicDataModel<T> {
        fun reqPublicData(@NonNull resultCallBack: IBaseContract.IResultCallBack<T>?)
    }

    //首页 ->今日业绩model 接口
    interface ILoadCurrDayDataModel<T> {
        //首页->今日业绩
        fun loadCurrDayData(@NonNull resultCallBack: IBaseContract.IResultCallBack<T>?)
    }

    //获取商户需要的参数接口
    interface IQueryMerchantParamsModel<T> {
        fun reqQueryMerchantParams(@NonNull resultCallBack: IBaseContract.IResultCallBack<T>?)
    }

    interface ICheckVersionMode<T> {
        fun reqcheckVersion(@NonNull downloadUrl:String,@NonNull resultCallBack: IBaseContract.IResultProgressCallBack<T>?)
    }
}