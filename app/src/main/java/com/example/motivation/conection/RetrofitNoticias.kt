package com.example.motivation.conection

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitNoticias {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://noticias.gov.br/noticias-api/noticias/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun interfaceData() = retrofit.create(InterfaceData::class.java)
}