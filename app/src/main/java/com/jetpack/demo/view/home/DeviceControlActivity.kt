package com.jetpack.demo.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jetpack.demo.R
import com.jetpack.demo.base.BaseActivity
import com.jetpack.demo.databinding.ActivityDeviceControlBinding
import com.jetpack.demo.databinding.ActivityRootBinding
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

class DeviceControlActivity : BaseActivity<BaseViewModel, ActivityDeviceControlBinding>()  {


    override fun initView(savedInstanceState: Bundle?) {
        initStatusBar(this)

        views.viewScore.setSesameValues(90, "Air Pressure")
    }
}