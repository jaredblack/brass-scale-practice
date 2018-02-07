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
import android.view.View;
import android.widget.AdapterView;
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
    private Scale scale;
    private boolean firstTime = true;
    private Timer met;
    private int lastNote = 0;
    private boolean[] lastValves = new boolean[3];
    private TextView noteView;
    private boolean lastWasWrong = false;
    private int score = 0;
    private boolean firstOffense = true;
    private int[] currentScaleMode;
    private Note currentNoteSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        soundPool = new SoundPool.Builder().build();
        metId = soundPool.load(mContext, R.raw.click, 1);
        currentScaleMode = ScaleType.MAJOR;
        setNewScale(Note.C4, ScaleType.MAJOR);

        noteView = findViewById(R.id.note_display);


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
        boolean[] correctValves = scale.getCurrentValves();
        boolean[] valvesPressed = ValveOnTouchListener.getValvesPressed();

            if (Arrays.equals(correctValves, valvesPressed)) {
                String currentNoteName = scale.getNoteName();

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
                lastNote = soundPool.play(noteIds[scale.getCurrentIndex()], 1, 1, 1, 1, 1f);
                if(!scale.incrementNote())
                    stop();
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
                noteView.setText(scale.getNoteName());
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
        if(scale.getCurrentIndex() == 7) {
            score += 16;
        } else {
            score += 6;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);


        MenuItem noteItem = menu.findItem(R.id.note_spinner);
        Spinner noteSpinner = (Spinner) noteItem.getActionView();
        ArrayAdapter<CharSequence> noteAdapter = ArrayAdapter.createFromResource(this,
                R.array.note_names, android.R.layout.simple_spinner_item);
        noteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        noteSpinner.setAdapter(noteAdapter);
        noteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String noteSelectedStr = adapterView.getItemAtPosition(i).toString();
                Log.d("OnItemSelectedListener", noteSelectedStr);

                switch(noteSelectedStr) {
                    case "C":
                        currentNoteSelected = Note.C4;
                        break;
                    case "G":
                        currentNoteSelected = Note.G4;
                        break;
                    case "D":
                        currentNoteSelected = Note.D4;
                        break;
                    case "A":
                        currentNoteSelected = Note.A3;
                        break;
                    case "E":
                        currentNoteSelected = Note.E4;
                        break;
                    case "G\u266D":
                        currentNoteSelected = Note.GB4;
                        break;
                    case "D\u266D":
                        currentNoteSelected = Note.DB4;
                        break;
                    case "A\u266D":
                        currentNoteSelected = Note.AB4;
                        break;
                    case "E\u266D":
                        currentNoteSelected = Note.EB4;
                        break;
                    case "B\u266D":
                        currentNoteSelected = Note.BB3;
                        break;
                    case "F":
                        currentNoteSelected = Note.F4;
                        break;
                }
                clearNoteIds();
                setNewScale(currentNoteSelected, currentScaleMode);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //NOTHING FOR NOW
            }
        });


        MenuItem typeItem = menu.findItem(R.id.type_spinner); //fidget_spinner
        Spinner typeSpinner = (Spinner) typeItem.getActionView();
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this,
                R.array.scale_types, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String typeSelectedStr = adapterView.getItemAtPosition(i).toString();

                Log.d("OnItemSelectedListener", typeSelectedStr);

                switch(typeSelectedStr) {
                    case "Major":
                        currentScaleMode = ScaleType.MAJOR;
                        break;
                    case "Dorian":
                        currentScaleMode = ScaleType.DORIAN;
                        break;
                    case "Mixolydian":
                        currentScaleMode = ScaleType.MIXOLYDIAN;
                        break;
                    case "Bebop":
                        currentScaleMode = ScaleType.BEBOP;
                }

                clearNoteIds();
                setNewScale(currentNoteSelected, currentScaleMode);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return true;
    }

    private void setNewScale(Note noteSelected, int[] currentScaleMode) {

        scale = new Scale(noteSelected, currentScaleMode);
        int[] notes = scale.getNoteRawIds();
        noteIds = new int[notes.length];
        for(int i = 0; i < notes.length; i++) {
            noteIds[i] = soundPool.load(mContext, notes[i], 1);
        }
    }

    private void clearNoteIds() {
        for(int i : noteIds) {
            soundPool.unload(i);
        }

        noteIds = new int[noteIds.length];
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
                stop();
            }
        }

        return super.onOptionsItemSelected(item);
    }


    private void stop() {
        met.cancel();
        soundPool.stop(lastNote);
        scale.reset();
        noteView.setText(R.string.note_display_default);
        firstTime = true;
        score = 0;
    }
}