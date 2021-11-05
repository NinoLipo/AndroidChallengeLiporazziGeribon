package com.example.integrador_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.integrador_android.databinding.ActivitySuggestionsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Suggestions : AppCompatActivity() {

    private lateinit var binding: ActivitySuggestionsBinding
    private var participantsNumber: Int = 1
    private val categoriesList: List<String> = mutableListOf("education", "recreational", "social", "diy",
        "charity", "cooking", "relaxation", "music", "busywork")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuggestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        participantsNumber = intent.getIntExtra(getString(R.string.participantsNumber),1)
        var activityCategory = intent.getStringExtra(getString(R.string.hardCodedCategory))
        val randomOk = intent.getBooleanExtra(getString(R.string.random), false)

        //Check if call was from random buttom
        if(randomOk)  {
            //binding.cvCategoria.visibility = View.VISIBLE AGREGAR CARDVIEW DE CATEGORIA
            this.setTitle("Random")
        }

        activityCategory?.let {
            searchActivityPerParticipants(participantsNumber, activityCategory )
            this.setTitle(activityCategory)
        }

        binding.btTryAnother.setOnClickListener {
            if(randomOk) activityCategory = categoriesList.random()

            searchActivityPerParticipants(participantsNumber,activityCategory)
        }

    }

    private fun searchActivityPerParticipants(queryParticipants: Int, queryActivity: String?){
        CoroutineScope(Dispatchers.IO).launch {

            val apiRequest = getRetrofit()
                .create(ApiService::class.java)
                .suggestionApiRequest("activity?/participants=$queryParticipants&type=$queryActivity")

            val apiResponse: SuggestionResponse? = apiRequest.body()

            runOnUiThread{
                if (apiRequest.isSuccessful){

                    val activityToShow: String = apiResponse?.activity ?: "No activities to suggest" //Ok?
                    binding.textView6.text = activityToShow

                    val typeToShow= apiResponse?.type ?: "No Type to suggest" //Ok?
                    binding.textView2.text = typeToShow

                    val priceToShow = PriceMapper(apiResponse?.price)
                    binding.tvActivityPrice.text = priceToShow

                    binding.tvParticipantsQuantity.text = participantsNumber.toString()
                }
            }

        }
    }

    private fun PriceMapper(price : Double?) : String {
        var messageCost = "default"
        price?.let {
            when{
                price == 0.0 ->  messageCost = "Free"
                price <= 0.3 -> messageCost =  "Low"
                price <= 0.6 -> messageCost =  "Medium"
                else -> messageCost = "High"
            }
        }
        return messageCost
    }

    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl("https://www.boredapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}