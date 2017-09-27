package choiyh1006.example.com.seouldal2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class sceneDetailPage extends Activity {
    TextView title,  dynamic;
    Typeface typeRe;
    Typeface typeBo;
    Typeface typeLi;
    int now_pos;

    TextView name, desc, loc1, loc2, call, like, viewde;
    RelativeLayout photo, lay;
    RelativeLayout food_lay;
    phpDown task;

    int now_like;
    double[] longi = {127.009325,126.989286,126.994642, 127.01011,127.068753,126.975710};
    double[] lati = {37.572569, 37.550985,37.580246, 37.566781, 37.529211, 37.600611};
    int[] likes=new int[100];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_detail_page);

        typeRe = Typeface.createFromAsset(getAssets(), "titi_regular.otf");
        typeBo = Typeface.createFromAsset(getAssets(), "titi_bold.otf");
        typeLi = Typeface.createFromAsset(getAssets(), "titi_light.otf");

        Intent intent= getIntent();
        now_pos = intent.getExtras().getInt("now");

        task = new phpDown();
        task.execute("http://toad45.mireene.com/night_likes.php");


        lay = (RelativeLayout) findViewById(R.id.lay);
        viewde = (TextView)findViewById(R.id.viewdetail);
        food_lay = (RelativeLayout)findViewById(R.id.food_lay);
        dynamic = (TextView)findViewById(R.id.dynamic);
        name = (TextView)findViewById(R.id.name);
        desc = (TextView)findViewById(R.id.desc);
        loc1 = (TextView)findViewById(R.id.loc1);
        loc2 = (TextView)findViewById(R.id.loc2);
        call = (TextView)findViewById(R.id.call);
        like = (TextView)findViewById(R.id.likenum);

        photo = (RelativeLayout)findViewById(R.id.photo);

        name.setTypeface(typeRe);
        like.setTypeface(typeLi);
        desc.setTypeface(typeLi);
        loc1.setTypeface(typeLi);
        loc2.setTypeface(typeLi);
        call.setTypeface(typeLi);
        viewde.setTypeface(typeLi);

        title = (TextView)findViewById(R.id.title);
        title.setTypeface(typeBo);

        dynamic.setTypeface(typeLi);


        String[] test = {"Naksanseonggwak-gil", "N Seoul Tower", "Chang-gyeong-gung", "DDP", "Ttukseom Resort","Bugak Mountain Highway"};
        switch(now_pos){
            case 0:
                title.setText(test[0]);
                dynamic.setText("-Explore Mural Village on Day and \nenjoy night view at Naksanseonggwak-gil \n" +
                        "Naksanseonggwak-gil is famous place for beautiful\n night view and it is also good to walk along.\n" +
                        "Popular dating place for couples.\n");
                food_lay.setBackgroundResource(R.drawable.night_view_1_1);
                name.setText("Naksanseonggwak-gil");
                desc.setText("Hansung Univ. Station, Exit 4");
                loc2.setText("Dongdaemun Station, Exit 5, 10");
                call.setText("");
                photo.setBackgroundResource(R.drawable.n1);

                break;
            case 1:
                title.setText(test[1]);
                dynamic.setText("-Symbol of Seoul, The N Seoul Tower.\n Water painting made by green lights\n" +
                                "The N Seoul Tower is located around Myeongdong.\n Enjoy shopping at Myeongdong on day and beautiful \nnight view at the N Seoul Tower. \n"
                );
                food_lay.setBackgroundResource(R.drawable.night_view_2_1);
                name.setText("N Seoul Tower");
                desc.setText("Mon-Fri / Sun 10:00~23:00,\nSat 10:00~24:00");
                loc2.setText("105 Namsangongwon-gil, \nYongsan-gu, Seoul");
                call.setText("02-3455-9277, 9288");
                photo.setBackgroundResource(R.drawable.n2);
                break;
            case 2:
                title.setText(test[2]);
                dynamic.setText("-Moonlight Tour at Chang-gyeong-gung Palace\n" +
                        "Experience special Old Palace\n with atmosphere of autumn night.\n");
                food_lay.setBackgroundResource(R.drawable.night_view_3_1);
                name.setText("Chang-gyeong-gung");
                desc.setText("19:00~22:00");
                loc2.setText("185 Changgyeonggung-ro, \nJongno-gu, Seoul");
                call.setText("02-762-4868");
                photo.setBackgroundResource(R.drawable.n3);
                break;
            case 3:
                title.setText(test[3]);
                dynamic.setText("-New rising hot night view place ddp! \n" +
                        "Your eyes can enjoy harmony of the lights\n turning on when sun sets.\n Exhibition of LED Rose Garden \nis also attractive. \n");
                food_lay.setBackgroundResource(R.drawable.night_view_4_1);
                name.setText("DDP");
                desc.setText("");
                loc2.setText("D281, Eulji-ro, Jung-gu,Seoul 100-197");
                call.setText("02-2153-0000");
                photo.setBackgroundResource(R.drawable.n4);
                break;
            case 4:
                title.setText(test[4]);
                dynamic.setText("-Emotion of autumn night, Ttukseom Resort\n" +
                        "You can fully enjoy the feeling of \ncool autumn night and night scene \nat once in Ttukseom Resort\n");
                food_lay.setBackgroundResource(R.drawable.night_view_5_1);
                name.setText("Ttukseom Resort");
                desc.setText("");
                loc2.setText("428 Jayang-dong, Gwangjin-gu, Seoul");
                call.setText("02-3780-0736");
                photo.setBackgroundResource(R.drawable.n5);
                break;
            case 5:
                title.setText(test[5]);
                dynamic.setText("-Night drive course - Bugak Skyway\n" +
                        "This is where you can feel the romantic \natmosphere of the autumn night.\n Nice scene of the Octagonal pavilion\nin the mountains is welcoming visitors.\n");
                food_lay.setBackgroundResource(R.drawable.night_view_6_1);
                name.setText("Bugak Mountain Highway");
                desc.setText("");
                loc2.setText("Jeongneung-dong, Seongbuk-gu, Seoul");
                call.setText("02-731-0673");
                photo.setBackgroundResource(R.drawable.n6);
                break;
        }

    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.back_btn:
                finish();
                break;
            case R.id.lay:
                Intent intent = new Intent(sceneDetailPage.this, sceneMapPage.class);
                intent.putExtra("now", now_pos);
                intent.putExtra("lati", lati[now_pos]);
                intent.putExtra("longi",longi[now_pos]);
                intent.putExtra("like", now_like);
                startActivity(intent);
                overridePendingTransition(R.anim.fade, R.anim.hold);
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
            showList(str);
            //txtView.setText(str);
        }
    }
    public void showList(String str){
        int data_length = 0;
        String[] data = str.split("_");
        data_length = Integer.parseInt(data[0]);

        for(int i=0; i<data_length; i++){
            likes[i] = Integer.parseInt(data[i+1]);
            Log.d("ads", data[i+1]);
        }

        like.setText(String.valueOf(likes[now_pos]));
        now_like = likes[now_pos];

    }
}
