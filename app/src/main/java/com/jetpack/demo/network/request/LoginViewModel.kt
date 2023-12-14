package com.jetpack.demo.network.request

import androidx.lifecycle.MutableLiveData
import com.jetpack.demo.network.apiService
import com.jetpack.demo.network.models.UserInfo
import com.jetpack.demo.network.stateCallback.UpdateUiState
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.request

/**
 * author : Jason
 *  date   : 2023/12/14 14:08
 *  desc   :
 */
class LoginViewModel : BaseViewModel() {

    //自动脱壳过滤处理请求结果，判断结果是否成功

    var loginState = MutableLiveData<UpdateUiState<UserInfo>>()


    fun login(username: String, password: String) {
        request({apiService.login(username, password)}, {
            //成功
            val uiState = UpdateUiState(isSuccess = true, data = it)
            loginState.value = uiState
        }, {
            //失败
            val uiState = UpdateUiState(isSuccess = false, data = UserInfo(), errorMsg = it.message)
            loginState.value = uiState
        })
    }

}