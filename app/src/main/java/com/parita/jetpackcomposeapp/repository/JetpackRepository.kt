package com.parita.jetpackcomposeapp.repository

import com.google.gson.JsonObject
import com.parita.jetpackcomposeapp.api.RetrofitApi
import com.parita.jetpackcomposeapp.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class JetpackRepository @Inject constructor(private val api: RetrofitApi) {
    suspend fun getMusicTracks(
        term: String,
        locale:String,
        offset: Int,
        limit: Int
    ): Resource<JsonObject> {
        val response = try {
            api.getTrackHits(term, locale, offset, limit)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured: ${e.localizedMessage}")
        }
        return Resource.Success(response)
    }
}