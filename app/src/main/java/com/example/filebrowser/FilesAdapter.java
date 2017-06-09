package com.example.filebrowser;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

/**
 * Created by Андрей on 07.06.2017.
 */

class FilesAdapter extends BaseAdapter {

    private final List<File> mFileList;
    public FilesAdapter(List<File> fileList){
        mFileList = fileList;
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

    public int getCounTFilesConstaints(File dir){
        if (dir.listFiles()!=null){
            return dir.listFiles().length;
        }else {
            return 0;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_of_dir, parent, false);
            viewHolder.mNameView = (TextView) convertView.findViewById(R.id.nameOfDir);
            viewHolder.mFullPathView = (TextView) convertView.findViewById(R.id.fullPath);
            viewHolder.mFolderImageView = (ImageView) convertView.findViewById(R.id.imageFolder);
            viewHolder.mCountFiles = (TextView) convertView.findViewById(R.id.CountFiles);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();

        if (getItem(position).isDirectory()){
            viewHolder.mNameView.setText(getItem(position).getName());
            viewHolder.mFullPathView.setText(getItem(position).getAbsolutePath());
            viewHolder.mFolderImageView.setImageResource(R.mipmap.ic_folder);
            viewHolder.mCountFiles.setText("Files inside " + String.valueOf(getCounTFilesConstaints(getItem(position))));
            } else {
                    viewHolder.mNameView.setText(getItem(position).getName());
                    viewHolder.mNameView.setPadding(30,0,0,0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                viewHolder.mNameView.setTextAppearance(R.style.FileText);
            }
            convertView.setClickable(true);
            }

        return convertView;
    }
    private class ViewHolder{
        TextView mFullPathView;
        TextView mNameView;
        TextView mCountFiles;
        ImageView mFolderImageView;
    }

}
