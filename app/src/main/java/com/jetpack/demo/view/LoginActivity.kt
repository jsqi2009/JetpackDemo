package com.jetpack.demo.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.jetpack.demo.MainActivity
import com.jetpack.demo.R
import com.jetpack.demo.RootActivity
import com.jetpack.demo.base.BaseActivity
import com.jetpack.demo.databinding.ActivityLoginBinding
import com.jetpack.demo.utils.CacheUtil
import com.jetpack.demo.network.request.LoginViewModel
import com.jetpack.demo.view.home.DeviceControlActivity
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

class LoginActivity : BaseActivity<BaseViewModel, ActivityLoginBinding>(), OnClickListener {

    //请求数据viewModel
    private val loginViewModel: LoginViewModel by viewModels()

    override fun initView(savedInstanceState: Bundle?) {
        //强烈注意：使用addLoadingObserve 将非绑定当前activity的viewmodel绑定loading回调 防止出现请求时不显示 loading 弹窗bug
        addLoadingObserve(loginViewModel)
        initStatusBar(this)
        initData()
    }

    private fun login() {
        showLoading()
        loginViewModel.login(views.etAccount.text.toString(), "112233")
    }

    override fun createObserver() {
        loginViewModel.loginState.observe(this, Observer {
            hideLoading()
            if(it.isSuccess){
                CacheUtil.setUserInfo(it.data)
                CacheUtil.setToken(it.data!!.token)

                //val intent = Intent(this, RootActivity::class.java)
                val intent = Intent(this, DeviceControlActivity::class.java)
                startActivity(intent)
            } else{
                showToast(it.errorMsg)
            }
        })
    }

    private fun initData() {

        views.etAccount.setText("18697337630")

        views.tvLogin.setOnClickListener(this)
    }



    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvLogin -> {
                if (CacheUtil.isLogin()) {
                    //val intent = Intent(this, RootActivity::class.java)
                    val intent = Intent(this, DeviceControlActivity::class.java)
                    startActivity(intent)
                } else {
                    login()
                }

            }
            else -> {}
        }
    }
}