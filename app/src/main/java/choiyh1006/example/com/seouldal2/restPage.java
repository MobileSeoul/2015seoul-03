package choiyh1006.example.com.seouldal2;

import android.content.Context;
import android.graphics.Typeface;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class restPage extends FragmentActivity {

    SupportMapFragment fragment;
    GoogleMap googleMap;
    Marker marker;
    Typeface typeBo;
    TextView title;
    LocationManager locationManager;
    ArrayList<Double> lati = new ArrayList<Double>();
    ArrayList<Double> longi = new ArrayList<Double>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_page);

        typeBo = Typeface.createFromAsset(getAssets(), "titi_bold.otf");
        title= (TextView)findViewById(R.id.title);
        title.setTypeface(typeBo);
        // 위치정보 관리자를 생성한다.
        locationManager = (LocationManager) getSystemService(
                Context.LOCATION_SERVICE);

        fragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment);
        googleMap = fragment.getMap();

        Geocoder geocoder = new Geocoder(this);
        GpsInfo gps = new GpsInfo(restPage.this);

        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();

// Creating a LatLng object for the current location
        LatLng latLng = new LatLng(latitude, longitude);

// Showing the current location in Google Map
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

// Map 을 zoom 합니다.
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(17));

        loadTxt();

        for (int i = 0; i < lati.size(); i++) {
            LatLng latLng2 = new LatLng(lati.get(i), longi.get(i));

            MarkerOptions optFirst = new MarkerOptions();
            optFirst.position(latLng2);// 위도 • 경도
            googleMap.addMarker(optFirst).showInfoWindow();
        }
    }


    public void onClick(View v){
        switch(v.getId()){
            case R.id.back_btn:
                finish();
                break;
        }
    }

    public void loadTxt(){
        InputStream inputData = getResources().openRawResource(R.raw.toilet_sql);


        try {
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputData,"EUC_KR"));
            while(true){
                String string= bufferedReader.readLine();

                if(string != null){
                    String[] data = string.split(",");
                    double lati2 = Double.valueOf(data[0]);
                    double longi2 = Double.valueOf(data[1]);
                    lati.add(lati2);
                    longi.add(longi2);
                }else{

                    break;
                }
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

