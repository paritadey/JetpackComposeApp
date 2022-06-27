package com.parita.jetpackcomposeapp.api

import com.parita.jetpackcomposeapp.util.JetpackConstant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val client = OkHttpClient.Builder().apply {
        addInterceptor(JetpackInterceptor())
    }.build()

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(JetpackConstant.BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
    val api: RetrofitApi by lazy {
        retrofit.create(RetrofitApi::class.java)
    }
}