package com.jetpack.demo.network.models

import java.io.Serializable


class VideoListInfo: Serializable {

    var current_page: Int? = null
    var data: ArrayList<VideoInfo>? = ArrayList()
    var first_page_url: String? = null
    var from: String? = null
    var last_page: Int? = 0
    var last_page_url: String? = null
    var next_page_url: String? = null
    var path: String? = null
    var per_page: Int? = 0
    var prev_page_url: String? = null
    var to: Int? = 0
    var total: Int? = 0
    override fun toString(): String {
        return "VideoListInfo(current_page=$current_page, data=$data, first_page_url=$first_page_url, from=$from, last_page=$last_page, last_page_url=$last_page_url, next_page_url=$next_page_url, path=$path, per_page=$per_page, prev_page_url=$prev_page_url, to=$to, total=$total)"
    }

}