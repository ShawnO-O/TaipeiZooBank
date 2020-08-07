package com.zoo.taipeizoo.model.source.repository.areaList

import com.zoo.taipeizoo.model.local.AreaItem

interface AreaListDataSource {
    fun getAreaList():ArrayList<AreaItem>
}