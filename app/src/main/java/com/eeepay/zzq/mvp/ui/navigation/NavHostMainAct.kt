package com.eeepay.zzq.mvp.ui.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.eeepay.zzq.agent_kotlin.R


class NavHostMainAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_host_main)
        // 1 动态加载
        val controller = Navigation.findNavController(
            this@NavHostMainAct,
            R.id.nav_host_fragment
        ).apply {
            //设置xml文件
            //在Activity根据业务需要使用setGraph切换不同的Navigation
            this.setGraph(R.navigation.nav_graden)
            //使用navigate带动画跳转或者弹出Fragment
//            val navOptions = NavOptions.Builder()
//                .setEnterAnim(R.anim.from_right) //进入动画
//                .setExitAnim(R.anim.to_left) //退出动画
//                .setPopEnterAnim(R.anim.to_left) //弹出进入动画
//                .setPopExitAnim(R.anim.from_right) //弹出退出动画
//                .build()
//            this.navigate(R.id.action_oneFragment_to_twoFragment, null, navOptions)

            //3使用popBackStack弹出Fragment
            //方式一 弹出当前的Fragment
            //controller.navigate(R.id.action_oneFragment_to_twoFragment); //从oneFragment进入到twoFragment
            //controller.navigate(R.id.action_twoFragment_to_threeFragment);  //从twoFragment进入到threeFragment
            //controller.popBackStack(); //弹出threeFragment，回到twoFragment
                //方式2
//            controller.setGraph(R.navigation.demo_one_nav);
//            controller.navigate(R.id.action_oneFragment_to_twoFragment); //从oneFragment进入到twoFragment
//            controller.navigate(R.id.action_twoFragment_to_threeFragment); //从twoFragment进入到threeFragment
//            controller.popBackStack(R.id.twoFragment, true); //弹出到twoFragment,第二个参数的布尔值如果为true则表示参数一的Fragment一起弹出，这个时候我们就会回到oneFragment
            //另外，如果你发现目标Fragment不存在跳转失败后，你可以参考以下这种实现，直接创建指定目标的Fragment
//             boolean jumpStatus = Navigation.findNavController(getView()).popBackStack(R.id.scenesTimingFragment, false);
//            if (!jumpStatus){
//                Navigation.findNavController(getView()).navigate(R.id.scenesTimingFragment);
//            }

    //****** 4 getNavInflater  Navigation创建
//            val navGraph: NavGraph = this.getNavInflater()
//                .inflate(R.navigation.demo_one_nav) //获得NavGraph 就是navigation，可以操作各种Fragment的增加或移除功能，一个是代码操作一个是xml操作
//            this.setGraph(navGraph) //其实用这种方式setGraph(navGraph)与直接setGraph(R.navigation.demo_one_nav);一样


//            5 Fragment里嵌套Navigation
//            mNavController = Navigation.findNavController(getActivity(), R.id.scenes_fragment);//请注意，这个 R.id.scenes_fragment是Fragment布局里的fragment


        }

        //2 动态加载
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment??:return
//        //有点像动态加载xml
//        val navigation = navHostFragment.navController.navInflater.inflate(R.navigation.nav_graden)
//        navigation.startDestination = R.id.loginFragment//起点
//        navHostFragment.navController.graph = navigation//导航图
        //也可以传递参数
//        navHostFragment.arguments?.let { it.putString("zzq","zzqybh5201314") }

        ///这个R.id.fragment就是Activity布局里fragment控件的id
//        val findNavController =
//            Navigation.findNavController(NavHostMainAct@this, R.id.nav_host_fragment)
//        NavOptions.Builder()
//            .setPopUpTo(R.id.loginFragment, true)
//            .build()


    }
}