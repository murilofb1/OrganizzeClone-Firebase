package com.murilofb.organizzeclone.JA_FOI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.murilofb.organizzeclone.helpers.FireBaseHelper;
import com.murilofb.organizzeclone.R;

public class LoginActivity extends AppCompatActivity {
    private Toast tst;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tst = new Toast(getApplicationContext());
        final Button btnLogin = findViewById(R.id.btnLogIn);
        final EditText edtEmail = findViewById(R.id.edtEmail);
        final EditText edtSenha = findViewById(R.id.edtPsswd);
        // final CheckBox checkSaveLogin = findViewById(R.id.check_save_login);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tst.cancel();

                String email = edtEmail.getText().toString();
                String password = edtSenha.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {
                    FireBaseHelper.logarUsuario(LoginActivity.this, email, password);

                } else {
                    tst = Toast.makeText(LoginActivity.this, "Preecha ambos os campos primeiro", Toast.LENGTH_SHORT);
                    tst.show();
                }
            }
        });


    }

}