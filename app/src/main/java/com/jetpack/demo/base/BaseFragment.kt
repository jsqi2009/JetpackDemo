package com.jetpack.demo.base

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import me.hgj.jetpackmvvm.base.activity.BaseVmVbActivity
import me.hgj.jetpackmvvm.base.fragment.BaseVmVbFragment
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

/**
 * author : Jason
 *  date   : 2023/12/14 12:51
 *  desc   :
 */
abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding> : BaseVmVbFragment<VM, VB>(){

    abstract override fun initView(savedInstanceState: Bundle?)


    override fun lazyLoadData() {

    }

    /**
     * 创建liveData数据观察 懒加载之后才会触发
     */
    override fun createObserver() {}


    /**
     * 打开等待框 在这里实现你的等待框展示
     */
    override fun showLoading(message: String) {
    }

    /**
     * 关闭等待框 在这里实现你的等待框关闭
     */
    override fun dismissLoading() {

    }

    override fun onDestroy() {
        super.onDestroy()
        mBus.unregister(this)
    }
}