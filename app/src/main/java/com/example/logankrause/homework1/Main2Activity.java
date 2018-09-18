package com.example.logankrause.homework1;

import android.Manifest;
import android.annotation.SuppressLint;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.byox.drawview.views.DrawView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Main2Activity extends AppCompatActivity {

    int red;
    int green;
    int blue;
    DrawView drawView;
    RelativeLayout layout;
    SharedPreferences sPreferences;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part2);
        drawView = findViewById(R.id.drawViewer);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        TextView tst = findViewById(R.id.valueTestTxt);
        Intent intent = getIntent();

        // Assigns the RBG values for future use.
        if (intent != null) {
            red = intent.getIntExtra("RedValue", 1);
            green = intent.getIntExtra("GreenValue", 1);
            blue = intent.getIntExtra("BlueValue", 1);
        }
        drawView.setDrawColor(Color.rgb(red, green, blue));
        String colorDetailText = "COLORS: " + red + "r, " + green + "g, " + blue + "b";
        tst.setText(colorDetailText);
    }

    /*
    Goes back to the Home page.
     */
    public void homePage(View view) {
        final Intent pageChange = new Intent(this, MainActivity.class);
        startActivity(pageChange);
    }

    /*
    Saves View to device.
     */
    public void saveImage(View view) throws IOException {
        try {
            String path = Environment.DIRECTORY_DCIM;
            Bitmap bitmap;
            OutputStream output;
            bitmap = Bitmap.createBitmap(drawView.getWidth(), drawView.getHeight(), Bitmap.Config.ARGB_8888);
            File filePath = Environment.getExternalStorageDirectory();
            File dir = new File(filePath.getAbsolutePath() + "/Captures");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, "myimage.png");

            try {

                if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    output = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, output);
                    Toast.makeText(Main2Activity.this, "Image Saved", Toast.LENGTH_SHORT).show();
                    output.flush();
                    output.close();
                }
                else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    Clears the Canvas.
     */
    public void resetImage(View view) {
        layout = (RelativeLayout) findViewById(R.id.activity_part2);
        layout.removeView(drawView);
    }

    /*
    Goes to Color picker page.
     */
    public void pickColor(View view) {
        final Intent colorPicker = new Intent(this, ColorPickerActivity.class);
        startActivity(colorPicker);
    }
}