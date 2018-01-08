package com.jhblack.brassscalepractice;
import android.content.Context;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private SoundPool soundPool;
    private boolean firstTime = true;
    int soundId;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        soundPool = new SoundPool.Builder().build();
        soundId = soundPool.load(mContext, R.raw.a4, 1);
        Button bt = findViewById(R.id.button);

        bt.setOnTouchListener(new View.OnTouchListener() {
            int newSoundId = soundId;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if(firstTime) {
                            newSoundId = soundPool.play(soundId, 1, 1, 1, -1, 1f);
                            firstTime = false;
                        } else {
                            soundPool.resume(soundId);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        soundPool.pause(newSoundId);

                        Log.i("MotionEvent.ACTION_UP", "original soundId was " + soundId + " newSoundId: " + newSoundId);
                        break;
                }
                return false;
            }
        });
    }



}
