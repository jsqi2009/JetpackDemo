package com.jetpack.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jetpack.demo.adapter.VideoListAdapter
import com.jetpack.demo.base.BaseActivity
import com.jetpack.demo.databinding.ActivityLoginBinding
import com.jetpack.demo.databinding.ActivityRootBinding
import com.jetpack.demo.network.models.VideoListInfo
import com.jetpack.demo.network.request.LoginViewModel
import com.jetpack.demo.network.request.VideoViewModel
import com.jetpack.demo.utils.CacheUtil
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

class RootActivity : BaseActivity<BaseViewModel, ActivityRootBinding>() {
    //请求数据viewModel
    private val videoViewModel: VideoViewModel by viewModels()

    private lateinit var mAdapter: VideoListAdapter
    private var page = 1
    private var videoListInfo: VideoListInfo? = null
    private var totalCount = 0

    override fun initView(savedInstanceState: Bundle?) {
        //强烈注意：使用addLoadingObserve 将非绑定当前activity的viewmodel绑定loading回调 防止出现请求时不显示 loading 弹窗bug
        addLoadingObserve(videoViewModel)
        initStatusBar(this)

        initRecycler()
        initRefresh()
        initData()
    }

    override fun onResume() {
        super.onResume()
        getVideoList()
    }

    private fun getVideoList() {
        showLoading()
        videoViewModel.getVideoList(this, page, "", "", "15", "", "id", "video")
    }

    override fun createObserver() {

        val map: MutableMap<String, Any> = HashMap()

        videoViewModel.videoListState.observe(this, Observer {
            hideLoading()
            if (it.isSuccess){
                totalCount = it.data?.total!!
                videoListInfo = it.data
                refreshData()

            } else{
                showToast(it.errorMsg)
            }
        })
    }

    private fun initData() {

    }

    private fun refreshData() {

        if (page == 1) {
            mAdapter.data.clear()
        }
        videoListInfo?.data?.let {
            mAdapter.data.addAll(it)
        }
        mAdapter.notifyDataSetChanged()

        if (mAdapter.data.size > 0) {
            views.recycler.visibility = View.VISIBLE
        } else {
            views.recycler.visibility = View.GONE
        }
    }


    private fun initRecycler() {

        mAdapter = VideoListAdapter(3, arrayListOf())
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        views.recycler.layoutManager = layoutManager
        views.recycler.adapter = mAdapter
        views.recycler.setHasFixedSize(true)
        mAdapter.setOnItemClickListener { adapter, view, position ->

        }
    }

    private fun initRefresh() {

        views.refreshLayout.setRefreshFooter(ClassicsFooter(this))
        views.refreshLayout.setRefreshHeader(ClassicsHeader(this))
        views.refreshLayout.setOnLoadMoreListener {
            views.refreshLayout.finishLoadMore(2000)
            //call API


        }
        views.refreshLayout.setOnRefreshListener {
            views.refreshLayout.finishRefresh(2000)
            //call API

        }
    }




}