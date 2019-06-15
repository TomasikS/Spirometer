package com.example.kostra;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class DetailsActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo);


        DbHandler db = new DbHandler(this);
        ImageView image = findViewById(R.id.photo);


        Bitmap bitmap = db.getBitmap();


        if (bitmap != null) image.setImageBitmap(bitmap);
        else Toast.makeText(getBaseContext(), "error loading photo", Toast.LENGTH_SHORT).show();


        TextView textViewID = findViewById(R.id.id);
        TextView textViewFirstname = findViewById(R.id.firstname);
        TextView textViewLastname = findViewById(R.id.lastname);
        TextView textViewDiagnose = findViewById(R.id.diagnose);
        TextView textViewNote = findViewById(R.id.note);


        int id;
        String name;
        String lastname;
        String diagnose;
        String note;


        id = db.GetUserID();
        name = db.GetUserName();
        lastname = db.GetUserLastname();
        diagnose = db.GetUserDiagnose();
        note = db.GetUserNote();

//
//        Toast.makeText(getBaseContext(), name, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getBaseContext(), lastname, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getBaseContext(), diagnose, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getBaseContext(), note, Toast.LENGTH_SHORT).show();


        textViewID.setText(String.valueOf(id));
        textViewFirstname.setText(name);
        textViewLastname.setText(lastname);
        textViewDiagnose.setText(diagnose);
        textViewNote.setText(note);

        Button back = (Button) findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(DetailsActivity.this, Settings.class);
                startActivity(intent);
            }
        });


    }


}