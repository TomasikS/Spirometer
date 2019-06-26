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

public class total_average extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        page = getArguments().getInt("someInt", 0);
//        title = getArguments().getString("someTitle");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_total_average, container, false);


        GraphView graph = (GraphView) view.findViewById(R.id.graph2);

        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.MIDDLE);

        DbHandler db = new DbHandler(getContext());

        List<Integer> volume = new ArrayList<>();
        volume = db.GetVolume();

        List<Integer> speed = new ArrayList<>();
        speed = db.GetSpeed();
        TextView etVolume = (TextView) view.findViewById(R.id.editText2);
        TextView etSpeed = (TextView) view.findViewById(R.id.editText22);

        int avg_volume = 0;
        int avg_speed = 0;

        for (int j = 0; j < volume.size(); j++)
            avg_volume = avg_volume + volume.get(j);

        if (volume.size() > 0) {
            avg_volume = avg_volume / volume.size();
        }

        if (volume.size() == 0) {
            avg_volume = avg_volume;
        }


        etVolume.setText(String.valueOf(avg_volume) + " l");


        for (int j = 0; j < speed.size(); j++)
            avg_speed = avg_speed + speed.get(j);

        if (speed.size() > 0) {
            avg_speed = avg_speed / speed.size();
        }

        if (speed.size() == 0) {
            avg_speed = avg_speed;
        }


        etSpeed.setText(String.valueOf(avg_speed) + " km/h");


        DataPoint[] dataPoints = new DataPoint[volume.size()];
        for (int i = 0; i < volume.size(); i++) {

            dataPoints[i] = new DataPoint(i, volume.get(i));
        }


        DataPoint[] dataPointsSpeed = new DataPoint[speed.size()];
        for (int i = 0; i < speed.size(); i++) {

            dataPointsSpeed[i] = new DataPoint(i, speed.get(i));
        }


        LineGraphSeries<DataPoint> series_Speed = new LineGraphSeries<DataPoint>(dataPointsSpeed);
        LineGraphSeries<DataPoint> series_Volume = new LineGraphSeries<DataPoint>(dataPoints);

        series_Speed.setColor(R.color.colorPrimaryDark);
        series_Volume.setColor(R.color.colorPrimaryDark);

        graph.addSeries(series_Volume);

        graph.addSeries(series_Speed);
        return view;
    }
}
