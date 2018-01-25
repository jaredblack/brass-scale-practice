package com.jhblack.brassscalepractice;

import android.util.Log;

/**
 * Created by Jared on 1/15/2018.
 */

public class Scale
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
        return currentNote.getValves();

    }

    public Note getCurrentNote() {
        return currentNote;
    }

    public void reset() {
        currentNote = startingNote;
    }

    public void incrementNote() {
        if(scaleLoc < scaleType.length && goingUp) {
            currentNote = Note.getNoteById(currentNote.getValue() + scaleType[scaleLoc]);
            scaleLoc++;
        } else if(scaleLoc >= 0 && )
    }

    public String getNoteName() {
//        switch(currentNote) {
//            case 0: return "C";
//            case 1: return "D";
//            case 2: return "E";
//            case 3: return "F";
//            case 4: return "G";
//            case 5: return "A";
//            case 6: return "B";
//            case 7: return "C";
//        }
//        return "L#";
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
