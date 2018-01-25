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

    public Scale(Note startingNote, int[] scaleType) {
        currentNote = 0;
        scaleArr = new boolean[scaleType.length][3];
        int noteId = startingNote.getValue();
        for(int r = 0; r < scaleArr.length; r++) {
            scaleArr[r] = Note.getNoteById(noteId).getValves();
            noteId += scaleType[r];
        }
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
        return Note.getNoteById(currentNote).getName();
    }

    public int[] getNoteRawIds() {
        return
    }
}
