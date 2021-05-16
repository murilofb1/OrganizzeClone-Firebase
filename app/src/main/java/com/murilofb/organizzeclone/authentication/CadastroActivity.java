package com.murilofb.organizzeclone.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.murilofb.organizzeclone.helpers.FireBaseHelper;
import com.murilofb.organizzeclone.models.Usuario;
import com.murilofb.organizzeclone.R;

public class CadastroActivity extends AppCompatActivity {

    private static Toast tst;


    private static final String EDIT_TEXT_ERROR = "Ainda faltam alguns campos a serem preenchidos";
    private static final String MISSING_PASSWORD = "Está faltando preencher sua senha";
    private static final String MISSING_PASSWORD2 = "Está faltando preencher a confirmação da senha";
    private static final String PASSWORD_DONTMATCH = "As senha não conferem";
    private static final String MISSING_EMAIL = "Está faltando preencher o email";
    private static final String MISSING_NAME = "Está faltando preecher o seu nome";
    private static final String MISSING_ALL = "Preencha todos os campos primeiro";


    private String strNome;
    private String strEmail;
    private String strSenha1;
    private String strSenha2;

    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtSenha1;
    private EditText edtSenha2;
    private Button btnCadastrar;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        tst = new Toast(getApplicationContext());
        edtNome = findViewById(R.id.edt_cadastro_nome);
        edtEmail = findViewById(R.id.edt_cadastro_email);
        edtSenha1 = findViewById(R.id.edt_cadastro_senha1);
        edtSenha2 = findViewById(R.id.edt_cadastro_senha2);
        btnCadastrar = findViewById(R.id.btn_cadastro_confirmar);


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkEditTextFields()) {

                } else {

                    Usuario usuario = new Usuario(strNome, strEmail, strSenha1);
                    FireBaseHelper.cadastrarUsuario(CadastroActivity.this, usuario);
                }
            }
        });
    }

    //Está definindo as strings como os valores nos campos de EditText
    private boolean checkEditTextFields() {
        tst.cancel();
        strNome = edtNome.getText().toString();
        strEmail = edtEmail.getText().toString();
        strSenha1 = edtSenha1.getText().toString();
        strSenha2 = edtSenha2.getText().toString();

        tst = Toast.makeText(CadastroActivity.this, "", Toast.LENGTH_SHORT);

        if (strNome.isEmpty() && strEmail.isEmpty() && strSenha1.isEmpty() && strSenha2.isEmpty()) {
            tst.setText(MISSING_ALL);
        } else if (strNome.isEmpty() && !strEmail.isEmpty() && !strSenha1.isEmpty() && !strSenha2.isEmpty()) {
            tst.setText(MISSING_NAME);
        } else if (!strNome.isEmpty() && strEmail.isEmpty() && !strSenha1.isEmpty() && !strSenha2.isEmpty()) {
            tst.setText(MISSING_EMAIL);
        } else if (!strNome.isEmpty() && !strEmail.isEmpty() && strSenha1.isEmpty() && !strSenha2.isEmpty()) {
            tst.setText(MISSING_PASSWORD);
        } else if (!strNome.isEmpty() && !strEmail.isEmpty() && !strSenha1.isEmpty() && strSenha2.isEmpty()) {
            tst.setText(MISSING_PASSWORD2);
        } else if (!strNome.isEmpty() && !strEmail.isEmpty() && !strSenha1.isEmpty() && !strSenha2.isEmpty()) {
            if (strSenha2.equals(strSenha1)) {

                return false;
            } else {
                tst.setText(PASSWORD_DONTMATCH);
            }
        } else {
            tst.setText(EDIT_TEXT_ERROR);
        }
        tst.show();
        return true;
    }

}