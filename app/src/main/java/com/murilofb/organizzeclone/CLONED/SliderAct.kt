package com.murilofb.organizzeclone.CLONED

import android.content.Intent
import android.os.Bundle

import com.heinrichreimersoftware.materialintro.app.IntroActivity
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide
import com.murilofb.organizzeclone.Fragments.SignInUpFragment
import com.murilofb.organizzeclone.R
import com.murilofb.organizzeclone.activities.MainScreenAct
import com.murilofb.organizzeclone.helpers.FirebaseH

class SliderAct : IntroActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isButtonBackVisible = false
        isButtonNextVisible = false
        isFullscreen = true

        addSlide(
            FragmentSlide.Builder()
                .background(R.color.slider_backgroud)
                .fragment(R.layout.intro_1)
                .canGoBackward(false)
                .build()
        )

        addSlide(
            FragmentSlide.Builder()
                .background(R.color.slider_backgroud)
                .fragment(R.layout.intro_2)
                .build()
        )

        addSlide(
            FragmentSlide.Builder()
                .background(R.color.slider_backgroud)
                .fragment(R.layout.intro_3)
                .build()
        )

        addSlide(
            FragmentSlide.Builder()
                .background(R.color.slider_backgroud)
                .fragment(R.layout.intro_4)
                .build()
        )

        addSlide(
            FragmentSlide.Builder()
                .background(R.color.slider_backgroud)
                .fragment(SignInUpFragment())
                .canGoForward(false)
                .build()
        )
    }

    override fun onStart() {
        super.onStart()
        if (FirebaseH.isSomeoneLogged()) {
            startActivity(Intent(this, MainScreenAct::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}