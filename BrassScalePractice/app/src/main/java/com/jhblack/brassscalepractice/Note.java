package com.jhblack.brassscalepractice;

/**
 * Created by Jared on 1/20/2018.
 */

public enum Note implements Fingerings {

    E3(0,TTT, R.raw.e3),F3(1,TFT,R.raw.f3),GB3(2,FTT,R.raw.gb3),
    G3(3,TTF,R.raw.g3),AB3(4,TFF,R.raw.ab3),A3(5,FTF,R.raw.a3),
    BB3(6,FFF,R.raw.bb3),B3(7,TTT,R.raw.b3), C4(8,TFT,R.raw.c4),
    DB4(9,FTT,R.raw.db4),D4(10,TTF,R.raw.d4),EB4(11,TFF,R.raw.eb4),
    E4(12,FTF,R.raw.e4),F4(13,FFF,R.raw.f4),GB4(14,FTT,R.raw.gb4),
    G4(15,TTF,R.raw.g4),AB4(16,TFF,R.raw.ab4),A4(17,FTF,R.raw.a4),
    BB4(18,FFF,R.raw.bb4),B4(19,TTF,R.raw.b4), C5(20,TFF,R.raw.c5),
    DB5(21,FTF,R.raw.db5),D5(22,FFF,R.raw.d5),EB5(23,TFF,R.raw.eb5),
    E5(24,FTF,R.raw.e5),F5(25,FFF,R.raw.f5),GB5(26,FTT,R.raw.gb5),
    G5(27,TTF,R.raw.g5),AB5(28,TFF,R.raw.ab5),A5(29,FTF,R.raw.a5),
    BB5(30,FFF,R.raw.bb5),B5(31,TTF,R.raw.b5), C6(32,TFF,R.raw.c6),
    DB6(33,FTF,R.raw.db6),D6(34,FFF,R.raw.d6),EB6(35,TFF,R.raw.eb6),
    E6(36,FTF,R.raw.e6),F6(37,FFF,R.raw.f6);


    private int value;
    private boolean[] valves;
    private int rawId;

    Note(int value, boolean[] valves, int rawId) {
        this.value = value;
        this.valves = valves;
        this.rawId = rawId;
    }

    public int getValue() {
        return value;
    }

    public boolean[] getValves() {
        return valves;
    }

    public int getRawId() {
        return rawId;
    }

    public Note getNoteById(int id) {
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
            case 35: return E6;
            case 36: return F6;
            default:
                throw new IllegalArgumentException(id + "is not a valid note id!");
        }
    }
}
