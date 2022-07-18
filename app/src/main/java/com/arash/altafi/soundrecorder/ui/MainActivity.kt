package com.arash.altafi.soundrecorder.ui

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.arash.altafi.soundrecorder.R
import com.arash.altafi.soundrecorder.adapter.SectionPagerAdapter
import com.arash.altafi.soundrecorder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
        setupViewPager()

    }

    override fun onStart() {
        super.onStart()
        getPermission()
    }

    private fun setupViewPager() = with(binding) {
        val sectionAdapter = SectionPagerAdapter(this@MainActivity)
        viewPager.adapter = sectionAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab , position ->
            tab.text = resources.getString(TAB_TITLE[position])
        }.attach()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            if(!allPermissionGranted()) {
                Toast.makeText(this, "Need permission!!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun allPermissionGranted() = REQUIRED_PERMISSION.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun getPermission() {
        if(!allPermissionGranted()) {
            ActivityCompat
                .requestPermissions(
                    this,
                    REQUIRED_PERMISSION,
                    REQUEST_RECORD_AUDIO_PERMISSION
                )
        }
    }

    companion object {
        private val TAB_TITLE = intArrayOf(R.string.recorder_tab, R.string.saved_file_tab)
        private val REQUIRED_PERMISSION = arrayOf(Manifest.permission.RECORD_AUDIO)
        private const val REQUEST_RECORD_AUDIO_PERMISSION = 201
    }
}