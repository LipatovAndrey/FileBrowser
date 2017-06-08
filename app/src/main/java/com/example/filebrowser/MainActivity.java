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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.os.Environment.getRootDirectory;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private List<File> mFiles;
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
            mFile = new File(path);
           }else{
            mFile = Environment.getRootDirectory();
        }
        mFiles = doListFiles(mFile);
        mListView.setAdapter(new FilesAdapter(mFiles));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent updateActivity = new Intent(parent.getContext(), parent.getContext().getClass());
                updateActivity.putExtra(PATH, mFiles.get(position).getAbsolutePath());
                Log.d("path", mFiles.get(position).getAbsolutePath());
                startActivity(updateActivity);
            }
        });

    }
    private List<File> doListFiles(File path){
        File[] files = path.listFiles();
        List<File> fileList = new ArrayList<>();
        if (files!=null){
            Arrays.sort(files);
            for (File f : files){
                if (f.isDirectory()){fileList.add(f);}
            }

            for (File f : files){
                if (!f.isDirectory()){fileList.add(f);}
            }
        }
    return fileList;}
}
