package com.example.logonpf.onibus;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;

public class OnibusService extends Service {

    private Timer timer;
    private OnibusTask task;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("ONIBUS", "SERVICO INICIADO!!!");
        timer = new Timer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String linha = intent.getExtras().getString("linha");
        String distancia = intent.getExtras().getString("distancia");
        Log.i("ONIBUS", "PROCESSANDO... Linha = " +
                linha + ", dist = " + distancia);
        task = new OnibusTask(linha, distancia, getBaseContext());
        timer.schedule(task, 0, 5 * 1000);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i("ONIBUS", "SERVICO FINALIZADO!!!");
        timer.cancel();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
