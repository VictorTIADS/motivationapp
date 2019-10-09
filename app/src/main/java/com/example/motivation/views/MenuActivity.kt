package com.example.motivation.views

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.motivation.R
import com.example.motivation.util.MotivationConstants
import com.example.motivation.util.SecurityPreferences
import kotlinx.android.synthetic.main.activity_menu.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton


class MenuActivity : AppCompatActivity() {


    private lateinit var mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //INSTANCIA
        mSecurityPreferences = SecurityPreferences(this)
        setContentView(R.layout.activity_menu)




//CHAMADAS DE FUNCOES
        setRadioButton()
        setBack(mSecurityPreferences.getstoredTheme(MotivationConstants.KEY.THEME_KEY))
        setInfo()

//EVENTOS DE BOTÃO
        btnEdit.setOnClickListener {

            handleSave()


        }
//EVENTOS DE BOTÃO
        btnSair.setOnClickListener {


            var aletin = alert("Deseja realmente sair?", "Você está prestes a sair") {


                yesButton {

                    mSecurityPreferences.clear()
                    val i = Intent(baseContext, SplashActivity::class.java)
                    i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(i)
                }
                noButton {}
            }
            aletin.iconResource = R.drawable.ic_info_black_24dp
            aletin.show()
        }
//EVENTOS DE BOTÃO
        btnBack.setOnClickListener {
            finish()
        }
//EVENTOS DE BOTÃO
        rdDark.setOnClickListener {
            rdLight.isChecked = false
            mSecurityPreferences.storeTheme(
                MotivationConstants.KEY.THEME_KEY,
                MotivationConstants.THEME_FILTER.DARK
            )
            setBack(mSecurityPreferences.getstoredTheme(MotivationConstants.KEY.THEME_KEY))
        }
        rdLight.setOnClickListener {
            rdDark.isChecked = false
            mSecurityPreferences.storeTheme(
                MotivationConstants.KEY.THEME_KEY,
                MotivationConstants.THEME_FILTER.LIGHT
            )
            setBack(mSecurityPreferences.getstoredTheme(MotivationConstants.KEY.THEME_KEY))

        }
    }

    //FIM ON CREATE
//ON RESUME
    override fun onResume() {
        super.onResume()
        setBack(mSecurityPreferences.getstoredTheme(MotivationConstants.KEY.THEME_KEY))

    }
//FIM ON RESUME

    private fun setInfo() {
        txtNameMenu.setText(mSecurityPreferences.getstoredString(MotivationConstants.KEY.PERSON_NAME))

    }


    private fun isValid(): Boolean {

        var name = mSecurityPreferences.getstoredString(MotivationConstants.KEY.PERSON_NAME)


        if (txtNameMenu.text!!.isEmpty()) {
            layoutTextInput.error = "Por favor, digite um nome"
            return false
        } else if (txtNameMenu.text.toString() == name) {
            layoutTextInput.error = "Por favor, digite um nome diferente"
            return false
        } else {

            txtNameMenu.hideKeyboard()
            var snak = txtNameMenu.longSnackbar("Nome Alterado Com Sucesso!")
            snak.view.setBackgroundColor(ContextCompat.getColor(this, R.color.sucess))
            snak.setActionTextColor(Color.WHITE)
            snak.setAction("Voltar", { finish() })
            layoutTextInput.clearFocus()
            layoutTextInput.error = null
            return true
        }


    }


    private fun handleSave() {
        if (isValid()) {
            var name = txtNameMenu.text.toString()
            mSecurityPreferences.storeString(MotivationConstants.KEY.PERSON_NAME, name)
        } else {
            isValid()
        }
    }


    fun View.hideKeyboard() {
        val inputManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }


    private fun setRadioButton() {

        var themeNow = mSecurityPreferences.getstoredTheme(MotivationConstants.KEY.THEME_KEY)
        when (themeNow) {


            MotivationConstants.THEME_FILTER.LIGHT -> {
                rdLight.isChecked = true
            }

            MotivationConstants.THEME_FILTER.DARK -> {
                rdDark.isChecked = true
            }


        }


    }

    private fun setBack(fundo: Int) {

        when (fundo) {

            MotivationConstants.THEME_FILTER.LIGHT -> {
                funMenu.setBackgroundResource(R.drawable.mainmenu)
            }

            MotivationConstants.THEME_FILTER.DARK -> {
                funMenu.setBackgroundResource(R.drawable.space)
            }


        }

    }


}