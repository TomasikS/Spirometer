package com.example.kostra;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;

import static com.example.kostra.R.color.ram;

public class Settings extends AppCompatActivity {

    View view;
    public static final int PICKFILE_RESULT_CODE = 1;
    private int PICK_IMAGE_REQUEST = 1;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final ImageView photo = findViewById(R.id.imageView);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                photo.setImageBitmap(bitmap);

                DbHandler dbHandler = new DbHandler(Settings.this);

                dbHandler.addEntry(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stack);
        final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final ImageView photo = findViewById(R.id.imageView);

        view = inflater.inflate(R.layout.inserttoolbar, null);


        final LinearLayout llm = findViewById(R.id.stackMain);
        final LinearLayout lll = llm.findViewById(R.id.dynamic1);
        final LinearLayout llinsert = lll.findViewById(R.id.dynamic);
        final LinearLayout llinsert1 = lll.findViewById(R.id.dynamic0);
        final LinearLayout llinsert2 = lll.findViewById(R.id.dynamic3);
        final TextView textViewNote = findViewById(R.id.editTextNote);
        final TextView textViewDiagnose = findViewById(R.id.editTextDiagnose);
        final LinearLayout llinsert4 = lll.findViewById(R.id.dynamic4);
        final LinearLayout llinsert5 = lll.findViewById(R.id.dynamic5);
        final LinearLayout container = llm.findViewById(R.id.toolbarcontainer);

        getSupportActionBar().hide();
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.BLACK);

        toolbar.setTitle("Settings");

        container.addView(toolbar);


        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data;
                // String imageUrl = "https://4.imimg.com/data4/GK/NP/MY-1941908/plain-button-500x500.jpg";
                // loadImageWithGlide(imageUrl);
                Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
                chooseFile.setType("*/*");
                chooseFile = Intent.createChooser(chooseFile, "Choose a file");
                startActivityForResult(chooseFile, PICKFILE_RESULT_CODE);

            }
        });


        ImageView changeButton = findViewById(R.id.change);


        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentMain = new Intent(Settings.this, MainActivity.class);
                startActivity(intentMain);
            }
        });


        final View line = findViewById(R.id.Line);


        final TextView textView = findViewById(R.id.textID);


        textView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if (textView.getText().length() > 0)
                    textView.setVisibility(View.GONE);
                view = inflater.inflate(R.layout.search, null);
                EditText searchBox = (EditText) view.findViewById(R.id.textid);
                searchBox.setBackgroundResource(ram);
                llinsert.addView(searchBox);
                searchBox.setTag(1);
                searchBox.setText(textView.getText());

                /// Toast.makeText(getBaseContext(), textView.getText(), Toast.LENGTH_SHORT).show();

            }
        });


        final TextView meno = findViewById(R.id.textIDFirstname);


        meno.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (meno.getText().length() > 0)


                    meno.setVisibility(View.GONE);

                view = inflater.inflate(R.layout.name, null);
                EditText searchBox = (EditText) view.findViewById(R.id.textname);
                searchBox.setBackgroundResource(ram);

                llinsert1.addView(searchBox);
                searchBox.setTag(2);

            }
        });

        final TextView textViewLName = findViewById(R.id.editTextLName);


        textViewLName.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (textViewLName.getText().length() > 0)

                    textViewLName.setVisibility(View.GONE);

                view = inflater.inflate(R.layout.lastname, null);
                EditText searchBox = (EditText) view.findViewById(R.id.textlastname);
                searchBox.setBackgroundResource(ram);
                llinsert2.addView(searchBox);
                searchBox.setTag(3);

            }
        });


        textViewDiagnose.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (textViewDiagnose.getText().length() > 0)

                    textViewDiagnose.setVisibility(View.GONE);

                view = inflater.inflate(R.layout.diagnose, null);
                EditText searchBox = (EditText) view.findViewById(R.id.textdiagnose);
                searchBox.setBackgroundResource(ram);
                llinsert4.addView(searchBox);
                searchBox.setTag(4);

            }
        });


        textViewNote.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (textViewNote.getText().length() > 0)

                    textViewNote.setVisibility(View.GONE);

                view = inflater.inflate(R.layout.textnote, null);
                EditText searchBox = (EditText) view.findViewById(R.id.textnote);
                searchBox.setBackgroundResource(ram);
                searchBox.setScroller(new Scroller(getApplicationContext()));
                searchBox.setVerticalScrollBarEnabled(true);
                searchBox.setMaxLines(5);
                llinsert5.addView(searchBox);
                searchBox.setTag(5);

            }
        });


        AppCompatImageView view = findViewById(R.id.change);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText etID = (EditText) llinsert.findViewWithTag(1);
                EditText etFirstname = (EditText) llinsert1.findViewWithTag(2);
                EditText etLastname = (EditText) llinsert2.findViewWithTag(3);
                EditText etDiagnose = (EditText) llinsert4.findViewWithTag(4);
                EditText etNote = (EditText) llinsert5.findViewWithTag(5);


                textView.setText(" ");
                meno.setText(" ");
                textViewLName.setText(" ");
                textViewDiagnose.setText(" ");
                textViewNote.setText(" ");


//                if (llinsert.findViewWithTag(1) != null) {
//                    etID.setText(" ");
//
//
//                    // Toast.makeText(getBaseContext(), etID.getText(), Toast.LENGTH_SHORT).show();
//                }
//
//
//                if (llinsert1.findViewWithTag(2) != null) {
//                    etFirstname.setText(" ");
//                }
//                if (llinsert2.findViewWithTag(3) != null) {
//                    etLastname.setText(" ");
//                }
//                if (llinsert4.findViewWithTag(4) != null) {
//                    etDiagnose.setText(" ");
//                }
//                if (llinsert5.findViewWithTag(5) != null) {
//                    etNote.setText(" ");
//                }

                if (textView.getText().toString().length() == 1) {
                    Toast.makeText(getApplicationContext(), "Wrong input ID", Toast.LENGTH_SHORT).show();

                }


                if (meno.getText().toString().length() == 1) {
                    Toast.makeText(getApplicationContext(), "Wrong input name", Toast.LENGTH_SHORT).show();

                }

                if (textViewLName.getText().toString().length() == 1) {
                    Toast.makeText(getApplicationContext(), "Wrong input lastname", Toast.LENGTH_SHORT).show();

                }


                if (textViewDiagnose.getText().toString().length() == 1) {
                    Toast.makeText(getApplicationContext(), "Wrong input diagnose", Toast.LENGTH_SHORT).show();

                }


//                if (textViewNote.getText().toString().length() == 1) {
//                    Toast.makeText(getApplicationContext(), "Wrong input", Toast.LENGTH_SHORT).show();

                //  }

                if ((llinsert.findViewWithTag(1) != null) &&
                        (llinsert1.findViewWithTag(2) != null) &&
                        (llinsert2.findViewWithTag(3) != null) &&
                        (llinsert4.findViewWithTag(4) != null) &&
                        (llinsert5.findViewWithTag(5) != null) &&


                        (etID.getText().length() > 1) &&
                        (etFirstname.getText().length() > 1) &&
                        (etLastname.getText().length() > 1) &&
                        (etDiagnose.getText().length() > 1) &&
                        (etNote.getText().length() > -1)) {


                    String pom_ID = etID.getText().toString().substring(1, 3);
                    int ID = Integer.parseInt(pom_ID);
                    String firstname = etFirstname.getText().toString();
                    String lastname = etLastname.getText().toString();
                    String diagnose = etDiagnose.getText().toString();
                    String note = "  ";
                    etID.setText(ID);
                    etFirstname.setText(firstname);
                    etLastname.setText(lastname);
                    etDiagnose.setText(diagnose);
                    etNote.setText(note);


                    DbHandler dbHandler = new DbHandler(Settings.this);
                    dbHandler.insertUserDetails(ID, firstname, lastname, diagnose, note);
                    Intent intent = new Intent(Settings.this, DetailsActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Details Inserted Successfully", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(getApplicationContext(), "error value", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }


}







