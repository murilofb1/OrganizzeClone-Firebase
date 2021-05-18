package com.murilofb.organizzeclone.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.murilofb.organizzeclone.helpers.DateCustom;
import com.murilofb.organizzeclone.helpers.FireBaseHelper;
import com.murilofb.organizzeclone.helpers.MaskFormats;
import com.murilofb.organizzeclone.models.Movimentacao;
import com.murilofb.organizzeclone.R;

public class NovaReceitaActivity extends AppCompatActivity {
    private static Intent thisIntent;
    private FloatingActionButton fabReceita;
    private EditText edtDescricao;
    private EditText edtValorReceita;
    private EditText edtData;
    private EditText edtCategoria;
    private double receitaTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_receita);
        fabReceita = findViewById(R.id.fab_confirmar_receita);
        edtDescricao = findViewById(R.id.edt_descricao_receita);
        edtValorReceita = findViewById(R.id.edt_valor_receita);
        edtData = findViewById(R.id.edt_data_receita);
        edtCategoria = findViewById(R.id.edt_categoria_receita);
        edtData.setText(DateCustom.getCurrentDate());
        recuperarReceitaTotal();

        MaskFormats.dateMask(edtData);
        fabReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarEdtText()){
                    Movimentacao movimentacao = new Movimentacao(
                            edtData.getText().toString(),
                            edtDescricao.getText().toString(),
                            FireBaseHelper.TIPO_RECEITA,
                            edtCategoria.getText().toString(),
                            Double.parseDouble(edtValorReceita.getText().toString())
                    );
                    FireBaseHelper.cadastrarMovimentacao(movimentacao,receitaTotal);
                    finish();
                }

            }
        });

    }

    public static void openActivity(Context context) {
        if (thisIntent == null) {
            thisIntent = new Intent(context, NovaReceitaActivity.class);
        }
        context.startActivity(thisIntent);
    }

    private boolean validarEdtText() {
        String textoCategoria = edtCategoria.getText().toString();
        String textoValor = edtValorReceita.getText().toString();
        String textoDescricao = edtDescricao.getText().toString();
        String textoData = edtData.getText().toString();

        String DEFAULT_ERROR_MSG = "Preencha todos os campos primeiro!";

        if (textoCategoria.isEmpty() || textoValor.isEmpty() || textoDescricao.isEmpty() || textoData.isEmpty()) {
            Toast.makeText(NovaReceitaActivity.this, DEFAULT_ERROR_MSG, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void recuperarReceitaTotal() {
        DatabaseReference userReference = FireBaseHelper.getCurrentUserReference();
        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Movimentacao.Usuario usuario = snapshot.getValue(Movimentacao.Usuario.class);
                receitaTotal = usuario.getReceitaTotal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}