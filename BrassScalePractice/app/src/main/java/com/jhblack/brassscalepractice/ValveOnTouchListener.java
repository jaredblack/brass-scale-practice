package com.jhblack.brassscalepractice;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Jared on 1/14/2018.
 */

public class ValveOnTouchListener implements View.OnTouchListener {

    private static boolean[] valvesPressed = new boolean[4];
    private int valveId;

    public ValveOnTouchListener(int id) {
        this.valveId = id;
    }

    public boolean onTouch(View v, MotionEvent motionEvent) {
        valvesPressed[valveId] = true;
        return true;
    }

    public static boolean[] getValvesPressed() {
        return valvesPressed;
    }
}
