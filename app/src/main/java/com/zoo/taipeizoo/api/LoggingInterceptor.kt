package com.zoo.taipeizoo.api

import android.util.Log
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


internal class LoggingInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
        val request = chain.request()
        val t1 = System.nanoTime()//请求发起的时间

        val method = request.method()
        if ("POST" == method) {
            val sb = StringBuilder()
            if (request.body() is FormBody) {
                val body = request.body() as FormBody
                for (i in 0 until body.size()) {
                    sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",")
                }
                sb.delete(sb.length - 1, sb.length)
                Log.d("CSDN_LQR", String.format("发送请求 %s on %s %n%s %nRequestParams:{%s}",
                        request.url(), chain.connection(), request.headers(), sb.toString()))
            }
        } else {
            Log.d("CSDN_LQR", String.format("发送请求 %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()))
        }
        val response = chain.proceed(request)
        val t2 = System.nanoTime()//收到响应的时间
        //这里不能直接使用response.body().string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
        //个新的response给应用层处理
        val responseBody = response.peekBody(1024 * 1024)
        Log.d("CSDN_LQR",
                String.format("接收响应: [%s] %n返回json:【%s】 %n%.1fms %n",
                        response.request().url(),
                        responseBody.string(),
                        (t2 - t1) / 1e6
//                        ,
//                        response.headers()
                ))
        return response
    }
}