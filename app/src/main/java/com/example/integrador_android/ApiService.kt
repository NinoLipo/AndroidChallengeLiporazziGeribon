package com.example.integrador_android

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun suggestionApiRequest(@Url url: String):Response<SuggestionResponse>
}