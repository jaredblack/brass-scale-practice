package com.jhblack.brassscalepractice;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private SoundPool soundPool;
    int soundId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();
        soundPool = new SoundPool.Builder().build();
        soundId = soundPool.load(mContext, R.raw.a4, 1);
    }

    public void playA(View view) {
        
        soundPool.play(soundId, 1, 1, 1, 0, 1f);
//        SoundPool.OnLoadCompleteListener listener = new SoundPool.OnLoadCompleteListener() {
//            @Override
//            public void onLoadComplete(SoundPool soundPool, int i, int i1) {
//                int soundId = soundPool.load(mContext, R.raw.a4, 1);
//                soundPool.play(soundId, 1, 1, 1, 0, 1f);
//            }
//        };

//        soundPool.setOnLoadCompleteListener(listener);
    }


}
