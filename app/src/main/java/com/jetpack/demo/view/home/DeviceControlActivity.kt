package com.jetpack.demo.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.jetpack.demo.R
import com.jetpack.demo.base.BaseActivity
import com.jetpack.demo.databinding.ActivityDeviceControlBinding
import com.jetpack.demo.databinding.ActivityRootBinding
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

class DeviceControlActivity : BaseActivity<BaseViewModel, ActivityDeviceControlBinding>(), OnClickListener  {


    override fun initView(savedInstanceState: Bundle?) {
        initStatusBar(this)

        init()
    }


    private fun init() {
        views.viewScore.updateValues(99, "")

        views.tvUpdate.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvUpdate -> {
                views.viewScore.updateValues(46, "")
            }
            else -> {}
        }
    }
}