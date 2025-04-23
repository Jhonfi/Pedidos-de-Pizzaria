package com.example.atv05;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    CheckBox checkBoxCalabresa, checkBoxMarguerita, checkBoxPortuguesa, checkBoxMussarela, checkBoxQuatroQueijos;
    Button buttonProximaEtapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBoxCalabresa = findViewById(R.id.checkBoxCalabresa);
        checkBoxMarguerita = findViewById(R.id.checkBoxMarguerita);
        checkBoxPortuguesa = findViewById(R.id.checkBoxPortuguesa);
        checkBoxMussarela = findViewById(R.id.checkBoxMussarela);
        checkBoxQuatroQueijos = findViewById(R.id.checkBoxQuatroQueijos);
        buttonProximaEtapa = findViewById(R.id.buttonProximaEtapa);

        buttonProximaEtapa.setOnClickListener(v -> {
            ArrayList<String> saboresSelecionados = new ArrayList<>();

            if (checkBoxCalabresa.isChecked()) {
                saboresSelecionados.add(checkBoxCalabresa.getText().toString());
            }
            if (checkBoxMarguerita.isChecked()) {
                saboresSelecionados.add(checkBoxMarguerita.getText().toString());
            }
            if (checkBoxPortuguesa.isChecked()) {
                saboresSelecionados.add(checkBoxPortuguesa.getText().toString());
            }
            if (checkBoxMussarela.isChecked()) {
                saboresSelecionados.add(checkBoxMussarela.getText().toString());
            }
            if (checkBoxQuatroQueijos.isChecked()) {
                saboresSelecionados.add(checkBoxQuatroQueijos.getText().toString());
            }

            if (saboresSelecionados.isEmpty()) {
                Toast.makeText(this, "Por favor, selecione pelo menos um sabor.", Toast.LENGTH_SHORT).show();
            } else {
                int numeroDePizzas = saboresSelecionados.size();

                Intent intent = new Intent(MainActivity.this, SizePaymentActivity.class);
                intent.putStringArrayListExtra("SABORES_SELECIONADOS", saboresSelecionados);
                intent.putExtra("NUM_PIZZAS", numeroDePizzas); // Passa a quantidade de pizzas
                startActivity(intent);
            }
        });
    }
}