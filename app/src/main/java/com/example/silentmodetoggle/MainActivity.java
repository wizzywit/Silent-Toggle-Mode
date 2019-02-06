package com.example.silentmodetoggle;

import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.silentmodetoggle.util.RingerHelper;


public class MainActivity extends AppCompatActivity {
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.layout);

        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RingerHelper.performToggle(audioManager);
                updateUI();

            }
        });
    }

    private void updateUI() {
        ImageView imageView = (ImageView) findViewById(R.id.imageon);
        int phoneImage = RingerHelper.isPhoneSilent(audioManager)?R.drawable.speaker_off:R.drawable.speaker_on;
        imageView.setImageResource(phoneImage);
        if(RingerHelper.isPhoneSilent(audioManager)){
            Toast.makeText(this,"speaker silent",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this,"speaker on",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume(){
        super.onResume();
        updateUI();
    }
}
