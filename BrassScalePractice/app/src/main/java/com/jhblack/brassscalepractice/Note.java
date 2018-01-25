package com.jhblack.brassscalepractice;

/**
 * Created by Jared on 1/20/2018.
 */

public enum Note implements Fingerings {

    E3(0,TTT, R.raw.e3, "E"),F3(1,TFT,R.raw.f3,"F"),GB3(2,FTT,R.raw.gb3,"F#/G\u23E5"),
    G3(3,TTF,R.raw.g3,"G"),AB3(4,TFF,R.raw.ab3,"G#/B\u266D"),A3(5,FTF,R.raw.a3,"A"),
    BB3(6,FFF,R.raw.bb3,"A#/B\u266D"),B3(7,TTT,R.raw.b3,"B"), C4(8,TFT,R.raw.c4,"C"),
    DB4(9,FTT,R.raw.db4,"C#/D\u266D"),D4(10,TTF,R.raw.d4,"D"),EB4(11,TFF,R.raw.eb4,"D#/E\u266D"),
    E4(12,FTF,R.raw.e4,"E"),F4(13,FFF,R.raw.f4,"F"),GB4(14,FTT,R.raw.gb4,"F#/G\u266D"),
    G4(15,TTF,R.raw.g4,"G"),AB4(16,TFF,R.raw.ab4,"G#/A\u266D"),A4(17,FTF,R.raw.a4,"A"),
    BB4(18,FFF,R.raw.bb4,"A#/B\u266D"),B4(19,TTF,R.raw.b4,"B"), C5(20,TFF,R.raw.c5,"C"),
    DB5(21,FTF,R.raw.db5,"C#/D\u266D"),D5(22,FFF,R.raw.d5,"D"),EB5(23,TFF,R.raw.eb5,"D#/E\u266D"),
    E5(24,FTF,R.raw.e5,"E"),F5(25,FFF,R.raw.f5,"F"),GB5(26,FTT,R.raw.gb5,"F#/G\u266D"),
    G5(27,TTF,R.raw.g5,"G"),AB5(28,TFF,R.raw.ab5,"G#/A\u266D"),A5(29,FTF,R.raw.a5,"A"),
    BB5(30,FFF,R.raw.bb5,"A#/B\u266D"),B5(31,TTF,R.raw.b5,"B"), C6(32,TFF,R.raw.c6,"C"),
    DB6(33,FTF,R.raw.db6,"C#/D\u266D"),D6(34,FFF,R.raw.d6,"D"),EB6(35,TFF,R.raw.eb6,"D#/E\u266D"),
    E6(36,FTF,R.raw.e6,"E"),F6(37,FFF,R.raw.f6,"F");


    private int value;
    private boolean[] valves;
    private int rawId;
    private String name;

    Note(int value, boolean[] valves, int rawId, String name) {
        this.value = value;
        this.valves = valves;
        this.rawId = rawId;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public boolean[] getValves() {
        return valves;
    }

    public String getName() {
        return name;
    }

    public int getRawId() {
        return rawId;
    }

    public static Note getNoteById(int id) {
        switch(id) {
            case 0: return E3;
            case 1: return F3;
            case 2: return GB3;
            case 3: return G3;
            case 4: return AB3;
            case 5: return A3;
            case 6: return BB3;
            case 7: return B3;
            case 8: return C4;
            case 9: return DB4;
            case 10: return D4;
            case 11: return EB4;
            case 12: return E4;
            case 13: return F4;
            case 14: return GB4;
            case 15: return G4;
            case 16: return AB4;
            case 17: return A4;
            case 18: return BB4;
            case 19: return B4;
            case 20: return C5;
            case 21: return DB5;
            case 22: return D5;
            case 23: return EB5;
            case 24: return E5;
            case 25: return F5;
            case 26: return GB5;
            case 27: return G5;
            case 28: return AB5;
            case 29: return A5;
            case 30: return BB5;
            case 31: return B5;
            case 32: return C6;
            case 33: return DB6;
            case 34: return D6;
            case 35: return EB6;
            case 36: return E6;
            case 37: return F6;
            default:
                throw new IllegalArgumentException(id + "is not a valid note id!");
        }
    }
}
