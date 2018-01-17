package com.jhblack.brassscalepractice;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Jared on 1/14/2018.
 */

public class ValveOnTouchListener implements View.OnTouchListener {

    private static boolean[] valvesPressed = new boolean[3];
    private int valveId;

    public ValveOnTouchListener(int id) {
        this.valveId = id;
    }

    public boolean onTouch(View v, MotionEvent motionEvent) {
        switch(motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN: valvesPressed[valveId - 1] = true;
            break;
            case MotionEvent.ACTION_UP: valvesPressed[valveId-1] = false;

        }
        return true;
    }

    public static boolean[] getValvesPressed() {
        return valvesPressed;
    }


}
