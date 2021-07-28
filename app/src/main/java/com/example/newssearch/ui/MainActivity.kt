package com.example.newssearch.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newssearch.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(
            R.id.fragmentPlaceHolder,
            NewsFragment.newInstance(),
            "NewsFragment"
        ).commit()
    }
}