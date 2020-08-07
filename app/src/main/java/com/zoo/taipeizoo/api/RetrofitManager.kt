package com.zoo.taipeizoo.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitManager {
    var retrofit: Retrofit

    init {
        val baseUrl = "https://data.taipei/api/v1/dataset/"
        val builder = OkHttpClient.Builder()
        // Log信息拦截器
        val loggingInterceptor = LoggingInterceptor()
        //loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY//这里可以选择拦截级别
        //设置 Debug Log 模式
        builder.addInterceptor(loggingInterceptor)

        val client = builder.build()
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun getZooApi() = retrofit.create(ZooApi::class.java)
}