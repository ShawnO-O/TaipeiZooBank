package com.zoo.taipeizoo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.Menu
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.zoo.taipeizoo.model.local.AreaItem
import com.zoo.taipeizoo.view.areaList.AreaListFragment
import com.zoo.taipeizoo.view.areaList.AreaListInterface
import com.zoo.taipeizoo.view.plantInfo.PlantInfoFragment

class MainActivity : AppCompatActivity(),AreaListInterface {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
//        var fragmentManager = supportFragmentManager
//        var fragmentTransitionSupport = fragmentManager.beginTransaction()
        var areaListFragment = AreaListFragment.getInstance()
        areaListFragment.setInterface(this)
        var plantListFragment = PlantInfoFragment.getInstance()

//        fragmentTransitionSupport.replace(R.id.nav_host_fragment,areaListFragment,"areaList")
//        fragmentTransitionSupport.commit()
    }



    fun initView() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
//                , R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onItemClick(areaItem: AreaItem) {

    }

}