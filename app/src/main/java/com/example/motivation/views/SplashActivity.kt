package com.example.motivation.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.motivation.R
import com.example.motivation.util.MotivationConstants
import com.example.motivation.util.SecurityPreferences
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private lateinit var mSecurity: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)



//INSTANCIA
        mSecurity = SecurityPreferences(this)


//FUNÇÕES
        verifyUsername()

        btnNext.setOnClickListener {
            var intentMain = Intent(this, MainActivity::class.java)



            if (isValid()) {
                handleSave()

                startActivity(intentMain)

                finish()
            } else {
                isValid()
            }


        }
    }


    private fun handleSave() {
        if (isValid()) {
            val name: String = txtName.text.toString()
            mSecurity.storeString(MotivationConstants.KEY.PERSON_NAME, name)

        } else {
            isValid()
        }

    }

    private fun isValid(): Boolean {

        if (txtName.text.isNullOrEmpty()) {
            textInputLayoutName.error = "Informe Seu Nome"


            return false

        } else {

            textInputLayoutName.clearFocus()
            textInputLayoutName.error = null
        }
        return true
    }

    private fun verifyUsername() {
        var UserName = mSecurity.getstoredString(MotivationConstants.KEY.PERSON_NAME)

        if (UserName != "") {

            startActivity(Intent(this, MainActivity::class.java))
            finish()

        }
    }



}
