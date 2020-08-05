package com.eeepay.zzq;

/*
 * 描述：RxHttp共有3种指定域名的方式，按优先级排名分别是：手动输入域名 > 指定非默认域名 > 使用默认域名。
 * 作者：zhuangzeqin
 * 时间: 2020/2/26-11:11
 * 邮箱：zzq@eeepay.cn
 * 备注: main 定义的这个文件夹里的RurlConfig以后各自的渠道自己去定义 切记
 */
public final class UrlConfig {
    //以后测试环境打包的APP地址用这个阿里云外网域名地址---测试的请求数据接口
    public static final String TEST_BASEURL = "http://cs-kq-agentapi2.51ydmw.com/kqAgentApi2/";//测试
    //准生产的请求数据接口
    public static final String QUASIPRODUCTION_BASEURL = "http://test-kqagentapi2.qhylpay.com/kqAgentApi2/";//准生产
    //正式的请求数据接口
    public static final String OFFICIAL_BASEURL = "http://agentapi2.skuaiqian.cn/kqAgentApi2/";//生产;
    //圣伟请求数据接口192.168.3.14   172
    public static final String DEVELOP_BASEURL = "http://192.168.1.147:9000/kqAgentApi2/";//开发环境
    //圣伟请求数据接口192.168.3.14   172
//    @Domain(name = "sw_dev")
//    public static final String DEVELOP_BASEURL_SW = "http://192.168.3.14:9000/kqAgentApi2/";//sw开发环境
//    //刘开松开发环境
//    @Domain(name = "lks")
//    public static final String DEVELOP_BASEURL_LKS = "http://192.168.4.28:9000/kqAgentApi2/";//刘开松开发环境
//    @DefaultDomain
//    public static String baseDefaultUrl = TEST_BASEURL;//默认的域名地址生产的OFFICIAL_BASEURL,如果需要运行切换环境则动态改变baseDefaultUrl的值

    //@Domain(name = "zzq")/** ------注释说明--通过@Domain()注解标注非默认域名，就会在RxHttp类中生成setDomainToXxxIfAbsent()方法，其中Xxx就是注解中取的别名。------ **/
    //public static String wanandroid = "https://www.wanandroid.com/";//动态域名地址；非默认域名，并取别名为BaseUrlWanandroid 有时我们需要跟不同的后台人员调试，这时候就可以用到动态域名
    //以后测试环境打包的APP地址用这个阿里云外网域名地址
//    @Domain(name = "sdb_test")
//    public static final String AGENTAPI2_BASEURL2 = "http://cs-ys-agentapi2.51ydmw.com/";


    //========================================02 快展钱Grpc域名配置相关=================================================
    public static final String TEST_URL = "cs-kq-agentapi.51ydmw.com";//测试环境 182服务器
    public static final String TEST_PORT = "8090";

    public static final String QUASIPRODUCTION_URL = "test-kqagentapi.qhylpay.com";//准生产
    public static final String QUASIPRODUCTION_PORT = "57009";

    public static final String PRODUCE_URL = "agentapi.skuaiqian.cn";//生产域名
    public static final String PRODUCE_PORT = "57009";//生产域名
}
