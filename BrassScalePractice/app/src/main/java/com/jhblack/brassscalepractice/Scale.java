package com.jhblack.brassscalepractice;

import android.util.Log;

/**
 * Created by Jared on 1/15/2018.
 */

public class Scale {
    private boolean[][] scaleArr;
    private String scaleName;
    int currentNote;
    boolean goingUp = true;

    public Scale() {
        currentNote = 0;
        scaleArr = new boolean[8][3];

        scaleArr[0][0] = false;
        scaleArr[0][1] = false;
        scaleArr[0][2] = false;

        scaleArr[1][0] = true;
        scaleArr[1][1] = false;
        scaleArr[1][2] = true;

        scaleArr[2][0] = true;
        scaleArr[2][1] = true;
        scaleArr[2][2] = false;

        scaleArr[3][0] = true;
        scaleArr[3][1] = false;
        scaleArr[3][2] = false;

        scaleArr[4][0] = false;
        scaleArr[4][1] = false;
        scaleArr[4][2] = false;

        scaleArr[5][0] = true;
        scaleArr[5][1] = true;
        scaleArr[5][2] = false;

        scaleArr[6][0] = false;
        scaleArr[6][1] = true;
        scaleArr[6][2] = false;

        scaleArr[7][0] = false;
        scaleArr[7][1] = false;
        scaleArr[7][2] = false;
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
