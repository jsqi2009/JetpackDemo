package com.jetpack.demo.network

import com.jetpack.demo.network.models.ApiResponse
import com.jetpack.demo.network.models.UserInfo
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * author : Jason
 *  date   : 2023/12/14 13:21
 *  desc   :
 */
interface ApiService {

    companion object {
        //const val BASE_SERVER_URL = "https://new.med-video.com/api/m/"
        const val BASE_SERVER_URL = "https://newtest.med-video.com/api/m/"
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



}