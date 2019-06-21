package com.example.kostra;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class history extends Fragment {


    // Store instance variables based on arguments passed
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_history, null);


        DbHandler db = new DbHandler(getContext());
        List<String> dates = new ArrayList();
        dates = db.GetDate();

        List<String> times = new ArrayList();
        times = db.GetTime();


        List<Integer> volumeOfUser = new ArrayList();
        volumeOfUser = db.GetVolume();


        List<Integer> speedOfUser = new ArrayList();
        speedOfUser = db.GetSpeed();


        for (int i = 0; i < dates.size(); i++) {


            LinearLayout mainLayout = view.findViewById(R.id.ll2);
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);


            LinearLayout linearLayout2 = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            LinearLayout linearLayoutHorizontal = new LinearLayout(getContext());
            linearLayoutHorizontal.setOrientation(LinearLayout.HORIZONTAL);

            LinearLayout linearLayoutHorizontal2 = new LinearLayout(getContext());
            linearLayoutHorizontal2.setOrientation(LinearLayout.HORIZONTAL);


            LinearLayout linearLayoutVertical = new LinearLayout(getContext());
            linearLayoutVertical.setOrientation(LinearLayout.VERTICAL);


            TextView date = new TextView(getContext());
            date.setText("date");


            TextView newText = new TextView(getContext());
            newText.setText("Average speed");


            TextView editText = new EditText(getContext());
            TextView speed = new EditText(getContext());


            ImageView imageView = new ImageView(getContext());
            imageView.setBackgroundColor(Color.BLACK);


            imageView.setLayoutParams(new LinearLayout.LayoutParams(280, 400));

            imageView.setBackgroundResource(R.drawable.ic_graf);

            linearLayout2.setOrientation(LinearLayout.HORIZONTAL);
            mainLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);


            LinearLayout ll = new LinearLayout(getContext());
            LinearLayout ll2 = new LinearLayout(getContext());
            LinearLayout linearLayout3 = new LinearLayout(getContext());

            linearLayout.addView(date);
            linearLayout.addView(editText);

            editText.setText(dates.get(i) + " " + times.get(i));


            linearLayout.setGravity(Gravity.CENTER);

            linearLayout2.addView(newText);
            linearLayout2.addView(speed);

            speed.setText(speedOfUser.get(i).toString() + "km/h");
            EditText volume = new EditText(getContext());


            volume.setText(volumeOfUser.get(i).toString() + "l");
            TextView volume_describe = new TextView(getContext());
            volume_describe.setText("Average volume");


            linearLayout3.addView(volume_describe);
            linearLayout3.addView(volume);


            ll2.setOrientation(LinearLayout.VERTICAL);

            ll2.addView(linearLayout);
            ll2.addView(linearLayout2);
            ll2.addView(linearLayout3);

            ll.addView(ll2);

            GridLayout gridLayout = new GridLayout(getContext());
            gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
            gridLayout.setColumnCount(2);
            gridLayout.setRowCount(1);

            gridLayout.addView(imageView, 0);
            gridLayout.addView(ll, 1);


            mainLayout.addView(gridLayout);

        }
        //
        //    db.deleteAllMeasurements();
        //SaveMeasurement();
//        Toast.makeText(getContext(), String.valueOf(dates.size()), Toast.LENGTH_SHORT).show();
//        Toast.makeText(getContext(), dates.get(39), Toast.LENGTH_SHORT).show();
        return view;

    }

    public void SaveMeasurement() {
        DbHandler db = new DbHandler(getContext());
        db.insertToHistory("26.4.2019", "20:00", 8, 21);
        db.insertToHistory("21.5.2019", "10:00", 8, 225);
        //Toast.makeText(getContext(), "data saved", Toast.LENGTH_SHORT).show();
    }

}



