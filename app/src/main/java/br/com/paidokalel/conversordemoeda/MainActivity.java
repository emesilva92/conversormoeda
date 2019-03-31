package br.com.paidokalel.conversordemoeda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SeekBar seekbarMoedaFinal;
    private TextView textSeekbar;
    private int posicaoSeekbarMoedaFinal;
    String stringTextSeekbar;

    List<Moeda> moedas = new ArrayList<>();

    private Spinner spinnerMoedaInicial;
    Moeda moedaInicialSelecionada = new Moeda();
    Moeda moedaFinalSelecionada = new Moeda();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //iniciando variáveis
        spinnerMoedaInicial = findViewById(R.id.spinnerMoedaInicial);
        seekbarMoedaFinal = findViewById(R.id.seekBarMoedaFinal);
        seekbarMoedaFinal.setMin(0);
        textSeekbar = findViewById(R.id.textSeekbar);

        //Alimentando List<Moedas>
        moedas.add(new Moeda(1, "Bolívar Soberano Venezuelano", "Bs S", "VES", 0.0003));
        moedas.add(new Moeda(2, "Dólar dos Estados Unidos", "$", "USD", 1));
        moedas.add(new Moeda(3, "Euro", "€", "EUR", 1.1073));
        moedas.add(new Moeda(4, "Iene Japonês", "¥", "JPY", 0.0089));
        moedas.add(new Moeda(5, "Libra Esterlina", "£", "GBP", 1.2905));
        moedas.add(new Moeda(6, "Peso Argentino", "$", "ARS", 0.0228));
        moedas.add(new Moeda(7, "Real", "R$", "BRL", 0.252));
        moedas.add(new Moeda(8, "Rupia Indiana", "₹", "INR", 0.014));
        moedas.add(new Moeda(9, "Won Sul-Coreano", "₩", "KRW", 0.000869));

        //tamanho máximo do seekbar igual ao tamanho da lista moedas
        seekbarMoedaFinal.setMax(moedas.size()-1);
        stringTextSeekbar = "Moeda final: " + moedas.get(posicaoSeekbarMoedaFinal);
        textSeekbar.setText(stringTextSeekbar);

        //Alimentando array de Moedas para o spinner
        String[] arrayMoedas = new String[moedas.size()];
        for (int i = 0; i < moedas.size(); i++)
            arrayMoedas[i] = moedas.get(i).toString();

        //Alimentando Spinner
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_item, arrayMoedas);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMoedaInicial.setAdapter(adaptador);

        seekbarMoedaFinal.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int posicao, boolean fromUser) {
                posicaoSeekbarMoedaFinal = posicao;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
             }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                stringTextSeekbar = "Moeda final: " + moedas.get(posicaoSeekbarMoedaFinal);
                textSeekbar.setText(stringTextSeekbar);
             }
        });
    }

    @Override
    public void onClick(View v) {
        //Valor Moeda Inicial
        EditText editValorMoedaInicial = findViewById(R.id.editValorInicial);

        //Tratamento valor vazio
        if(editValorMoedaInicial.getText().toString().isEmpty()){
            Toast.makeText(this, "O valor inicial é obrigatório.", Toast.LENGTH_SHORT).show();
        } else{
            double valorMoedaInicial = Double.parseDouble(editValorMoedaInicial.getText().toString());

            //MoedaInicial
            moedaInicialSelecionada = moedas.get(spinnerMoedaInicial.getSelectedItemPosition());

            //MoedaFinal
            moedaFinalSelecionada = moedas.get(posicaoSeekbarMoedaFinal);

            //Calculo
            double taxaRelativa = moedaInicialSelecionada.getValorEmUSD()/moedaFinalSelecionada.getValorEmUSD();
            double resultado = valorMoedaInicial*taxaRelativa;
            DecimalFormat formatarDouble = new DecimalFormat("#.00");
            String stringResultado;
            if (valorMoedaInicial!=1){
                String resultadoFormatado = formatarDouble.format(resultado);
                stringResultado = moedaInicialSelecionada.getSimbolo() + " " + valorMoedaInicial
                        +" = " + moedaFinalSelecionada.getSimbolo() + " " + resultadoFormatado;
            }else {
                stringResultado = moedaInicialSelecionada.getSimbolo() + " " + valorMoedaInicial
                        +" = " + moedaFinalSelecionada.getSimbolo() + " " + resultado;
            }
            //Mostra resultado
            TextView viewInfo = findViewById(R.id.textInformacaoResultado);
            TextView viewResultado = findViewById(R.id.textResultado);
            String stringInfoResultado = "Moeda inicial: "+moedaInicialSelecionada.getNome() + "\r\n" +
                    "Valor da moeda inicial: " + valorMoedaInicial + "\r\n" +
                    "Moeda final: " + moedaFinalSelecionada.getNome();
            viewInfo.setText(stringInfoResultado);
            viewResultado.setText(stringResultado);
        }
    }
}
