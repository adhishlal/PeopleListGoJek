package com.gopay.network

import com.gopay.network.responses.PeopleResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("people/")
    suspend fun fetchPeople(@Query("page") page: Int): PeopleResponseModel
}