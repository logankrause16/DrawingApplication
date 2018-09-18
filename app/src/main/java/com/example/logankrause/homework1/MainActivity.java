package com.example.logankrause.homework1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // New Code Here:

        final Button colorBtn = (Button) findViewById(R.id.changeColor);
        final Button drawPageBtn = (Button) findViewById(R.id.drawPageBtn);

        final EditText colorTxt = (EditText) findViewById(R.id.colorText);
        final TextView colorDetails =(TextView) findViewById(R.id.colorDetails);

        final Intent pageChange = new Intent(this, Main2Activity.class);

        colorBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                Random rnd = new Random();
                int r = rnd.nextInt(255) + 1;
                int g = rnd.nextInt(255) + 1;
                int b = rnd.nextInt(255) + 1;

                colorTxt.setTextColor(Color.rgb(r, g, b));

                String hex = String.format("#%02x%02x%02x", r, g, b);

                colorDetails.setText("Color: " + r + "r, " + g + "g, " + b + "b, " + hex);
            }
        });

        drawPageBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(pageChange);
            }
        });
    }
}
