package com.kitchen.recipeskotlin.data.network

import retrofit2.Response
import retrofit2.http.GET

import com.kitchen.recipeskotlin.data.model.ModelFilter
import retrofit2.http.Query

interface FoodApiClient {
    @GET("/.json")
    suspend fun getAll(
        @Query("strCategory") strCategory: String
    ): Response<ModelFilter>

    @GET("/.json")
    suspend fun getAllDetail(
        @Query("idMeal") idMeal: String
    ): Response<ModelFilter>
}