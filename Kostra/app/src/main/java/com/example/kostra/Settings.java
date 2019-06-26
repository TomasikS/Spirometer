package com.example.kostra;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
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
import java.util.ArrayList;
import java.util.List;

import static com.example.kostra.R.color.ram;

public class Settings extends AppCompatActivity {

    View view;
    public static final int PICKFILE_RESULT_CODE = 1;
    private int PICK_IMAGE_REQUEST = 1;

    Boolean IsBitmap = false;
    boolean clicked = false;
    boolean clickedname = false;
    boolean clickedsurname = false;
    boolean clickeddiagnose = false;
    boolean clickednote = false;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final ImageView photo = findViewById(R.id.imageView);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                photo.setImageBitmap(bitmap);
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

        final LinearLayout llinsert4 = lll.findViewById(R.id.dynamic4);
        final LinearLayout llinsert5 = lll.findViewById(R.id.dynamic5);
        final LinearLayout container = llm.findViewById(R.id.toolbarcontainer);
        DbHandler dbHandler = new DbHandler(Settings.this);


        getSupportActionBar().hide();
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.BLACK);

        toolbar.setTitle("Settings");

        container.addView(toolbar);

        int id = dbHandler.GetUserID();
        String firstname = dbHandler.GetUserName();
        String lastname = dbHandler.GetUserLastname();
        String diagnose = dbHandler.GetUserDiagnose();
        String note = dbHandler.GetUserNote();
        List<Integer> listID = dbHandler.GetIDList();


        final TextView textView = findViewById(R.id.textID);
        final TextView textViewLName = findViewById(R.id.editTextLName);
        final TextView meno = findViewById(R.id.textIDFirstname);
        final TextView textViewNote = findViewById(R.id.editTextNote);
        final TextView textViewDiagnose = findViewById(R.id.editTextDiagnose);


        textView.setText(String.valueOf(id));
        meno.setText(firstname);
        textViewLName.setText(lastname);
        textViewDiagnose.setText(diagnose);
        textViewNote.setText(note);


        // Toast.makeText(getBaseContext(), textView.getText(), Toast.LENGTH_SHORT).show();
//        if ((firstname == null) || (lastname == null) || (diagnose == null)) {
//            dbHandler.deleteAll();

//        for (int i = 0; i < listID.size() - 1; i++) {
//            dbHandler.delete(listID.get(i));
//            Toast.makeText(getBaseContext(), String.valueOf(listID.get(i) + "item"), Toast.LENGTH_SHORT).show();
//        }


        // int count = listID.size();
//

        dbHandler.deleteAll();
//        count = listID.size();
//
        // Toast.makeText(getBaseContext(), String.valueOf(count + "velkost"), Toast.LENGTH_SHORT).show();

//        // }


        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data;
                Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
                chooseFile.setType("*/*");
                chooseFile = Intent.createChooser(chooseFile, "Choose a file");
                startActivityForResult(chooseFile, PICKFILE_RESULT_CODE);
                //Intent intentMain = new Intent(Settings.this, Settings.class);
                //startActivity(intentMain);
                IsBitmap = true;
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


        // final TextView IDDescribe = findViewById(R.id.textView);


        ShapeDrawable sd = new ShapeDrawable();
        int color = ContextCompat.getColor(getBaseContext(), R.color.colorPrimary);
        sd.getPaint().setColor(color);
        sd.getPaint().setStrokeWidth(10f);
        sd.setShape(new RectShape());
        sd.getPaint().setStyle(Paint.Style.STROKE);

        textView.setBackground(sd);


//        textView.setOnClickListener(new View.OnClickListener() {
//
//
//            @Override
//            public void onClick(View v) {
//
//                if (textView.getText().length() > 0)
//                    textView.setVisibility(View.GONE);
//                view = inflater.inflate(R.layout.search, null);
//                EditText searchBox = (EditText) view.findViewById(R.id.textid);
//                searchBox.setBackgroundResource(ram);
//                llinsert.removeView(textView);
//                llinsert.addView(searchBox);
//                searchBox.setTag(1);
//
//                textView.setBackground(null);
//                textView.setText("ID");
//                clicked = true;
//
//                // searchBox.setText(textView.getText());
//
////                if (textView.getText().length() > 0) {
////                    textView.setText(textView.getText());
////                    searchBox.setText(searchBox.getText());
////
////                }
//
//            }
//        });


        textView.setText("1");

        //    Toast.makeText(getBaseContext(), String.valueOf(clicked), Toast.LENGTH_SHORT).show();

        final TextView menoDescribe = findViewById(R.id.textViewMeno);

        menoDescribe.setVisibility(View.VISIBLE);


        meno.setBackground(sd);


        meno.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (meno.getText().length() > 0)


                    meno.setVisibility(View.GONE);

                view = inflater.inflate(R.layout.name, null);
                EditText searchBox = (EditText) view.findViewById(R.id.textname);
                searchBox.setBackgroundResource(ram);
                llinsert1.removeView(meno);
                llinsert1.addView(searchBox);
                searchBox.setTag(2);

                meno.setBackground(null);
                menoDescribe.setText("Firstname");
                clickedname = true;
            }
        });


        textViewLName.setBackground(sd);

        textViewLName.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (textViewLName.getText().length() > 0)

                    textViewLName.setVisibility(View.GONE);

                view = inflater.inflate(R.layout.lastname, null);
                EditText searchBox = (EditText) view.findViewById(R.id.textlastname);
                searchBox.setBackgroundResource(ram);
                llinsert2.removeView(textViewLName);
                llinsert2.addView(searchBox);
                searchBox.setTag(3);
                clickedsurname = true;
            }
        });


        textViewDiagnose.setBackground(sd);

        textViewDiagnose.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (textViewDiagnose.getText().length() > 0)

                    textViewDiagnose.setVisibility(View.GONE);

                view = inflater.inflate(R.layout.diagnose, null);
                EditText searchBox = (EditText) view.findViewById(R.id.textdiagnose);
                searchBox.setBackgroundResource(ram);
                llinsert4.removeView(textViewDiagnose);
                llinsert4.addView(searchBox);
                searchBox.setTag(4);
                clickeddiagnose = true;

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
                searchBox.setBackgroundResource(ram);
                searchBox.setTag(5);
                clickednote = true;
                if (textViewNote.getText().length() > 0) searchBox.setText(" ");
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


//                textView.setText(" ");
//                meno.setText(" ");
//                textViewLName.setText(" ");
//                textViewDiagnose.setText(" ");
//                textViewNote.setText(" ");


//                if (llinsert.findViewWithTag(1) != null) {
//                    etID.setText(etID.getText());
//                    //  textView.setText("    ");
//
//                }


                if (llinsert1.findViewWithTag(2) != null) {
                    etFirstname.setText(etFirstname.getText());
                    //    meno.setText("      ");

                }
                if (llinsert2.findViewWithTag(3) != null) {
                    etLastname.setText(etLastname.getText());
                    //     textViewLName.setText("     ");
                }
                if (llinsert4.findViewWithTag(4) != null) {
                    etDiagnose.setText(etDiagnose.getText());
                    //    textViewDiagnose.setText("      ");
                }

                if (textViewNote.getText().toString().length() < 2) {

                    textViewNote.setVisibility(View.GONE);

                    View view = inflater.inflate(R.layout.textnote, null);
                    EditText searchBox = (EditText) view.findViewById(R.id.textnote);
                    searchBox.setBackgroundResource(ram);
                    searchBox.setScroller(new Scroller(getApplicationContext()));
                    searchBox.setVerticalScrollBarEnabled(true);
                    searchBox.setMaxLines(5);
                    llinsert5.addView(searchBox);
                    searchBox.setTag(5);
                    //  searchBox.setText(" ");
                    ///    Toast.makeText(getBaseContext(), searchBox.getText(), Toast.LENGTH_SHORT).show();
                }


                EditText etNote = (EditText) llinsert5.findViewWithTag(5);


                if (llinsert5.findViewWithTag(5) != null) {

                    etNote.setText(etNote.getText());


                }


                if ((IsBitmap == false)) {
//
//
                    Toast.makeText(getApplicationContext(), "empty image ", Toast.LENGTH_SHORT).show();
                }


//


                if ((IsBitmap == true) && (clickedname == true) && (clickedsurname == true) && (clickeddiagnose == true)) {


//                    (((etID.getText().length() > 0) &&
                    if (((etFirstname.getText().length() > 1) &&
                            (etLastname.getText().length() > 1) &&
                            (etDiagnose.getText().length() > 1) &&
//                            (isNumeric(etID.getText().toString())) &&
                            (isString(etFirstname.getText().toString())) &&
                            (isString(etLastname.getText().toString())) &&
                            (isString(etDiagnose.getText().toString())))) {
                        List<LastValue> meranie = new ArrayList<>();


//                        String pom_ID = etID.getText().toString();
//                        String ID_NEW = pom_ID.replace(" ", "");
//
//                        int ID = Integer.parseInt(ID_NEW);

                        meranie.add(new LastValue(1, etFirstname.getText().toString(), etLastname.getText().toString(), etDiagnose.getText().toString(), etNote.getText().toString()));

                        //      Toast.makeText(getBaseContext(), "Saving to db", Toast.LENGTH_SHORT).show();


                        ///   Toast.makeText(getBaseContext(), "bitmap saved", Toast.LENGTH_SHORT).show();


                        //   Toast.makeText(getBaseContext(), String.valueOf(ID), Toast.LENGTH_SHORT).show();


                        String firstname = etFirstname.getText().toString();
                        String lastname = etLastname.getText().toString();
                        String diagnose = etDiagnose.getText().toString();
                        String note = "";

                        if (clickednote == true) note = etNote.getText().toString();


//                        String note = etNote.getText().toString();

//                    Toast.makeText(getBaseContext(), firstname, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getBaseContext(), lastname, Toast.LENGTH_SHORT).show();

                        // Toast.makeText(getBaseContext(), String.valueOf(ID_NEW), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getBaseContext(), diagnose, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getBaseContext(), note, Toast.LENGTH_SHORT).show();

                        //
                        //dbHandler.insertUserDetails(ID, firstname, lastname, diagnose, note);
//                    Intent intent = new Intent(Settings.this, DetailsActivity.class);
//                    startActivity(intent);


                        SaveBitmap();
                        SaveData(1, firstname, lastname, diagnose, note);
                        //Toast.makeText(getApplicationContext(), "Details deleted Successfully", Toast.LENGTH_SHORT).show();
                        //    dbHandler.insertUserDetails(ID, firstname, lastname, diagnose, note);


                        Intent intent = new Intent(Settings.this, DetailsActivity.class);
                        startActivity(intent);

                        //Toast.makeText(getApplicationContext(), "Details Inserted Successfully", Toast.LENGTH_SHORT).show();
                        // textView.setText(String.valueOf(id));
                        meno.setText(firstname);
                        textViewLName.setText(lastname);
                        textViewDiagnose.setText(diagnose);
                        textViewNote.setText(note);


                    } else {


                        Toast.makeText(getApplicationContext(), "wrong input", Toast.LENGTH_SHORT).show();

                    }


                } else {


                    Toast.makeText(getApplicationContext(), "wrong / empty input", Toast.LENGTH_SHORT).show();

                }

            } // click listener

        });


    }

    public void SaveBitmap() {
        final ImageView photo = findViewById(R.id.imageView);
        photo.invalidate();
        BitmapDrawable drawable = (BitmapDrawable) photo.getDrawable();

        DbHandler dbHandler = new DbHandler(Settings.this);
        dbHandler.insertBitmap(drawable.getBitmap());
        Toast.makeText(getBaseContext(), "bitmap saved", Toast.LENGTH_SHORT).show();
        dbHandler.close();

    }


    public void SaveData(int ID, String firstname, String lastname, String diagnose, String note) {

        DbHandler dbHandler = new DbHandler(Settings.this);
        dbHandler.insertUserDetails(ID, firstname, lastname, diagnose, note);
        Intent intent = new Intent(Settings.this, DetailsActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Details Inserted Successfully", Toast.LENGTH_SHORT).show();
        dbHandler.close();

    }

//
//    public static boolean isNumeric(String str) {
//        if (str == null)
//            return false;
//        for (char c : str.toCharArray())
//            if (c < '0' || c > '9')
//                return false;
//        return true;
//    }


    boolean isString(String str) {
        for (char c : str.toCharArray()) {
            if ((Character.isUpperCase(c)) || (Character.isLowerCase(c))) return true;

        }
        return false;
    }

}







