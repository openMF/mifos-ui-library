package org.mifos.mobile.ui

import android.app.UiModeManager
import android.content.Context
import android.os.Build.VERSION
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<MaterialToolbar>(R.id.topAppBar).setOnMenuItemClickListener {
            if(it.itemId == R.id.editTheme){
                switchNightMode()
            }
            true
        }
    }

    private fun switchNightMode() {
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

}