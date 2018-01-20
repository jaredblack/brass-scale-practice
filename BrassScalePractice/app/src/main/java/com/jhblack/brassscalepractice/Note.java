package com.jhblack.brassscalepractice;

/**
 * Created by Jared on 1/20/2018.
 */

public enum Note implements Fingerings{

//
//    private final boolean[] ttt = {true,true,true};
//    private final boolean[] tft = {true,false,true};
//    private final boolean[] ftt = {false,true,true};
//    private final boolean[] ttf = {true,true,false};
//    private final boolean[] tff = {true,false,false};
//    private final boolean[] ftf = {false,true,false};
//    private final boolean[] fff = {false,false,false};


    E3(0,TTT),F3(1,TFT),GB3(2,FTT),G3(3,TTF),AB3(4,TFF),A3(5,FTF),BB3(6,FFF),B3(7,TTT),
    C4(8,TFT),DB4(9,FTT),D4(10,TTF),EB4(11,TFF),E4(12,FTF),F4(13,FFF),GB4(14,FTT),G4(15,TTF),AB4(16,TFF),A4(17,FTF),BB4(18,FFF),B4(19,TTF),
    C5(20,TFF),DB5(21,FTF),D5(22,FFF),EB5(23,TFF),E5(24,FTF),F5(25,FFF),GB5(26,FTT),G5(27,TTF),AB5(28,TFF),A5(29,FTF),BB5(30,FFF),B5(31,TTF),
    C6(32,TFF),DB6(33,FTF),D6(34,FFF),EB6(35,TFF),E6(36,FTF),F6(37,FFF);


    private int value;
    private boolean[] valves;

    Note(int value, boolean[] valves) {
        this.value = value;
        this.valves = valves;
    }

    public int getValue() {
        return value;
    };

    public boolean[] getValves() {
        return valves;
    };
}
