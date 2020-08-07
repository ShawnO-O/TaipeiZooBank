package com.zoo.taipeizoo.sealed

import com.zoo.taipeizoo.model.data.callApiResult.plantInfo.PlantInfoResults

sealed class PlantInfoStatus {
    data class Success(val data: PlantInfoResults) : PlantInfoStatus()
    data class Failure(val message: String) : PlantInfoStatus()
    data class Error(val message: String) : PlantInfoStatus()
}