package choiyh1006.example.com.seouldal2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MainActivity extends Activity {
    phpDown task;
    String now_weatherr="";
    TextView text, text2, text3, ment;
    TextView scene, rest, festival, food, view_more;

    RelativeLayout main_lay;
    String now_weather="";
    String now_temp="";

    long now=System.currentTimeMillis();
    Date date = new Date(now);
    int test = 0;

    Typeface typeRe;
    Typeface typeBo;
    Typeface typeLi;
    ImageView weather_icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        typeRe = Typeface.createFromAsset(getAssets(), "titi_regular.otf");
        typeBo = Typeface.createFromAsset(getAssets(), "titi_bold.otf");
        typeLi = Typeface.createFromAsset(getAssets(), "titi_light.otf");

        text = (TextView)findViewById(R.id.text);
        text2 = (TextView)findViewById(R.id.text2);
        text3 = (TextView)findViewById(R.id.text3);
        ment = (TextView)findViewById(R.id.ment);


        view_more = (TextView)findViewById(R.id.view_more);
        main_lay = (RelativeLayout)findViewById(R.id.relativeLayout);

        weather_icon = (ImageView)findViewById(R.id.weather_icon);
        scene=(TextView)findViewById(R.id.scene_btn);
        rest = (TextView)findViewById(R.id.rest_btn);
        festival = (TextView)findViewById(R.id.festival_btn);
        food = (TextView)findViewById(R.id.food_btn);

        scene.setTypeface(typeRe);
        rest.setTypeface(typeRe);
        festival.setTypeface(typeRe);
        food.setTypeface(typeRe);

        view_more.setTypeface(typeLi);
        text.setTypeface(typeRe);
        text2.setTypeface(typeRe);
        text3.setTypeface(typeBo);
        ment.setTypeface(typeLi);


        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean isGPS = lm.isProviderEnabled (LocationManager.GPS_PROVIDER);
        if(isGPS) {
            GpsInfo gps = new GpsInfo(MainActivity.this);

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            getLocation(latitude, longitude);

            task = new phpDown();
            task.execute("http://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitude+"&appid=c3c02a7bf6a05a0e88d10ec50dcf6efb");
        }
        else {
            test = 1;
            Toast.makeText(MainActivity.this, "GPS 사용을 체크해주세요 .", Toast.LENGTH_LONG).show();
            startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0);
        }
    }


    public void getLocation(double lat, double lng){
        String str = null;
        Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);

        List<Address> address;
        try {
            if (geocoder != null) {
                    address = geocoder.getFromLocation(lat, lng, 1);
                    if (address != null && address.size() > 0) {
                        str = address.get(0).getAddressLine(0).toString();
                    }
            }
        } catch (IOException e) {
            Log.e("MainActivity", "We can't find your address.");
            e.printStackTrace();
        }
        //String[] str_temp = str.split(" ");
        text2.setText(str);

    }
    private class phpDown extends AsyncTask<String, Integer,String> {
        @Override
        protected String doInBackground(String... urls) {
            StringBuilder jsonHtml = new StringBuilder();
            try {
                // 연결 url 설정
                URL url = new URL(urls[0]);
                // 커넥션 객체 생성
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                // 연결되었으면.
                if (conn != null) {
                    conn.setConnectTimeout(10000);
                    conn.setUseCaches(false);
                    // 연결되었음 코드가 리턴되면.
                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                        for (; ; ) {
                            // 웹상에 보여지는 텍스트를 라인단위로 읽어 저장.
                            String line = br.readLine();
                            if (line == null) break;
                            // 저장된 텍스트 라인을 jsonHtml에 붙여넣음
                            jsonHtml.append(line + "\n");
                        }
                        br.close();
                    }
                    conn.disconnect();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return jsonHtml.toString();

        }

        protected void onPostExecute(String str){
            Log.d("str", str);
            jsonParse(str);
        }
    }

    public void jsonParse(String str) {
        String[] strData = str.split("\"");
        int check = 0;
        for(int i=0; i<strData.length; i++){
            Log.d("tag", strData[i]);
            if(strData[i].equals("weather")){
                check = 1;
            }else if(strData[i].equals("main") && check == 1){
                now_weather = strData[i+2];
                check = 2;
            }else if(strData[i].equals("main") && check == 2){
                check = 3;
            }else if(strData[i].equals("temp") && check == 3){
                now_temp = strData[i+1].substring(1, strData[i+1].length()-1);
                check = 4;
            }
        }



        text3.setText(now_weather);
        switch(now_weather){
            case "Clouds":
                now_weatherr = "Clouds";
                main_lay.setBackgroundResource(R.drawable.bg_cloudy);
                weather_icon.setImageResource(R.drawable.icon_cloudy);
                String strr0 = "How about Bulgogi in cloudy day?";
                ment.setText(strr0);
                break;
            case "Clear":
                now_weatherr = "Clear";
                main_lay.setBackgroundResource(R.drawable.bg_clear);
                weather_icon.setImageResource(R.drawable.icon_clear);
                String strr1 = "How about Chicken and Beer\n in Han-River in sunny day?";
                //String strr1 = "How about MakGeolLi(Rice-wine) \nand Pajeon(Pancake) in rainy day?";
                ment.setText(strr1);
                break;
            case "Rain":
                now_weatherr = "Rain";
                main_lay.setBackgroundResource(R.drawable.bg_rain);
                weather_icon.setImageResource(R.drawable.icon_rain);
                String strr2 = "How about MakGeolLi(Rice-wine) and Pajeon(Pancake) in rainy day?";
                ment.setText(strr2);
                break;
            case "Snow":
                now_weatherr = "Snow";
                main_lay.setBackgroundResource(R.drawable.bg_snow);
                weather_icon.setImageResource(R.drawable.icon_snow);
                String strr3 = "How about KalGukSu(Noodle Soup)\n in snowy day?";
                ment.setText(strr3);
                break;
            default:
                now_weatherr = "Clear";
                main_lay.setBackgroundResource(R.drawable.bg_default);
                weather_icon.setImageResource(R.drawable.icon_clear);
                String strr4 = "How about Chicken and Beer\n in Han-River in sunny day?";
                ment.setText(strr4);
                break;
        }


//        SpannableStringBuilder sps = new SpannableStringBuilder();
//        SpannableString ss = new SpannableString(strr);
//        ss.setSpan(new ForegroundColorSpan(Color.parseColor("#000000")), 10, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        sps.append(ss);
//        ment.setText(sps);

    }


    public void onClick(View v){
        switch(v.getId()){
            case R.id.view_more:
                switch(now_weather){
                    case "Clouds"://불고기페이지
                        Intent i2 = new Intent(MainActivity.this, foodDetailPage.class);
                        i2.putExtra("now", 5);
                        startActivity(i2);
                        overridePendingTransition(R.anim.fade, R.anim.hold);
                        break;
                    case "Clear"://치맥페이지
                        Intent i3 = new Intent(MainActivity.this, foodDetailPage.class);
                        i3.putExtra("now", 0);
                        startActivity(i3);
                        overridePendingTransition(R.anim.fade, R.anim.hold);
                        break;
                    case "Rain"://파전페이지
                        Intent i4 = new Intent(MainActivity.this, foodDetailPage.class);
                        i4.putExtra("now", 1);
                        startActivity(i4);
                        overridePendingTransition(R.anim.fade, R.anim.hold);
                        break;
                    case "Snow"://칼국수페이지
                        Intent i5 = new Intent(MainActivity.this, foodDetailPage.class);
                        i5.putExtra("now", 2);
                        startActivity(i5);
                        overridePendingTransition(R.anim.fade, R.anim.hold);
                        break;
                    default:
                        Intent i6 = new Intent(MainActivity.this, foodDetailPage.class);
                        i6.putExtra("now", 0);
                        startActivity(i6);
                        overridePendingTransition(R.anim.fade, R.anim.hold);
                        break;
                }
                break;
            case R.id.food_btn:
                Intent intent = new Intent(MainActivity.this, foodPage.class);
                intent.putExtra("now", now_weatherr);
                startActivity(intent);
                overridePendingTransition(R.anim.fade, R.anim.hold);
                break;
            case R.id.scene_btn:
                Intent intent2 = new Intent(MainActivity.this, scenePage.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.fade, R.anim.hold);
                break;
            case R.id.rest_btn:
                Intent intent3 = new Intent(MainActivity.this, restPage.class);
                startActivity(intent3);
                overridePendingTransition(R.anim.fade, R.anim.hold);
                break;
            case R.id.festival_btn:                Intent intent4 = new Intent(MainActivity.this, festivalPage.class);
                startActivity(intent4);
                overridePendingTransition(R.anim.fade, R.anim.hold);
                break;
        }
    }


    /////////////////////////////////////////////시계//////////////////////////////////////////
    @Override
    protected void onStart(){
        super.onStart();
        Thread myThread = new Thread(new Runnable() {
            public void run(){
                while(true){
                    try{
                        handler.sendMessage(handler.obtainMessage());
                        Thread.sleep(1000);
                    }catch(Throwable t){

                    }
                }
            }
        });
        myThread.start();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            updateThread();
        }
    };

    private void updateThread(){
        //if(test != 1){
            String now_time = now_time();

            text.setText(now_time);
       //}

    }

    public String now_time(){
        long now = System.currentTimeMillis();
// 현재 시간을 저장 한다.
        Date date = new Date(now);
// 시간 포맷으로 만든다.
        SimpleDateFormat sdfHour = new SimpleDateFormat("HH");
        String temp = sdfHour.format(date);
        String ampm="";
        if(Integer.parseInt(temp)>=12){
            temp = String.valueOf(Integer.parseInt(temp) - 12);
            ampm = " PM";
        }else{
            ampm = " AM";
        }
        SimpleDateFormat sdfMin = new SimpleDateFormat("mm");
        String temp2 = sdfMin.format(date);
        temp = temp + ":" + temp2 + ampm;


        SimpleDateFormat sdfMon = new SimpleDateFormat("MM/dd");
        SimpleDateFormat sdfDate = new SimpleDateFormat("E");
        String str = sdfMon.format(date);
        String te = sdfDate.format(date);

        switch(te){
            case "월":
                te = " Mon ";
                break;
            case "화":
                te = " Tue ";
                break;
            case "수":
                te = " Wed ";
                break;
            case "목":
                te = " Thu ";
                break;
            case "금":
                te = " Fri ";
                break;
            case "토":
                te = " Sat ";
                break;
            case "일":
                te = " Sun ";
                break;
        }
        str = str + te + temp;
        return str;
    }
    /////////////////////////////////////////////시계끝//////////////////
}
