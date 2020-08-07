package com.zoo.taipeizoo.model.source.remote.plantInfo

import com.zoo.taipeizoo.api.RetrofitManager
import com.zoo.taipeizoo.model.data.callApiParameter.PlantInfoParameter
import com.zoo.taipeizoo.model.data.callApiResult.plantInfo.PlantInfoResults
import com.zoo.taipeizoo.model.source.repository.plantInfo.PlantInfoDataSource
import io.reactivex.Single
import retrofit2.Response

class PlantInfoRemoteDataSource : PlantInfoDataSource {

    companion object {
        fun getInstance() = InstanceHolder.instance
        val zooApi = RetrofitManager.getZooApi()
    }

    private object InstanceHolder {
        val instance = PlantInfoRemoteDataSource()
    }

    override fun getPlantInfo(plantInfoParameter: PlantInfoParameter): Single<Response<PlantInfoResults>> {
        return zooApi.getPlantInfo(
            q = plantInfoParameter.q,
            limit = plantInfoParameter.limit,
            offset = plantInfoParameter.offset,
            scope = "resourceAquire"
        )
    }
}