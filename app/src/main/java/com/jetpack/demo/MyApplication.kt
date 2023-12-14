package com.jetpack.demo

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.hjq.toast.ToastUtils
import com.tencent.mmkv.MMKV
import me.hgj.jetpackmvvm.base.BaseApplication
import timber.log.Timber

/**
 * author : Jason
 *  date   : 2023/12/14 10:36
 *  desc   :
 */
class MyApplication: BaseApplication(), ViewModelStoreOwner {

    private lateinit var mAppViewModelStore: ViewModelStore

    private var mFactory: ViewModelProvider.Factory? = null


    companion object {
        lateinit var instance: MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        mAppViewModelStore = ViewModelStore()

        init()
    }

    private fun init() {
        MMKV.initialize(this, this.filesDir.absolutePath + "/demo")
        ToastUtils.init(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    /**
     * 获取一个全局的ViewModel
     */
    fun getAppViewModelProvider(): ViewModelProvider {
        return ViewModelProvider(this, this.getAppFactory())
    }

    private fun getAppFactory(): ViewModelProvider.Factory {
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(this)
        }
        return mFactory as ViewModelProvider.Factory
    }

    override val viewModelStore: ViewModelStore
        get() = mAppViewModelStore


}