package com.example.motivation.views

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.motivation.NoticiaAdapter
import com.example.motivation.R
import com.example.motivation.conection.RetrofitNoticias
import com.example.motivation.model.NoticiaItem
import com.example.motivation.model.Response
import kotlinx.android.synthetic.main.activity_news.*
import retrofit2.Call
import retrofit2.Callback
import java.util.*

class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)





        txtPesquisa.setOnKeyListener { view, i, keyEvent ->
            getNotice(txtPesquisa.text.toString())
            txtPesquisa.hideKeyboard()
            true
        }

        getNotice(Calendar.getInstance().get(Calendar.YEAR).toString())
        btnBackNews.setOnClickListener {
            finish()
        }
    }

    private fun getNotice(searchValue: String) {
        val call = RetrofitNoticias().interfaceData()

        controlShow(View.GONE, View.VISIBLE,View.GONE)

        call.getNoticias(searchValue).enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                //setContentView(R.layout.erro_request_activity)
                controlShow(View.GONE, View.GONE,View.VISIBLE)

            }

            override fun onResponse(
                call: Call<Response>,
                response: retrofit2.Response<Response>
            ) {
                if (response.code() == 200) {
                    val resposta = response.body()
                    resposta?.let {
                        Log.d("aspk", "Resultado da busca: ${it.resultado.numeroNoticias}")

                        initUI(resposta.resultado.noticias.noticia)
                        controlShow(View.VISIBLE, View.GONE,View.GONE)


                    }
                }
            }

        })
    }


    fun initUI(lista: ArrayList<NoticiaItem>) {


        val adapter = NoticiaAdapter(this, lista)
        var layoutManeger = LinearLayoutManager(this)
        val recyclerViewvar = recycleView

        recyclerViewvar.layoutManager = layoutManeger

        recyclerViewvar .adapter = adapter

    }


    fun controlShow(recycle:Int,progressbar:Int,constraintErro:Int){
        recycleView.visibility = recycle
        newsProgressBar.visibility = progressbar
        constraintLayoutErro.visibility = constraintErro
    }


    fun View.hideKeyboard() {
        val inputManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }



}
