package com.example.musicforuda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startmusic(View view) {
        Intent intent = new Intent(this, musicplay.class);
        Bundle bundle = new Bundle();
        bundle.putString("result", "n1听力");
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void startlist(View view) {
        Intent intent = new Intent(this, list.class);
        startActivity(intent);
    }
}
