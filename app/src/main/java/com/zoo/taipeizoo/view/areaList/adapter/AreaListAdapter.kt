package com.zoo.taipeizoo.view.areaList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zoo.taipeizoo.R
import com.zoo.taipeizoo.model.local.AreaItem
import com.zoo.taipeizoo.view.areaList.AreaListInterface
import com.zoo.taipeizoo.view.areaList.viewHolder.AreaListViewHolder

class AreaListAdapter(private val areaListInterface: AreaListInterface) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data = ArrayList<AreaItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            1 ->
                return AreaListViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_area_list_pub, parent, false)
                )

            else ->
                throw Exception("View Type not found !!")
        }
    }

    override fun getItemViewType(position: Int) = data[position].viewType

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AreaListViewHolder -> holder.setData(data, position, areaListInterface)
        }
    }
}