package com.example.musicforuda;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class musicplay extends AppCompatActivity {
    private Button playMusic;
    private Button pauseMusic;
    private Button stopMusic;

    private TextView totalTime_text;
    private TextView playingTime_text;

    private SeekBar playingProcess;

    private int totalTime = 0;

    private MediaPlayer mediaPlayer = new MediaPlayer();

    private Handler hangler = new Handler();
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicplay);
        Intent intent = getIntent();
        String result = intent.getStringExtra("result");
        TextView tt = (TextView) findViewById(R.id.musicName);
        tt.setText(result);
        playMusic = (Button) findViewById(R.id.play);
        playMusic.setOnClickListener(new myOnClickListener());

        pauseMusic = (Button) findViewById(R.id.pause);
        pauseMusic.setOnClickListener(new myOnClickListener());

        stopMusic = (Button) findViewById(R.id.stop);
        stopMusic.setOnClickListener(new myOnClickListener());


        playingProcess = (SeekBar) findViewById(R.id.seek);
        playingProcess.setOnSeekBarChangeListener(new mySeekBarListener());

        totalTime_text = (TextView) findViewById(R.id.totalTime);
        playingTime_text = (TextView) findViewById(R.id.playingTime);
        initMediaPlayer(result);
    }

    public void tolist(View view) {
        Intent intent = new Intent(this, list.class);
        mediaPlayer.reset();
        startActivity(intent);
    }

    public void setTotalTime() {
        totalTime = mediaPlayer.getDuration() / 1000;
        Log.d("MediaPlayerTest", String.valueOf(totalTime));
        String pos = String.valueOf(totalTime / 60 / 10) + String.valueOf(totalTime / 60 % 10)
                + ':' + String.valueOf(totalTime % 60 / 10) + String.valueOf(totalTime % 60 % 10);
        totalTime_text.setText(pos);
        playingProcess.setProgress(0);
        playingProcess.setMax(totalTime);
    }

    public void updateTimepos() {
        int timepos = playingProcess.getProgress() + 1;
        if (timepos >= totalTime - 1) {
            timepos = 0;
            flag = false;
        }
        playingProcess.setProgress(timepos);
        String pos = String.valueOf(timepos / 60 / 10) + String.valueOf(timepos / 60 % 10)
                + ':' + String.valueOf(timepos % 60 / 10) + String.valueOf(timepos % 60 % 10);
        playingTime_text.setText(pos);

    }

    public void initMediaPlayer(String result) {
        try {
            if (result.contains("炉心融解")) {
                mediaPlayer = MediaPlayer.create(this, R.raw.myheart);
            } else if (result.contains("月西江")) {
                mediaPlayer = MediaPlayer.create(this, R.raw.moon);
            } else if (result.contains("深海少女")) {
                mediaPlayer = MediaPlayer.create(this, R.raw.girlinsea);
            } else if (result.contains("爱言叶")) {
                mediaPlayer = MediaPlayer.create(this, R.raw.mukusong2);
            } else {
                mediaPlayer = MediaPlayer.create(this, R.raw.musicn1);
            }
            setTotalTime();
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refreshTimepos() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag && playingProcess.getProgress() < totalTime - 1) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    hangler.post(new Runnable() {
                        @Override
                        public void run() {
                            updateTimepos();
                        }
                    });
                }
            }
        }).start();
    }

    private class mySeekBarListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            updateTimepos();
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            mediaPlayer.seekTo(playingProcess.getProgress() * 1000);
            updateTimepos();
        }
    }

    private class myOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.play:
                    setPlayMusic();
                    break;
                case R.id.pause:
                    setPauseMusic();
                    break;
                case R.id.stop:
                    setresetMusic();
                    break;
            }
        }
    }

    public void setPlayMusic() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            flag = true;
            refreshTimepos();
        }
    }

    public void setPauseMusic() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            flag = false;
        }
    }

    public void setresetMusic() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            Toast.makeText(musicplay.this, "准备重新开始播放音乐", Toast.LENGTH_LONG).show();
            mediaPlayer.reset();
            TextView tem = (TextView) findViewById(R.id.musicName);
            String result = tem.getText().toString();
            initMediaPlayer(result);
            mediaPlayer.start();
        }
    }

}