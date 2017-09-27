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


public class sceneMapPage extends FragmentActivity {
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
    String[] sql_name = {"Naksanseonggwak-gil(Nak-san-seong-gwak-gil)","N%20Seoul%20Tower%20(Nam%20San%20Tower)", "Chang-gyeong-gung%20Palace%20Night%20hours", "Dongdaemoon%20Digital%20Plaza(ddp)", "Ttukseom%20Resort", "Bugak%20Mountain%20Highway"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_map_page);

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

        String[] test = {"Naksanseonggwak-gil", "N Seoul Tower", "Chang-gyeong-gung", "DDP", "Ttukseom Resort","Bugak Mountain Highway"};
        switch(now_pos){
            case 0:
                title.setText(test[0]);

                t_name.setText("Naksanseonggwak-gil");
                t_desc.setText("Hansung Univ. Station, Exit 4");
                t_loc2.setText("Dongdaemun Station, Exit 5, 10");
                t_call.setText("");
                photo.setBackgroundResource(R.drawable.n1);

                break;
            case 1:
                title.setText(test[1]);
                t_name.setText("N Seoul Tower");
                t_desc.setText("Mon-Fri / Sun 10:00~23:00,\nSat 10:00~24:00");
                t_loc2.setText("105 Namsangongwon-gil, \nYongsan-gu, Seoul");
                t_call.setText("02-3455-9277, 9288");
                photo.setBackgroundResource(R.drawable.n2);
                break;
            case 2:
                title.setText(test[2]);

                t_name.setText("Chang-gyeong-gung");
                t_desc.setText("19:00~22:00");
                t_loc2.setText("185 Changgyeonggung-ro, \nJongno-gu, Seoul");
                t_call.setText("02-762-4868");

                photo.setBackgroundResource(R.drawable.n3);
                break;
            case 3:
                title.setText(test[3]);

                t_name.setText("DDP");
                t_desc.setText("");
                t_loc2.setText("D281, Eulji-ro, Jung-gu,Seoul 100-197");
                t_call.setText("02-2153-0000");

                photo.setBackgroundResource(R.drawable.n4);
                break;
            case 4:
                title.setText(test[4]);

                t_name.setText("Ttukseom Resort");
                t_desc.setText("");
                t_loc2.setText("428 Jayang-dong, Gwangjin-gu, Seoul");
                t_call.setText("02-3780-0736");

                photo.setBackgroundResource(R.drawable.n5);
                break;
            case 5:
                title.setText(test[5]);

                t_name.setText("Bugak Mountain Highway");
                t_desc.setText("");
                t_loc2.setText("Jeongneung-dong, Seongbuk-gu, Seoul");
                t_call.setText("02-731-0673");

                photo.setBackgroundResource(R.drawable.n6);
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

        GpsInfo gps = new GpsInfo(sceneMapPage.this);

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
                task.execute("http://toad45.mireene.com/save_like_night.php?name="+sql_name[now_pos]+"&like="+like);
                Toast.makeText(sceneMapPage.this, "Success!", Toast.LENGTH_SHORT).show();
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


