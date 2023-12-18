package com.jetpack.demo

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.hjq.toast.ToastUtils
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
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

        initGlobalSmartRefresh()
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

    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore
    }

    private fun initGlobalSmartRefresh() {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setPrimaryColorsId(R.color.white, R.color.text_color_3) //全局设置主题颜色
            layout.setReboundDuration(100)
            layout.setHeaderMaxDragRate(4.0f)
            layout.setHeaderHeight(90f)
            layout.setEnableHeaderTranslationContent(true)
            layout.setDisableContentWhenLoading(false)
            layout.setEnableOverScrollDrag(true)
            layout.setEnableOverScrollBounce(true)
            ClassicsHeader(context) //指定为经典Header

        }
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
            layout.setPrimaryColorsId(R.color.white, R.color.text_color_3) //全局设置主题颜色
            layout.setReboundDuration(100)
            layout.setEnableHeaderTranslationContent(true)
            layout.setDisableContentWhenLoading(false)
            layout.setEnableOverScrollDrag(true)
            layout.setEnableOverScrollBounce(true)
            ClassicsFooter(context).setDrawableSize(2000f)  //指定为经典Footer
        }
    }




}