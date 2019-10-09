package com.example.motivation.conection

import com.example.motivation.model.Response
import retrofit2.Call
import retrofit2.http.*

interface InterfaceData {

    @GET("charada-aleatoria")
    @Headers("Accept:application/json")
    fun data():Call<Model>

    //?b=Pernambuco&cod=VICTOR&wt=json
    @GET("busca")
    fun getNoticias(
        @Query("b") b: String = "Pernambuco",
        @Query("cod") cod: String = "VICTOR",
        @Query("wt") wt: String = "json"
    ): Call<Response>


}