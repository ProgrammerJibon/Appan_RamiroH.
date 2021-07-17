package com.yapue.appan;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SaveImage extends AsyncTask<Bitmap, Bitmap, Bitmap> {
    public Activity activity;
    public String url;
    public String path;

    public SaveImage(Activity activityx, String urlx, String pathx) {
        this.activity = activityx;
        this.url = urlx;
        this.path = pathx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (!(new File(Environment.getExternalStorageDirectory() + "/.ProgrammerJibon")).exists()) {
            if (!(new File(Environment.getExternalStorageDirectory() + "/.ProgrammerJibon")).mkdir()) {
                Log.e("errnos", "Need permit...");
            }
        }
    }

    @Override
    protected Bitmap doInBackground(Bitmap[] objects) {
        if (url == null) {
            return BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_person_black_24dp);
        }
        try {
            Log.e("errnos", "Getting Images...(1)");
            URL urlx = new URL(url);
            HttpURLConnection urlxConnection = (HttpURLConnection) urlx.openConnection();
            urlxConnection.setUseCaches(true);
            InputStream urlxInputStream = urlxConnection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(urlxInputStream);
            Log.e("errnos", "Getting Images...(1)");
            return bitmap;
        } catch (Exception e) {
            Log.e("errnos", e.toString());
            return null;
        }
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_person_black_24dp);
        }
        File sdCard = Environment.getExternalStorageDirectory();
        File folder = new File(sdCard.getAbsoluteFile(), ".ProgrammerJibon");
        if (!folder.exists()) {
            folder.mkdir();
        }
        File file = new File(folder.getAbsoluteFile(), path);
        if (!file.exists()) {
            try {
                FileOutputStream out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
                out.flush();
                out.close();
            } catch (Exception error) {
                error.printStackTrace();
                Toast.makeText(activity, error.toString(), Toast.LENGTH_LONG).show();
            }

        } else {
            if (file.delete()) {
                try {
                    FileOutputStream out = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
                    out.flush();
                    out.close();
                } catch (Exception error) {
                    error.printStackTrace();
                    Toast.makeText(activity, error.toString(), Toast.LENGTH_LONG).show();
                }
            }


        }


    }
}