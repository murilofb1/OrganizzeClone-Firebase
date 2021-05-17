package com.murilofb.organizzeclone.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.murilofb.organizzeclone.adapters.AdapterMovimentacao;
import com.murilofb.organizzeclone.helpers.FireBaseHelper;
import com.murilofb.organizzeclone.helpers.NumberFormatHelper;
import com.murilofb.organizzeclone.models.Movimentacao;
import com.murilofb.organizzeclone.JA_FOI.Usuario;
import com.murilofb.organizzeclone.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;


import java.util.ArrayList;
import java.util.List;

public class MainScreenActivity extends AppCompatActivity {


    private static Intent thisActivity;
    private static double saldoAtual;

    private final static String msgPadrao = "Olá ";

    private static com.github.clans.fab.FloatingActionButton fabDespesas;
    private static com.github.clans.fab.FloatingActionButton fabReceitas;
    private TextView txtMSG;
    private TextView txtSaldo;
    private TextView txtMsgSaldo;
    private MaterialCalendarView calendarView;
    private RecyclerView recyclerView;
    private String mesAno;
    private List<Movimentacao> movList;
    private AdapterMovimentacao adapterMovimentacao;
    private Usuario currentUser;
    //Listeners
    // Click
    private View.OnClickListener fabClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.fab_despesas) {
                NovaDespesaActivity.openActivity(MainScreenActivity.this);
            } else {
                NovaReceitaActivity.openActivity(MainScreenActivity.this);
            }
        }
    };
    // Month Changed
    private OnMonthChangedListener recyclerhandler = new OnMonthChangedListener() {
        @Override
        public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
            String mesConvertido = NumberFormatHelper.twoDigitFormat(date.getMonth() + 1);
            mesAno = mesConvertido + date.getYear();
            FireBaseHelper.getUserTransactionsReference().child(mesAno).removeEventListener(valueEventListenerRecycler);
            atualizarRecycler();

        }
    };
    // Value Event
    private ValueEventListener valueEventListenerUser;
    private ValueEventListener valueEventListenerRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        configurarActionBar();
        inicializarComponentes();
        setListeners();

    }

    @Override
    protected void onStart() {
        super.onStart();
        carregarDados();
        atualizarRecycler();
    }

    @Override
    protected void onStop() {
        super.onStop();
        FireBaseHelper.getCurrentUserReference().removeEventListener(valueEventListenerUser);
        FireBaseHelper.getUserTransactionsReference().child(mesAno).removeEventListener(valueEventListenerRecycler);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_sair) {
            FireBaseHelper.deslogar();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public static void openActivity(Context context) {
        if (thisActivity == null) {
            thisActivity = new Intent(context, MainScreenActivity.class);
        }
        context.startActivity(thisActivity);
    }

    private void carregarDados() {
        valueEventListenerUser = FireBaseHelper.getCurrentUserReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ///////

                currentUser = snapshot.getValue(Usuario.class);
                double receitas = currentUser.getReceitaTotal();
                double despesas = currentUser.getDespesaTotal();
                saldoAtual = receitas - despesas;
                String valorConvertido = NumberFormatHelper.formatToCurrency(saldoAtual);
                txtSaldo.setText(valorConvertido);
                txtMSG.setText(msgPadrao + currentUser.getFirstName());
                txtMsgSaldo.setText(getString(R.string.txt_saldoGeral));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void inicializarComponentes() {
        fabDespesas = findViewById(R.id.fab_despesas);
        fabReceitas = findViewById(R.id.fab_receitas);
        txtSaldo = findViewById(R.id.txt_saldo);
        txtMSG = findViewById(R.id.txt_msg_ola);
        txtMsgSaldo = findViewById(R.id.txt_saldo_geral);
        calendarView = findViewById(R.id.material_calendar);
        String[] nomeMesesPTBR = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        calendarView.setTitleMonths(nomeMesesPTBR);

        CalendarDay calendarDay = calendarView.getCurrentDate();
        String mesConvertido = NumberFormatHelper.twoDigitFormat((calendarDay.getMonth() + 1));
        movList = new ArrayList<>();
        mesAno = mesConvertido + calendarDay.getYear();


        recyclerView = findViewById(R.id.recycler_movimentacoes);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);
        adapterMovimentacao = new AdapterMovimentacao(MainScreenActivity.this, movList);
        recyclerView.setAdapter(adapterMovimentacao);
        swipe();

    }

    private void setListeners() {
        fabReceitas.setOnClickListener(fabClick);
        fabDespesas.setOnClickListener(fabClick);
        calendarView.setOnMonthChangedListener(recyclerhandler);
    }

    private void atualizarRecycler() {

        valueEventListenerRecycler = FireBaseHelper.getUserTransactionsReference().child(mesAno).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                movList.clear();
                for (DataSnapshot dados : snapshot.getChildren()) {
                    Movimentacao mov = dados.getValue(Movimentacao.class);
                    mov.setKey(dados.getKey());
                    movList.add(mov);
                }
                adapterMovimentacao.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void configurarActionBar() {
        getSupportActionBar().setTitle("");
        getSupportActionBar().setElevation(0);
    }

    private void swipe() {
        ItemTouchHelper.Callback itemTouch = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.ACTION_STATE_IDLE;
                int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                excluirMovimentacao(viewHolder);
            }
        };
        new ItemTouchHelper(itemTouch).attachToRecyclerView(recyclerView);
    }

    private void excluirMovimentacao(RecyclerView.ViewHolder viewHolder) {
        int position = viewHolder.getAdapterPosition();
        final Movimentacao mov = movList.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainScreenActivity.this);
        builder.setTitle("Confirmar Exclusão");
        builder.setMessage("Deseja excluir o item do dia: " + mov.getData());
        builder.setCancelable(false);
        builder.setPositiveButton("YEP", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String movkey = mov.getKey();
                FireBaseHelper.getUserTransactionsReference().child(mesAno).child(movkey).removeValue();
                Toast.makeText(MainScreenActivity.this, "Removido com sucesso", Toast.LENGTH_SHORT).show();
                if (mov.getTipo().equals("r")) {
                    double valorReajustado = currentUser.getReceitaTotal() - mov.getValor();
                    FireBaseHelper.alterarReceita(valorReajustado);
                } else {
                    double valorReajustado = currentUser.getDespesaTotal() - mov.getValor();
                    FireBaseHelper.alterarDespesa(valorReajustado);
                }

                adapterMovimentacao.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("NOPS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainScreenActivity.this, "Exclusão cancelada", Toast.LENGTH_SHORT).show();
                adapterMovimentacao.notifyDataSetChanged();
            }
        });
        builder.create();
        builder.show();

    }
}