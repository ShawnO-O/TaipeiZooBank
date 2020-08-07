package com.zoo.taipeizoo.viewModel.plantInfo

import androidx.lifecycle.MutableLiveData
import com.zoo.taipeizoo.model.data.callApiParameter.PlantInfoParameter
import com.zoo.taipeizoo.model.source.repository.plantInfo.PlantInfoRepository
import com.zoo.taipeizoo.sealed.PlantInfoStatus
import com.zoo.taipeizoo.viewModel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tw.com.bank518.utils.executors.addToCompositeDisposable

class PlantInfoViewModel(var plantInfoRepository: PlantInfoRepository) : BaseViewModel() {
    val plantIndoResult = MutableLiveData<PlantInfoStatus>()

    fun getPlantInfo(plantInfoParameter: PlantInfoParameter) {
        plantInfoRepository.getPlantInfo(plantInfoParameter)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                it.body()?.let { result ->
                    plantIndoResult.value = PlantInfoStatus.Success(result)
                }
            }
            .subscribe({}, {
                it.printStackTrace()
                plantIndoResult.value = PlantInfoStatus.Error("$it")
            })
            .addToCompositeDisposable(compositeDisposable)
    }
}