package choiyh1006.example.com.seouldal2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class festivalMapPage extends FragmentActivity {
    SupportMapFragment fragment;
    GoogleMap googleMap;
    Marker marker;

    TextView title, t_name, t_desc, t_loc1, t_loc2, t_call;
    LocationManager locationManager;
    RelativeLayout photo;

    String name= "";
    String desc = "";
    String loc1 = "";
    String loc2 = "";
    String call = "";
    double longi, lati;
    int likes;

    int pos, now_pos;
    Typeface typeRe;
    Typeface typeBo;
    Typeface typeLi;
    phpDown task;
    String[] test = {"Jeong-dong%20Culture%20Night", "The%20Seoul%20Bamdokkaebi%20Night%20Market", "Namsan%20Mountain%20Goblins%20alloween", "Han%20River%20lights%20of%20Fall%20event", "Lotte%20World%20Halloween%20Party"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festival_map_page);

        typeRe = Typeface.createFromAsset(getAssets(), "titi_regular.otf");
        typeBo = Typeface.createFromAsset(getAssets(), "titi_bold.otf");
        typeLi = Typeface.createFromAsset(getAssets(), "titi_light.otf");

        photo  =(RelativeLayout)findViewById(R.id.photo);
        t_name = (TextView)findViewById(R.id.name);
        t_desc = (TextView)findViewById(R.id.desc);
        t_loc1= (TextView)findViewById(R.id.loc1);
        t_loc2 = (TextView)findViewById(R.id.loc2);
        t_call = (TextView)findViewById(R.id.call);

        title = (TextView)findViewById(R.id.title);
        title.setTypeface(typeBo);

        Intent intent = getIntent();

        longi = intent.getExtras().getDouble("longi");
        lati = intent.getExtras().getDouble("lati");
        likes = intent.getExtras().getInt("like");
        now_pos = intent.getExtras().getInt("now");


        switch(now_pos){
            case 0:
                title.setText(test[0]);
                t_name.setText("Jeong-dong Culture Night");
                t_desc.setText("Hansung Univ. Station, Exit 4");
                t_loc1.setText("October 29-31, 2015 (for three nights)");
                t_loc2.setText("Jeong-dong, Jung-gu");
                t_call.setText("02-3396-844");
                photo.setBackgroundResource(R.drawable.f1);

                break;
            case 1:
                title.setText(test[1]);
                t_name.setText("The Seoul Bamdokkaebi Night Market");
                t_desc.setText("October 1~2, 8~10, 16~17, 18:00~24:00");
                t_loc2.setText("Yeouido Hangang Park");
                t_call.setText("070-8230-8911");
                photo.setBackgroundResource(R.drawable.f2);
                break;
            case 2:
                title.setText(test[2]);

                t_name.setText("Namsan Mountain Goblins Halloween");
                t_desc.setText("10/17(Sat)11:00~16:00\n10/30(Sat) 10:00~22:00\n11/01(Sun) 10:00~16:00");
                t_loc2.setText("Jae-Mi Ro(Fun road) \n(Myeong-dong Station exit 3 ~ \nSeoul animation center)");
                t_call.setText("Halloween Party, Costume play");

                photo.setBackgroundResource(R.drawable.f3);
                break;
            case 3:
                title.setText(test[3]);

                t_name.setText("Han River lights of Fall event ");
                t_desc.setText("Oct 21-25, 2015");
                t_loc2.setText("Ttukseom Hangang Park");
                t_call.setText("19:00~23:00");

                photo.setBackgroundResource(R.drawable.f4);
                break;
            case 4:
                title.setText(test[4]);

                t_name.setText("Lotte World Halloween Party");
                t_desc.setText("September 5 - November 1, 2015");
                t_loc2.setText("240 Olympic-ro Songpa-Gu, Seoul,\n Korea HotelLotte Lotteworld");
                t_call.setText("");

                photo.setBackgroundResource(R.drawable.f5);
                break;

        }

        t_name.setTypeface(typeRe);
        t_desc.setTypeface(typeLi);
        t_loc1.setTypeface(typeLi);
        t_loc2.setTypeface(typeLi);
        t_call.setTypeface(typeLi);



        // 위치정보 관리자를 생성한다.
        locationManager = (LocationManager) getSystemService(
                Context.LOCATION_SERVICE);

//        fragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);
        fragment =(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        googleMap = fragment.getMap();

        GpsInfo gps = new GpsInfo(festivalMapPage.this);

        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();

// Creating a LatLng object for the current location
        LatLng latLng = new LatLng(lati, longi);

// Showing the current location in Google Map
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

// Map 을 zoom 합니다.
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(17));


        MarkerOptions optFirst = new MarkerOptions();
        optFirst.position(latLng);// 위도 • 경도
        optFirst.title(test[now_pos]);// 제목 미리보기
        // optFirst.snippet(desc);
//        optFirst.icon(BitmapDescriptorFactory.fromResource(
//                R.drawable.ic_launcher));
        googleMap.addMarker(optFirst).showInfoWindow();
    }



    public void onClick(View v){
        switch(v.getId()){
            case R.id.back_btn:
                finish();
                break;
            case R.id.like_btn:
                task = new phpDown();
                String like = String.valueOf(likes+1);
                task.execute("http://toad45.mireene.com/save_like_festival.php?name="+test[now_pos]+"&like="+like);
                Toast.makeText(festivalMapPage.this, "Success!", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    private class phpDown extends AsyncTask<String, Integer,String> {
        @Override
        protected String doInBackground(String... urls) {
            StringBuilder jsonHtml = new StringBuilder();
            try{
                // 연결 url 설정
                URL url = new URL(urls[0]);
                // 커넥션 객체 생성
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                // 연결되었으면.
                if(conn != null){
                    conn.setConnectTimeout(10000);
                    conn.setUseCaches(false);
                    // 연결되었음 코드가 리턴되면.
                    if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                        for(;;){
                            // 웹상에 보여지는 텍스트를 라인단위로 읽어 저장.
                            String line = br.readLine();
                            if(line == null) break;
                            // 저장된 텍스트 라인을 jsonHtml에 붙여넣음
                            jsonHtml.append(line + "\n");
                        }
                        br.close();
                    }
                    conn.disconnect();
                }
            } catch(Exception ex){
                ex.printStackTrace();
            }
            return jsonHtml.toString();

        }

        protected void onPostExecute(String str){
            Log.d("str", str);
            //txtView.setText(str);
        }
    }
}


