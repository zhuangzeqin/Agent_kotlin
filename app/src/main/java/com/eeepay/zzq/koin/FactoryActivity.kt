package com.eeepay.zzq.koin

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.eeepay.zzq.agent_kotlin.R
import kotlinx.android.synthetic.main.activity_factory.*
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named


/**
 * 描述：koin factory
 * 作者：zhuangzeqin
 * 时间: 2021/3/26-16:41
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class FactoryActivity : AppCompatActivity() {
    val TAG = FactoryActivity::class.java.simpleName
    //调用方式有大致下面几种,后面会再说到
    val userInfo by inject<UserInfo>()//方法一
    val userInfo2: UserInfo by inject()//方法二

    //2 获取单例对象
    val single1: SingleData by inject()
    val single2 by inject<SingleData>()

    //viewmodel 方式
    val myViewModel: MyViewModel by viewModel()
    val myViewModel2 by viewModel<MyViewModel>()

    // 带参数1
    val appData by inject<AppData>()


    val norData1 by inject<NormalData>(named("nameAnum"))//限定符1
    val norData2: NormalData by inject(named("app"))//限定符2

    //
    val weatherData by inject<WeatherData>(named("wea_name"))
    val weatherData2 by inject<WeatherData>(named("wea_app"))
    val weatherData3 by inject<WeatherData>(named("wea_appData"))


    //声明进样参数---构造参数从外面传入 通过parametersOf()函数来传参
    var btnShow: Button? = null
    val viewData by inject<ViewData> { parametersOf(btnShow) }

    val proData :ProData by inject()
//    val csopeTypeW: ScopeData by currentScope.inject()//直接获取了ScopeTypeTwo对象

    @KoinApiExtension
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_factory)

        //1通过factory 获取方式
        mb_factory.setOnClickListener {
            val userInfo3 = get<UserInfo>()//方法三
            userInfo.address = "1111"
            userInfo.sayHello()
            userInfo2.address = "2222"
            userInfo2.sayHello()
            userInfo3.address = "33333"
            userInfo3.sayHello()
        }

        //2 通过单例地方式获取koin 注入 对象
        mb_single.setOnClickListener {
            single1.userName = "zhuangzeqin"
            single1.age = 31
            println("${single1.hashCode().toString()}")
            println("${single1.toString()}")
            single2.userName = "张三"
            single2.age = 32
            println("${single2.hashCode().toString()}")
            println("${single2.toString()}")
        }

        Log.d(TAG, "onCreate: "+myViewModel.hashCode().toString())
        Log.d(TAG, "onCreate: "+myViewModel2.hashCode().toString())
        Log.d(TAG, "onCreate: "+myViewModel.NumData.toString())

        mb_viewmodel.setOnClickListener {
            myViewModel.NumData = 1
            Log.d(TAG, "onCreate: "+myViewModel.NumData.toString())
        }

        val norData3 = get<NormalData>(named("appData"))//限定符3
        norData1.printInfo("norData1")
        norData2.printInfo("norData2")
        norData3.printInfo("norData3")

        //
        weatherData.printData("weather1")
        weatherData2.printData("weather2")
        weatherData3.printData("weather3")


        btnShow = findViewById(R.id.mb_factory)
        //这边要注意,btn的初始化要在ViewData的调用之前,否
        // 则会报空指针.koin的注入是懒加载模式的,只有在调用对象的时候,才会实例化对象
        viewData.prinId()
        //这边直接new对象,看里面注入的对象信息
        CompontData().priInfo()


         println("通过Property方式获取:${proData.str}")
//        val scope = getKoin().createScope("scopeId1", named("myScope"))//创建scope方式一
//        bindScope(scope)//scope与界面绑定,只有这边创建绑定了之后,其他地方才能获取到这个作用域
//        val scopeData = scope.get<ScopeData>()//获取作用域下的类
//        CSKoinLog.I("ScopeCurentActivity中的ScopeData是否为空:" + (scopeData == null))
//        CSKoinLog.I("ScopeCurentActivity中的ScopeData地址:" + (scopeData.hashCode()))
    }
}

