package com.zoo.taipeizoo.view.plantInfo.viewHolder

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zoo.taipeizoo.model.local.ItemBottomSheetPlant
import kotlinx.android.synthetic.main.item_plant_photo.view.*

class PlantInfoImgViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun setData(itemBottomSheetPlant: ItemBottomSheetPlant) {
        itemView.apply {
            Log.d("shawnTest","${itemBottomSheetPlant.imgUrl}")
            if(itemBottomSheetPlant.imgUrl!="")
            Glide.with(context).load(itemBottomSheetPlant.imgUrl).into(ivItemPlantPhoto)
        }
    }
}