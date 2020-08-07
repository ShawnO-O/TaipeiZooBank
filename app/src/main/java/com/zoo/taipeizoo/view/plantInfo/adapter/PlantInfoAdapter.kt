package com.zoo.taipeizoo.view.plantInfo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zoo.taipeizoo.R
import com.zoo.taipeizoo.model.local.ItemBottomSheetPlant
import com.zoo.taipeizoo.view.plantInfo.viewHolder.PlantInfoImgViewHolder
import com.zoo.taipeizoo.view.plantInfo.viewHolder.PlantInfoTextViewHolder
import java.lang.Exception

class PlantInfoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data = ArrayList<ItemBottomSheetPlant>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            1 -> {
                return PlantInfoImgViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_plant_photo, parent, false)
                )
            }
            2 -> {
                return PlantInfoTextViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_plant_text, parent, false)
                )

            }
            else -> {
                throw Exception("view type not found!!")
            }
        }
    }

    override fun getItemViewType(position: Int) = data[position].viewType

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PlantInfoImgViewHolder -> {
                holder.setData(data[position])
            }
            is PlantInfoTextViewHolder -> {
                holder.setData(data[position])
            }
        }
    }
}