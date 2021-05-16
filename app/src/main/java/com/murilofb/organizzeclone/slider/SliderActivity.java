package com.murilofb.organizzeclone.slider;

import android.os.Bundle;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.murilofb.organizzeclone.Fragments.SignInUpFragment;
import com.murilofb.organizzeclone.activities.MainScreenActivity;
import com.murilofb.organizzeclone.helpers.FireBaseHelper;
import com.murilofb.organizzeclone.R;

public class SliderActivity extends IntroActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FireBaseHelper.getFirebaseAuth().signOut();

        setButtonBackVisible(false);
        setButtonNextVisible(false);
        setFullscreen(true);
        //setPagerIndicatorVisible(false);
        

        addSlide(new FragmentSlide.Builder()
                .background(R.color.slider_backgroud)
                .fragment(R.layout.intro_1)
                .canGoBackward(false)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(R.color.slider_backgroud)
                .fragment(R.layout.intro_2)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(R.color.slider_backgroud)
                .fragment(R.layout.intro_3)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(R.color.slider_backgroud)
                .fragment(R.layout.intro_4)
                .build());


        addSlide(new FragmentSlide.Builder()
                .background(R.color.slider_backgroud)
                .fragment(new SignInUpFragment())
                .canGoForward(false)
                .build());


    }

    @Override
    protected void onStart() {
        super.onStart();

        if (FireBaseHelper.getFirebaseAuth().getCurrentUser() != null) {
            finish();
            MainScreenActivity.openActivity(SliderActivity.this);

        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}