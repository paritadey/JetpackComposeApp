package com.parita.jetpackcomposeapp.api

import com.parita.jetpackcomposeapp.repository.JetpackRepository
import com.parita.jetpackcomposeapp.util.JetpackConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RetrofitService {
    @Singleton
    @Provides
    fun provideUserRepository(
        api: RetrofitApi
    ) = JetpackRepository(api)

    @Singleton
    @Provides
    fun provideJetpackApi():RetrofitApi{
        val client = OkHttpClient.Builder().apply {
            addInterceptor(JetpackInterceptor())
        }.build()
        return Retrofit.Builder()
            .baseUrl(JetpackConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(RetrofitApi::class.java)
    }
}