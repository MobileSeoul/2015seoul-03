package choiyh1006.example.com.seouldal2;


import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class detailPage extends FragmentActivity {

    SupportMapFragment fragment;
    GoogleMap googleMap;
    Marker marker;

    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);



        // 위치정보 관리자를 생성한다.
        locationManager = (LocationManager) getSystemService(
                Context.LOCATION_SERVICE);

//        fragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);
        fragment =(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        googleMap = fragment.getMap();

        GpsInfo gps = new GpsInfo(detailPage.this);

        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();

// Creating a LatLng object for the current location
        LatLng latLng = new LatLng(latitude, longitude);

// Showing the current location in Google Map
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

// Map 을 zoom 합니다.
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(17));


        MarkerOptions optFirst = new MarkerOptions();
        optFirst.position(latLng);// 위도 • 경도
        optFirst.title("우승훈 위치");// 제목 미리보기
        optFirst.snippet("멍청이");
//        optFirst.icon(BitmapDescriptorFactory.fromResource(
//                R.drawable.ic_launcher));
        googleMap.addMarker(optFirst).showInfoWindow();
    }
}
