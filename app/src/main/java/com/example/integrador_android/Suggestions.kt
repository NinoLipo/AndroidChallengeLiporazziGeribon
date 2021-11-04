package com.example.integrador_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.integrador_android.databinding.ActivitySuggestionsBinding

class Suggestions : AppCompatActivity() {
    private lateinit var binding: ActivitySuggestionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuggestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val participantsNumber = intent.getIntExtra(getString(R.string.participantsNumber),1)

        val activityCategory = intent.getStringExtra(getString(R.string.hardCodedCategory))

        Log.println(Log.WARN,"INTENT1", participantsNumber.toString())
        Log.println(Log.WARN,"INTENT1", activityCategory.toString())

        binding.textView2.text = participantsNumber.toString()
        binding.textView6.text = activityCategory
    }
}