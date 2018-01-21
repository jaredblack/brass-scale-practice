package com.jhblack.brassscalepractice;

import android.util.Log;

/**
 * Created by Jared on 1/15/2018.
 */

public class Scale {
    private boolean[][] scaleArr;
    private String scaleName;
    private int currentNote;
    private boolean goingUp = true;

    public Scale(Note startingNote, ScaleType scaleType) {
        currentNote = 0;
        scaleArr = new boolean[8][3];
    }

    public boolean[] getNextNote() {
        return scaleArr[currentNote];

    }

    public int getCurrentNote() {
        return currentNote;
    }

    public void reset() {
        currentNote = 0;
    }

    public void incrementNote() {
        if(goingUp && currentNote < 7)
            currentNote++;
        else if(!goingUp && currentNote > 0)
            currentNote--;
        else {
            goingUp = !goingUp;
            if(goingUp) currentNote++;
            else currentNote--;
        }
    }

    public String getNoteName() {
        switch(currentNote) {
            case 0: return "C";
            case 1: return "D";
            case 2: return "E";
            case 3: return "F";
            case 4: return "G";
            case 5: return "A";
            case 6: return "B";
            case 7: return "C";
        }
        return "L#";
    }

    public int[] getNoteNames() {
        int[] noteFiles = new int[8];
        noteFiles[0] = R.raw.bb3;
        noteFiles[1] = R.raw.c4;
        noteFiles[2] = R.raw.d4;
        noteFiles[3] = R.raw.eb4;
        noteFiles[4] = R.raw.f4;
        noteFiles[5] = R.raw.g4;
        noteFiles[6] = R.raw.a4;
        noteFiles[7] = R.raw.bb4;
        return noteFiles;
    }
}
