package com.jetpack.demo.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.jetpack.demo.MainActivity
import com.jetpack.demo.MyApplication
import com.jetpack.demo.R
import com.jetpack.demo.base.BaseActivity
import com.jetpack.demo.databinding.ActivityLoginBinding
import com.jetpack.demo.utils.CacheUtil
import com.jetpack.demo.viewmodel.request.LoginViewModel
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import kotlin.math.log

class LoginActivity : BaseActivity<BaseViewModel, ActivityLoginBinding>(), OnClickListener {

    //请求数据viewModel
    private val loginViewModel: LoginViewModel by viewModels()

    override fun initView(savedInstanceState: Bundle?) {
        initStatusBar(this)
        initData()
    }

    override fun createObserver() {
        loginViewModel.loginState.observe(this, Observer {
            if(it.isSuccess){
                CacheUtil.setUserInfo(it.data)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                //showToast(it.errorMsg)
            }
        })
    }

    private fun initData() {

        mView.tvLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvLogin -> {
                loginViewModel.login(mView.etAccount.text.toString(), "112233")
            }
            else -> {}
        }
    }
}