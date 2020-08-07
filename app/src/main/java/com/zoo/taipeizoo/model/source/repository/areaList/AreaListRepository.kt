package com.zoo.taipeizoo.model.source.repository.areaList

import com.zoo.taipeizoo.model.local.AreaItem
import com.zoo.taipeizoo.model.source.remote.areaList.AreaListRemoteDataSource

class AreaListRepository(private val areaListRemoteDataSource: AreaListRemoteDataSource) :  AreaListDataSource {

    companion object {
        fun getInstance() = InstanceHolder.instance
    }

    private object InstanceHolder {
        val instance = AreaListRepository(AreaListRemoteDataSource.getInstance())
    }

    override fun getAreaList(): ArrayList<AreaItem> {
        return areaListRemoteDataSource.getAreaList()
    }
}