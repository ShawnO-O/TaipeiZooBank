package com.zoo.taipeizoo

import android.app.Application
import com.zoo.taipeizoo.utils.handleSSLHandshake

class ZooApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //因為open data 的圖片網址沒有https!!
        handleSSLHandshake.handleSSLHandshake1()
    }
}