package com.jetpack.demo.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jetpack.demo.R
import com.jetpack.demo.content.Contants
import com.jetpack.demo.network.models.VideoInfo
import com.jetpack.demo.utils.TimeUtils

/**
 * index区分哪个页面使用
 *  1: 专家详情页面   2：搜索结果页面   3：浏览记录   4：我的收藏    1：首页推荐
 */
class VideoListAdapter(val index: Int, data: ArrayList<VideoInfo>) :
    BaseQuickAdapter<VideoInfo, BaseViewHolder>(
        R.layout.item_knowledge_result_list, data
    ) {

    var searchTerms : String? = ""

    override fun convert(holder: BaseViewHolder, item: VideoInfo) {

        item.apply {
            if (media!!.type == Contants.Media_Video) {
                holder.getView<View>(R.id.knowledgeVerSmall).visibility = View.VISIBLE

                if (index == 3 || index == 4) {
                    holder.getView<View>(R.id.knowledgeVerSmall).findViewById<ImageView>(R.id.ivSmallMoreAction).visibility = View.VISIBLE
                } else {
                    holder.getView<View>(R.id.knowledgeVerSmall).findViewById<ImageView>(R.id.ivSmallMoreAction).visibility = View.GONE
                }

                if (item.fee_type == "vip") {
                    holder.getView<View>(R.id.knowledgeVerSmall).findViewById<TextView>(R.id.tvTag).text = "VIP"
                } else {
                    holder.getView<View>(R.id.knowledgeVerSmall).findViewById<TextView>(R.id.tvTag).text = "限免"
                }
                holder.getView<View>(R.id.knowledgeVerSmall).findViewById<TextView>(R.id.tvDuration).text = TimeUtils.second2Time(item.media!!.duration!!)
                holder.getView<View>(R.id.knowledgeVerSmall).findViewById<TextView>(R.id.tvReadCount).text = (item.play_num!! + item.virtual_play!!).toString()
                if (item.cover != null && !item.cover!!.cover.isNullOrEmpty()) {
                    Glide.with(context)
                        .load(item.cover!!.cover!!)
                        .into(holder.getView<View>(R.id.knowledgeVerSmall).findViewById<ImageView>(R.id.ivImage))
                }

                holder.getView<View>(R.id.knowledgeVerSmall).findViewById<TextView>(R.id.tvTitle).text = item.title

            }
        }



    }


}