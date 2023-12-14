package com.jetpack.demo.utils

import android.content.Context
import android.os.Environment
import android.os.Environment.MEDIA_MOUNTED
import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jetpack.demo.content.Contants
import com.jetpack.demo.content.Contants.KEY_CLINICAL_TOKEN
import com.jetpack.demo.content.Contants.KEY_CLINICAL_TOKEN_EXPIRE_TIME
import com.jetpack.demo.content.Contants.KEY_CLINICAL_TOKEN_LAST_TIME
import com.jetpack.demo.content.Contants.KEY_TOKEN
import com.jetpack.demo.network.models.UserInfo
import com.tencent.mmkv.MMKV
import java.io.File
import java.math.BigDecimal


/**
 * @author jsqi
 * @time 2020/1/19 14:16
 */
object CacheUtil {

    // 获取文件
    //Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
    //Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据

    /**
     * 获取缓存大小
     * @param context
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun getTotalCacheSize(context: Context): String {
        var cacheSize = getFolderSize(context.cacheDir)
        if (Environment.getExternalStorageState() == MEDIA_MOUNTED) {
            cacheSize += getFolderSize(context.externalCacheDir!!)
        }
        return getFormatSize(cacheSize)
    }

    /***
     * 清理所有缓存
     * @param context
     */
    fun clearAllCache(context: Context) {
        deleteDir(context.cacheDir)
        if (Environment.getExternalStorageState() == MEDIA_MOUNTED) {
            deleteDir(context.externalCacheDir)
        }
    }

    private fun deleteDir(dir: File?): Boolean {
        if (dir != null && dir!!.isDirectory) {
            val children = dir!!.list()
            for (i in children.indices) {
                val success = deleteDir(File(dir, children[i]))
                if (!success) {
                    return false
                }
            }
        }
        return dir!!.delete()
    }


    @Throws(Exception::class)
    fun getFolderSize(file: File): Long {
        var size: Long = 0
        try {
            val fileList = file.listFiles()
            for (i in fileList.indices) {
                // 如果下面还有文件
                if (fileList[i].isDirectory) {
                    size += getFolderSize(fileList[i])
                } else {
                    size += fileList[i].length()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return size
    }

    /**
     * 格式化单位
     *
     * @param size
     * @return
     */
    private fun getFormatSize(size: Long): String {
        val kiloByte = size / 1024
        if (kiloByte < 1) {
            //            return size + "Byte";
            return "0K"
        }

        val megaByte = kiloByte / 1024
        if (megaByte < 1) {
            val result1 = BigDecimal(kiloByte.toString())
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                .toPlainString() + "KB"
        }

        val gigaByte = megaByte / 1024
        if (gigaByte < 1) {
            val result2 = BigDecimal(megaByte.toString())
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                .toPlainString() + "MB"
        }

        val teraBytes = gigaByte / 1024
        if (teraBytes < 1) {
            val result3 = BigDecimal(gigaByte.toString())
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                .toPlainString() + "GB"
        }
        val result4 = BigDecimal(teraBytes)
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB"
    }

    /**
     * 获取保存的账户信息
     */
    fun getUserInfo(): UserInfo? {
        val kv = MMKV.mmkvWithID("app")
        val userStr = kv.decodeString("user")
        return if (TextUtils.isEmpty(userStr)) {
            null
        } else {
            Gson().fromJson(userStr, UserInfo::class.java)
        }
    }

    /**
     * 设置账户信息
     */
    fun setUserInfo(userResponse: UserInfo?) {
        val kv = MMKV.mmkvWithID("app")
        if (userResponse == null) {
            kv.encode("user", "")
            setIsVip(false)
            setIsLogin(false)
        } else {
            kv.encode("user", Gson().toJson(userResponse))
            setIsVip(userResponse.is_vip!!)
            setIsLogin(true)
        }

    }

    /**
     * 更新账户信息
     */
    fun updateUserInfo(flag: Boolean) {
        val userInfo = getUserInfo()
        if (flag) {
            userInfo!!.following = userInfo.following + 1
        } else {
            if (userInfo!!.following > 0) {
                userInfo.following = userInfo.following - 1
            }
        }
        setUserInfo(userInfo)
    }

    /**
     * 是否开通会员
     */
    fun isVip(): Boolean {
        val kv = MMKV.mmkvWithID("app")
        return kv.decodeBool("vip", false)
    }

    /**
     * 设置是否开通会员
     */
    fun setIsVip(isVip: Boolean) {
        val kv = MMKV.mmkvWithID("app")
        kv.encode("vip", isVip)
    }

    /**
     * 是否同意协议
     */
    fun getAgreeRule(): Boolean {
        val kv = MMKV.mmkvWithID("app")
        return kv.decodeBool(Contants.KEY_AGREE_RULE, false)
    }

    /**
     * 设置是否同意协议
     */
    fun setAgreeRule(flag: Boolean) {
        val kv = MMKV.mmkvWithID("app")
        kv.encode(Contants.KEY_AGREE_RULE, flag)
    }

    /**
     * 是否已经登录
     */
    fun isLogin(): Boolean {
        val kv = MMKV.mmkvWithID("app")
        return kv.decodeBool("login", false)
    }

    /**
     * 设置是否已经登录
     */
    fun setIsLogin(isLogin: Boolean) {
        val kv = MMKV.mmkvWithID("app")
        kv.encode("login", isLogin)
    }

    /**
     * 设置token
     */
    fun setToken(token: String) {
        val kv = MMKV.mmkvWithID("app")
        kv.encode(KEY_TOKEN, token)
    }

    /**
     * 获取token
     */
    fun getToken(): String {
        val kv = MMKV.mmkvWithID("app")
        return kv.decodeString(KEY_TOKEN, "")!!
    }

    /**
     * 是否开通会员
     */
    fun isRuleFirst(): Boolean {
        val kv = MMKV.mmkvWithID("app")
        return kv.decodeBool("rule", false)
    }

    /**
     * 设置是否开通会员
     */
    fun setIsRuleFirst(isVip: Boolean) {
        val kv = MMKV.mmkvWithID("app")
        kv.encode("rule", isVip)
    }

    /**
     * 是否是第一次登陆
     */
    fun isFirst(): Boolean {
        val kv = MMKV.mmkvWithID("app")
        return kv.decodeBool("first", true)
    }

    /**
     * 是否是第一次登陆
     */
    fun setFirst(first: Boolean): Boolean {
        val kv = MMKV.mmkvWithID("app")
        return kv.encode("first", first)
    }

    /**
     * 是否已经登录
     */
    fun isClosePush(): Boolean {
        val kv = MMKV.mmkvWithID("app")
        return kv.decodeBool("push", false)
    }

    /**
     * 设置是否已经登录
     */
    fun setIsClosePush(isLogin: Boolean) {
        val kv = MMKV.mmkvWithID("app")
        kv.encode("push", isLogin)
    }

    /**
     * 设置地下室默认歌曲
     *//*
    fun getBasement(): BasementStoreItem? {
        val kv = MMKV.mmkvWithID("app")
        val userStr = kv.decodeString("basementStore")
        return if (TextUtils.isEmpty(userStr)) {
            null
        } else {
            Gson().fromJson(userStr, BasementStoreItem::class.java)
        }
    }

    *//**
     * 设置账户信息
     *//*
    fun setBasement(basementStoreItem: BasementStoreItem?) {
        val kv = MMKV.mmkvWithID("app")
        if (basementStoreItem == null) {
            kv.encode("basementStore", "")
            setIsBasement(false)
        } else {
            kv.encode("basementStore", Gson().toJson(basementStoreItem))
            setIsBasement(true)
        }

    }*/

    /**
     * 设置是否已经记录地下是默认歌曲
     */
    fun setIsBasement(isLogin: Boolean) {
        val kv = MMKV.mmkvWithID("app")
        kv.encode("basement", isLogin)
    }

    /**
     * 设置是否已经记录地下是默认歌曲
     */
    fun getIsBasement(): Boolean {
        val kv = MMKV.mmkvWithID("app")
        return kv.decodeBool("basement", false)
    }

    /**
     * 是否已经登录
     */
    fun isSinaSuccess(): Boolean {
        val kv = MMKV.mmkvWithID("app")
        return kv.decodeBool("sina", false)
    }

    /**
     * 设置是否已经登录
     */
    fun setIsSinaSuccess(isSuccess: Boolean) {
        val kv = MMKV.mmkvWithID("app")
        kv.encode("sina", isSuccess)
    }


    /**
     * 获取搜索历史缓存数据
     */
    fun getSearchHistoryData(): ArrayList<String> {
        val kv = MMKV.mmkvWithID("cache")
        val searchCacheStr = kv.decodeString("history")
        if (!TextUtils.isEmpty(searchCacheStr)) {
            return Gson().fromJson(searchCacheStr, object : TypeToken<ArrayList<String>>() {}.type)
        }
        return arrayListOf()
    }

    fun setSearchHistoryData(searchResponseStr: String) {
        val kv = MMKV.mmkvWithID("cache")
        kv.encode("history", searchResponseStr)
    }

    /**
     * 是否已经登录
     */
    fun werchatType(): Int {
        val kv = MMKV.mmkvWithID("app")
        return kv.decodeInt("wType", -1)
    }

    /**
     * 设置是否已经登录
     */
    fun setWerchatType(type: Int) {
        val kv = MMKV.mmkvWithID("app")
        kv.encode("wType", type)
    }

    /**
     * 是否打开推送
     */
    fun isPush(): Boolean {
        val kv = MMKV.mmkvWithID("app")
        return kv.decodeBool("push", true)
    }

    /**
     * 是设置是否打开
     */
    fun setPush(first: Boolean): Boolean {
        val kv = MMKV.mmkvWithID("app")
        return kv.encode("push", first)
    }

    /**
     * 设置精要token
     */
    fun setClinicalToken(token: String) {
        val kv = MMKV.mmkvWithID("app")
        kv.encode(KEY_CLINICAL_TOKEN, token)
    }

    /**
     * 获取精要token
     */
    fun getClinicalToken(): String {
        val kv = MMKV.mmkvWithID("app")
        return kv.decodeString(KEY_CLINICAL_TOKEN, "")!!
    }

    /**
     * 设置精要过期时间
     */
    fun setClinicalTokenExpireTime(expireTime: Int) {
        val kv = MMKV.mmkvWithID("app")
        kv.encode(KEY_CLINICAL_TOKEN_EXPIRE_TIME, expireTime)
    }

    /**
     * 获取精要过期时间
     */
    fun getClinicalTokenExpireTime(): Int {
        val kv = MMKV.mmkvWithID("app")
        return kv.decodeInt(KEY_CLINICAL_TOKEN_EXPIRE_TIME, 0)
    }

    /**
     * 设置上次获取精要token时间
     */
    fun setClinicalTokenLastTime(lastTime: String) {
        val kv = MMKV.mmkvWithID("app")
        kv.encode(KEY_CLINICAL_TOKEN_LAST_TIME, lastTime)
    }

    /**
     * 获取上次获取精要token时间
     */
    fun getClinicalTokenLastTime(): String {
        val kv = MMKV.mmkvWithID("app")
        return kv.decodeString(KEY_CLINICAL_TOKEN_LAST_TIME, "")!!
    }

}