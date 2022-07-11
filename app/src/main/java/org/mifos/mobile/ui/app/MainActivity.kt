package org.mifos.mobile.ui.app

import android.app.UiModeManager
import android.content.Context
import android.os.Build.VERSION
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import org.mifos.mobile.ui.app.R
import org.mifos.mobile.ui.app.databinding.ActivityMainBinding
import org.mifos.mobile.ui.getThemeAttributeColor
import org.mifos.mobile.ui.setStatusBarColor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.bottomNav.setupWithNavController(findNavController(R.id.navHostFragment))

        setStatusBarColor(getThemeAttributeColor(com.google.android.material.R.attr.colorSurface))
    }

    override fun onSupportNavigateUp() =
        findNavController(R.id.navHostFragment).navigateUp() || super.onSupportNavigateUp()

    private fun findNavController(navHostId: Int): NavController {
        val navHostFragment = supportFragmentManager.findFragmentById(navHostId) as NavHostFragment
        return navHostFragment.navController
    }
}


fun Context.switchNightMode() {
    val uiManager = getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
    if (VERSION.SDK_INT <= 22) {
        uiManager.enableCarMode(0)
    }
    if (uiManager.nightMode == UiModeManager.MODE_NIGHT_NO) {
        uiManager.nightMode = UiModeManager.MODE_NIGHT_YES
    } else {
        uiManager.nightMode = UiModeManager.MODE_NIGHT_NO
    }
}