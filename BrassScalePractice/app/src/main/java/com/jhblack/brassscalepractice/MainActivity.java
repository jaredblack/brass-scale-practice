package com.jhblack.brassscalepractice;

import android.content.Context;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private SoundPool soundPool;
    int metId;
    Button valve1;
    Button valve2;
    Button valve3;

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
    }

    public void startMetronome(View view) {
        Timer met = new Timer();
        TimerTask metTask = new TimerTask() {

            @Override
            public void run() {
                soundPool.play(metId, 1, 1, 1, 0, 1f);
                boolean[] valves = ValveOnTouchListener.getValvesPressed();
                if(valves[1] && valves[2] && valves[3]) {
                    Log.d("MainActivity", "You Are a Good Boi");
                } else {
                    Log.d("MainActivity", "Bad Valves!");
                }
            }
        };
        met.schedule(metTask, 0L, 600L);
    }
}
