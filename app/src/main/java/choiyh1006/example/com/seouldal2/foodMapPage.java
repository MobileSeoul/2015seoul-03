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


public class foodMapPage extends FragmentActivity {
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_map_page);

        typeRe = Typeface.createFromAsset(getAssets(), "titi_regular.otf");
        typeBo = Typeface.createFromAsset(getAssets(), "titi_bold.otf");
        typeLi = Typeface.createFromAsset(getAssets(), "titi_light.otf");

        photo  =(RelativeLayout)findViewById(R.id.photo);
        t_name = (TextView)findViewById(R.id.name);
        t_desc = (TextView)findViewById(R.id.desc);
        t_loc1= (TextView)findViewById(R.id.loc1);
        t_loc2 = (TextView)findViewById(R.id.loc2);
        t_call = (TextView)findViewById(R.id.call);


        Intent intent = getIntent();
        name = intent.getExtras().getString("name");
        desc = intent.getExtras().getString("desc");
        loc1 = intent.getExtras().getString("loc1");
        loc2 = intent.getExtras().getString("loc2");
        call = intent.getExtras().getString("call");
        longi = intent.getExtras().getDouble("longi");
        lati = intent.getExtras().getDouble("lati");
        likes = intent.getExtras().getInt("like");
        pos = intent.getExtras().getInt("pos");
        now_pos = intent.getExtras().getInt("now");

        switch(now_pos){
            case 0:
                if(pos == 0)
                    photo.setBackgroundResource(R.drawable.f0101);
                else if(pos == 1)
                    photo.setBackgroundResource(R.drawable.f0102);
                else if(pos == 2)
                    photo.setBackgroundResource(R.drawable.f0103);
                else if(pos == 3)
                    photo.setBackgroundResource(R.drawable.f0104);
                else if(pos == 4)
                    photo.setBackgroundResource(R.drawable.f0105);
                else if(pos == 5)
                    photo.setBackgroundResource(R.drawable.f0106);
                else if(pos == 6)
                    photo.setBackgroundResource(R.drawable.f0107);
                else if(pos == 7)
                    photo.setBackgroundResource(R.drawable.f0108);
                else if(pos == 8)
                    photo.setBackgroundResource(R.drawable.f0109);
                else if(pos == 9)
                    photo.setBackgroundResource(R.drawable.f0110);
                break;
            case 1:
                if(pos == 0)
                    photo.setBackgroundResource(R.drawable.f0201);
                else if(pos == 1)
                    photo.setBackgroundResource(R.drawable.f0202);
                else if(pos == 2)
                    photo.setBackgroundResource(R.drawable.f0203);
                else if(pos == 3)
                    photo.setBackgroundResource(R.drawable.f0204);
                else if(pos == 4)
                    photo.setBackgroundResource(R.drawable.f0205);
                else if(pos == 5)
                    photo.setBackgroundResource(R.drawable.f0206);
                else if(pos == 6)
                    photo.setBackgroundResource(R.drawable.f0207);
                else if(pos == 7)
                    photo.setBackgroundResource(R.drawable.f0208);
                else if(pos == 8)
                    photo.setBackgroundResource(R.drawable.f0210);
                break;
            case 2:
                if(pos == 0)
                    photo.setBackgroundResource(R.drawable.f0301);
                else if(pos == 1)
                    photo.setBackgroundResource(R.drawable.f0302);
                else if(pos == 2)
                    photo.setBackgroundResource(R.drawable.f0303);
                else if(pos == 3)
                    photo.setBackgroundResource(R.drawable.f0304);
                else if(pos == 4)
                    photo.setBackgroundResource(R.drawable.f0305);
                else if(pos == 5)
                    photo.setBackgroundResource(R.drawable.f0306);
                else if(pos == 6)
                    photo.setBackgroundResource(R.drawable.f0307);
                else if(pos == 7)
                    photo.setBackgroundResource(R.drawable.f0308);
                else if(pos == 8)
                    photo.setBackgroundResource(R.drawable.f0309);
                else if(pos == 9)
                    photo.setBackgroundResource(R.drawable.f0310);
                break;
            case 3:
                if(pos == 0)
                    photo.setBackgroundResource(R.drawable.f0401);
                else if(pos == 1)
                    photo.setBackgroundResource(R.drawable.f0402);
                else if(pos == 2)
                    photo.setBackgroundResource(R.drawable.f0403);
                else if(pos == 3)
                    photo.setBackgroundResource(R.drawable.f0404);
                else if(pos == 4)
                    photo.setBackgroundResource(R.drawable.f0405);
                else if(pos == 5)
                    photo.setBackgroundResource(R.drawable.f0406);
                else if(pos == 6)
                    photo.setBackgroundResource(R.drawable.f0407);
                else if(pos == 7)
                    photo.setBackgroundResource(R.drawable.f0408);
                else if(pos == 8)
                    photo.setBackgroundResource(R.drawable.f0409);
                break;
            case 4:
                if(pos == 0)
                    photo.setBackgroundResource(R.drawable.f0501);
                else if(pos == 1)
                    photo.setBackgroundResource(R.drawable.f0502);
                else if(pos == 2)
                    photo.setBackgroundResource(R.drawable.f0503);
                else if(pos == 3)
                    photo.setBackgroundResource(R.drawable.f0504);
                else if(pos == 4)
                    photo.setBackgroundResource(R.drawable.f0505);
                else if(pos == 5)
                    photo.setBackgroundResource(R.drawable.f0506);
                else if(pos == 6)
                    photo.setBackgroundResource(R.drawable.f0507);
                else if(pos == 7)
                    photo.setBackgroundResource(R.drawable.f0508);
                else if(pos == 8)
                    photo.setBackgroundResource(R.drawable.f0509);
                else if(pos == 9)
                    photo.setBackgroundResource(R.drawable.f0510);
                break;
            case 5:
                if(pos == 0)
                    photo.setBackgroundResource(R.drawable.f0601);
                else if(pos == 1)
                    photo.setBackgroundResource(R.drawable.f0602);
                else if(pos == 2)
                    photo.setBackgroundResource(R.drawable.f0603);
                else if(pos == 3)
                    photo.setBackgroundResource(R.drawable.f0604);
                else if(pos == 4)
                    photo.setBackgroundResource(R.drawable.f0605);
                else if(pos == 5)
                    photo.setBackgroundResource(R.drawable.f0606);
                else if(pos == 6)
                    photo.setBackgroundResource(R.drawable.f0607);
                else if(pos == 7)
                    photo.setBackgroundResource(R.drawable.f0608);
                else if(pos == 8)
                    photo.setBackgroundResource(R.drawable.f0609);
                else if(pos == 9)
                    photo.setBackgroundResource(R.drawable.f0610);
                break;
            case 6:
                if(pos == 0)
                    photo.setBackgroundResource(R.drawable.f0701);
                else if(pos == 1)
                    photo.setBackgroundResource(R.drawable.f0702);
                else if(pos == 2)
                    photo.setBackgroundResource(R.drawable.f0703);
                else if(pos == 3)
                    photo.setBackgroundResource(R.drawable.f0704);
                else if(pos == 4)
                    photo.setBackgroundResource(R.drawable.f0705);
                else if(pos == 5)
                    photo.setBackgroundResource(R.drawable.f0706);
                else if(pos == 6)
                    photo.setBackgroundResource(R.drawable.f0707);
                else if(pos == 7)
                    photo.setBackgroundResource(R.drawable.f0708);
                else if(pos == 8)
                    photo.setBackgroundResource(R.drawable.f0709);
                else if(pos == 9)
                    photo.setBackgroundResource(R.drawable.f0710);
                break;
            case 7:
                if(pos == 0)
                    photo.setBackgroundResource(R.drawable.f0801);
                else if(pos == 1)
                    photo.setBackgroundResource(R.drawable.f0802);
                else if(pos == 2)
                    photo.setBackgroundResource(R.drawable.f0803);
                else if(pos == 3)
                    photo.setBackgroundResource(R.drawable.f0804);
                else if(pos == 4)
                    photo.setBackgroundResource(R.drawable.f0805);
                else if(pos == 5)
                    photo.setBackgroundResource(R.drawable.f0806);
                else if(pos == 6)
                    photo.setBackgroundResource(R.drawable.f0807);
                else if(pos == 7)
                    photo.setBackgroundResource(R.drawable.f0808);
                else if(pos == 8)
                    photo.setBackgroundResource(R.drawable.f0809);
                else if(pos == 9)
                    photo.setBackgroundResource(R.drawable.f0810);
                break;
            case 8:
                if(pos == 0)
                    photo.setBackgroundResource(R.drawable.f0901);
                else if(pos == 1)
                    photo.setBackgroundResource(R.drawable.f0902);
                else if(pos == 2)
                    photo.setBackgroundResource(R.drawable.f0903);
                else if(pos == 3)
                    photo.setBackgroundResource(R.drawable.f0904);
                else if(pos == 4)
                    photo.setBackgroundResource(R.drawable.f0905);
                else if(pos == 5)
                    photo.setBackgroundResource(R.drawable.f0906);
                else if(pos == 6)
                    photo.setBackgroundResource(R.drawable.f0907);
                else if(pos == 7)
                    photo.setBackgroundResource(R.drawable.f0908);
                else if(pos == 8)
                    photo.setBackgroundResource(R.drawable.f0909);
                else if(pos == 9)
                    photo.setBackgroundResource(R.drawable.f0910);
                break;

            case 9:
                if(pos == 0)
                    photo.setBackgroundResource(R.drawable.f1001);
                else if(pos == 1)
                    photo.setBackgroundResource(R.drawable.f1002);
                else if(pos == 2)
                    photo.setBackgroundResource(R.drawable.f1003);
                else if(pos == 3)
                    photo.setBackgroundResource(R.drawable.f1004);
                else if(pos == 4)
                    photo.setBackgroundResource(R.drawable.f1005);
                else if(pos == 5)
                    photo.setBackgroundResource(R.drawable.f1006);
                else if(pos == 6)
                    photo.setBackgroundResource(R.drawable.f1007);
                else if(pos == 7)
                    photo.setBackgroundResource(R.drawable.f1008);
                else if(pos == 8)
                    photo.setBackgroundResource(R.drawable.f1009);
                else if(pos == 9)
                    photo.setBackgroundResource(R.drawable.f1010);
                break;
        }

        t_name.setText(name);
        t_desc.setText(desc);
        t_loc1.setText(loc1);
        t_loc2.setText(loc2);
        t_call.setText(call);


        t_name.setTypeface(typeRe);

        t_desc.setTypeface(typeLi);
        t_loc1.setTypeface(typeLi);
        t_loc2.setTypeface(typeLi);
        t_call.setTypeface(typeLi);

        title = (TextView)findViewById(R.id.title);
        title.setTypeface(typeBo);
        title.setText(name);

        // 위치정보 관리자를 생성한다.
        locationManager = (LocationManager) getSystemService(
                Context.LOCATION_SERVICE);

//        fragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);
        fragment =(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        googleMap = fragment.getMap();

        GpsInfo gps = new GpsInfo(foodMapPage.this);

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
        optFirst.title(name);// 제목 미리보기
        optFirst.snippet(desc);
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
                String[] n_name = name.split(" ");
                name = delete_space(n_name);
                task.execute("http://toad45.mireene.com/save_like.php?name="+name+"&like="+like);
                Toast.makeText(foodMapPage.this, "Success!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public String delete_space(String[] str){
        String new_str = "";
        for(int j=0; j<str.length; j++){
            new_str+=str[j];
            if(j!=str.length-1){
                new_str+="%20";
            }
        }
        return new_str;
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


