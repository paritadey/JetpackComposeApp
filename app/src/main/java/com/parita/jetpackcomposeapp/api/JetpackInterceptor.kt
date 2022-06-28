package com.parita.jetpackcomposeapp.api

import com.parita.jetpackcomposeapp.util.JetpackConstant.RapidAPI_Host
import com.parita.jetpackcomposeapp.util.JetpackConstant.RapidAPI_KEY
import com.parita.jetpackcomposeapp.util.JetpackConstant.RapidApi_Key_Name
import com.parita.jetpackcomposeapp.util.JetpackConstant.RapidApi_host_Name
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class JetpackInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
            .newBuilder()
            .addHeader(RapidApi_Key_Name, RapidAPI_KEY)
            .addHeader(RapidApi_host_Name, RapidAPI_Host)
            .build()
        return chain.proceed(request)
    }
}