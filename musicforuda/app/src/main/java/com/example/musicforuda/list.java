package com.example.musicforuda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class list extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        final ArrayList<miku_music_list> words = new ArrayList<miku_music_list>();

        words.add(new miku_music_list("一万星","miku"));
        words.add(new miku_music_list("bluestar","miku"));
        words.add(new miku_music_list("炉心融解","miku"));
        words.add(new miku_music_list("紡ぐ","miku"));
        words.add(new miku_music_list("月西江","miku"));
        words.add(new miku_music_list("初音未来的暴走","miku"));
        words.add(new miku_music_list("里外情人","miku"));
        words.add(new miku_music_list("爱言叶","miku"));
        words.add(new miku_music_list("no name","miku"));
        words.add(new miku_music_list("気まぐれな","miku"));
        words.add(new miku_music_list("last night","miku"));
        words.add(new miku_music_list("深海少女","miku"));
        words.add(new miku_music_list("lemon","miku"));
        AndroidFlavorAdapter songAdapter = new AndroidFlavorAdapter(this,words);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.listone);
        listView.setAdapter(songAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(list.this, musicplay.class);
                Bundle bundle = new Bundle();
                bundle.putString("result", words.get(i).getSongs());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


}
