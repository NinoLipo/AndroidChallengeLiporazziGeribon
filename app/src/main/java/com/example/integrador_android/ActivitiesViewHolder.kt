package com.example.integrador_android


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.integrador_android.databinding.ItemActivitiesBinding

class ActivitiesViewHolder(itemView: View, activitiesInstance: Activities): RecyclerView.ViewHolder(itemView), View.OnClickListener {
    private val binding = ItemActivitiesBinding.bind(itemView)
   private val activitiesInstance = activitiesInstance

    init {
        itemView.setOnClickListener(this) /*this --> El viewholder*/
        binding.btSuggestionTrigger.setOnClickListener {
            /*Log.println(Log.WARN,"8888","btn")*/
            val intent = Intent(activitiesInstance.applicationContext, Suggestions::class.java)
            activitiesInstance.activityStarter(intent)
        }
    }

    override fun onClick(view: View?){
        TODO()
    }

    fun bind(activityOption: String) {
        binding.tvActivityCategory.text = activityOption
    }



}

interface OnItemClickListener{
    fun onItemClick()
}