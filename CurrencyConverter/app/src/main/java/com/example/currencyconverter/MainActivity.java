package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText amount;
    private TextView result;

    private List<String> units;

    private String from, to;

    private Map<String, Double> equal1EUR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        units = new ArrayList<>();
        equal1EUR = new HashMap<>();

        units.add("USD - US dollar");
        equal1EUR.put("USD - US dollar", 1.1832);

        units.add("JPY - Japanese yen");
        equal1EUR.put("JPY - Japanese yen", 123.74);

        units.add("BGN - Bulgarian lev");
        equal1EUR.put("BGN - Bulgarian lev", 1.9558);

        units.add("CZK - Czech koruna");
        equal1EUR.put("CZK - Czech koruna", 27.337);

        units.add("DKK - Danish krone");
        equal1EUR.put("DKK - Danish krone", 7.4406);

        units.add("GBP - Pound sterling");
        equal1EUR.put("GBP - Pound sterling", 0.90718);

        units.add("HUF - Hungarian forint");
        equal1EUR.put("HUF - Hungarian forint", 365.41);

        units.add("PLN - Polish zloty");
        equal1EUR.put("PLN - Polish zloty", 4.5842);

        units.add("RON - Romanian leu");
        equal1EUR.put("RON - Romanian leu", 4.8765);

        units.add("SEK - Swedish krona");
        equal1EUR.put("SEK - Swedish krona", 10.3060);

        units.add("CHF - Swiss franc");
        equal1EUR.put("CHF - Swiss franc", 1.0732);

        units.add("ISK - Icelandic krona");
        equal1EUR.put("ISK - Icelandic krona", 165.30);

        units.add("NOK - Norwegian krone");
        equal1EUR.put("NOK - Norwegian krone", 10.8398);

        units.add("HRK - Croatian kuna");
        equal1EUR.put("HRK - Croatian kuna", 7.5745);

        units.add("RUB - Russian rouble");
        equal1EUR.put("RUB - Russian rouble", 90.5710);

        units.add("TRY - Turkish lira");
        equal1EUR.put("TRY - Turkish lira", 9.6466);

        units.add("AUD - Australian dollar");
        equal1EUR.put("AUD - Australian dollar", 1.6585);

        units.add("BRL - Brazilian real");
        equal1EUR.put("BRL - Brazilian real", 6.6711);

        units.add("CAD - Canadian dollar");
        equal1EUR.put("CAD - Canadian dollar", 1.5589);

        units.add("CNY - Chinese yuan renminbi");
        equal1EUR.put("CNY - Chinese yuan renminbi", 7.9345);

        units.add("HKD - Hong Kong dollar");
        equal1EUR.put("HKD - Hong Kong dollar", 9.1699);

        units.add("IDR - Indonesian rupiah");
        equal1EUR.put("IDR - Indonesian rupiah", 17344.17);

        units.add("ILS - Israeli shekel");
        equal1EUR.put("ILS - Israeli shekel", 4.0021);

        units.add("INR - Indian rupee");
        equal1EUR.put("INR - Indian rupee", 87.1635);

        units.add("KRW - South Korean won");
        equal1EUR.put("KRW - South Korean won", 1332.89);

        units.add("MXN - Mexican peso");
        equal1EUR.put("MXN - Mexican peso", 24.6622);

        units.add("MYR - Malaysian ringgit");
        equal1EUR.put("MYR - Malaysian ringgit", 4.9274);

        units.add("NZD - New Zealand dollar");
        equal1EUR.put("NZD - New Zealand dollar", 1.7639);

        units.add("PHP - Philippine peso");
        equal1EUR.put("PHP - Philippine peso", 57.157);

        units.add("SGD - Singapore dollar");
        equal1EUR.put("SGD - Singapore dollar", 1.6078);

        units.add("THB - Thai baht");
        equal1EUR.put("THB - Thai baht", 36.940);

        units.add("ZAR - South African rand");
        equal1EUR.put("ZAR - South African rand", 19.0620);

        ArrayAdapter<String> from_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, units);
        Spinner from_unit = findViewById(R.id.from_unit);

        from_unit.setAdapter(from_adapter);
        from_unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                from = units.get(i);
                onChange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> to_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, units);
        Spinner to_unit = findViewById(R.id.to_unit);

        to_unit.setAdapter(to_adapter);
        to_unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                to = units.get(i);
                onChange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        amount = findViewById(R.id.amount);
        result = findViewById(R.id.result);

        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                onChange();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void onChange() {
        String currAmount = amount.getText().toString();

        if ("".equals(currAmount) || "-".equals(currAmount)) {
            result.setText(currAmount);
        } else {
            result.setText(String.valueOf(Double.parseDouble(currAmount) * equal1EUR.get(to) / equal1EUR.get(from)));
        }
    }
}