package com.example.coolmusicplayer;

import android.annotation.SuppressLint;

public interface Permission {
    @SuppressLint("NewApi")
    void onRequestsPermissionsResults(int requestCode, String[] permissions, int[] grandResults);
}
