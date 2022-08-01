package com.parita.jetpackcomposeapp.api

import android.app.Application
import com.parita.jetpackcomposeapp.repository.JetpackRepository
import com.parita.jetpackcomposeapp.room.NoteDatabase
import com.parita.jetpackcomposeapp.room.NotesDatabaseDao
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
    fun getNoteDatabase(context: Application): NoteDatabase {
        return NoteDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun appDao(noteDatabase: NoteDatabase): NotesDatabaseDao {
        return noteDatabase.noteDao()
    }

    @Singleton
    @Provides
    fun provideUserRepository(
        api: RetrofitApi,
        notesDatabaseDao: NotesDatabaseDao
    ) = JetpackRepository(api, notesDatabaseDao)

    @Singleton
    @Provides
    fun provideJetpackApi(): RetrofitApi {
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