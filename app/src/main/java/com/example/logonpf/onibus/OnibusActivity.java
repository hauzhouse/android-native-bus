package com.example.logonpf.onibus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class OnibusActivity extends AppCompatActivity {

    private EditText edtLinha;
    private EditText edtDistancia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onibus);
        edtLinha = (EditText) findViewById(R.id.edtLinha);
        edtDistancia = (EditText) findViewById(R.id.edtDistancia);
    }

    public void iniciar(View v) {
        Intent start = new Intent(this, OnibusService.class);
        start.putExtra("linha", edtLinha.getText().toString());
        start.putExtra("distancia", edtDistancia.getText().toString());
        startService(start);
        finish();

    }

    public void parar(View v) {
        Intent stop = new Intent(this, OnibusService.class);
        stopService(stop);
    }
}
