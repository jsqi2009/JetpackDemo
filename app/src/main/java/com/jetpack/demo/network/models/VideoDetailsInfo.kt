package com.jetpack.demo.network.models

import java.io.Serializable


class VideoDetailsInfo: Serializable {

    var id: Int? = null
    var store_id: Int? = null
    var author_id: Int? = null
    var title: String? = null
    var description: String? = null
    var cover: CoverInfo? = null
    var media: MediaInfo? = null
    var fee_type: String? = null
    var status: Int? = null
    var is_show: String? = null
    var play_num: Int? = null
    var collection_num: Int? = null
    var share_num: Int? = null
    var virtual_play: Int? = null
    var virtual_collection: Int? = null
    var arrangement: Int? = null
    var created_at: String? = null
    var updated_at: String? = null
    var collected: Boolean? = false
    var starred: Boolean? = false
    var comment_count: Int? = 0
    var commends_count: Int? = 0
    var playable_times: Int? = 0
    var departments: ArrayList<DepartmentInfo>? = ArrayList()
    var author: AuthorInfo? = null
    var reviews: ArrayList<ReviewInfo>? = ArrayList()

    //针对法与情
    var type: String? = null
    var doc: String? = null
    var topic_id: Int? = 0
    var study_record: VideoInfo.StudyRecordInfo? = null
    var isPlaying: Boolean = false

    class CoverInfo: Serializable {

        var id: Int? = null
        var url: String? = null
        var cover: String? = null
        var width: Int? = null
        var height: Int? = null
        var duration: Int? = null
        var direction: String? = null
        var duration_int: Int? = null
    }

    class MediaInfo: Serializable {

        var id: Int? = null
        var url: String? = null
        var type: String? = null
        var width: Int? = null
        var height: Int? = null
        var duration: Int? = null
        var direction: String? = null
        var duration_int: Int? = null
        var format: ArrayList<FormatInfo>? = ArrayList()
    }

    class FormatInfo: Serializable {

        var url: String? = null
        var name: String? = null
        var level: String? = null
    }

    class DepartmentInfo: Serializable {

        var name: String? = null
        var department_id: String? = null
    }

    class ReviewInfo: Serializable {

        var id: Int? = 0
        var name: String? = null
        var title: String? = null
        var avatar: String? = null
        var content: String? = null
        var hospital: String? = null
        var doctor_id: Int? = 0
        var specialty: String? = null
        var department: Any? = null
        var updated_at: String? = null
        var description: String? = null
        var hospital_id: Int? = 0
    }

    class StudyRecordInfo: Serializable {

        var id: Int? = null
        var store_id: Int? = null
        var user_id: Int? = null
        var device_id: String? = null
        var duration: Int? = null
        var media: VideoInfo.MediaInfo? = null
        var time_to: Int? = null
        var over: String? = null
        var created_at: String? = null
        var updated_at: String? = null
    }

    override fun toString(): String {
        return "VideoDetailsInfo(id=$id, store_id=$store_id, author_id=$author_id, title=$title, description=$description, cover=$cover, media=$media, fee_type=$fee_type, status=$status, is_show=$is_show, play_num=$play_num, collection_num=$collection_num, share_num=$share_num, virtual_play=$virtual_play, virtual_collection=$virtual_collection, arrangement=$arrangement, created_at=$created_at, updated_at=$updated_at, collected=$collected, starred=$starred, comment_count=$comment_count, playable_times=$playable_times, departments=$departments, author=$author)"
    }

}

class AuthorInfo: Serializable {
    val address: String? = null
    val avatar: String? = null
    val birthday: String? = null
    val created_at: String? = null
    val department: String? = null
    val department_id: Int = 0
    val fans: Int = 0
    val favourite: Int = 0
    val favourite_by: Int = 0
    val following: Int = 0
    val hospital: String? = null
    val instructor: String? = null
    val is_vip: Boolean = false
    val license_image: ArrayList<String>? = null
    val real_name: String? = null
    val star: Int = 0
    val title: String? = null
    val title_id: Int = 0
    val updated_at: String? = null
    val id: Int = 0
    val user_id: Int = 0
    val vip_expire: String? = null
    val introduction: String? = null
    val is_following: Boolean = false
    val certified: String? = null
    val user_type: String? = null
    val name: String? = null
}

