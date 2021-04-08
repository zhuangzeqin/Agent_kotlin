package com.eeepay.zzq

import android.app.Application
import android.content.Context
import android.view.Gravity
import android.view.View
import androidx.lifecycle.ProcessLifecycleOwner
import com.cainiao5.mylibrary.libModule
import com.eeepay.zzq.base.RxHttpManager
import com.eeepay.zzq.koin.*
import com.eeepay.zzq.mvp.ui.lifecycle.ApplicationLifecycleObserver
import com.eeepay.zzq.utils.FastSharedPreferencesTools
import com.eeepay.zzq.utils.ToastUtils
import com.eeepay.zzq.utils.Utils
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module


/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2020/7/28-17:24
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
class APP : Application() {
    val tag = APP::class.java.simpleName

    //lateinit 不能用来修饰基本数据类型，因为基本类型的属性在类加载后的准备阶段都会被初始化为默认值
    //lateinit不能修饰val变量，只能修饰可变的属性
    lateinit var context: Context
    override fun onCreate() {
        super.onCreate()
        context = this
        Utils.init(this)
        FastSharedPreferencesTools.getInstance().init(this)
        RxHttpManager.init()
        //初始化ToastUtils
        ToastUtils.setGravity(Gravity.CENTER, 0, 0)
        //注册App生命周期观察者
        ProcessLifecycleOwner.get().getLifecycle().addObserver(ApplicationLifecycleObserver())

//        ForegroundCallbacks().get(this)
//        ForegroundCallbacks().get(this)!!.addListener(object : ForegroundCallbacks.Listener {
//                override fun onBecameForeground() {
////                    L.d("当前程序切换到前台")
//                    Log.d(tag,"${"当前程序切换到前台"}")
//                }
//
//                override fun onBecameBackground() {
////                    L.d("当前程序切换到后台")
//                    Log.d(tag,"${"当前程序切换到后台"}")
//                }
//            })
//开始启动koin
        startKoin {

            //这边传Application对象,这样你注入的类中,需要app对象的时候,可以直接使用
            androidContext(this@APP)
//            androidLogger()
//            fileProperties()
            androidFileProperties("android.properties")//默认名字为koin.properties,你也可以直接重新设置名称
            modules(appModule, libModule.theLibModule)//这里面传各种被注入的模块对象,支持多模块注入
        }
    }

    val appModule = module {//里面添加各种注入对象
//        1 Factory注入方式跟普通new一个对象一毛一样.
        factory {
           UserInfo()
            //开始初始化Koin的时候,不是传了一个androidContext(this@MyApp),将appLication对象传了进去,Koin中,就已经记录了这个application,所以在你需要用到application对象的时候,直接通过get()方法调用就可以了
            AppData(get())
        }

        factory {
            ProData(getProperty("userName"))//该方法可以设置泛型对象,你已经是一个成熟的程序员了,要学会自己举一反三
        }
        factory(named("nameAnum")) {
            //该限定符的构造方法中包含字符串和数字
            NormalData("曹老板", 12)
        }
        factory(named("app")) {
            //该限定符定义构造方法中有appliaction的
            NormalData(get<Application>())
        }
        factory(named("appData")){
            //该限定符定义构造方法中有AppData的
            NormalData(get<AppData>())
        }

        factory(named("wea_name")) {
            WeatherData(get<NormalData>(named("nameAnum")))
            //这边get方法中有一个泛型,可以指定传入的对象的类型,因为我构造函数只有一个,所以会智能输入,可以省略掉
        }
        factory(named("wea_app")) {
            WeatherData(get(named("app")))//这边就智能省略掉泛型了
        }
        factory(named("wea_appData")) {
            WeatherData(get(named("appData")))
        }

        factory {
                (view: View) -> ViewData(view)//外部调用的方式,如果是多参数也一样,聪明的同学么应该要学会举一反三了
        }
        factory {
            ModuleData(get())
        }
        // 2 单例的注入方式
        single {
            SingleData()
        }
        //3 viewmodel 注入方式
        viewModel {
            MyViewModel()
        }

//        scope(named<FactoryActivity>()) {//scope类型的注入方式一,通过标签的方式
//            scoped {
//                ScopeData()
//            }
//        }

//        scope<FactoryActivity>{
//            scoped{
//                ScopeData()
//            }
//        }



    }
}