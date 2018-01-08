package com.jhblack.brassscalepractice;

import android.content.Context;
import android.widget.Button;



/**
 * Created by Jared on 1/6/2018.
 */

public class TouchableButton extends Button {
    public TouchableButton(Context context) {
        super(context);
    }

    @Override
    public boolean performClick() {
        return true;
    }
}
