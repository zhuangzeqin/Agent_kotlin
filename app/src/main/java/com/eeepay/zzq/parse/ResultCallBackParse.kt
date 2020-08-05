package com.eeepay.zzq.lib_common.parse
import android.util.Log
import com.eeepay.zzq.event.AppBus
import com.eeepay.zzq.event.AppEvent
import com.eeepay.zzq.lib_common.event.AppEventData
import com.eeepay.zzq.lib_common.singleton.OptimizeSingletonManager
import okhttp3.Response
import rxhttp.RxHttpPlugins
import rxhttp.wrapper.annotation.Parser
import rxhttp.wrapper.entity.ParameterizedTypeImpl
import rxhttp.wrapper.exception.ParseException
import rxhttp.wrapper.parse.AbstractParser
import java.io.IOException
import java.lang.reflect.Type
import java.net.HttpURLConnection

/**
 * 描述：输入T,输出T,并对code统一判断
 * 抽象出后台的数据格式
 * 作者：zhuangzeqin
 * 时间: 2020/7/24-15:46
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
@Parser(name = "ResultCallBack", wrappers = [List::class])
open class ResultCallBackParse<T> : AbstractParser<T> {
    /**
     * 此构造方法适用于任意Class对象，但更多用于带泛型的Class对象，如：List<Student>
     * <p>
     * 用法:
     * Java: .asParser(new ResponseParser<List<Student>>(){})
     * Kotlin: .asParser(object : ResponseParser<List<Student>>() {})
     * <p>
     * 注：此构造方法一定要用protected关键字修饰，否则调用此构造方法将拿不到泛型类型
     */
    protected constructor() : super()

    /**
     * 此构造方法仅适用于不带泛型的Class对象，如: Student.class
     * <p>
     * 用法
     * Java: .asParser(new ResponseParser<>(Student.class))   或者  .asResponse(Student.class)
     * Kotlin: .asParser(ResponseParser(Student::class.java)) 或者  .asResponse(Student::class.java)
     */
    public constructor(type: Type) : super(type)

    @Suppress("UNCHECKED_CAST")
    @Throws(IOException::class)
    override fun onParse(response: Response): T {
        val type: Type = ParameterizedTypeImpl[ResultCallBack::class.java, mType] //获取泛型类型
        val data: ResultCallBack<T> = convert(response, type)//根据类型转换data
        val code: Int = data.code//code 标识码 401
        val mesage: String? = data.message//错误提示语
        var t = data.data //获取data字段
        val count: Int = data.count//总数
        if (HttpURLConnection.HTTP_UNAUTHORIZED == code) {//401
            // 标识码 401 被登录拦截了下来了
            RxHttpPlugins.cancelAll();//取消所有的请求
            Log.d("ResultCallBackParse", "<----401 被登录拦截了下来了,需要调转到登录页面---->");
            AppBus.getInstance().post(AppEvent(code, "401 token 失效"))//java 的写法 发布事件，在主界面进行订阅401 跳转到登录页
//            SingletonManager.post(AppEvent(code, "401 token 失效"))//kotlin 的写法
//            LazilySingletonManager.getInstance().post(AppEvent(code, "401 token 失效"))//kotlin 的写法
//            LazilyDCLSingletonManager.mInstance.post(AppEvent(code, "401 token 失效"));
            OptimizeSingletonManager.getInstance().post(AppEventData(code, "401 token 失效"))
            throw ParseException(code.toString(), mesage, response)
        }
        //不成功的情况
        if (!data.success) {
            throw ParseException(code.toString(), mesage, response);
        }
        /*
        * 考虑到有些时候服务端会返回：{"errorCode":0,"errorMsg":"关注成功"}  类似没有data的数据
        * 此时code正确，但是data字段为空，直接返回data的话，会报空指针错误，
        * 所以，判断泛型为String类型时，重新赋值，并确保赋值不为null
        */
        //，Kotlin 中的 == 等同于调用 equals() 函数，比较两个对象引用是否相等要用 === 操作符。
        if (t == null && mType === String::class.java) {
            t = mesage as T
        }
        /* ------注释说明-------- */
        //? 标识当前对象可以为空；
        //!! 标识当前对象不为空的情况下执行
        return t!!
    }

}