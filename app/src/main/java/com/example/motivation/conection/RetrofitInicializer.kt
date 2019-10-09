package com.example.motivation.conection

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInicializer {


    private val retrofit = Retrofit.Builder()
        .baseUrl("https://us-central1-kivson.cloudfunctions.net/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()



    fun interfaceData() = retrofit.create(InterfaceData::class.java)
}