package com.jetpack.demo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.jetpack.demo.R
import com.jetpack.demo.base.BaseActivity
import com.jetpack.demo.databinding.ActivityLoginBinding
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

class LoginActivity : BaseActivity<BaseViewModel, ActivityLoginBinding>(), OnClickListener {

    override fun initView(savedInstanceState: Bundle?) {
        initStatusBar(this)
        initData()
    }

    override fun createObserver() {
        super.createObserver()
    }

    private fun initData() {

        mView.tvLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvLogin -> {

            }
            else -> {}
        }
    }
}