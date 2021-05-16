package com.murilofb.organizzeclone.authentication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.murilofb.organizzeclone.databinding.ActivityLoginBinding

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
        }
    }


}