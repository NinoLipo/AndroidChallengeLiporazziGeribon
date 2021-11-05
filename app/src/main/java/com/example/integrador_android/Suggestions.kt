package com.example.integrador_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.integrador_android.databinding.ActivitySuggestionsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Suggestions : AppCompatActivity() {
    private lateinit var binding: ActivitySuggestionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuggestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val participantsNumber = intent.getIntExtra(getString(R.string.participantsNumber),1)
        val activityCategory = intent.getStringExtra(getString(R.string.hardCodedCategory))
        Log.println(Log.ERROR,"BANDERA","Linea 23")
        activityCategory?.let {
            Log.println(Log.ERROR,"BANDERA","Linea 25")
            searchActivityPerParticipants(participantsNumber, activityCategory )
            Log.println(Log.ERROR,"BANDERA","Linea 27")
        }

    }

    private fun searchActivityPerParticipants(queryParticipants: Int, queryActivity: String){
        CoroutineScope(Dispatchers.IO).launch {

            val apiRequest = getRetrofit()
                .create(ApiService::class.java)
                .suggestionApiRequest("activity?/participants=$queryParticipants&type=$queryActivity")

            val apiResponse: SuggestionResponse? = apiRequest.body()
            Log.println(Log.ERROR,"BANDERA",apiResponse.toString())

            runOnUiThread{
                if (apiRequest.isSuccessful){
                    val activityToShow: String = apiResponse?.activity ?: "No activities to suggest" //Ok?
                    binding.textView2.text = activityToShow
                    val queryToShow: String= apiResponse?.type ?: "No Type to suggest" //Ok?
                    binding.textView6.text = queryToShow
                }
            }

        }
    }

    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl("https://www.boredapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}