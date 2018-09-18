package com.example.logankrause.homework1;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Save {
    private Context context;
    private String nameOfFolder = "/capture";
    private String nameofFile = "image1";

    public void SaveImage(Bitmap Image) {
        //context = con;
        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath();
        File dir = new File(file_path);

        if(!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(dir, nameofFile +".jpg");

        try {
            FileOutputStream _out = new FileOutputStream(file);
            Image.compress(Bitmap.CompressFormat.JPEG, 85, _out);
            _out.flush();
            _out.close();
            fileCreated(file);
            SuccessfulSave();
        }
        catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
    }

    public void SuccessfulSave() {
        Toast.makeText(context, "Picture Saved", Toast.LENGTH_SHORT).show();
    }

    private void fileCreated(File file) {
        MediaScannerConnection.scanFile(context, new String[]{file.toString()}, null, new MediaScannerConnection.OnScanCompletedListener() {
            @Override
            public void onScanCompleted(String path, Uri uri) {
                {
                    Log.e("ExternalStorage", "Scanned " + path + ":");
                    Log.e("ExternalStorage","-> uri=" + uri);
                }
            }
        });
    }

}
