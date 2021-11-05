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

    private lateinit var adapter: ActivitiesAdapter
    private var participantsNumber = 1

    private val categoriesList: List<String> = mutableListOf("education", "recreational", "social", "diy",
        "charity", "cooking", "relaxation", "music", "busywork")

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityActivitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.setTitle("Activities")
        participantsNumber = intent.getIntExtra(getString(R.string.participantsNumber),1)
        recyclerViewInit()

        binding.imRandom.setOnClickListener{
            val randomOk = true
            val randomCategory = categoriesList.random()
            val intents = Intent(this,Suggestions::class.java).also {
                it.putExtra(getString(R.string.participantsNumber), participantsNumber)
                it.putExtra(getString(R.string.hardCodedCategory), randomCategory)
                it.putExtra(getString(R.string.random),randomOk)
            }
            startActivity(intents)
        }

        binding.rvActivities.setOnClickListener(){
            val intents = Intent(this,Suggestions::class.java).also {
                val randomOk = false
                it.putExtra(getString(R.string.participantsNumber), participantsNumber)
                it.putExtra(getString(R.string.hardCodedCategory), "ho")
                it.putExtra(getString(R.string.random),randomOk)
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

    fun activityStarter(categorySelected : String){
        val randomOk = false
        val intents = Intent(this,Suggestions::class.java).also {
            it.putExtra(getString(R.string.participantsNumber), participantsNumber)
            it.putExtra(getString(R.string.hardCodedCategory), categorySelected)
            it.putExtra(getString(R.string.random),randomOk)
        }
        startActivity(intents)

    }
}

