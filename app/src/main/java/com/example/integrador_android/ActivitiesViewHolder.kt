package com.example.integrador_android


import android.content.Intent
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.integrador_android.databinding.ItemActivitiesBinding

class ActivitiesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
    private val binding = ItemActivitiesBinding.bind(itemView)

    init {
        itemView.setOnClickListener(this) /*this --> El viewholder*/
        binding.btSuggestionTrigger.setOnClickListener {
            Log.println(Log.WARN,"8888","btn")
            //val intent = Intent(this, Suggestions::class.java)
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

/*
class DogViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemPerroBinding.bind(view)

    fun bind(imagen: String) {
        Picasso.get().load(imagen).into(binding.ivPerro)
    }
*/