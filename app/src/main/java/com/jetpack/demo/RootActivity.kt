package com.jetpack.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.jetpack.demo.base.BaseActivity
import com.jetpack.demo.databinding.ActivityLoginBinding
import com.jetpack.demo.databinding.ActivityRootBinding
import com.jetpack.demo.network.models.VideoListInfo
import com.jetpack.demo.network.request.LoginViewModel
import com.jetpack.demo.network.request.VideoViewModel
import com.jetpack.demo.utils.CacheUtil
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

class RootActivity : BaseActivity<BaseViewModel, ActivityRootBinding>() {
    //请求数据viewModel
    private val videoViewModel: VideoViewModel by viewModels()

    private var page = 1
    private var videoListInfo: VideoListInfo? = null
    private var totalCount = 0

    override fun initView(savedInstanceState: Bundle?) {
        //强烈注意：使用addLoadingObserve 将非绑定当前activity的viewmodel绑定loading回调 防止出现请求时不显示 loading 弹窗bug
        addLoadingObserve(videoViewModel)
        initStatusBar(this)
        initData()
    }

    override fun onResume() {
        super.onResume()
        getVideoList()
    }

    private fun getVideoList() {
        showLoading()
        videoViewModel.getVideoList(this, page, "", "", "2", "", "id", "video")
    }

    override fun createObserver() {

        val map: MutableMap<String, Any> = HashMap()

        videoViewModel.videoListState.observe(this, Observer {
            hideLoading()
            if (it.isSuccess){
                totalCount = it.data?.total!!
                videoListInfo = it.data

            } else{
                showToast(it.errorMsg)
            }
        })
    }

    private fun initData() {

    }




}