package com.example.motivation.util

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.view.inputmethod.InputMethodManager

class SecurityPreferences(contex:Context) {




    private val MsharedPreferences:SharedPreferences = contex.getSharedPreferences("Motivation",Context.MODE_PRIVATE)

    fun clear(){
        MsharedPreferences.edit().clear().apply()
    }

    fun storeString(key:String,value:String){
        MsharedPreferences.edit().putString(key,value).apply()
    }


    fun getstoredString(key:String): String? {
        return MsharedPreferences.getString(key,"")


    }

    fun storeTheme(key:String,value:Int){
        MsharedPreferences.edit().putInt(key,value).apply()
    }

    fun getstoredTheme(key:String):Int{
        return MsharedPreferences.getInt(key,0)
    }



}