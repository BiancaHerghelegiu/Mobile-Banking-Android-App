package com.example.proiectmobilebanking;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        draw();
        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }
    private void draw(){
        List<Coordonate> list=new ArrayList<>();
        list.add(new Coordonate(44.426958,26.102490,"Bucharest"));
        list.add(new Coordonate(46.7667,23.6,"Cluj-Napoca"));
        list.add(new Coordonate(47.17,27.57,"Iasi"));
        list.add(new Coordonate(46.2333,27.6667,"Barlad"));
        LatLng firstcity=new LatLng(list.get(0).getLatitude(),list.get(0).getLatitude());
        for(Coordonate coordinate:list){
            LatLng city=new LatLng(coordinate.getLatitude(),coordinate.getLongitude());
            Marker marker=mMap.addMarker(new MarkerOptions().position(city).icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_attach_money_black_24dp)).title(coordinate.getName()));
            marker.showInfoWindow();
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(firstcity));


    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable resource = ContextCompat.getDrawable(context, vectorResId);
        resource.setBounds(0, 0, resource.getIntrinsicWidth(), resource.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(resource.getIntrinsicWidth(), resource.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        resource.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
    class Coordonate {
        private Double latitude;
        private Double longitude;
        private String name;

        public Coordonate(Double latitude, Double longitude, String city) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.name = city;
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
