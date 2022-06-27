package com.parita.jetpackcomposeapp.api

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class JetpackInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
            .newBuilder()
            .addHeader("X-RapidAPI-Key", "b741a1115cmsh2000882239b456ep1fe83ajsn1ce716699ab9")
            .addHeader("X-RapidAPI-Host", "shazam.p.rapidapi.com")
            .build()
        return chain.proceed(request)
    }
}