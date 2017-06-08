package com.example.filebrowser;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;

import static android.os.Environment.getRootDirectory;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private File mFile;
    private final static String PATH = "path";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listOfFiles);
    }






    @Override
    protected void onResume() {
        super.onResume();
        String path = getIntent().getStringExtra(PATH);
        if (path!=null){
            Log.d("path", path);
            mFile = new File(path);
           }else{
            mFile = Environment.getRootDirectory();
        }
        mListView.setAdapter(new FilesAdapter(mFile));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("item", mFile.listFiles()[position].getName());
                Intent updateActivity = new Intent(parent.getContext(), parent.getContext().getClass());
                updateActivity.putExtra(PATH, mFile.listFiles()[position].getAbsolutePath());
                startActivity(updateActivity);
            }
        });
    }
}
