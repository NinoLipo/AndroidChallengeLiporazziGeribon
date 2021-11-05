package com.example.integrador_android

import com.google.gson.annotations.SerializedName

class RespuestaActivity(var status : String,
                        @SerializedName("activity") var actividad : String,
                        @SerializedName("price") var precio : Double,
                        @SerializedName("type") var categoria : String
                        ) {

}


