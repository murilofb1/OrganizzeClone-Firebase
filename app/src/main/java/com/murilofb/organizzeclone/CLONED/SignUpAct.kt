package com.murilofb.organizzeclone.CLONED

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.murilofb.organizzeclone.databinding.ActivitySignupBinding
import com.murilofb.organizzeclone.helpers.FirebaseH
import com.murilofb.organizzeclone.models.UserMD

class SignUpAct : AppCompatActivity() {
    lateinit var toast: Toast
    lateinit var binding: ActivitySignupBinding

    lateinit var strName: String
    lateinit var strEmail: String
    lateinit var strPsswd: String
    lateinit var strPsswd2: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Inicializando o toast para não sobrepo-lo ao clicar varias vezes
        toast = Toast(applicationContext)
        //SETTANDO O CLICK LISTENER NO BOTÃO DE CADASTRO
        binding.btnConfirmSignUp.setOnClickListener {
            if (!checkEditTextFields()) {
                val user = UserMD(strName, strEmail, strPsswd)
                //ESSA FUNÇÃO AINDA NÃO ESTÁ FAZENDO NADA
                FirebaseH.registerUser(this, user)
            }
        }
    }

    /*
    PASSAR ESSA FUNÇÃO PARA UM HELPER PARA PODER UTILIZAR EM OUTRAS CLASSES
    COLOCAR PARA RECEBER UM ARRAY DE EDIT TEXT COMO PARAMETRO
     */
    private fun checkEditTextFields(): Boolean {
        /*
        PASSAR ISSO PRO XML CAMPO DE STRINGS
        */
        val EDIT_TEXT_ERROR = "Ainda faltam alguns campos a serem preenchidos";
        val MISSING_PASSWORD = "Está faltando preencher sua senha";
        val MISSING_PASSWORD2 = "Está faltando preencher a confirmação da senha";
        val PASSWORD_DONTMATCH = "As senha não conferem";
        val MISSING_EMAIL = "Está faltando preencher o email";
        val MISSING_NAME = "Está faltando preecher o seu nome";
        val MISSING_ALL = "Preencha todos os campos primeiro";

        toast.cancel()
        toast = Toast.makeText(applicationContext, "", Toast.LENGTH_SHORT)

//  VERIFICANDO CADA CAMPO QUE ESTÁ VAZIO E MANDANDO UMA MENSAGEM PERSONALIZADA PARA CADA CAMPO
        if (strName.isEmpty() && strEmail.isEmpty() && strPsswd.isEmpty() && strPsswd2.isEmpty()) {
            toast.setText(MISSING_ALL)
        } else if (strName.isEmpty() && strEmail.isNotEmpty() && strPsswd.isNotEmpty() && strPsswd2.isNotEmpty()) {
            toast.setText(MISSING_NAME)
        } else if (strName.isNotEmpty() && strEmail.isEmpty() && strPsswd.isNotEmpty() && strPsswd2.isNotEmpty()) {
            toast.setText(MISSING_EMAIL)
        } else if (strName.isNotEmpty() && strEmail.isNotEmpty() && strPsswd.isEmpty() && strPsswd2.isNotEmpty()) {
            toast.setText(MISSING_PASSWORD)
        } else if (strName.isNotEmpty() && strEmail.isNotEmpty() && strPsswd.isNotEmpty() && strPsswd2.isEmpty()) {
            toast.setText(MISSING_PASSWORD2)
        } else if (strName.isNotEmpty() && strEmail.isNotEmpty() && strPsswd.isNotEmpty() && strPsswd2.isNotEmpty()) {
            if (strPsswd2.equals(strPsswd)) return false
            else toast.setText(PASSWORD_DONTMATCH);
        } else {
            toast.setText(EDIT_TEXT_ERROR);
        }
        toast.show()
        return true
    }
}