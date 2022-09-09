package com.example.truecaller.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.truecaller.R
import com.example.truecaller.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupUI()
    }

    private fun setupUI() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, MainFragment())
            .commit()
    }
}