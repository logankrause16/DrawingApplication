package com.example.logankrause.homework1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class ColorPickerActivity extends AppCompatActivity {

    int r = 0;
    int g = 0;
    int b = 0;
    TextView colorPreview;
    SeekBar red;
    SeekBar green;
    SeekBar blue;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);

        red = (SeekBar) findViewById(R.id.redValue);
        green = (SeekBar) findViewById(R.id.greenValue);
        blue = (SeekBar) findViewById(R.id.blueValue);
        colorPreview = (TextView) findViewById(R.id.previewColor);

        // When the red seek bar is changed, update the color
        red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                r = progress;
                ChangePreviewColor(r,g,b);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // When the green seek bar is changed, update the color
        green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                g = progress;
                ChangePreviewColor(r,g,b);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // When the blue seek bar is changed, update the color
        blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                b = progress;
                ChangePreviewColor(r,g,b);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    public void ChangePreviewColor(int r, int g, int b) {
        colorPreview.setBackgroundColor(Color.rgb(r,g,b));
    }

    public void SaveColor(View view) {
        Intent drawPage = new Intent(this, Main2Activity.class);
        drawPage.putExtra("RedValue", r);
        drawPage.putExtra("GreenValue", g);
        drawPage.putExtra("BlueValue", b);

        startActivity(drawPage);
    }
}
