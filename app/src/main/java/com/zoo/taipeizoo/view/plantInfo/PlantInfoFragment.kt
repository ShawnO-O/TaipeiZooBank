package com.zoo.taipeizoo.view.plantInfo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.zoo.taipeizoo.R
import com.zoo.taipeizoo.model.data.callApiParameter.PlantInfoParameter
import com.zoo.taipeizoo.model.data.callApiResult.plantInfo.ResultData
import com.zoo.taipeizoo.model.local.ItemBottomSheetPlant
import com.zoo.taipeizoo.sealed.PlantInfoStatus
import com.zoo.taipeizoo.utils.LoadMode
import com.zoo.taipeizoo.view.BaseFragment
import com.zoo.taipeizoo.view.areaList.AreaListFragment
import com.zoo.taipeizoo.view.plantInfo.adapter.PlantInfoAdapter
import com.zoo.taipeizoo.view.plantInfo.adapter.PlantListAdapter
import com.zoo.taipeizoo.viewModel.ViewModelFactory
import com.zoo.taipeizoo.viewModel.plantInfo.PlantInfoViewModel
import kotlinx.android.synthetic.main.dialog_bottom_sheet_plant.*
import kotlinx.android.synthetic.main.fragment_plant_info.*


class PlantInfoFragment : BaseFragment(), PlantInfoInterface {
    private var page = 1
    private val perPage = 20
    private var loadMode = LoadMode.NONE

    companion object {
        fun getInstance() = InstanceHolder.instance
    }

    private object InstanceHolder {
        val instance = AreaListFragment()
    }

    private val plantInfoViewModel: PlantInfoViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory.getInstance()).get(PlantInfoViewModel::class.java)
    }

    private val plantListAdapter: PlantListAdapter by lazy {
        PlantListAdapter(this)
    }

    private val plantInfoAdapter: PlantInfoAdapter by lazy {
        PlantInfoAdapter()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_plant_info, container, false)
        return root
    }

    var title = ""
    var picUrl = ""
    var info = ""
    var category = ""
    var memo = ""
    var webUrl = ""

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        page = 1
        arguments?.apply {
            title = getString("title") ?: ""
            (activity as AppCompatActivity?)!!.supportActionBar!!.title = title
            picUrl = getString("picUrl") ?: ""
            info = getString("info") ?: ""
            category = getString("category") ?: ""
            memo = getString("memo") ?: ""
            webUrl = getString("webUrl") ?: ""
        }
        getPlantInfo()
        initViewModel()
        initView()
    }

    private fun getPlantInfo() {
        plantInfoViewModel.getPlantInfo(
                PlantInfoParameter(
                        q = title,
                        limit = "$perPage",
                        offset = "${(page - 1) * perPage}"
                )
        )

    }

    private lateinit var bottomSheet: BottomSheetBehavior<View>
    private fun initView() {
        bottomSheet = BottomSheetBehavior.from(includeBottomSheet)
        setListener()
        initRecycler()
        initViewData()
    }

    private fun initViewData() {
        context?.let {
            Glide.with(it).setDefaultRequestOptions(RequestOptions().override(ivPlantInfoAreaPhoto.width,ivPlantInfoAreaPhoto.height).placeholder(R.mipmap.personalitytest_no_login).centerInside()).load(picUrl).into(ivPlantInfoAreaPhoto)
        }
        ivPlantInfoAreaInfo.text = info
        ivPlantInfoAreaMemo.text = memo
        ivPlantInfoAreaCategory.text = category
        ivPlantInfoAreaWeb.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(webUrl)))
        }
    }

    private fun setListener() {
        ivBottomSheetHeaderClose.setOnClickListener {
            bottomSheet.state = BottomSheetBehavior.STATE_HIDDEN
        }
    }

    private fun initRecycler() {
        rvPlantList.setPullRefreshEnabled(false)
        rvPlantList.layoutManager = LinearLayoutManager(context)
        rvPlantList.adapter = plantListAdapter
        rvPlantList.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onLoadMore() {
                loadMode = LoadMode.LOADMORE
                page++
                getPlantInfo()
            }

            override fun onRefresh() {
                loadMode = LoadMode.REFRESH
                page = 1
                getPlantInfo()
            }

        })
        rvBottomSheetPlantInfo.layoutManager = LinearLayoutManager(context)
        rvBottomSheetPlantInfo.adapter = plantInfoAdapter

    }

    private fun initViewModel() {
        plantInfoViewModel.apply {
            plantIndoResult.observe(viewLifecycleOwner, Observer {
                when (loadMode) {
                    LoadMode.LOADMORE -> {
                        rvPlantList.loadMoreComplete()
                    }
                    LoadMode.REFRESH -> {
                        rvPlantList.refreshComplete()
                    }
                    LoadMode.NONE -> {
                    }
                }
                when (it) {
                    is PlantInfoStatus.Success -> {
                        includePlantInfoListShimmer.visibility = View.GONE
                        rvPlantList.visibility = View.VISIBLE
                        if (page == 1)
                            plantListAdapter.data = it.data.plantInfoDataResult.results
                        else
                            plantListAdapter.data.addAll(it.data.plantInfoDataResult.results)
                        plantListAdapter.notifyDataSetChanged()
                    }
                    is PlantInfoStatus.Error -> {
                        showTost(it.message)
                    }
                }

            })
        }
    }

    override fun onItemClick(resultData: ResultData) {
        resultData.apply {
            clBottomSheetHeaderTitle.text = fNameCh
            val dataList = ArrayList<ItemBottomSheetPlant>()
            dataList.add(ItemBottomSheetPlant(viewType = 1,imgUrl = fPic01URL))
            dataList.add(ItemBottomSheetPlant(viewType = 2,title = fNameCh,text = fNameEn))
            dataList.add(ItemBottomSheetPlant(viewType = 2, title = "別名", text = fAlsoKnown))
            dataList.add(ItemBottomSheetPlant(viewType = 2, title = "簡介", text = fBrief))
            dataList.add(ItemBottomSheetPlant(viewType = 2, title = "辨識方式", text = fFeature))
            dataList.add(ItemBottomSheetPlant(viewType = 2,title = "功能性",text = fFunctionAndApplication)            )
            dataList.add(ItemBottomSheetPlant(viewType = 2, title = "最後更新$fUpdate"))
            plantInfoAdapter.data = dataList
            plantInfoAdapter.notifyDataSetChanged()
        }
        rvPlantList.smoothScrollToPosition(0)
        bottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
    }
}