package com.example.integrador_android

data class SuggestionResponse(var status: String,
    var activity: String,
    var accessibility: Double,
    var type: String,
    var price: Double,
)

/*
"activity": "Learn how to fold a paper crane",
"accessibility": 0.05,
"type": "education",
"participants": 1,
"price": 0.1,
"key": "3136036"
 */