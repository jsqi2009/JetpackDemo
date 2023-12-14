package com.jetpack.demo.network.request

import android.content.Context
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.jetpack.demo.network.apiService
import com.jetpack.demo.network.models.UserInfo
import com.jetpack.demo.network.models.VideoListInfo
import com.jetpack.demo.network.stateCallback.UpdateUiState
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.request

/**
 * author : Jason
 *  date   : 2023/12/14 14:08
 *  desc   :
 */
class VideoViewModel : BaseViewModel() {

    //自动脱壳过滤处理请求结果，判断结果是否成功

    var videoListState = MutableLiveData<UpdateUiState<VideoListInfo>>()


    fun getVideoList(mContext: Context,
                     page: Int,
                     author_id: String,
                     department_id: String,
                     category_id: String,
                     keyword: String,
                     sort_by: String,
                     path: String) {

        val map: MutableMap<String, Any> = HashMap()
        map["page"] = page
        if (!TextUtils.isEmpty(author_id)) {
            map.put("author_id", author_id)
        }
        if (!TextUtils.isEmpty(department_id)) {
            map.put("department_id", department_id)
        }
        if (!TextUtils.isEmpty(category_id)) {
            map.put("category_id", category_id)
        }
        if (!TextUtils.isEmpty(keyword)) {
            map.put("keyword", keyword)
        }
        if (!TextUtils.isEmpty(sort_by)) {
            map.put("sort_by", sort_by)
        }

        request({apiService.getVideoList(map)}, {
            //成功
            val uiState = UpdateUiState(isSuccess = true, data = it)
            videoListState.value = uiState
        }, {
            //失败
            val uiState = UpdateUiState(isSuccess = false, data = VideoListInfo(), errorMsg = it.message)
            videoListState.value = uiState
        })
    }

}