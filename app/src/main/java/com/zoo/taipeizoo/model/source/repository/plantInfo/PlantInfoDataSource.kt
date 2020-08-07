package com.zoo.taipeizoo.model.source.repository.plantInfo

import com.zoo.taipeizoo.model.data.callApiParameter.PlantInfoParameter
import com.zoo.taipeizoo.model.data.callApiResult.plantInfo.PlantInfoResults
import io.reactivex.Single
import retrofit2.Response

interface PlantInfoDataSource {
    fun getPlantInfo(plantInfoParameter: PlantInfoParameter):Single<Response<PlantInfoResults>>
}