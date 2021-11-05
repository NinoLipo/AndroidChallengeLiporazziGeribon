package com.example.integrador_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.core.widget.addTextChangedListener
import com.example.integrador_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TextWatcher {
    private lateinit var binding: ActivityMainBinding
    private var participantsNumber: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btStart.isEnabled = false

        binding.etNumberParticipants.addTextChangedListener(this)

        binding.tvTermsConditions.setOnClickListener {
            val intentToActivities = Intent(this, Terms::class.java)
            startActivity(intentToActivities)
        }
        binding.btStart.setOnClickListener {
            val intentToActivities = Intent(this, Activities::class.java).also {
                it.putExtra(getString(R.string.participantsNumber),participantsNumber)
            }
            startActivity(intentToActivities)
        }

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        ValidateParticipants(p0)
    }

    override fun onTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {
        ValidateParticipants(p0)
    }

    override fun afterTextChanged(s: Editable?) {
        ValidateParticipants(s)
    }

    private fun ValidateParticipants(number : CharSequence?){
        number?.let {
            if(it.isNotEmpty()) {
                participantsNumber = number.toString().toInt()
                binding.btStart.isEnabled = participantsNumber >= 1
            } else {
                binding.btStart.isEnabled = false
            }

        }
    }

}