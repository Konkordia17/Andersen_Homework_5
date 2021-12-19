package com.example.andersen_homework_5

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


class MainActivity : AppCompatActivity(), Navigate {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        if (supportFragmentManager.findFragmentById(R.id.container) == null && (width < 2500)) {
            navigateTo(ListFragment(), false)
        }
    }

    override fun navigateTo(
        fragment: Fragment,
        addToBackStackBoolean: Boolean
    ) {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        val isBigScreen = width > 2500
        if (isBigScreen) {
            navigateWithBigScreen(fragment)
        } else {
            navigateWithSmallScreen(fragment, addToBackStackBoolean)
        }
    }

    private fun navigateWithBigScreen(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.detailFragmentContainer, fragment)
            .commit()
    }

    private fun navigateWithSmallScreen(fragment: Fragment, addToBackStackBoolean: Boolean) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .apply {
                if (addToBackStackBoolean) addToBackStack(null)
            }
            .commit()
    }
}

interface Navigate {
    fun navigateTo(
        fragment: Fragment,
        addToBackStackBoolean: Boolean = true
    )
}