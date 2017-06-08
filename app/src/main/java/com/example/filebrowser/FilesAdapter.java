package com.example.filebrowser;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Андрей on 07.06.2017.
 */

class FilesAdapter extends BaseAdapter {
    private final File mPath;
    private List<File> mFileList;
    public FilesAdapter(File path){
        mPath = path;
        mFileList = new ArrayList<>();
        File[] files = mPath.listFiles();
        if (files!=null){
            for (File f : files){
                if (f.isDirectory()){mFileList.add(f);}
            }
            for (File f : files){
                if (!f.isDirectory()){mFileList.add(f);}
            }
        }


    }
    @Override
    public int getCount() {
      return mFileList.size();

    }

    @Override
    public File getItem(int position) {
        return mFileList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (getItem(position).isDirectory()){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_of_dir, parent, false);
            TextView nameOfDirectoryView = (TextView) convertView.findViewById(R.id.nameOfDir);
            TextView infoTextView = (TextView) convertView.findViewById(R.id.info);
            nameOfDirectoryView.setText(getItem(position).getName());
            infoTextView.setText(getItem(position).getAbsolutePath());


        }
        else{
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_of_files, parent, false);
            convertView.setClickable(true);
            TextView nameOfDirectoryView = (TextView) convertView.findViewById(R.id.nameOfFile);
            nameOfDirectoryView.setText(getItem(position).getName());

        }
        return convertView;
    }
}
