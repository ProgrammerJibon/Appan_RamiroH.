package com.yapue.appan;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import io.fabric.sdk.android.services.concurrency.AsyncTask;

public class SaveImage extends AsyncTask {
    public Activity activity;
    public String url;
    public File path;

    public SaveImage(Activity activityx, String urlx, File pathx) {
        this.activity = activityx;
        this.url = urlx;
        this.path = pathx;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
            File destination = path;
            FileOutputStream fileOutputStream = new FileOutputStream(destination);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception error) {
            return false;
        }
        return true;
    }
}
