package com.jhblack.brassscalepractice;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private SoundPool soundPool;
    private int metId;
    private int[] noteIds;
    private int wrongId;
    private Button valve1;
    private Button valve2;
    private Button valve3;
    private Scale cMaj;
    private boolean firstTime = true;
    private Timer met;
    private int lastNote = 0;
    private boolean[] lastValves = new boolean[3];
    private Button startButton;
    private TextView noteView;
    private boolean lastWasWrong = false;
    private int score = 0;
    private boolean firstOffense = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        soundPool = new SoundPool.Builder().build();
        metId = soundPool.load(mContext, R.raw.click, 1);
        startButton = findViewById(R.id.start_button);
        cMaj = new Scale();
        int[] notes = cMaj.getNoteNames();
        noteIds = new int[8];
        noteView = findViewById(R.id.note_display);
        for(int i = 0; i < notes.length; i++) {
            noteIds[i] = soundPool.load(mContext, notes[i], 1);
        }
        wrongId = soundPool.load(mContext, R.raw.wrong, 1);

        valve1 = findViewById(R.id.valve_1);
        valve2 = findViewById(R.id.valve_2);
        valve3 = findViewById(R.id.valve_3);
        updateScoreText();
        valve1.setOnTouchListener(new ValveOnTouchListener(1));
        valve2.setOnTouchListener(new ValveOnTouchListener(2));
        valve3.setOnTouchListener(new ValveOnTouchListener(3));
//        Spinner spinner = findViewById(R.id.note_selector);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.note_names, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);

    }

    public void start(View view) {

        if (firstTime) {
            met = new Timer();
            TimerTask metTask = new TimerTask() {
                @Override
                public void run() {
                    valveCheck();
                }
            };
            met.schedule(metTask, 0L, 90L);
            startButton.setText(R.string.stop_state);
            firstTime = false;
        } else {
            met.cancel();
            soundPool.stop(lastNote);
            cMaj.reset();
            startButton.setText(R.string.start_state);
            noteView.setText(R.string.note_display_default);
            firstTime = true;
            score = 0;
        }
    }

    public void valveCheck() {
        boolean[] correctValves = cMaj.getNextNote();
        boolean[] valvesPressed = ValveOnTouchListener.getValvesPressed();

            if (Arrays.equals(correctValves, valvesPressed)) {
                String currentNoteName = cMaj.getNoteName();

                if(lastWasWrong) {
                    valve1.getBackground().clearColorFilter();
                    valve2.getBackground().clearColorFilter();
                    valve3.getBackground().clearColorFilter();
                } else {
                    incrementScore();
                }
                updateScoreText();

                Log.d("MainActivity/valveCheck", "Note " + currentNoteName + " is correct");
                soundPool.stop(lastNote);
                lastNote = soundPool.play(noteIds[cMaj.getCurrentNote()], 1, 1, 1, 1, 1f);
                cMaj.incrementNote();
                lastWasWrong = false;
            } else if(!Arrays.equals(valvesPressed, lastValves)){

                wrongValves(correctValves);

            }
        lastValves = Arrays.copyOf(valvesPressed, 3);
    }

    private void updateScoreText() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                noteView.setText(cMaj.getNoteName());
                Resources res = getResources();
                TextView scoreView = findViewById(R.id.score_view);
                String scoreText = res.getString(R.string.score, score);
                scoreView.setText(scoreText);
            }
        });
    }

    private boolean wrongValves(boolean[] correctValves) {
        Log.d("Main/wrongValves", "WRONG!");
        if(firstOffense) {
            firstOffense = false;
            Log.d("MainActivity", "wrongValves: first offense");

            return false;
        } else {
            if (correctValves[0])
                valve1.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            if (correctValves[1])
                valve2.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            if (correctValves[2])
                valve3.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            soundPool.play(wrongId, 1, 1, 1, 1, 1f);
            firstOffense = true;
            lastWasWrong = true;
            Log.d("MainActivity", "wrongValves: second offense");
            return true;
        }

    }

    private void incrementScore() {
        if(cMaj.getCurrentNote() == 7) {
            score += 16;
        } else {
            score += 6;
        }
    }


}