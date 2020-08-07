package com.zoo.taipeizoo.view.areaList

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.zoo.taipeizoo.R
import com.zoo.taipeizoo.model.local.AreaItem
import com.zoo.taipeizoo.utils.LoadMode
import com.zoo.taipeizoo.view.BaseFragment
import com.zoo.taipeizoo.view.areaList.adapter.AreaListAdapter
import com.zoo.taipeizoo.viewModel.ViewModelFactory
import com.zoo.taipeizoo.viewModel.areaList.AreaListViewModel
import kotlinx.android.synthetic.main.fragment_area_list.*

class AreaListFragment : BaseFragment(), AreaListInterface {
//    enum class LoadMode {
//        NONE, LOADMORE, REFRESH
//    }

    private var loadMode = LoadMode.NONE

    companion object {
        fun getInstance() = InstanceHolder.instance
    }

    private object InstanceHolder {
        val instance = AreaListFragment()
    }

    lateinit var areaListInterface: AreaListInterface
    fun setInterface(areaListInterface: AreaListInterface) {
        this.areaListInterface = areaListInterface
    }

    private val arealistViewModel: AreaListViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory.getInstance()).get(AreaListViewModel::class.java)
    }

    private val areaListAdapter: AreaListAdapter by lazy {
        AreaListAdapter(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_area_list, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        initView()
    }

    private fun initView() {
        initRecyclerView()
        getAreaList()
    }

    private fun getAreaList() {
        arealistViewModel.getAreaList()
    }

    private fun initRecyclerView() {
        rvAreaList.layoutManager = LinearLayoutManager(context)
        rvAreaList.adapter = areaListAdapter
        rvAreaList.setLoadingMoreEnabled(false)
        rvAreaList.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onLoadMore() {
                loadMode = LoadMode.LOADMORE
            }

            override fun onRefresh() {
                loadMode = LoadMode.REFRESH
                getAreaList()
            }
        })
    }

    private fun initViewModel() {
        arealistViewModel.apply {
            areaListResult.observe(viewLifecycleOwner, Observer {
                //只是為了看到效果
                Handler().postDelayed({
                    includeAreaListShimmer.visibility = View.GONE
                    rvAreaList.visibility = View.VISIBLE
                }, 1000)

                when (loadMode) {
                    LoadMode.LOADMORE -> {
                        rvAreaList.loadMoreComplete()
                    }
                    LoadMode.REFRESH -> {
                        //為了看起來有load的效果
                        Handler().postDelayed({ rvAreaList.refreshComplete() }, 500)
                    }
                }
                areaListAdapter.data = it
                areaListAdapter.notifyDataSetChanged()
            })
        }
    }


    override fun onItemClick(areaItem: AreaItem) {
        val bundle = Bundle()
        bundle.putString("title", areaItem.name)
        bundle.putString("picUrl", areaItem.picUrl)
        bundle.putString("info", areaItem.info)
        bundle.putString("category", areaItem.category)
        bundle.putString("memo", areaItem.memo)
        bundle.putString("webUrl", areaItem.detailUrl)
        findNavController(this).navigate(R.id.action_nav_home_to_nav_galler, bundle)
    }
}