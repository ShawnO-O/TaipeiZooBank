package com.zoo.taipeizoo.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.zoo.taipeizoo.MainActivity
import com.zoo.taipeizoo.R

class LeadingPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_leading_page)
        Handler().postDelayed({
            Intent().apply {
                setClass(this@LeadingPage, MainActivity::class.java)
                startActivity(this)
                finish()
            }
        }, 500)
    }

}