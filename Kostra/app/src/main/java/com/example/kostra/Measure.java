package com.example.kostra;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Measure extends AppCompatActivity {
    static int id;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Measure.id = id;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.measure);

        TextView describeDate = findViewById(R.id.textView2);
        TextView describeSpeed = findViewById(R.id.textView4);
        TextView describeVolume = findViewById(R.id.textView6);


        TextView Date = findViewById(R.id.textView3);
        TextView Speed = findViewById(R.id.textView5);
        TextView Volume = findViewById(R.id.textView7);


        DbHandler db = new DbHandler(getBaseContext());


        int speed;
        speed = db.GetSpeed(id);

        Speed.setText(String.valueOf(speed));

        int volume = db.GetVolume(id);

        Volume.setText(String.valueOf(volume));

        String date = db.GetDate(id);
        Date.setText(date);
    }


}
