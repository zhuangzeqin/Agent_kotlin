package com.eeepay.zzq.agent_kotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eeepay.zzq.Api
import com.eeepay.zzq.ApiConstant
import com.eeepay.zzq.bean.LoginktInfo
import com.eeepay.zzq.bean.MerFilterInfo
import com.eeepay.zzq.parse.ErrorInfo
import com.eeepay.zzq.utils.EncRSA
import com.eeepay.zzq.utils.FastSharedPreferencesTools
import com.rxjava.rxlife.lifeOnMain
import rxhttp.RxHttp
import rxhttp.wrapper.annotations.NonNull
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.util.*
import kotlin.collections.HashMap

/**
 * kotlin 练手项目
 */
class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        btn_login.setOnClickListener {
//                loginRequset("18681490423", "123456")
//        }
//        btn_get.setOnClickListener(View.OnClickListener {
//
//            getCode(getUUID()!!)
//        })
//        btn_post.setOnClickListener(View.OnClickListener {
//            val id = it.id
//            getPostRequest()
//        })
    }

    /**
     * 登录的请求操作
     */
    fun loginRequset(@NonNull userName: String, @NonNull password: String) {
        var encpassword: String? = null
        encpassword = try {
            EncRSA.EncBase64Pass(password) //RSA 加密密码
        } catch (e: Exception) {
            e.printStackTrace().toString()
        }
        /** ------注释说明-参数的封装-------  */
        val mParams: HashMap<String, String> = HashMap()
        mParams.put("userName", userName)
        mParams.put("password", encpassword) //RSA加密后的密码
        val lib_team_id = "110010"//组织id
        mParams.put("agentOem", lib_team_id) //代理商oem值 盛代宝为200010
        RxHttp.postJson(Api.API_LOGIN_URL).addAll(mParams)
            .asResultCallBack(LoginktInfo.Data::class.java).lifeOnMain(this).subscribe(
                { t ->
//                    tv_hello.text = t.agentName
                    FastSharedPreferencesTools.getInstance().putSerializable("LoginInfo", t)
                }, { error ->
                    val errorInfo = ErrorInfo(error)//错误信息
//                    tv_hello.text = "${errorInfo.errorCode}:${errorInfo.errorMsg}"
//                    Toast.makeText(this, tv_hello.text, Toast.LENGTH_LONG).show()
                }
            )
    }

    /**
     * get请求
     */
    fun getCode(@NonNull uuid: String) {
        RxHttp.get(ApiConstant.API_LOADCAPTCHA_URL).add("uuid", uuid).asOkResponse()
            .lifeOnMain(this).subscribe({ result ->
                val body = result.body
                val byteStream = body!!.byteStream()
                val readStream = readStream(byteStream)
//                Glide.with(this).asGif().load(readStream)
//                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//                    .into(iv_code)
            }, { error ->
                val errorInfo = ErrorInfo(error)//错误信息
//                tv_hello.text = "${errorInfo.errorCode}:${errorInfo.errorMsg}"
//                Toast.makeText(this, tv_hello.text, Toast.LENGTH_LONG).show()
            })
    }

    /**
     * post 请求
     */
    fun getPostRequest() {
        val serializable = FastSharedPreferencesTools.getInstance().getSerializable("LoginInfo", null)
        val userInfo = serializable as LoginktInfo.Data
        RxHttp.postJson(ApiConstant.API_WILD_MER_URL).add("merchantKey","").
        asResultCallBackList(MerFilterInfo.DataBean::class.java).lifeOnMain(this).subscribe(
            {
                    t -> Toast.makeText(this, t.toString(), Toast.LENGTH_LONG).show()
            },
            { error ->
                val errorInfo = ErrorInfo(error)//错误信息
//                tv_hello.text = "${errorInfo.errorCode}:${errorInfo.errorMsg}"
//                Toast.makeText(this, tv_hello.text, Toast.LENGTH_LONG).show()
            }

        )
    }

    /**
     * 获取32位UUID
     *
     * @return
     */
    fun getUUID(): String? {
        return UUID.randomUUID().toString().replace("-".toRegex(), "")
    }

    /**
     * 将图片内容解析成字节数组
     *
     * @param inStream
     * @return byte[]
     * @throws Exception
     */
    @Throws(java.lang.Exception::class)
    fun readStream(inStream: InputStream): ByteArray? {
        val buffer = ByteArray(1024)
        var len = -1
        val outStream = ByteArrayOutputStream()
        // while ((len = inStream.read(buffer)) != -1)
//        while (inStream.read(buffer).also { len = it } != -1) {
        while (inStream.read(buffer).also { i -> len = i } != -1) {
            outStream.write(buffer, 0, len)
        }
        val data = outStream.toByteArray()
        outStream.flush()
        outStream.close()
        inStream.close()
        return data
    }


}

