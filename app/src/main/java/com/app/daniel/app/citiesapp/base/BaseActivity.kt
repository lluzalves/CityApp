package com.app.daniel.app.citiesapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.app.daniel.app.citiesapp.R
import com.fondesa.kpermissions.request.PermissionRequest.*
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseActivity : AppCompatActivity(), AcceptedListener, DeniedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    public override fun onResume() {
        super.onResume()
    }

    public override fun onPause() {
        super.onPause()
    }

    public override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBackPressed() = when (NavHostFragment.findNavController(nav_host_fragment).currentDestination?.id) {
        R.id.home -> {
            finish()
        }
        else -> {
            super.onBackPressed()
        }
    }

    override fun onPermissionsAccepted(permissions: Array<out String>) {

    }

    override fun onPermissionsDenied(permissions: Array<out String>) {

    }


}