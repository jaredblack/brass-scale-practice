package com.jhblack.brassscalepractice;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
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
        cMaj = new Scale(Note.EB4, ScaleType.MAJOR);
        int[] notes = cMaj.getNoteRawIds();
        noteIds = new int[8];
        noteView = findViewById(R.id.note_display);
        for(int i = 0; i < notes.length; i++) {
            noteIds[i] = soundPool.load(mContext, notes[i], 1);
        }
        wrongId = soundPool.load(mContext, R.raw.wrong, 1);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        valve1 = findViewById(R.id.valve_1);
        valve2 = findViewById(R.id.valve_2);
        valve3 = findViewById(R.id.valve_3);
        updateScoreText();
        valve1.setOnTouchListener(new ValveOnTouchListener(1));
        valve2.setOnTouchListener(new ValveOnTouchListener(2));
        valve3.setOnTouchListener(new ValveOnTouchListener(3));
    }




    public void valveCheck() {
        boolean[] correctValves = cMaj.getCurrentValves();
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
                lastNote = soundPool.play(noteIds[cMaj.getCurrentIndex()], 1, 1, 1, 1, 1f);
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
        if(cMaj.getCurrentIndex() == 7) {
            score += 16;
        } else {
            score += 6;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_start) {
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
                soundPool.stop(lastNote);
                cMaj.reset();
                noteView.setText(R.string.note_display_default);
                firstTime = true;
                score = 0;
            }
        }

        return super.onOptionsItemSelected(item);
    }

}