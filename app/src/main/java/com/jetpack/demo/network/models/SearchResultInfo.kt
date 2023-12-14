package com.jetpack.demo.network.models

import java.io.Serializable


class SearchResultInfo: Serializable {

    var videos: ArrayList<VideoInfo>? = ArrayList()
    var docs: ArrayList<DocsInfo>? = ArrayList()
//    var courses: ArrayList<CourseDetailInfo>? = ArrayList()
//    var lives: ArrayList<LiveInfo>? = ArrayList()
//    var articles: ArrayList<ArticleItemInfo>? = ArrayList()

    var videos_show_more: Boolean = false
    var docs_show_more: Boolean = false
    var courses_show_more: Boolean = false
    var articles_show_more: Boolean = false



}

class VideoInfo: Serializable {

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
    var play_num: Int? = 0
    var collection_num: Int? = null
    var share_num: Int? = null
    var virtual_play: Int? = 0
    var virtual_collection: Int? = null
    var arrangement: Int? = null
    var created_at: String? = null
    var updated_at: String? = null
    var departments: ArrayList<DepartmentInfo>? = ArrayList()
    var day: String? = null

    //针对法与情
    var type: String? = null
    var doc: String? = null
    var topic_id: Int? = 0
    var study_record: StudyRecordInfo? = null
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
        var duration: Long? = null
        var direction: String? = null
        var duration_int: Int? = null
        var format: ArrayList<FormatInfo>? = ArrayList()
        var docs: ArrayList<String> = ArrayList()
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

    class StudyRecordInfo: Serializable {

        var id: Int? = null
        var store_id: Int? = null
        var user_id: Int? = null
        var device_id: String? = null
        var duration: Int? = null
        var media: MediaInfo? = null
        var time_to: Int? = null
        var over: String? = null
        var created_at: String? = null
        var updated_at: String? = null
    }

    override fun toString(): String {
        return "VideoInfo(id=$id, store_id=$store_id, author_id=$author_id, title=$title, description=$description, cover=$cover, media=$media, fee_type=$fee_type, status=$status, is_show=$is_show, play_num=$play_num, collection_num=$collection_num, share_num=$share_num, virtual_play=$virtual_play, virtual_collection=$virtual_collection, arrangement=$arrangement, created_at=$created_at, updated_at=$updated_at)"
    }

}

class DocsInfo: Serializable {

    var serialNumber: String? = null
    var titleCn: String? = null
    var titleEn: String? = null
    var definiens: String? = null
    var fileSize: String? = null
    var fileSource: String? = null
    var clinicalDepartment: String? = null
    var keyword: String? = null
    var publishTime: String? = null
    var contextIntroduction: String? = null
    var fileSourceName: String? = null
    var fileType: Int? = 0
    var adaptiveVersion: String? = null
    var fileExtension: String? = null
    var copyrightRestrictions: String? = null    //1 不可预览    2 可预览
    var population: String? = null
    val collected: Boolean = false
    val remain_times: Int = 0
    override fun toString(): String {
        return "DocsInfo(serialNumber=$serialNumber, titleCn=$titleCn, titleEn=$titleEn, definiens=$definiens, fileSize=$fileSize, fileSource=$fileSource, clinicalDepartment=$clinicalDepartment, keyword=$keyword, publishTime=$publishTime, contextIntroduction=$contextIntroduction, fileSourceName=$fileSourceName, fileType=$fileType, adaptiveVersion=$adaptiveVersion, fileExtension=$fileExtension, copyrightRestrictions=$copyrightRestrictions, population=$population, collected=$collected, remain_times=$remain_times)"
    }


}