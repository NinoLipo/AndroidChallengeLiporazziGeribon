package com.example.integrador_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.integrador_android.databinding.ActivityTermsBinding

class Terms : AppCompatActivity() {

    private lateinit var binding: ActivityTermsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTermsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btQuit.setOnClickListener {
            finish()
        }
    }
}