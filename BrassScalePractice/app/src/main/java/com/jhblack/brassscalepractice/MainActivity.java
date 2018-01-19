package com.jhblack.brassscalepractice;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private SoundPool soundPool;
    int metId;
    int[] noteIds;
    int wrongId;
    Button valve1;
    Button valve2;
    Button valve3;
    Scale cMaj;
    boolean firstTime = true;
    Timer met;
    int lastNote = 0;
    boolean[] lastValves = new boolean[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        soundPool = new SoundPool.Builder().build();
        metId = soundPool.load(mContext, R.raw.click, 1);

        cMaj = new Scale();
        int[] notes = cMaj.getNoteNames();
        noteIds = new int[8];
        for(int i = 0; i < notes.length; i++) {
            noteIds[i] = soundPool.load(mContext, notes[i], 1);
        }
        wrongId = soundPool.load(mContext, R.raw.wrong, 1);

        valve1 = findViewById(R.id.valve_1);
        valve2 = findViewById(R.id.valve_2);
        valve3 = findViewById(R.id.valve_3);

        valve1.setOnTouchListener(new ValveOnTouchListener(1));
        valve2.setOnTouchListener(new ValveOnTouchListener(2));
        valve3.setOnTouchListener(new ValveOnTouchListener(3));


    }

    public void startMetronome(View view) {

        if (firstTime) {
            met = new Timer();
            TimerTask metTask = new TimerTask() {
                @Override
                public void run() {
                    valveCheck();
                }
            };
            met.schedule(metTask, 0L, 90L);
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
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TextView noteView = findViewById(R.id.note_display);
                    noteView.setText(cMaj.getNoteName());
                }
            });


            valve1.getBackground().clearColorFilter();

            Log.d("MainActivity/valveCheck", "Note " + cMaj.getNoteName() + " is correct");
            soundPool.stop(lastNote);
            lastNote = soundPool.play(noteIds[cMaj.getCurrentNote()], 1, 1, 1, 1, 1f);


            cMaj.incrementNote();
        } else if(!Arrays.equals(valvesPressed, lastValves)) {
            Log.d("boi", "valveCheck: BOId");
            wrongValves(correctValves);
        }

        lastValves = Arrays.copyOf(valvesPressed, 3);
    }

    private void wrongValves(boolean[] correctValves) {
        Log.d("Main/wrongValves", "WRONG!");
        if(correctValves[0])
            valve1.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
        if(correctValves[1])
            valve2.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
        if(correctValves[2])
            valve3.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);

        soundPool.play(wrongId,1,1,1,1,1f);
    }
}