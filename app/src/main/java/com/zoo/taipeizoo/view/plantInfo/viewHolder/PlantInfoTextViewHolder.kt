package com.zoo.taipeizoo.view.plantInfo.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.zoo.taipeizoo.model.local.ItemBottomSheetPlant
import kotlinx.android.synthetic.main.item_plant_text.view.*

class PlantInfoTextViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun setData(itemBottomSheetPlant: ItemBottomSheetPlant) {
        itemView.apply {
            tvPlantTitle.text = itemBottomSheetPlant.title
            tvPlantText.text = itemBottomSheetPlant.text
        }
    }
}