package com.example.atv05;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class SizePaymentActivity extends AppCompatActivity {

    TextView textViewSaboresSelecionados;
    RadioGroup radioGroupTamanho;
    RadioGroup radioGroupPagamento;
    Button buttonCalcularPedido;

    private static final double PRECO_P = 25.00;
    private static final double PRECO_M = 35.00;
    private static final double PRECO_G = 45.00;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size_payment);

        textViewSaboresSelecionados = findViewById(R.id.textViewSaboresSelecionados);
        radioGroupTamanho = findViewById(R.id.radioGroupTamanho);
        radioGroupPagamento = findViewById(R.id.radioGroupPagamento);
        buttonCalcularPedido = findViewById(R.id.buttonCalcularPedido);

        Bundle extras = getIntent().getExtras();

        final ArrayList<String> sabores;
        int numeroDePizzas = 0; // Variável para armazenar a quantidade de pizzas

        if (extras != null) {
            sabores = extras.getStringArrayList("SABORES_SELECIONADOS");
            numeroDePizzas = extras.getInt("NUM_PIZZAS", 0); // *** RECEBE A QUANTIDADE DE PIZZAS ***
        } else {
            sabores = null;
            numeroDePizzas = 0; // Garante valor padrão se extras for nulo
        }

        if (sabores != null && !sabores.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (String sabor : sabores) {
                sb.append("- ").append(sabor).append("\n");
            }
            textViewSaboresSelecionados.setText(sb.toString().trim());
        } else {
            textViewSaboresSelecionados.setText("Nenhum sabor selecionado");
        }

        // Torna a variável final para poder ser usada na lambda
        final int finalNumeroDePizzas = numeroDePizzas;


        buttonCalcularPedido.setOnClickListener(v -> {
            int selectedTamanhoId = radioGroupTamanho.getCheckedRadioButtonId();
            if (selectedTamanhoId == -1) {
                Toast.makeText(this, "Por favor, selecione o tamanho da pizza.", Toast.LENGTH_SHORT).show();
                return;
            }

            int selectedPagamentoId = radioGroupPagamento.getCheckedRadioButtonId();
            if (selectedPagamentoId == -1) {
                Toast.makeText(this, "Por favor, selecione o método de pagamento.", Toast.LENGTH_SHORT).show();
                return;
            }

            double precoUnitarioPorTamanho = 0.0; // Preço de uma pizza do tamanho escolhido
            String tamanhoSelecionado = "";

            if (selectedTamanhoId == R.id.radioTamanhoP) {
                precoUnitarioPorTamanho = PRECO_P;
                tamanhoSelecionado = "Pequena";
            } else if (selectedTamanhoId == R.id.radioTamanhoM) {
                precoUnitarioPorTamanho = PRECO_M;
                tamanhoSelecionado = "Média";
            } else if (selectedTamanhoId == R.id.radioTamanhoG) {
                precoUnitarioPorTamanho = PRECO_G;
                tamanhoSelecionado = "Grande";
            }

            // *** CALCULA O PREÇO TOTAL: PREÇO POR TAMANHO * QUANTIDADE DE PIZZAS ***
            double precoTotal = precoUnitarioPorTamanho * finalNumeroDePizzas;


            RadioButton selectedPagamentoRadioButton = findViewById(selectedPagamentoId);
            String metodoPagamento = selectedPagamentoRadioButton.getText().toString();

            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            String precoTotalFormatado = decimalFormat.format(precoTotal);

            Intent intent = new Intent(SizePaymentActivity.this, OrderSummaryActivity.class);
            intent.putStringArrayListExtra("SABORES_SELECIONADOS", sabores);
            intent.putExtra("TAMANHO_SELECIONADO", tamanhoSelecionado);
            intent.putExtra("METODO_PAGAMENTO", metodoPagamento);
            intent.putExtra("PRECO_TOTAL", precoTotalFormatado);
            intent.putExtra("NUM_PIZZAS", finalNumeroDePizzas); // *** PASSA A QUANTIDADE DE PIZZAS PARA O RESUMO ***

            startActivity(intent);
        });
    }
}