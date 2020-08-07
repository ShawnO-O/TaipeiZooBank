package com.zoo.taipeizoo.model.source.repository.plantInfo

import com.zoo.taipeizoo.model.data.callApiParameter.PlantInfoParameter
import com.zoo.taipeizoo.model.data.callApiResult.plantInfo.PlantInfoResults
import com.zoo.taipeizoo.model.source.remote.plantInfo.PlantInfoRemoteDataSource
import io.reactivex.Single
import retrofit2.Response

class PlantInfoRepository(private val plantInfoRemoteDataSource: PlantInfoRemoteDataSource):PlantInfoDataSource {

    companion object {
        fun getInstance() = InstanceHolder.instance
    }

    private object InstanceHolder {
        val instance = PlantInfoRepository(PlantInfoRemoteDataSource.getInstance())
    }

    override fun getPlantInfo(plantInfoParameter: PlantInfoParameter): Single<Response<PlantInfoResults>> {
        return plantInfoRemoteDataSource.getPlantInfo(plantInfoParameter)
    }
}