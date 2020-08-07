package com.zoo.taipeizoo.api

import com.zoo.taipeizoo.model.data.callApiResult.plantInfo.PlantInfoResults
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ZooApi {
    @GET("f18de02f-b6c9-47c0-8cda-50efad621c14")
    fun getPlantInfo(
        @Query("q") q: String,
        @Query("limit") limit: String,
        @Query("offset") offset: String,
        @Query("scope") scope: String
    ):Single<Response<PlantInfoResults>>

}