package com.jetpack.demo.network.models

import android.annotation.SuppressLint
import java.io.Serializable

@SuppressLint("ParcelCreator")
class UserInfo: Serializable{
    val auto_renewal: String = ""
    val avatar: String = ""
    val license_image: ArrayList<String>? = null
    val balance_android: Double = 0.0
    val balance_ios: Double = 0.0
    val birthday: String = ""
    val real_name: String = ""
    val created_at: String = ""
    val deleted_at: String = ""
    val email: String = ""
    val title: String = ""
    val department: String = ""
    val expires_in: Int = 0
    val gender: String = ""
    val id: Int = 0
    val is_active: Boolean = false
    var is_vip: Boolean = false
    val last_login_at: String = ""
    val login_times: Int = 0
    val mobile: String = ""
    val name: String = ""
    val address: String = ""
    val instructor: String = ""
    val introduction: String = ""
    val hospital: String = ""
    val pull_down: String = ""
    val pull_up: String = ""
    var token: String = ""
    val type: String = ""
    val updated_at: String = ""
    val user_id: Int = 0
    val vip_expire: Long = 0
    val fans: Int = 0
    var following: Int = 0
    val star: Int = 0
    val favourite: Int = 0
    var certified: String = ""
    var certification_status: Int? = null   //1 待审核     2 未通过    3 已通过
    var unread_reply: Int = 0
    var unread_notification: Int = 0
    var unread_total: Int = 0
    val department_id: Int = 0
    val title_id: Int = 0
    val union_list: UnionInfo = UnionInfo()
    val star_sent: Int = 0
    val favourite_by: Int = 0





    class UnionInfo : Serializable {
        val wechat: Boolean = false
        val qq: Boolean = false
        val ios: Boolean = false
    }
}
