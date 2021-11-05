package com.example.integrador_android

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.integrador_android.databinding.ActivityActivitiesBinding
import com.example.integrador_android.databinding.ItemActivitiesBinding

class Activities : AppCompatActivity() {
    private lateinit var binding: ActivityActivitiesBinding
    /*private lateinit var binding2: ItemActivitiesBinding*/

    private lateinit var adapter: ActivitiesAdapter

    private val categoriesList: List<String> = mutableListOf("A","B","C","D","E","F","G","H","I","J","K")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivitiesBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val participantsNumber = intent.getIntExtra(getString(R.string.participantsNumber),1)
        Log.println(Log.WARN,"INTENT1", participantsNumber.toString())

        recyclerViewInit()

        /*HARCODE: Intent hacia tercera pantalla*/
        binding.imRandom.setOnClickListener{
            val stringAux = "education"
            val intents = Intent(this,Suggestions::class.java).also {
                it.putExtra(getString(R.string.participantsNumber), participantsNumber)
                it.putExtra(getString(R.string.hardCodedCategory), stringAux)
            }
            startActivity(intents)
        }

        binding.rvActivities.setOnClickListener(){
            val intents = Intent(this,Suggestions::class.java).also {
                it.putExtra(getString(R.string.participantsNumber), participantsNumber)
                it.putExtra(getString(R.string.hardCodedCategory), "ho")
            }
            startActivity(intents)

        }

    }

    fun recyclerViewInit(){
        adapter = ActivitiesAdapter(categoriesList,this) /*This Activities instance must arrive to ActivitiesView holder
        to invoke activityStarter*/

        binding.rvActivities.layoutManager = LinearLayoutManager(this)
        binding.rvActivities.adapter = adapter

    }

    fun activityStarter(intent: Intent){
        startActivity(intent)
    }
}

