package com.example.integrador_android

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ActivitiesAdapter(private var activitiesList: List<String>, val aux:Activities):RecyclerView.Adapter<ActivitiesViewHolder>() {
/*
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivitiesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ActivitiesViewHolder(layoutInflater.inflate(R.layout.item_activities,parent,false))
    }
*/
    lateinit var viewHolder: ActivitiesViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivitiesViewHolder{
        val itemView: View =LayoutInflater.from(parent.context).inflate(R.layout.item_activities,parent,false)

    return ActivitiesViewHolder(itemView, aux)
    }

    override fun onBindViewHolder(holder: ActivitiesViewHolder, position: Int) {
        viewHolder = holder
        val activityInPosition = activitiesList[position]
        holder.bind(activityInPosition)

    }

    override fun getItemCount() = activitiesList.size
}

/*
class DogAdapter(private var images: List<String>): RecyclerView.Adapter<DogViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DogViewHolder(layoutInflater.inflate(R.layout.item_perro,parent,false))
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val dogInPosition = images[position]
        holder.bind(dogInPosition)
    }

    override fun getItemCount() = images.size
}
*/