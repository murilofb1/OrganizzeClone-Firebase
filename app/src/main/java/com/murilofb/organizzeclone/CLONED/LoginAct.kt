package com.murilofb.organizzeclone.CLONED

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.murilofb.organizzeclone.databinding.ActivityLoginBinding
import com.murilofb.organizzeclone.helpers.FirebaseH

class LoginAct : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var toast: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        toast = Toast(applicationContext)
        setContentView(binding.root)
        //SETANDO BOTAO DE LOGAR
        binding.btnLogIn.setOnClickListener {
            toast.cancel()
            val strEmail = binding.edtEmail.text.toString()
            val strPsswd = binding.edtPsswd.text.toString()
            if (strEmail.isNotEmpty() && strPsswd.isNotEmpty()) {
                //ESSA FUNÇÃO NAO FAZ NADA AINDA
                FirebaseH.loginUser(this, strEmail, strPsswd)
            } else {//JOGAR PARA O ARQUIVO DE STR
                toast = Toast.makeText(this, "Preecha ambos os campos primeiro", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }


}