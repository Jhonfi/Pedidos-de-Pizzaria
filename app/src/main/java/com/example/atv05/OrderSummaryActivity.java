package com.example.atv05;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class OrderSummaryActivity extends AppCompatActivity {

    TextView textViewResumoCompleto;
    Button buttonNovoPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        textViewResumoCompleto = findViewById(R.id.textViewResumoCompleto);
        buttonNovoPedido = findViewById(R.id.buttonNovoPedido);

        Bundle extras = getIntent().getExtras();
        int numberOfPizzas = 0; // Variável para a quantidade de pizzas
        ArrayList<String> sabores = null;
        String tamanho = null;
        String pagamento = null;
        String precoTotal = null;

        if (extras != null) {
            numberOfPizzas = extras.getInt("NUM_PIZZAS", 0); // Recebe a quantidade de pizzas
            sabores = extras.getStringArrayList("SABORES_SELECIONADOS");
            tamanho = extras.getString("TAMANHO_SELECIONADO");
            pagamento = extras.getString("METODO_PAGAMENTO");
            precoTotal = extras.getString("PRECO_TOTAL");
        }

        StringBuilder resumo = new StringBuilder();
        resumo.append("Resumo do Pedido:\n\n");

        if (numberOfPizzas > 0) {
            resumo.append("Quantidade: ").append(numberOfPizzas).append(" pizza(s)\n");
        } else {
            resumo.append("Quantidade: Não especificada\n");
        }
        resumo.append("\n");


        if (sabores != null && !sabores.isEmpty()) {
            resumo.append("Sabores:\n");
            for (String sabor : sabores) {
                resumo.append("- ").append(sabor).append("\n");
            }
            resumo.append("\n");
        } else {
            resumo.append("Sabores: Nenhum selecionado\n\n");
        }

        if (tamanho != null && !tamanho.isEmpty()) {
            resumo.append("Tamanho: ").append(tamanho).append("\n");
        } else {
            resumo.append("Tamanho: Não especificado\n");
        }

        if (pagamento != null && !pagamento.isEmpty()) {
            resumo.append("Pagamento: ").append(pagamento).append("\n");
        } else {
            resumo.append("Pagamento: Não especificado\n");
        }

        if (precoTotal != null && !precoTotal.isEmpty()) {
            resumo.append("\nTotal: R$ ").append(precoTotal);
        } else {
            resumo.append("\nTotal: Não calculado");
        }

        textViewResumoCompleto.setText(resumo.toString());

        buttonNovoPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderSummaryActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}