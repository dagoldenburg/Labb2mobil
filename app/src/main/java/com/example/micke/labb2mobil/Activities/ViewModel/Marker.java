package com.example.micke.labb2mobil.Activities.ViewModel;

import android.view.View;
import android.widget.ImageView;

import com.example.micke.labb2mobil.R;

import java.util.ArrayList;

/**
 * Created by Micke on 11/28/2017.
 */

public class Marker extends GameObject {
    private static int[] whiteMarkerIds = {
            R.id.wMarker0,R.id.bMarker0,
            R.id.wMarker1,R.id.bMarker1,
            R.id.wMarker2,R.id.bMarker2,
            R.id.wMarker3,R.id.bMarker3,
            R.id.wMarker4,R.id.bMarker4,
            R.id.wMarker5,R.id.bMarker5,
            R.id.wMarker6,R.id.bMarker6,
            R.id.wMarker7,R.id.bMarker7,
            R.id.wMarker8,R.id.bMarker8,
            R.id.wMarker9,R.id.bMarker9
    };
    private static ArrayList<Marker> markers = new ArrayList<>();
    private static int counter = 0;
    public Marker(View v, int position) {
            super((ImageView) v.findViewById(counter++), position);
    }

    public static void initMarkers(){
        markers = new ArrayList<>();
    }

    public static ArrayList<Marker> getMarkers() {
        return markers;
    }

    public static void setMarkers(ArrayList<Marker> m) {
        markers = m;
    }
}
