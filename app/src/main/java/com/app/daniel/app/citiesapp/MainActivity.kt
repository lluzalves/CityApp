package com.app.daniel.app.citiesapp

import android.os.Bundle
import com.app.daniel.app.citiesapp.base.BaseActivity
import com.app.daniel.app.citiesapp.dependency.ApplicationDependency

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        ApplicationDependency.SHARED.inject(this)
    }
}
