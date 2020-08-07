package com.zoo.taipeizoo.view.areaList.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zoo.taipeizoo.R
import com.zoo.taipeizoo.model.local.AreaItem
import com.zoo.taipeizoo.view.areaList.AreaListInterface
import kotlinx.android.synthetic.main.item_area_list_pub.view.*

class AreaListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun setData(data: ArrayList<AreaItem>, position: Int, areaListInterface: AreaListInterface) {
        itemView.apply {
            data[position].let { item ->
                Glide.with(context).setDefaultRequestOptions(RequestOptions().placeholder(R.mipmap.personalitytest_no_login)).load(item.picUrl).into(imgAreaListIcon)
                tvAreaListTitle.text = item.name
                tvAreaListContent.text = item.info
                tvAreaListCloseInfo.text = if (item.memo != "") item.memo else "無休館資訊"
                clAreaListMain.setOnClickListener {
                    areaListInterface.onItemClick(item)
                }
            }

        }
    }
}