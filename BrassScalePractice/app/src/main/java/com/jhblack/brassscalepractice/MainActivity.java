package com.jhblack.brassscalepractice;

import android.content.Context;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private SoundPool soundPool;
    int metId;
    Button valve1;
    Button valve2;
    Button valve3;
    Scale cMaj;
    boolean firstTime = true;
    Timer met;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        soundPool = new SoundPool.Builder().build();
        metId = soundPool.load(mContext, R.raw.click, 1);

        valve1 = findViewById(R.id.valve_1);
        valve2 = findViewById(R.id.valve_2);
        valve3 = findViewById(R.id.valve_3);

        valve1.setOnTouchListener(new ValveOnTouchListener(1));
        valve2.setOnTouchListener(new ValveOnTouchListener(2));
        valve3.setOnTouchListener(new ValveOnTouchListener(3));

        cMaj = new Scale();
    }

    public void startMetronome(View view) {

        if (firstTime) {
            met = new Timer();
            TimerTask metTask = new TimerTask() {
                @Override
                public void run() {
                    //soundPool.play(metId, 1, 1, 1, 0, 1f);
                    valveCheck();
                }
            };
            met.schedule(metTask, 0L, 60L);
            firstTime = false;
        } else {
            met.cancel();
            firstTime = true;
        }
    }

    public void valveCheck() {

        boolean[] correctValves = cMaj.getNextNote();
        boolean[] valvesPressed = ValveOnTouchListener.getValvesPressed();
        if(Arrays.equals(correctValves, valvesPressed)){
            Log.d("MainActivity/valveCheck", "Note " + cMaj.getNoteName() + " is correct");
            cMaj.incrementNote();

        }
    }
}
