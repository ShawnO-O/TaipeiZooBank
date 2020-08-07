package com.zoo.taipeizoo.model.source.remote.areaList

import com.zoo.taipeizoo.model.local.ZooArea
import com.zoo.taipeizoo.model.local.AreaItem
import com.zoo.taipeizoo.model.source.repository.areaList.AreaListDataSource

class AreaListRemoteDataSource : AreaListDataSource {

    companion object {
        fun getInstance() = InstanceHolder.instance
    }

    private object InstanceHolder {
        val instance = AreaListRemoteDataSource()
    }

    override fun getAreaList(): ArrayList<AreaItem> {
        return ZooArea().getZooArea()
    }
}