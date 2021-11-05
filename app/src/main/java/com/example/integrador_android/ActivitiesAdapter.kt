package com.example.integrador_android

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ActivitiesAdapter(private var activitiesList: List<String>, val activitiesInstance: Activities):RecyclerView.Adapter<ActivitiesViewHolder>() {

    lateinit var viewHolder: ActivitiesViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivitiesViewHolder{
        val itemView: View =LayoutInflater.from(parent.context).inflate(R.layout.item_activities,parent,false)

    return ActivitiesViewHolder(itemView, activitiesInstance)
    }

    override fun onBindViewHolder(holder: ActivitiesViewHolder, position: Int) {
        viewHolder = holder
        val activityInPosition = activitiesList[position]
        holder.bind(activityInPosition)

    }

    override fun getItemCount() = activitiesList.size
}

