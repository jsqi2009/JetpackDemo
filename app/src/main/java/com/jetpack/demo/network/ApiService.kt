package com.jetpack.demo.network

import com.jetpack.demo.network.models.*
import retrofit2.http.*

/**
 * author : Jason
 *  date   : 2023/12/14 13:21
 *  desc   :
 */
interface ApiService {

    companion object {
        const val BASE_SERVER_URL = "https://new.med-video.com/api/m/"
//        const val BASE_SERVER_URL = "https://newtest.med-video.com/api/m/"
    }

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("mobile") username: String,
        @Field("captcha") pwd: String
    ): ApiResponse<UserInfo>


    /**
     * 视频列表
     */
    @GET("videos")
    suspend fun getVideoList(
        @QueryMap map: MutableMap<String, Any>
    ): ApiResponse<VideoListInfo>



}