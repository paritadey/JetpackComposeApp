package com.parita.jetpackcomposeapp.api

import com.google.gson.JsonObject
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface RetrofitApi {
    @GET("search")
    suspend fun getTrackHits(
        @Query("term") term: String,
        @Query("locale") locale: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): JsonObject

}