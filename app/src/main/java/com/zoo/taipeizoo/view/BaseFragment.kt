package com.zoo.taipeizoo.view

import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {
    fun showTost(message: String) = Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}