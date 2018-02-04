package com.jhblack.brassscalepractice;

import android.util.Log;

/**
 * Created by Jared on 1/15/2018.
 */

public class Scale {
    private String scaleName;
    private Note startingNote;
    private Note currentNote;
    private boolean goingUp = true;
    private int scaleLoc;
    private int[] scaleType;

    public Scale(Note startingNote, int[] scaleType) {
        currentNote = startingNote;
        this.startingNote = startingNote;
        this.scaleType = scaleType;
        scaleLoc = 0;
    }

    public boolean[] getCurrentValves() {
        Log.d("Scale", "scaleLoc: " + scaleLoc);
        return currentNote.getValves();
    }

    public Note getCurrentNote() {
        return currentNote;
    }

    public void reset() {
        currentNote = startingNote;
    }

    public boolean incrementNote() {
        if(scaleLoc < scaleType.length && goingUp) {
            currentNote = Note.getNoteById(currentNote.getValue() + scaleType[scaleLoc]);
            scaleLoc++;
            return true;
        } else if(scaleLoc > 0 && !goingUp)  {
            scaleLoc--;
            currentNote = Note.getNoteById(currentNote.getValue() - scaleType[scaleLoc]);

            return true;
        } else if(scaleLoc == 0 && !goingUp) {
            goingUp = true;
            currentNote = Note.getNoteById(currentNote.getValue() + scaleType[scaleLoc]);
            scaleLoc++;
            return true;
        } else if(scaleLoc == scaleType.length) {
            goingUp = !goingUp;

            scaleLoc--;
            currentNote = Note.getNoteById(currentNote.getValue() - scaleType[scaleLoc]);
            return true;
        }

        return false;
    }

    public String getNoteName() {
        return currentNote.getName();
    }

    public int getCurrentIndex() {
        return scaleLoc;
    }

    public int[] getNoteRawIds() {
        int[] rawIds = new int[scaleType.length + 1];
        Note workingNote = startingNote;
        rawIds[0] = workingNote.getRawId();
        for(int i = 1; i <= scaleType.length; i++) {
            workingNote = Note.getNoteById(workingNote.getValue() + scaleType[i-1]);
            rawIds[i] = workingNote.getRawId();
        }
        return rawIds;
    }
}
