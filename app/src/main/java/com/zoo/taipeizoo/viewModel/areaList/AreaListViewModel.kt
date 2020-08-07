package com.zoo.taipeizoo.viewModel.areaList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zoo.taipeizoo.model.local.AreaItem
import com.zoo.taipeizoo.model.source.repository.areaList.AreaListRepository

class AreaListViewModel(val areaListRepository: AreaListRepository) : ViewModel() {
    var areaListResult = MutableLiveData<ArrayList<AreaItem>>()

    fun getAreaList() {
        areaListResult.value = areaListRepository.getAreaList()
    }
}