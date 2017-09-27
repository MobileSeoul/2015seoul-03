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


public class festivalDetailPage extends Activity {
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
    double[] longi = {126.974085, 126.926419, 126.985534, 127.071307, 127.098161};
    double[] lati = {37.564781, 37.531662, 37.560551, 37.529173, 37.511126};
    int[] likes=new int[100];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festival_detail_page);

        typeRe = Typeface.createFromAsset(getAssets(), "titi_regular.otf");
        typeBo = Typeface.createFromAsset(getAssets(), "titi_bold.otf");
        typeLi = Typeface.createFromAsset(getAssets(), "titi_light.otf");

        Intent intent= getIntent();
        now_pos = intent.getExtras().getInt("now");

        task = new phpDown();
        task.execute("http://toad45.mireene.com/festival_like.php");


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


        String[] test = {"Jeong-dong Culture Night", "The Seoul Bamdokkaebi Night Market", "Namsan Mountain Goblins Halloween", "Han River lights of Fall event", "Lotte World Halloween Party"};
        switch(now_pos){
            case 0:
                title.setText(test[0]);
                dynamic.setText("-Stroll along Jeong-dong on the last night of October\n" +
                        "Jeong-dong is where the country’s early \nmodern history was started and thus contains\n many precious cultural heritage objects. \n" +
                        "The cultural heritage objects include \nDeoksugung Palace, museums, and foreign embassies. \n" +
                        "Here in Jeong-dong, we are holding \na special night festival.\n" +
                        "During the Jeong-dong Culture Night, \nhistoric and cultural facilities will \nbe open until late at night.  \nAn array of performances and special things \nto see will be held and provided.\n");
                food_lay.setBackgroundResource(R.drawable.festival_1_1);
                name.setText("Jeong-dong Culture Night");
                desc.setText("Hansung Univ. Station, Exit 4");
                loc1.setText("October 29-31, 2015 (for three nights)");
                loc2.setText("Jeong-dong, Jung-gu");
                call.setText("02-3396-844");
                photo.setBackgroundResource(R.drawable.f1);

                break;
            case 1:
                title.setText(test[1]);
                dynamic.setText("- A sensational market lights up \nthe night at Hangang River!\n" +
                                "A new Seoul market tradition is about to begin.\n" +
                                "The Seoul Bamdokkaebi Night Market will soon open,\n offering all kinds of delicious foods, \nrare and unique crafts and goods, and\n exciting performances that highlight \nSeoul’s rich cultural and artistic heritage.\n" +
                                "Expected to be the largest night market in Korea,\n Seoul Bamdokkaebi will be held at Yeouido\n Hangang Park in October 2015.\n" +
                                "Come and enjoy the exciting\n nightlife of Seoul with friends and family!\n"
                );
                food_lay.setBackgroundResource(R.drawable.festival_2_1);
                name.setText("The Seoul Bamdokkaebi Night Market");
                desc.setText("October 1~2, 8~10, 16~17, 18:00~24:00");
                loc2.setText("Yeouido Hangang Park");
                call.setText("070-8230-8911");
                photo.setBackgroundResource(R.drawable.f2);
                break;
            case 2:
                title.setText(test[2]);
                dynamic.setText("-Come and enjoy Road of comics 'Jae-Mi Ro\n(Fun Road)'!" +
                        "From Myeong-dong Station exit 3\n to Seoul animation center called \n'Jae-Mi Ro(Fun road)'\n is special theme road which\n tries to get imagination of comics.\n You can walk along following orange pattern\n marked on floor and enjoy comic murals. \nYou can also take pictures with cute characters. \n");
                food_lay.setBackgroundResource(R.drawable.festival_3_1);
                name.setText("Namsan Mountain Goblins Halloween");
                desc.setText("10/17(Sat)11:00~16:00\n10/30(Sat) 10:00~22:00\n11/01(Sun) 10:00~16:00");
                loc2.setText("Jae-Mi Ro(Fun road) \n(Myeong-dong Station exit 3 ~ \nSeoul animation center)");
                call.setText("Halloween Party, Costume play");
                photo.setBackgroundResource(R.drawable.f3);
                break;
            case 3:
                title.setText(test[3]);
                dynamic.setText("-Fall Night at Han-river is bright,\n Han river lights of Fall event!\n" +
                        "Clear sky and cool breeze and the beautifu\nl natural landscape that is steeped in colors.\n" +
                        "It is just the autumn full of romance. \n" +
                        "To enjoy fully the Fall, Yes! It's the Han River!\n" +
                        "Why don't you fill the deep emotion of fall,\n" +
                        "With the romantic falling light\n in Han river, the \"Festival of Lights\".\n\n");
                food_lay.setBackgroundResource(R.drawable.festival_4_1);
                name.setText("Han River lights of Fall event ");
                desc.setText("Oct 21-25, 2015");
                loc2.setText("Ttukseom Hangang Park");
                call.setText("19:00~23:00");
                photo.setBackgroundResource(R.drawable.f4);
                break;
            case 4:
                title.setText(test[4]);
                dynamic.setText("-Happy Halloween Party\n" +
                        "Ghost dance party of Halloween friends! \n" +
                        "Exciting dance party along with \nHalloween fairies and ghost friends!\n" +
                        "Happy Halloween party, full of attractions \nsuch as Halloween band dressed in pumpkin \nclothing and charming masked characters!\n" +
                        "Exciting ghost party where character friends\n invited to Lotty's Halloween party and \nghost friends enchanted by magic play together!\n" +
                        "Happy Halloween party, full of various joys,\n including Dracula's love story, featuring ghost friends!\n");
                food_lay.setBackgroundResource(R.drawable.festival_5_1);
                name.setText("Lotte World Halloween Party");
                desc.setText("September 5 - November 1, 2015");
                loc2.setText("240 Olympic-ro Songpa-Gu, Seoul,\n Korea HotelLotte Lotteworld");
                call.setText("");
                photo.setBackgroundResource(R.drawable.f5);
                break;

        }

    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.back_btn:
                finish();
                break;
            case R.id.lay:
                Intent intent = new Intent(festivalDetailPage.this, festivalMapPage.class);
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
