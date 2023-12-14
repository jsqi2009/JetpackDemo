package me.hgj.jetpackmvvm.base

import android.app.Application
import android.content.Context
import me.hgj.jetpackmvvm.util.AndroidBus

/**
 * author : Jason
 *  date   : 2023/12/14 11:14
 *  desc   :
 */
open class BaseApplication : Application() {

    lateinit var mBus: AndroidBus

    override fun onCreate() {
        super.onCreate()

        mBus = AndroidBus()
        mBus.register(this)
    }

    companion object{

        operator fun get(context: Context): BaseApplication {
            return context.applicationContext as BaseApplication
        }

    }

}