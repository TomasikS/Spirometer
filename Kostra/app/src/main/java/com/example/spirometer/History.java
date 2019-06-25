package com.example.spirometer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class History  extends AppCompatActivity {
private SlidrInterface slidr;

public void onCreate(Bundle savedInstanceState)

{
  super.onCreate(savedInstanceState);
  setContentView(R.layout.history);
  slidr= Slidr.attach(this);
}

public void lockSide(View v)

{

    slidr.lock();

}

public void unlockSide(View v)

    {
     slidr.unlock();

    }




}
