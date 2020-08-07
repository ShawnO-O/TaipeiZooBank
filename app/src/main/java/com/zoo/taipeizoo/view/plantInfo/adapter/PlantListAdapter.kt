package com.zoo.taipeizoo.view.plantInfo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zoo.taipeizoo.R
import com.zoo.taipeizoo.model.data.callApiResult.plantInfo.ResultData
import com.zoo.taipeizoo.view.plantInfo.PlantInfoInterface
import com.zoo.taipeizoo.view.plantInfo.viewHolder.PlantListViewHolder

class PlantListAdapter(private var plantInfoInterface: PlantInfoInterface):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data  = ArrayList<ResultData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            1->{
                return PlantListViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_area_list_pub,parent,false)
                )
            }
            else->
                throw Exception("view type not found !!")
        }

    }

    override fun getItemViewType(position: Int) = data[position].viewType

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is PlantListViewHolder->{
             holder.setData(data,position,plantInfoInterface)
            }
        }
    }
}