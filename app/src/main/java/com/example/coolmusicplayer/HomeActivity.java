package com.example.coolmusicplayer;


import androidx.appcompat.app.AppCompatActivity;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.security.Permissions;
import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity implements Permission {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private static final String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    private static final int REQUEST_PERMISSIONS = 12345;
    private static final int PERMISSIONS_COUNT = 1;

    @SuppressLint("NewApi")
    private boolean arePermissionsDenied() {
        for (int i = 0; i < PERMISSIONS_COUNT; i++) {
            if (checkSelfPermission(PERMISSIONS[i]) != PackageManager.PERMISSION_GRANTED) {
                return true;
            }
        }
        return false;
    }

    @SuppressLint("NewApi")
    @Override
    public void onRequestsPermissionsResults(int requestCode, String[] permissions, int[] grandResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grandResults);
        if (arePermissionsDenied()) {
            ((ActivityManager) (this.getSystemService(ACTIVITY_SERVICE))).clearApplicationUserData();
            recreate();
        } else {
            onResume();
        }
    }

    private boolean isCoolMusicPlayerInit;

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && arePermissionsDenied()) {
            requestPermissions(PERMISSIONS, REQUEST_PERMISSIONS);
            return;
        }
        if (isCoolMusicPlayerInit) {
            final ListView listView = findViewById(R.id.listView);
            isCoolMusicPlayerInit = true;
        }
    }
    class textAdapter extends BaseAdapter{
        private List<String> data = new ArrayList<>();
        void  setData(List<String> mData){
            data.clear();
            data.addAll(mData);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public String getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.item, parent, false);
                convertView.setTag(new viewHolder((TextView)convertView.findViewById(R.id.myItem)));

            }
            viewHolder holder = (viewHolder) convertView.getTag();
            final String item = data.get(position);
            holder.info.setText(item.substring(item.lastIndexOf('/')+1));
            return convertView;
        }
        class  viewHolder{
            TextView info;
            viewHolder(TextView mInfo){
                info = mInfo;
            }
        }
    }

}


