package com.test.animation.logic.network.appdata

import android.content.Context
import android.util.Log
import com.test.animation.AnimationApp
import com.test.animation.logic.Utils.Gen
import com.test.animation.logic.Utils.dns
import com.test.animation.logic.Utils.ssl
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.Version
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class ServiceCreator() {
     var inter_face:PicaData

    init {
        val editor= AnimationApp.context.getSharedPreferences("data", 0).edit()
        val pref=AnimationApp.context.getSharedPreferences("data",Context.MODE_PRIVATE)
        val ss= Gen()//加密类
        val client_builder = OkHttpClient.Builder()
           client_builder.dns(dns())//设置DNS服务器
           client_builder.interceptors().add(Interceptor { chain ->
            val original_request = chain.request()
            val part_url=original_request.url().toString().replace("https://picaapi.picacomic.com/","")
            val real_time= ((System.currentTimeMillis() / 1000)+pref.getLong("key_time",0L)).toString()
               Log.d("time",real_time)
            val random_uuid = UUID.randomUUID().toString().replace("-", "")
            val key="~d}\$Q7\$eIni=V)9\\RK/P.RM4;9[7|@/CA}b~OW!3?EV`:<>M7pddUBL5n|0/*Cn"
            val row_str=part_url+real_time+random_uuid+original_request.method()+ "C69BAF41DA5ABD1FFEDC6D2FEA56B"
            val secret_signature=ss.C(row_str,key)
            val requestBuilder = original_request.newBuilder()
                .addHeader("api-key","C69BAF41DA5ABD1FFEDC6D2FEA56B")
                .addHeader("accept","application/vnd.picacomic.com.v1+json")
                .addHeader("app-channel","2")
                .addHeader("time",real_time)
                .addHeader("nonce",random_uuid)
                .addHeader("signature",secret_signature)
                .addHeader("app-version","2.2.1.3.3.4")
                .addHeader("app-uuid","defaultUuid")
                .addHeader("image-quality","medium")
                .addHeader("app-platform", "android")
                .addHeader("app-build-version","45")
                .method(original_request.method(),original_request.body())
            val request = requestBuilder.build()
            val proceed= chain.proceed(request);
            val _time= proceed.headers().get("Server-Time")
            if(_time!=null) {
                val time_min = _time.toLong() - (System.currentTimeMillis() / 1000)
                editor.putLong("key_time",time_min)
                editor.apply()


            }
            proceed

        })
        //CertificatePinner.Builder().build()

        //var lls= ssl()
      // client_builder.sslSocketFactory(lls,lls.systemDefaultTrustManager())
           val clients= client_builder.build()


           inter_face= Retrofit.Builder()
                .baseUrl("https://picaapi.picacomic.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(clients)
                .build()
                .create(PicaData::class.java)
    }




}