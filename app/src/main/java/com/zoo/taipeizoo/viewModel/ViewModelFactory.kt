package com.zoo.taipeizoo.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zoo.taipeizoo.model.source.repository.areaList.AreaListRepository
import com.zoo.taipeizoo.model.source.repository.plantInfo.PlantInfoRepository
import com.zoo.taipeizoo.view.areaList.AreaListFragment
import com.zoo.taipeizoo.view.plantInfo.PlantInfoFragment
import com.zoo.taipeizoo.viewModel.areaList.AreaListViewModel
import com.zoo.taipeizoo.viewModel.plantInfo.PlantInfoViewModel

class ViewModelFactory :ViewModelProvider.NewInstanceFactory(){

    companion object{
        fun getInstance() = InstanceHolder.instance
    }

    private object InstanceHolder{
        val instance = ViewModelFactory()
    }

    @SuppressWarnings("unchecked")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(AreaListViewModel::class.java) ->
                    AreaListViewModel(AreaListRepository.getInstance())
                isAssignableFrom(PlantInfoViewModel::class.java) ->
                    PlantInfoViewModel(PlantInfoRepository.getInstance())
                else ->
                    throw IllegalAccessException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}