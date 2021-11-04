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
    private var participantsNumber: Int = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.etNumberParticipants.addTextChangedListener{
            Log.println(Log.WARN,"PARTICIPANTES", participantsNumber.toString())
            try{
                afterTextChanged(it)
                if (participantsNumber <= 0) {
                    binding.btStart.isClickable= false
                } else {
                    binding.btStart.isClickable = true
                }
            } catch (e: NumberFormatException){
                Log.println(Log.WARN,"PARTICIPANTES", "INGRESE ALGO")
                //Poner Toast con alerta o TextView en el EditText
            }
        }

        binding.btStart.setOnClickListener {
            val intentToActivities = Intent(this, Activities::class.java).also {
                it.putExtra(getString(R.string.participantsNumber),participantsNumber)
            }
            startActivity(intentToActivities)

           /* Log.println(Log.ASSERT,"GGGG","HOLAAAAA")*/
        }

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
       participantsNumber = s.toString().toInt()
        /*Log.println(Log.ASSERT,"HHHH", "AFTERTEXT")*/
        /*Log.println(Log.ASSERT,"HHHH", editTextConverter)*/

    }

}