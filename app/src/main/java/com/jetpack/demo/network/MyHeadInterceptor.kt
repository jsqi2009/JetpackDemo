package com.jetpack.demo.network

import android.text.TextUtils
import com.jetpack.demo.utils.CacheUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * author : Jason
 *  date   : 2023/12/14 13:23
 *  desc   :
 */
class MyHeadInterceptor : Interceptor{

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        if (!TextUtils.isEmpty(CacheUtil.getToken())) {
            builder.addHeader("Authorization", "Bearer " + CacheUtil.getToken())
        }
        builder.addHeader("sh-platform", "android")
        builder.addHeader("sh-deviceid", "1234567890")
        return chain.proceed(builder.build())
    }
}