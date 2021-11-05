package com.example.integrador_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.FloatRange
import com.example.integrador_android.databinding.ActivitySuggestionsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Suggestions : AppCompatActivity() {
    private lateinit var binding: ActivitySuggestionsBinding
    private val categoriesList: List<String> = mutableListOf("education", "recreational", "social", "diy",
        "charity", "cooking", "relaxation", "music", "busywork")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuggestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val participantsNumber = intent.getIntExtra(getString(R.string.participantsNumber), 1)
        var activityCategory = intent.getStringExtra(getString(R.string.hardCodedCategory))
        val randomOk = intent.getBooleanArrayExtra(getString(R.string.random))

        getActivity(participantsNumber, activityCategory)

        binding.btTryAnother.setOnClickListener {
            randomOk.let {
                activityCategory = categoriesList.random()
                //cambiar titulo
            }
            getActivity(participantsNumber,activityCategory)
        }
    }



    private fun getActivityRetroFit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://www.boredapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getActivity(participants: Int, category: String?) {

        //Llamo a retrofit pasandole la query
        CoroutineScope(Dispatchers.IO).launch {

            val llamada = getActivityRetroFit()
                .create(APIService::class.java)
                .giveMeActivity("activity?participants=$participants")

            //capturo los resultadps que me interesan
            val respuesta: RespuestaActivity? = llamada.body()


            //actualizo la vista con esos resultados
            runOnUiThread {
                if (llamada.isSuccessful) {
                    val actividadACargar = respuesta?.actividad ?: "No-Activity"
                    binding.textView6.text = actividadACargar

                    val precio = MapearPrecio(respuesta?.precio)
                    binding.tvPrice.text = precio

                    binding.tvParticipantsQuantity.text = participants.toString()

                    val categoria = respuesta?.categoria ?: "No-Category"
                    binding.tvCategory.text = categoria
                }

            }
        }
    }
    private fun MapearPrecio(precio : Double?) : String {
        var precioFormateado = "default"
        precio?.let {
            when{
                precio == 0.0 ->  precioFormateado = "Free"
                precio <= 0.3 -> precioFormateado =  "Low"
                precio <= 0.6 -> precioFormateado =  "Medium"
                else -> precioFormateado = "High"
            }
        }
        return precioFormateado
    }

}