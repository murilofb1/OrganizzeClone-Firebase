package com.murilofb.organizzeclone.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.clans.fab.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.murilofb.organizzeclone.helpers.DateCustom;
import com.murilofb.organizzeclone.helpers.FireBaseHelper;
import com.murilofb.organizzeclone.helpers.MaskFormats;
import com.murilofb.organizzeclone.models.Movimentacao;
import com.murilofb.organizzeclone.models.Usuario;
import com.murilofb.organizzeclone.R;


public class NovaDespesaActivity extends AppCompatActivity {
    private static Intent thisIntent;
    private FloatingActionButton fabDespesa;
    private EditText edtDescricao;
    private EditText edtValorDespesa;
    private EditText edtData;
    private EditText edtCategoria;
    private double despesaTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_despesa);
        fabDespesa = findViewById(R.id.fab_confirmar_despesa);
        edtDescricao = findViewById(R.id.edt_descricao_despesa);
        edtValorDespesa = findViewById(R.id.edt_valor_despesa);
        edtData = findViewById(R.id.edt_data_despesa);
        edtCategoria = findViewById(R.id.edt_categoria_despesa);
        edtData.setText(DateCustom.getCurrentDate());
        MaskFormats.dateMask(edtData);
        recuperarDespesaTotal();
        

        fabDespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarEdtText()) {
                    Movimentacao movimentacao = new Movimentacao(
                            edtData.getText().toString(),
                            edtDescricao.getText().toString(),
                            FireBaseHelper.TIPO_DESPESA,
                            edtCategoria.getText().toString(),
                            Double.parseDouble(edtValorDespesa.getText().toString())
                    );
                    FireBaseHelper.cadastrarMovimentacao(movimentacao,despesaTotal);

                    finish();
                }
            }
        });
    }

    public static void openActivity(Context context) {
        if (thisIntent == null) {
            thisIntent = new Intent(context, NovaDespesaActivity.class);
        }
        context.startActivity(thisIntent);
    }

    private boolean validarEdtText() {
        String textoCategoria = edtCategoria.getText().toString();
        String textoValor = edtValorDespesa.getText().toString();
        String textoDescricao = edtDescricao.getText().toString();
        String textoData = edtData.getText().toString();

        String DEFAULT_ERROR_MSG = "Preencha todos os campos primeiro!";

        if (textoCategoria.isEmpty() || textoValor.isEmpty() || textoDescricao.isEmpty() || textoData.isEmpty()) {
            Toast.makeText(NovaDespesaActivity.this, DEFAULT_ERROR_MSG, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void recuperarDespesaTotal() {
        DatabaseReference userReference = FireBaseHelper.getCurrentUserReference();
        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuario usuario = snapshot.getValue(Usuario.class);
                despesaTotal = usuario.getDespesaTotal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}