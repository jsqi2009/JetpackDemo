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
            builder.addHeader("Authorization", "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL25ld3Rlc3QubWVkLXZpZGVvLmNvbS9hcGkvbS9hdXRoL2xvZ2luIiwiaWF0IjoxNzAyNTQzOTMyLCJleHAiOjE3MDYxNDM5MzIsIm5iZiI6MTcwMjU0MzkzMiwianRpIjoiZEd3TlNnNU8yODBjUWlOYSIsInN1YiI6NCwicHJ2IjoiYmI2NWQ5YjhmYmYwZGE5ODI3YzhlZDIzMWQ5YzU0YzgxN2YwZmJiMiJ9.IK8pcufmWRBh6lZB57_D3WbLY24c3DBT2rzOXkaxZTc").build()
        }
        builder.addHeader("sh-platform", "android").build()
        builder.addHeader("sh-deviceid", "b7be4b1c4c7237b18b16811f7370475f29b0e5cd0c8f9a51474c7cf8792c813f").build()

        return chain.proceed(builder.build())
    }
}