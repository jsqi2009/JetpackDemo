package com.jetpack.demo.base

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import androidx.viewbinding.ViewBinding
import com.gyf.immersionbar.ImmersionBar
import com.hjq.toast.ToastUtils
import com.jetpack.demo.R
import com.kaopiz.kprogresshud.KProgressHUD
import me.hgj.jetpackmvvm.base.activity.BaseVmVbActivity
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

/**
 * author : Jason
 *  date   : 2023/12/14 11:24
 *  desc   :
 */
abstract class BaseActivity<VM : BaseViewModel, VB : ViewBinding>: BaseVmVbActivity<VM, VB>() {

    var loadingDialog: KProgressHUD? = null

    /**
     * 当前Activityc创建后调用的方法 abstract修饰供子类实现
     */
    abstract override fun initView(savedInstanceState: Bundle?)

    /**
     * 创建liveData数据观察
     */
    override fun createObserver() {}


    /**
     * 打开等待框 在这里实现你的等待框展示
     */
    override fun showLoading(message: String) {
        showLoading()
    }

    /**
     * 关闭等待框 在这里实现你的等待框关闭
     */
    override fun dismissLoading() {
        hideLoading()
    }


    fun initStatusBar(mActivity: Activity) {
        ImmersionBar.with(mActivity)
            .statusBarDarkFont(true)
            .init()
    }

    fun showLoading() {
        if (this.loadingDialog == null || !this.loadingDialog!!.isShowing) {
            loadingDialog = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f)
            loadingDialog!!.show()
        }
    }

    fun hideLoading() {
        if (this.loadingDialog != null && this.loadingDialog!!.isShowing) {
            this.loadingDialog!!.dismiss()
            this.loadingDialog = null
        }
    }

    fun showToast(message: String?) {
        if (!TextUtils.isEmpty(message)) {
            ToastUtils.setView(R.layout.view_custom_toast);
            ToastUtils.setGravity(Gravity.CENTER)
            ToastUtils.show(message)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBus.unregister(this)
    }


}

