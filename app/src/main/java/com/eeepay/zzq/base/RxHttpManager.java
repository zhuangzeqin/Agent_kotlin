package com.eeepay.zzq.base;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.eeepay.zzq.agent_kotlin.BuildConfig;
import com.eeepay.zzq.bean.AppDeviceInfo;
import com.eeepay.zzq.bean.LoginktInfo;
import com.eeepay.zzq.utils.FastSharedPreferencesTools;
import com.eeepay.zzq.utils.Md5;
import com.eeepay.zzq.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;
import rxhttp.RxHttp;
import rxhttp.wrapper.callback.IConverter;
import rxhttp.wrapper.converter.GsonConverter;
import rxhttp.wrapper.utils.GsonUtil;

/**
 * 描述：RxHttp 管理类
 * 作者：zhuangzeqin
 * 时间: 2020年4月16日09:59:19
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public final class RxHttpManager {
    //再拼接的字符串后加上key=46940880d9fGklaq23lask;ldasdo;3asebfdylkj@@KqAgentapi2#$$^&pretty
    private static final String KEY_VALUE = "key=46940880d9fGklaq23lask;ldasdo;3asebfdylkj@@KqAgentapi2#$$^&pretty";
    //Gson 处理Null 值的问题
//    private static final Gson GSON = new GsonBuilder().registerTypeAdapterFactory(new NullToEmptyAdapterFactory()).create();
    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().create();
    private static final int TIMEOUT = 30;//超时时间30s
    private static final int MAXIDLECONNECTIONS = 5;//连接的个数
//    @Converter(name = "gsonConverter")
    public static final IConverter GSONCONVERTER = GsonConverter.create(GSON);
    /**
     * ------注释说明----RxHttp 初始化工作----
     **/
    public static void init() {
        OkHttpClient client = getOkHttpClient();
        //RxHttp初始化，自定义OkHttpClient对象，非必须
        RxHttp.init(client, BuildConfig.DEBUG);
        //设置全局的转换器，非必须
        RxHttp.setConverter(GSONCONVERTER);
        //设置公共参数，非必须 根据不同请求添加不同参数，子线程执行，每次发送请求前都会被回调
//            如果希望部分请求不回调这里，发请求前调用Param.setAssemblyEnabled(false)即可
//        RxHttp.setOnParamAssembly(param -> {
//            Method method = param.getMethod();//请求的方法 是get or post
//            Map<String, String> pubParams = new HashMap<>(3);
//            if (method.isPost()) {
//                UserInfo userInfo = UserInfo.getUserInfo2SP();
//                pubParams.put("agent_no", userInfo == null ? "" : userInfo.getAgentNo());//当前登录代理商编号,必填
//                pubParams.put("agentNo", userInfo == null ? "" : userInfo.getAgentNo());//当前登录代理商编号,必填 后台定义的字段不一样
//                pubParams.put("curAgentNo", userInfo == null ? "" : userInfo.getAgentNo());//当前登录代理商编号,必填 后台定义的字段不一样
//                return param.addAll(pubParams);//添加公共请求参数
//            }
//            return param;
//        });

    }

    /**
     * 相关 OkHttpClient 设置配置信息
     *
     * @return
     */
    @NotNull
    private static OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)  //失败重连
                // 这里你可以根据自己的机型设置同时连接的个数和时间，我这里5个，和每个保持时间为30
                .connectionPool(new ConnectionPool(MAXIDLECONNECTIONS, TIMEOUT, TimeUnit.SECONDS))
                .hostnameVerifier((hostname, session) -> true) //忽略host验证
                //            .addInterceptor(new TokenInterceptor())
                .addInterceptor(mHeader)
                .build();
    }

    /**
     * 默认所有的接口都需要签名
     * 签名规则
     * 获取request body的内容
     * 如果有loginToken,这加上,否则忽略
     * app-info 公共参数必须还有当前请求的时间戳(key为timestamp),要参与签名
     * 把上面的参数按字典值正序排序
     * 然后使用key=value&方式拼接起来
     * 再拼接的字符串后加上key=46940880d9f79f27bb7f85ca67102bfdylkj@@agentapi2#$$^&pretty
     * 最后对拼接完成的字符串进行md5签名,然后以sign为key放到请求head中
     * [注意]参数值为空的话忽略
     * 参考登陆接口的api文档说明
     */
//
    final static Interceptor mHeader = chain -> {
        //在这里获取到request后就可以做任何事情了
        Request request = chain.request();
        String isSkip = request.header("isSkip");//是否跳过验证 10086 跳过
        //这个是请求的url，也就是咱们前面配置的baseUrl
        HttpUrl httpUrlurl = request.url();
        //这个是请求方法
        String method = request.method();
        //获取请求body，只有@Body 参数的requestBody 才不会为 null
        RequestBody requestBody = request.body();
        TreeMap<String, Object> rootMap = new TreeMap<>();
        rootMap.clear();
        if (!TextUtils.isEmpty(isSkip) && TextUtils.equals(isSkip, "10086")) {
            ///是否跳过验证 10086 跳过
            request = signInfo(request, rootMap);
        } else {
            if (method.equals("POST")) {
                if (requestBody instanceof FormBody) {//FormBody--表单数据提交
                    for (int i = 0; i < ((FormBody) requestBody).size(); i++) {
                        rootMap.put(((FormBody) requestBody).encodedName(i), ((FormBody) requestBody).encodedValue(i));
                    }
                } else {
                    //buffer流
                    Buffer buffer = new Buffer();
                    requestBody.writeTo(buffer);
                    String oldParamsJson = buffer.readUtf8();
                    rootMap = GSON.fromJson(oldParamsJson, TreeMap.class);  //原始参数
                    if (rootMap == null) {
                        rootMap = new TreeMap<>();
                    }
                    buffer.flush();
                    buffer.close();
                    buffer.clear();
                }
                request = signInfo(request, rootMap);
            } else if (method.equals("GET")) {
                //通过请求地址(最初始的请求地址)获取到参数列表
                Set<String> parameterNames = httpUrlurl.queryParameterNames();
                for (String key : parameterNames) {  //循环参数列表
                    rootMap.put(key, httpUrlurl.queryParameter(key));
                }
                request = signInfo(request, rootMap);
            }
        }
        return chain.proceed(request);
    };

    /**
     * 签名构建信息
     *
     * @param request
     * @param rootMap
     * @return
     */
    private static Request signInfo(Request request, TreeMap<String, Object> rootMap) {
        String timestamp = System.currentTimeMillis() + "";//时间戳
        rootMap.put("timestamp", timestamp);
        LoginktInfo.Data loginInfo = (LoginktInfo.Data)FastSharedPreferencesTools.getInstance().getSerializable("LoginInfo", null);
//        UserInfo userInfo = UserInfo.getUserInfo2SP();
        String loginToken = loginInfo==null? "":loginInfo.getLoginToken();//登录的token
        rootMap.put("loginToken", loginToken);
        //1 遍历map key拼接  key=value&
        //2 value 空的不拼接
        String data = "";
        Iterator iter = rootMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            // 获取key
            String key = (String) entry.getKey();
            // 获取value
            Object value1 = entry.getValue();
            if (value1 instanceof String) {
                String value = (String) entry.getValue();
                if (!TextUtils.isEmpty(value)) {
                    data = data + key + "=" + value + "&";
                }
            } else if (value1 instanceof Boolean) {
                Boolean value = (Boolean) entry.getValue();
                if (value != null) {
                    data = data + key + "=" + value + "&";
                }
            } else if (value1 instanceof Integer) {
                Integer value = (Integer) entry.getValue();
                if (value != null) {
                    data = data + key + "=" + value + "&";
                }
            } else if (value1 instanceof Long) {
                Long value = (Long) entry.getValue();
                if (value != null) {
                    data = data + key + "=" + value + "&";
                }
            } else if (value1 instanceof Double) {
                Double value = (Double) entry.getValue();
                if (value != null) {
                    data = data + key + "=" + value + "&";
                }
            } else if (value1 instanceof Float) {
                Float value = (Float) entry.getValue();
                if (value != null) {
                    data = data + key + "=" + value + "&";
                }
            } else if (value1 instanceof ArrayList) {
                ArrayList<String> value = (ArrayList<String>) entry.getValue();
                if (value != null) {
                    JSONArray jsonArray = new JSONArray(value);
                    data = data + key + "=" + jsonArray.toString() + "&";
                }
            }
        }
        //再拼接的字符串后加上key=46940880d9f79f27bb7f85ca67102bfdylkj@@agentapi2#$$^&pretty
        data = data + KEY_VALUE;
        //最后对拼接完成的字符串进行md5签名,然后以sign为key放到请求body中
        String sign = Md5.encode(data);
        //重新组装 装换成json字符串
        String app_info = getAppDeviceInfo(sign, timestamp);
        //增加相同的head头信息 构建新的request
        request = request.newBuilder()
                .addHeader("Content-Type", "application/json; charset=UTF-8")
                .addHeader("Connection", "keep-alive")
                .addHeader("Accept", "*/*")
                .addHeader("app-info", app_info)
                .build();
        return request;
    }

    /**
     * 公共参数转换为json 字符串
     *
     * @return
     */
    private static String getAppDeviceInfo(String sign, String timestamp) {
        try {
            AppDeviceInfo appDeviceInfo = new AppDeviceInfo();
            String appName ="快展钱";// app的项目名比如盛代宝后面有可能是OEM
            appDeviceInfo.setAppName(appName);
            appDeviceInfo.setName(android.os.Build.MODEL); // 型号 Mi 5s plus
            appDeviceInfo.setSystemName("android");// android / ios
            appDeviceInfo.setSystemVersion(android.os.Build.VERSION.RELEASE);//系统版本 8.0.0
            appDeviceInfo.setDeviceId(getDeviceId());//设备id
            appDeviceInfo.setAppVersion(getVersionName());//app版本
            appDeviceInfo.setAppBuild(Utils.getUUID());//app构建版本
            String appNo = "33";// app的标志
            appDeviceInfo.setAppNo(appNo);
            appDeviceInfo.setAppChannel(appNo);//app渠道---
//            String loginToken = UserInfo.getUserInfo2SP().getLoginToken();//登录的token
            LoginktInfo.Data userInfo = (LoginktInfo.Data)FastSharedPreferencesTools.getInstance().getSerializable("LoginInfo", null);
//            UserInfo userInfo = UserInfo.getUserInfo2SP();
            String loginToken = userInfo==null? "":userInfo.getLoginToken();//登录的token
//        String loginToken = PreferenceUtils.getStringParam(ConfigPathConstants.SP_LOGINTOKEN, "unknow");
            appDeviceInfo.setLoginToken(loginToken);//登陆token
            appDeviceInfo.setTimestamp(timestamp);//当前的时间戳
            appDeviceInfo.setSign(sign);
            //新增参数； 为了方便集成极光推送的功能
            appDeviceInfo.setJpushDevice(getJpushDeviceId());//获取极光推送的注册的id
            String jsonData = GsonUtil.toJson(appDeviceInfo);//公共参数转换为json 字符串
            //最好编码一下； 防止乱码
            String encode = URLEncoder.encode(jsonData, "UTF-8");//App传递给后台时候编码
            return encode;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    // 获得软件版本名
    private static String getVersionName() {
        String versionname = "unknow";
        try {
            versionname = Utils.getApp().getPackageManager().getPackageInfo(
                    Utils.getApp().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionname;
    }

    /**
     * 返回安卓设备ID
     */
    @SuppressLint("HardwareIds")
    private static String getDeviceId() {
        String id = "NO Search";
        id = android.provider.Settings.Secure.getString(
                Utils.getApp().getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID);
        return id;
    }

    /**
     * 获取极光推送的注册的id
     *
     * @return
     */
    private static String getJpushDeviceId() {
        //新增参数； 为了方便集成极光推送的功能
//        String registerID = JPushInterface.getRegistrationID(Utils.getApp().getApplicationContext());
        return "0";
    }
}
