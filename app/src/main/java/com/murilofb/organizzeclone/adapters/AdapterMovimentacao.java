package com.murilofb.organizzeclone.adapters;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;


import com.murilofb.organizzeclone.helpers.NumberFormatHelper;
import com.murilofb.organizzeclone.models.Movimentacao;
import com.murilofb.organizzeclone.R;


import java.util.List;


public class AdapterMovimentacao extends RecyclerView.Adapter<AdapterMovimentacao.MyViewHolder> {

    List<Movimentacao> movimentacaoList;
    Context context;

    public AdapterMovimentacao(Context context, List<Movimentacao> movList) {
        this.movimentacaoList = movList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_movimentacao_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movimentacao mov = movimentacaoList.get(position);
        String valorVoncertido = NumberFormatHelper.formatToCurrency(mov.getValor());
        @ColorInt final int COLOR_RECEITA = 0xFF73B613;
        holder.txtRecyclerValor.setTextColor(COLOR_RECEITA);
        if (mov.getTipo().equals("d")) {
            @ColorInt final int COLOR_DESPESA = 0xFFB8251A;
            holder.txtRecyclerValor.setTextColor(COLOR_DESPESA);
            holder.txtRecyclerValor.setText("- " + valorVoncertido);
        }
        holder.txtRecyclerCategoria.setText(mov.getCategoria());
        holder.txtRecyclerDescricao.setText(mov.getDescricao());
        holder.txtRecyclerValor.setText(valorVoncertido);


    }

    @Override
    public int getItemCount() {
        return movimentacaoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtRecyclerValor;
        TextView txtRecyclerDescricao;
        TextView txtRecyclerCategoria;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtRecyclerValor = itemView.findViewById(R.id.txt_valor_recycler);
            txtRecyclerDescricao = itemView.findViewById(R.id.txt_descricao_recycler);
            txtRecyclerCategoria = itemView.findViewById(R.id.txt_categoria_recycler);
        }
    }
}
