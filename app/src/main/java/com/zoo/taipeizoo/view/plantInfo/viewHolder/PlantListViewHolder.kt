package com.zoo.taipeizoo.view.plantInfo.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zoo.taipeizoo.R
import com.zoo.taipeizoo.model.data.callApiResult.plantInfo.ResultData
import com.zoo.taipeizoo.view.plantInfo.PlantInfoInterface
import kotlinx.android.synthetic.main.item_area_list_pub.view.*

class PlantListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun setData(data:ArrayList<ResultData>,position:Int, plantInfoInterface: PlantInfoInterface) {
        itemView.apply {
            data[position].let {item->
                Glide.with(context)
                    .setDefaultRequestOptions(RequestOptions().placeholder(R.mipmap.personalitytest_no_login).centerCrop())
                    .load(item.fPic01URL.replace("http","https")).into(imgAreaListIcon)
                tvAreaListCloseInfo.visibility = View.GONE
                tvAreaListTitle.text = item.fNameCh
                tvAreaListContent.text = item.fAlsoKnown
                clAreaListMain.setOnClickListener{
                    plantInfoInterface.onItemClick(data[position])
                }
            }
        }
    }
}