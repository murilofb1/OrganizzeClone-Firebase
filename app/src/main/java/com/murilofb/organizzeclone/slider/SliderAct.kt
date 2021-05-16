package com.murilofb.organizzeclone.slider

import android.os.Bundle
import androidx.navigation.NavController

import com.heinrichreimersoftware.materialintro.app.IntroActivity
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide
import com.murilofb.organizzeclone.Fragments.SignInUpFragment
import com.murilofb.organizzeclone.R

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


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


}