package com.zoo.taipeizoo.widget


import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zoo.taipeizoo.R
import com.zoo.taipeizoo.model.data.callApiResult.plantInfo.ResultData
import com.zoo.taipeizoo.model.local.ItemBottomSheetPlant
import com.zoo.taipeizoo.view.plantInfo.adapter.PlantInfoAdapter
import kotlinx.android.synthetic.main.dialog_bottom_sheet_plant.*

/**
 * @author Max
 * @since 2020/06/16
 * 由上而下生命週期 */
class BottomSurveyDialogFragment(private val resultData: ResultData) : BottomSheetDialogFragment() {
    private val plantInfoAdapter: PlantInfoAdapter by lazy {
        PlantInfoAdapter()
    }

    private var dataList = ArrayList<ItemBottomSheetPlant>()

    override fun show(manager: FragmentManager, tag: String?) {
        //在每個add事務前增加一個remove,防止連續的add
        try {
            manager.beginTransaction().remove(this).commit()
            super.show(manager, tag)
        } catch (e: Exception) { //同一实例使用不同的tag会异常,这里捕获一下
            e.printStackTrace()
        }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // 解决dialog最上面狀態列表要黑屏問題
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        }
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        // 隱藏鍵盤
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        return dialog
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 客製化布局
        return inflater.inflate(R.layout.dialog_bottom_sheet_plant, container, false)
    }

    override fun onStart() {
        super.onStart()
        initView() // 初始化介面
        initRecycler()
        initViewData()
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme //改變Dialog上半部圓角的問題
    }

    private fun initView() {
        /* BottomDialog彈出高度調整 */
        view?.let {
            val mBehavior = BottomSheetBehavior.from(it.parent as View)
            val layoutParams = it.layoutParams
            val height = (resources.displayMetrics.heightPixels * 0.96).toInt() //屏幕高的96%
            layoutParams.height = height
            it.layoutParams = layoutParams
            mBehavior.peekHeight = height //防止BottomSheetDialogFragment彈出不完全
        }

    }

    private fun initRecycler() {
        rvBottomSheetPlantInfo.layoutManager = LinearLayoutManager(context)
        rvBottomSheetPlantInfo.adapter = plantInfoAdapter
    }

    private fun initViewData() {
        dataList.add(
            ItemBottomSheetPlant(
                viewType = 1,
                imgUrl = "http://www.zoo.gov.tw/iTAP/04_Plant/Lythraceae/subcostata/subcostata_1.jpg"
            )
        )
        dataList.add(
            ItemBottomSheetPlant(
                viewType = 2,
                title = resultData.fNameCh,
                text = resultData.fNameEn
            )
        )
        dataList.add(ItemBottomSheetPlant(viewType = 2, title = "簡介", text = resultData.fBrief))
        dataList.add(ItemBottomSheetPlant(viewType = 2, title = "辨識方式", text = resultData.fFeature))
        plantInfoAdapter.data = dataList
        plantInfoAdapter.notifyDataSetChanged()
    }
}