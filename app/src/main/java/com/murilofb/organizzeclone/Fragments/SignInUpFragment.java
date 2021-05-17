package com.murilofb.organizzeclone.Fragments;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.murilofb.organizzeclone.JA_FOI.CadastroActivity;
import com.murilofb.organizzeclone.JA_FOI.LoginActivity;
import com.murilofb.organizzeclone.R;


public class SignInUpFragment extends Fragment {

    private View.OnClickListener btnClickHandler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = null;
            if (view.getId() == R.id.btn_cadastrado) {
                i = new Intent(getContext(), LoginActivity.class);
            } else if (view.getId() == R.id.btn_cadastrar) {
                i = new Intent(getContext(), CadastroActivity.class);
            }
            startActivity(i);
        }
    };

    public SignInUpFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_cadastro, container, false);
        final Button btnCadastrar = view.findViewById(R.id.btn_cadastrar);
        final Button btnCadastrado = view.findViewById(R.id.btn_cadastrado);
        btnCadastrado.setOnClickListener(btnClickHandler);
        btnCadastrar.setOnClickListener(btnClickHandler);

        return view;
    }
}