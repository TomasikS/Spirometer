package com.example.kostra;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;


public class LastMeasure extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.lastmeasure, container, false);
        GraphView graph = (GraphView) view.findViewById(R.id.graph2);


        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.MIDDLE);


        DbHandler db = new DbHandler(getContext());

        List<Integer> volume = new ArrayList<>();
        volume = db.GetVolume();

        List<Integer> speed = new ArrayList<>();
        speed = db.GetSpeed();


        DataPoint[] dataPoints_volume = new DataPoint[volume.size()];
        DataPoint[] dataPoints_speed = new DataPoint[volume.size()];


        for (int i = 0; i < volume.size(); i++) {

            dataPoints_volume[i] = new DataPoint(i, volume.get(i));
        }


        for (int i = 0; i < speed.size(); i++) {

            dataPoints_speed[i] = new DataPoint(i, speed.get(i));
        }


        LineGraphSeries<DataPoint> series_volume = new LineGraphSeries<DataPoint>(dataPoints_volume);
        LineGraphSeries<DataPoint> series_speed = new LineGraphSeries<DataPoint>(dataPoints_speed);


        series_volume.setColor(R.color.colorPrimaryDark);
        graph.addSeries(series_volume);
        series_speed.setColor(R.color.colorPrimaryDark);
        graph.addSeries(series_speed);


        TextView speedText = view.findViewById(R.id.speed);
        TextView volumeText = view.findViewById(R.id.volume);

        speedText.setText(speed.get(speed.size() - 1).toString() + "km/h");
        volumeText.setText(volume.get(volume.size() - 1).toString() + "l");


        return view;


    }


}
