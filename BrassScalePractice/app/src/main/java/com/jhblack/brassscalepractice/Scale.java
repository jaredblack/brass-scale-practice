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

        return Note.getNoteById(currentNote).getName();

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

    public int[] getNoteRawIds() {
        return
    }
}
