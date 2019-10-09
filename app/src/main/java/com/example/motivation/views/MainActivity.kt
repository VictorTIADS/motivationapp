package com.example.motivation.views


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.motivation.R
import com.example.motivation.conection.Model
import com.example.motivation.conection.RetrofitInicializer
import com.example.motivation.mock.Mock
import com.example.motivation.util.MotivationConstants
import com.example.motivation.util.SecurityPreferences
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.share
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class MainActivity : AppCompatActivity() {

    private var teste:Int = 0

    private var mFilter: Int = MotivationConstants.FRASE_FILTER.ALL
    private lateinit var mSecurityPreferences: SecurityPreferences
    private var mMoke = Mock()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mSecurityPreferences = SecurityPreferences(this)

        statusHour()
        verifyUserName()
        setBack(mSecurityPreferences.getstoredTheme(MotivationConstants.KEY.THEME_KEY))
//INICIALIZACAO
        mFilter = 2
        refrashFrase()
        getCharada()
        imageAll.setImageResource(R.drawable.ic_all_selected)





        imageAll.setOnClickListener {
            mFilter = MotivationConstants.FRASE_FILTER.ALL
            imageAll.setImageResource(R.drawable.ic_all_selected)
            imageSun.setImageResource(R.drawable.ic_sun_un_selected)
            imageHappy.setImageResource(R.drawable.ic_happy_un_selected)
            snakBarShow("Todas", it)

        }

        imageSun.setOnClickListener {
            mFilter = MotivationConstants.FRASE_FILTER.SUN
            imageAll.setImageResource(R.drawable.ic_all_un_selected)
            imageSun.setImageResource(R.drawable.ic_sun_selected)
            imageHappy.setImageResource(R.drawable.ic_happy_un_selected)
            snakBarShow("Motivacionais", it)

        }

        imageHappy.setOnClickListener {
            mFilter = MotivationConstants.FRASE_FILTER.HAAPY
            imageHappy.setImageResource(R.drawable.ic_happy_selected)
            imageSun.setImageResource(R.drawable.ic_sun_un_selected)
            imageAll.setImageResource(R.drawable.ic_all_un_selected)
            snakBarShow("Engraçadas", it)

        }

        imageEdit.setOnClickListener {
            imageEdit.setImageResource(R.drawable.ic_selected_edit)
            var intentMenu = Intent(this, MenuActivity::class.java)
            startActivity(intentMenu)
        }

        btnRefrashFrase.setOnClickListener {
            refrashFrase()
        }

        btnShareFrase.setOnClickListener {
            var textoDaHora = textFrase.text.toString()
            share(textoDaHora)

        }

        btnAnswer.setOnClickListener {
            controlVisibility(View.GONE,View.GONE,View.VISIBLE,true,true,true)
        }

        btnRefrashJoke.setOnClickListener {
            controlVisibility(View.VISIBLE,View.GONE,View.GONE,false,false,false)
            getCharada()
        }
        btnShareJoke.setOnClickListener {
            var jokeDaHora = lblPergunta.text.toString()
            share(jokeDaHora)
        }







    }

    fun btnNoticia(view:View){
        var intentNews = Intent(this,NewsActivity::class.java)
        startActivity(intentNews)
    }

    private fun getCharada() {
        val call = RetrofitInicializer().interfaceData().data()
        call.enqueue(object : Callback<Model> {
            override fun onFailure(call: Call<Model>, t: Throwable) {
                lblPergunta.text = t.message
            }

            override fun onResponse(call: Call<Model>, response: Response<Model>) {
                val answer = response.body()
                controlVisibility(View.GONE,View.VISIBLE,View.GONE,true,true,true)
                answer?.let { initUI(it) }


            }
        })


    }

    private fun initUI(answer:Model){
        lblPergunta.text = answer?.pergunta
        lblResposta.text = answer?.resposta
    }



    private fun controlVisibility(progresbar:Int,pergunta:Int,resposta:Int,buttonAnswerJoke:Boolean,buttonRefrashJoke:Boolean,buttonShareJoke:Boolean){

        progressBar.visibility = progresbar
        lblPergunta.visibility = pergunta
        lblResposta.visibility = resposta
        btnAnswer.isEnabled = buttonAnswerJoke
        btnRefrashJoke.isEnabled = buttonRefrashJoke
        btnShareJoke.isEnabled = buttonShareJoke
    }


    override fun onResume() {
        super.onResume()
        verifyUserName()
        statusHour()
        imageEdit.setImageResource(R.drawable.ic_un_selected_edit)
        setBack(mSecurityPreferences.getstoredTheme(MotivationConstants.KEY.THEME_KEY))
    }


    private fun verifyUserName() {
        lblUserName.text = mSecurityPreferences.getstoredString(MotivationConstants.KEY.PERSON_NAME)
    }

    private fun refrashFrase() {

        textFrase.text = mMoke.getFrase(mFilter)

    }

    private fun share(text: String) {
        val share = Intent(Intent.ACTION_SEND)
        share.type = "text/plain"


        share.putExtra(
            Intent.EXTRA_SUBJECT,
            "Sended From MotivationApp"
        )
        share.putExtra(
            Intent.EXTRA_TEXT,
            " ${text} - MotivationApp "
        )

        startActivity(Intent.createChooser(share, "Compartilhar"))
    }

    private fun statusHour() {
        val cal = Calendar.getInstance()
        var hour = cal.get(Calendar.HOUR)
        var ampm = cal.get(Calendar.AM_PM)

        if (ampm == 0) {
            lblBomdia.text = "Bom Dia"
        } else if (ampm == 1) {
            if (hour >= 0 && hour < 6) {
                lblBomdia.text = "Boa Tarde"
            } else {
                lblBomdia.text = "Boa Noite"
            }
        }


    }

    private fun snakBarShow(text: String, i: View) {
        var snak = i.snackbar("Filtro: ${text}")
        snak.view.setBackgroundColor(ContextCompat.getColor(this, R.color.snack))
        snak.setActionTextColor(Color.WHITE)

    }





    private fun setBack(fundo: Int) {

        when (fundo) {

            MotivationConstants.THEME_FILTER.LIGHT -> {
                funMain.setBackgroundResource(R.drawable.mainback)
            }

            MotivationConstants.THEME_FILTER.DARK -> {
                funMain.setBackgroundResource(R.drawable.spacemain)
            }


        }

    }








}
