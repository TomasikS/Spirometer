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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

public class history extends Fragment {


    // Store instance variables based on arguments passed
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_history, null);

        for (int i = 0; i <= 20; i++) {


            LinearLayout mainLayout = view.findViewById(R.id.ll2);
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout linearLayoutVertical = new LinearLayout(getContext());
            linearLayoutVertical.setOrientation(LinearLayout.VERTICAL);


            TextView newText = new TextView(getContext());
            newText.setText("Average speed");
            TextView editText = new EditText(getContext());
            TextView speed = new EditText(getContext());


            mainLayout.setGravity(Gravity.CENTER);
            ImageView imageView = new ImageView(getContext());
            imageView.setBackgroundColor(Color.BLACK);


            linearLayout.setLayoutParams(
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                            , LinearLayout.LayoutParams.WRAP_CONTENT));

            linearLayout.setPadding(40, 20, 40, 20);
            linearLayout.setGravity(Gravity.CENTER);

            editText.setLayoutParams(
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                            , LinearLayout.LayoutParams.WRAP_CONTENT));
            newText.setLayoutParams(
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                            , LinearLayout.LayoutParams.WRAP_CONTENT));

            imageView.setLayoutParams(new LinearLayout.LayoutParams(250, 250));

            imageView.setBackgroundResource(R.mipmap.ic_launcher_round);


            linearLayout.addView(imageView);


            linearLayoutVertical.addView(editText);


            linearLayoutVertical.addView(newText);
            linearLayoutVertical.addView(speed);
            linearLayout.addView(linearLayoutVertical);
            mainLayout.addView(linearLayout);
        }
        return view;

    }
}



