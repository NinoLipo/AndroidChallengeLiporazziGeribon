package com.example.integrador_android


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.provider.Settings.System.getString
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.integrador_android.databinding.ItemActivitiesBinding
import androidx.appcompat.app.AppCompatActivity

class ActivitiesViewHolder(itemView: View, activitiesInstance: Activities): RecyclerView.ViewHolder(itemView){
    private val binding = ItemActivitiesBinding.bind(itemView)
   private val activitiesInstance = activitiesInstance

    init {
        binding.btSuggestionTrigger.setOnClickListener {
            val categorySelected = binding.tvActivityCategory.text.toString()
            activitiesInstance.activityStarter(categorySelected)
        }
    }

    fun bind(activityOption: String) {
        binding.tvActivityCategory.text = activityOption
    }

}

