package com.example.logonpf.onibus;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.TimerTask;

public class OnibusTask extends TimerTask{

    private String linha;
    private String distancia;
    private Context ctx;

    public OnibusTask(String linha, String distancia, Context ctx) {
        this.linha = linha;
        this.distancia = distancia;
        this.ctx = ctx;
    }

    @Override
    public void run() {

        Log.i("ONIBUS", "CONSULTANDO SERVICO" +
                " - LINHA = " + linha + "" +
                " - DIST = " + distancia + " - " + OnibusServer.getDistancia());

        try {
            int dist = Integer.parseInt(distancia);
            JSONArray dados = new JSONArray(OnibusServer.getDistancia());
            for (int i = 0; i < dados.length(); i++) {
                JSONObject d = dados.getJSONObject(i);
                if (d.getString("linha").equals(linha) &&
                        d.getInt("distancia") <= dist) {

                    Intent ret = new Intent(this.ctx, OnibusActivity.class);
                    ret.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    this.ctx.startActivity(ret);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
