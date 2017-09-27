package choiyh1006.example.com.seouldal2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class foodDetailPage extends Activity {

    String[] name = new String[10];
    String[] desc = new String[10];
    String[] loc1 = new String[10];
    String[] loc2 = new String[10];
    String[] call = new String[10];
    int[] likes = new int[10];
    double[] lati = new double[10];
    double[] longi = new double[10];

    private ListView mListView = null;
    private ListViewAdapter mAdapter = null;
    RelativeLayout food_lay;
    TextView title,  dynamic, dynamic2;
    Typeface typeRe;
    Typeface typeBo;

    Typeface typeLi;
    int now_pos = 0;
    phpDown task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail_page);

        task = new phpDown();
        task.execute("http://toad45.mireene.com/food.php");


        typeRe = Typeface.createFromAsset(getAssets(), "titi_regular.otf");
        typeBo = Typeface.createFromAsset(getAssets(), "titi_bold.otf");
        typeLi = Typeface.createFromAsset(getAssets(), "titi_light.otf");

        Intent intent= getIntent();
        now_pos = intent.getExtras().getInt("now");

        food_lay = (RelativeLayout)findViewById(R.id.food_lay);
        dynamic = (TextView)findViewById(R.id.dynamic);
        dynamic2 = (TextView)findViewById(R.id.dynamic2);

        title = (TextView)findViewById(R.id.title);

        title.setTypeface(typeBo);

        dynamic.setTypeface(typeLi);
        dynamic2.setTypeface(typeRe);

        dynamic.setText("Chic-Mac is a abbreviation to call together \nthe chicken and beer." +
                "\nChicken has become one of the best food  \nto eat with beer and it has become one of the highlights \n of drinking culture of Koreans.");
        String[] test = {"Chicken & Beer", "Pa-jeon&Mak-Geol-Li", "Whole Chicken with noodles", "Flour based food", "Pork belly","Bulgogi", "Naeng Myeon", "Dak-galbi(Spicy Stir-fried Chicken)", "Bibimbap", "Pat bing su" };

        switch(now_pos){
            case 0:
                title.setText(test[0]);
                dynamic.setText("Chic-Mac is a abbreviation to call together \nthe chicken and beer." +
                        "\nChicken has become one of the best food  \nto eat with beer and it has become one of the \nhighlights of drinking culture of Koreans.");
                food_lay.setBackgroundResource(R.drawable.food_img_1);
                break;
            case 1:
                title.setText(test[1]);
                dynamic.setText("Cultural food which Korean looks for on rainy days.\n"+
                   "It is made by putting seafood and variety of \ningredients such as vegetables in to dough \nand fry with pan just like pancakes.\n"+
                "You can enjoy the unique taste of Best harmony \nwith Korean traditional drink called 'Mak-gul-li' \nwhich is sweet rice wine."
                );
                food_lay.setBackgroundResource(R.drawable.food_img_2);
                break;
            case 2:
                title.setText(test[2]);
                dynamic.setText("Put whole chicken in a broth and boil it. \n" +
                        "Potatoes, rice, ginseng, jujube, mushroom and \nother various ingredients make the deep \nflavor of soup.\n" +
                        "After eating the meat with vinegar soy sauce,\n put noodles into boiling soup and enjoy. \n");
                food_lay.setBackgroundResource(R.drawable.food_img_3);
                break;
            case 3:
                title.setText(test[3]);
                dynamic.setText("One of the most popular Korean snack food \nmade from soft rice cake, \nfish cake and sweet red chili sauce; gochujang.");
                food_lay.setBackgroundResource(R.drawable.food_img_4);
                break;
            case 4:
                title.setText(test[4]);
                dynamic.setText("It is Pork belly meet which seems like a thick beacon.\n The literal meaning of the word is \n“three (sam) layered (gyeop) flesh (sal)”,\n referring to what appears to be three layers \nthat are visible in the meat. \n" +
                        "It is cooked on a grill and enjoy \nmost commonly with Kim-chi and salt.\n");
                food_lay.setBackgroundResource(R.drawable.food_img_5);
                break;
            case 5:
                title.setText(test[5]);
                dynamic.setText("It refers to marinated meat, \n(generally beef if used without a qualifier),\n cooked using traditional grilling techniques with special sauce. \n" +
                        "It tastes soft and sweet.\n");
                food_lay.setBackgroundResource(R.drawable.food_img_6);
                break;
            case 6:
                title.setText(test[6]);
                dynamic.setText("It is noodles with cold soup. \nThe soup tastes vary in different localities.\n" +
                        "Put the vinegar and mustard and enjoy\n the sour and cool taste of it.\n");
                food_lay.setBackgroundResource(R.drawable.food_img_7);
                break;
            case 7:
                title.setText(test[7]);
                dynamic.setText("Dak galbi is a popular Korean dish generally \nmade by stir-frying marinated diced chicken \nin a gochujang (chili pepper paste) based sauce,\n and sliced cabbage, sweet potato, scallions, \nonions, perilla leaves, and tteok (rice cake) together on a hot plate.\n" +
                        "It is more delicious when you eat\n with lettuce and perilla leaf.\n");
                food_lay.setBackgroundResource(R.drawable.food_img_8);
                break;
            case 8:
                title.setText(test[8]);
                dynamic.setText("It is a signature Korean dish which \nshows Korean dish style(mixing) well. \nThe word literally means \"mixed rice\". \nBibimbap is served as a bowl of warm white rice \ntopped with namul (sauteed and seasoned vegetables)\n and gochujang (chili pepper paste), soy sauce, \nor doenjang, a salty soybean paste. \nA raw or fried egg and sliced meat\n (usually beef) are common additions.");
                food_lay.setBackgroundResource(R.drawable.food_img_9);
                break;
            case 9:
                title.setText(test[9]);
                dynamic.setText("It is a Korean shaved ice dessert with \nsweet toppings such as chopped fruit, \ncondensed milk, fruit syrup, and red beans.");
                food_lay.setBackgroundResource(R.drawable.food_img_10);
                break;
        }


//        mListView = (ListView) findViewById(R.id.mList);
//        mAdapter = new ListViewAdapter(this);
//        mListView.setAdapter(mAdapter);
//
//        for(int i=0; i<10; i++)
//            mAdapter.addItem("꼭그닭(빠네치킨)", "580", "착한맛집, 담백한맛, 캐주얼", "강남역", "서울시 강남구 역삼동 817-33", "02-565-5335");
//
//
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
////                Intent i2 = new Intent(foodDetailPage.this, detailPage.class);
////                startActivity(i2);
////                overridePendingTransition(R.anim.fade, R.anim.hold);
//            }
//        });


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
        String[] data = str.split("/woo/");
        data_length = Integer.parseInt(data[0]);

        for(int i=343; i<433; i++){
            Log.d(String.valueOf(i), data[i]);

        }
        switch(now_pos)
        {
            case 0:
                int temp0;
                for(int i=0; i<10; i++){
                    temp0 = i;
                    name[i] = data[1+(temp0*9)];
                    desc[i] = data[2+(temp0*9)];
                    loc1[i] = data[3+(temp0*9)];
                    loc2[i] = data[4+(temp0*9)];
                    call[i] = data[5+(temp0*9)];
                    lati[i] = Double.parseDouble(data[7+(temp0*9)]);
                    longi[i] = Double.parseDouble(data[8+(temp0*9)]);
                    likes[i] = Integer.parseInt(data[9+(temp0*9)]);
                }
                break;
            case 1:
                int temp1;
                for(int i=0; i<9; i++){
                    temp1 = i;
                    name[i] = data[91+(temp1*9)];
                    desc[i] = data[92+(temp1*9)];
                    loc1[i] = data[93+(temp1*9)];
                    loc2[i] = data[94+(temp1*9)];
                    call[i] = data[95+(temp1*9)];
                    lati[i] = Double.parseDouble(data[97+(temp1*9)]);
                    longi[i] = Double.parseDouble(data[98+(temp1*9)]);
                    likes[i] = Integer.parseInt(data[99+(temp1*9)]);
                }
                break;
            case 2:
                int temp2;
                for(int i=0; i<10; i++){
                    temp2 = i;
                    name[i] = data[172+(temp2*9)];
                    desc[i] = data[173+(temp2*9)];
                    loc1[i] = data[174+(temp2*9)];
                    loc2[i] = data[175+(temp2*9)];
                    call[i] = data[176+(temp2*9)];
                    lati[i] = Double.parseDouble(data[178+(temp2*9)]);
                    longi[i] = Double.parseDouble(data[179+(temp2*9)]);
                    likes[i] = Integer.parseInt(data[180+(temp2*9)]);
                }
                break;
            case 3:
                int temp3;
                for(int i=0; i<9; i++){
                    temp3 = i;
                    name[i] = data[262+(temp3*9)];
                    desc[i] = data[263+(temp3*9)];
                    loc1[i] = data[264+(temp3*9)];
                    loc2[i] = data[265+(temp3*9)];
                    call[i] = data[266+(temp3*9)];
                    lati[i] = Double.parseDouble(data[268+(temp3*9)]);
                    longi[i] = Double.parseDouble(data[269+(temp3*9)]);
                    likes[i] = Integer.parseInt(data[270+(temp3*9)]);
                    Log.d("asd", String.valueOf(i));
                }
                break;
            case 4:
                int temp4;
                for(int i=0; i<10; i++){
                    temp4 = i;

                    name[i] = data[343+(temp4*9)];
                    desc[i] = data[344+(temp4*9)];
                    loc1[i] = data[345+(temp4*9)];
                    loc2[i] = data[346+(temp4*9)];
                    call[i] = data[347+(temp4*9)];
                    lati[i] = Double.parseDouble(data[349+(temp4*9)]);
                    longi[i] = Double.parseDouble(data[350+(temp4*9)]);
                    likes[i] = Integer.parseInt(data[351+(temp4*9)]);
                }
                break;
            case 5:
                int temp5;
                for(int i=0; i<10; i++){
                    temp5 = i;
                    name[i] = data[433+(temp5*9)];
                    Log.d(String.valueOf(433+(temp5*9)), data[433+(temp5*9)]);
                    desc[i] = data[434+(temp5*9)];
                    Log.d(String.valueOf(434+(temp5*9)), data[434+(temp5*9)]);
                    loc1[i] = data[435+(temp5*9)];
                    Log.d(String.valueOf(435+(temp5*9)), data[435+(temp5*9)]);
                    loc2[i] = data[436+(temp5*9)];
                    Log.d(String.valueOf(436+(temp5*9)), data[436+(temp5*9)]);
                    call[i] = data[437+(temp5*9)];
                    Log.d(String.valueOf(437+(temp5*9)), data[437+(temp5*9)]);
                    lati[i] = Double.parseDouble(data[439+(temp5*9)]);
                    Log.d(String.valueOf(439+(temp5*9)), data[439+(temp5*9)]);
                    longi[i] = Double.parseDouble(data[440+(temp5*9)]);
                    Log.d(String.valueOf(440+(temp5*9)), data[440+(temp5*9)]);
                    likes[i] = Integer.parseInt(data[441+(temp5*9)]);
                    Log.d(String.valueOf(441+(temp5*9)), data[441+(temp5*9)]);
                }
                break;
            case 6:
                int temp;
                for(int i=0; i<10; i++){
                    temp = i;
                    name[i] = data[523+(temp*9)];
                    desc[i] = data[524+(temp*9)];
                    loc1[i] = data[525+(temp*9)];
                    loc2[i] = data[526+(temp*9)];
                    call[i] = data[527+(temp*9)];
                    lati[i] = Double.parseDouble(data[529+(temp*9)]);
                    longi[i] = Double.parseDouble(data[530+(temp*9)]);
                    likes[i] = Integer.parseInt(data[531+(temp*9)]);
                }
                break;
            case 7:
                int temp7;
                for(int i=0; i<10; i++){
                    temp7 = i;
                    name[i] = data[613+(temp7*9)];
                    desc[i] = data[614+(temp7*9)];
                    loc1[i] = data[615+(temp7*9)];
                    loc2[i] = data[616+(temp7*9)];
                    call[i] = data[617+(temp7*9)];
                    lati[i] = Double.parseDouble(data[619+(temp7*9)]);
                    longi[i] = Double.parseDouble(data[620+(temp7*9)]);
                    likes[i] = Integer.parseInt(data[621+(temp7*9)]);
                }
                break;
            case 8:
                int temp8;
                for(int i=0; i<10; i++){
                    temp8 = i;
                    name[i] = data[703+(temp8*9)];
                    desc[i] = data[704+(temp8*9)];
                    loc1[i] = data[705+(temp8*9)];
                    loc2[i] = data[706+(temp8*9)];
                    call[i] = data[707+(temp8*9)];
                    lati[i] = Double.parseDouble(data[709+(temp8*9)]);
                    longi[i] = Double.parseDouble(data[710+(temp8*9)]);
                    likes[i] = Integer.parseInt(data[711+(temp8*9)]);
                }
                break;
            case 9:
                int temp9;
                for(int i=0; i<10; i++){
                    temp9 = i;
                    name[i] = data[793+(temp9*9)];
                    desc[i] = data[794+(temp9*9)];
                    loc1[i] = data[795+(temp9*9)];
                    loc2[i] = data[796+(temp9*9)];
                    call[i] = data[797+(temp9*9)];
                    lati[i] = Double.parseDouble(data[799+(temp9*9)]);
                    longi[i] = Double.parseDouble(data[800+(temp9*9)]);
                    likes[i] = Integer.parseInt(data[801+(temp9*9)]);
                }
                break;
        }


        mListView = (ListView) findViewById(R.id.mList);
        mAdapter = new ListViewAdapter(this);
        mListView.setAdapter(mAdapter);

        if(now_pos == 1 || now_pos==3)
            for(int q=0; q<9; q++){
                mAdapter.addItem(name[q], String.valueOf(likes[q]), desc[q], loc1[q], loc2[q], call[q]);
            }
        else
            for(int q=0; q<10; q++){
                mAdapter.addItem(name[q], String.valueOf(likes[q]), desc[q], loc1[q], loc2[q], call[q]);
            }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                Intent intent = new Intent(foodDetailPage.this, foodMapPage.class);
                intent.putExtra("pos", position);
                intent.putExtra("now", now_pos);
                intent.putExtra("name", name[position]);
                intent.putExtra("desc", desc[position]);
                intent.putExtra("loc1", loc1[position]);
                intent.putExtra("loc2", loc2[position]);
                intent.putExtra("call", call[position]);
                intent.putExtra("lati", lati[position]);
                intent.putExtra("longi",longi[position]);
                intent.putExtra("like", likes[position]);
                startActivity(intent);
                overridePendingTransition(R.anim.fade, R.anim.hold);
            }
        });

        setListViewHeightBasedOnChildren(mListView);

    }


    public void onClick(View v){
        switch(v.getId()){
            case R.id.back_btn:
                finish();
                break;
            case R.id.food_lay:
                //추천페이지로이동
                break;

        }
    }



    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    //////////////////////////listvIew/////////////////////////////////
    private class ViewHolder{
        public TextView title;
        public TextView likenum;
        public TextView desc;
        public TextView loc1;
        public TextView loc2;
        public TextView call;
        public TextView viewde;
        public RelativeLayout photo;

    }
    private class ListViewAdapter extends BaseAdapter {
        private Context mContext = null;
        private ArrayList<ListData2> mListData = new ArrayList<ListData2>();

        public ListViewAdapter(Context mContext){
            super();
            this.mContext = mContext;
        }

        @Override
        public int getCount(){
            return mListData.size();
        }
        @Override
        public Object getItem(int position){
            return mListData.get(position);
        }
        @Override
        public long getItemId(int position){
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            ViewHolder holder;
            if(convertView == null){
                holder = new ViewHolder();

                LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.listview_item_detail, null);

                holder.title = (TextView) convertView.findViewById(R.id.title);
                holder.likenum= (TextView) convertView.findViewById(R.id.likenum);
                holder.desc = (TextView) convertView.findViewById(R.id.desc);
                holder.loc1 = (TextView) convertView.findViewById(R.id.loc1);
                holder.loc2 = (TextView) convertView.findViewById(R.id.loc2);
                holder.call = (TextView) convertView.findViewById(R.id.call);
                holder.photo = (RelativeLayout) convertView.findViewById(R.id.photo);
                holder.viewde = (TextView) convertView.findViewById(R.id.viewdetail);

                holder.title.setTypeface(typeRe);
                holder.likenum.setTypeface(typeLi);
                holder.desc.setTypeface(typeLi);
                holder.loc1.setTypeface(typeLi);
                holder.loc2.setTypeface(typeLi);
                holder.call.setTypeface(typeLi);
                holder.viewde.setTypeface(typeLi);

                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }

            ListData2 mData = mListData.get(position);
            switch(now_pos){
                case 0:
                    if(position == 0)
                        holder.photo.setBackgroundResource(R.drawable.f0101);
                    else if(position == 1)
                        holder.photo.setBackgroundResource(R.drawable.f0102);
                    else if(position == 2)
                        holder.photo.setBackgroundResource(R.drawable.f0103);
                    else if(position == 3)
                        holder.photo.setBackgroundResource(R.drawable.f0104);
                    else if(position == 4)
                        holder.photo.setBackgroundResource(R.drawable.f0105);
                    else if(position == 5)
                        holder.photo.setBackgroundResource(R.drawable.f0106);
                    else if(position == 6)
                        holder.photo.setBackgroundResource(R.drawable.f0107);
                    else if(position == 7)
                        holder.photo.setBackgroundResource(R.drawable.f0108);
                    else if(position == 8)
                        holder.photo.setBackgroundResource(R.drawable.f0109);
                    else if(position == 9)
                        holder.photo.setBackgroundResource(R.drawable.f0110);
                    break;
                case 1:
                    if(position == 0)
                        holder.photo.setBackgroundResource(R.drawable.f0201);
                    else if(position == 1)
                        holder.photo.setBackgroundResource(R.drawable.f0202);
                    else if(position == 2)
                        holder.photo.setBackgroundResource(R.drawable.f0203);
                    else if(position == 3)
                        holder.photo.setBackgroundResource(R.drawable.f0204);
                    else if(position == 4)
                        holder.photo.setBackgroundResource(R.drawable.f0205);
                    else if(position == 5)
                        holder.photo.setBackgroundResource(R.drawable.f0206);
                    else if(position == 6)
                        holder.photo.setBackgroundResource(R.drawable.f0207);
                    else if(position == 7)
                        holder.photo.setBackgroundResource(R.drawable.f0208);
                    else if(position == 8)
                        holder.photo.setBackgroundResource(R.drawable.f0210);
                    break;
                case 2:
                    if(position == 0)
                        holder.photo.setBackgroundResource(R.drawable.f0301);
                    else if(position == 1)
                        holder.photo.setBackgroundResource(R.drawable.f0302);
                    else if(position == 2)
                        holder.photo.setBackgroundResource(R.drawable.f0303);
                    else if(position == 3)
                        holder.photo.setBackgroundResource(R.drawable.f0304);
                    else if(position == 4)
                        holder.photo.setBackgroundResource(R.drawable.f0305);
                    else if(position == 5)
                        holder.photo.setBackgroundResource(R.drawable.f0306);
                    else if(position == 6)
                        holder.photo.setBackgroundResource(R.drawable.f0307);
                    else if(position == 7)
                        holder.photo.setBackgroundResource(R.drawable.f0308);
                    else if(position == 8)
                        holder.photo.setBackgroundResource(R.drawable.f0309);
                    else if(position == 9)
                        holder.photo.setBackgroundResource(R.drawable.f0310);
                    break;
                case 3:
                    if(position == 0)
                        holder.photo.setBackgroundResource(R.drawable.f0401);
                    else if(position == 1)
                        holder.photo.setBackgroundResource(R.drawable.f0402);
                    else if(position == 2)
                        holder.photo.setBackgroundResource(R.drawable.f0403);
                    else if(position == 3)
                        holder.photo.setBackgroundResource(R.drawable.f0404);
                    else if(position == 4)
                        holder.photo.setBackgroundResource(R.drawable.f0405);
                    else if(position == 5)
                        holder.photo.setBackgroundResource(R.drawable.f0406);
                    else if(position == 6)
                        holder.photo.setBackgroundResource(R.drawable.f0407);
                    else if(position == 7)
                        holder.photo.setBackgroundResource(R.drawable.f0408);
                    else if(position == 8)
                        holder.photo.setBackgroundResource(R.drawable.f0409);
                    break;
                case 4:
                    if(position == 0)
                        holder.photo.setBackgroundResource(R.drawable.f0501);
                    else if(position == 1)
                        holder.photo.setBackgroundResource(R.drawable.f0502);
                    else if(position == 2)
                        holder.photo.setBackgroundResource(R.drawable.f0503);
                    else if(position == 3)
                        holder.photo.setBackgroundResource(R.drawable.f0504);
                    else if(position == 4)
                        holder.photo.setBackgroundResource(R.drawable.f0505);
                    else if(position == 5)
                        holder.photo.setBackgroundResource(R.drawable.f0506);
                    else if(position == 6)
                        holder.photo.setBackgroundResource(R.drawable.f0507);
                    else if(position == 7)
                        holder.photo.setBackgroundResource(R.drawable.f0508);
                    else if(position == 8)
                        holder.photo.setBackgroundResource(R.drawable.f0509);
                    else if(position == 9)
                        holder.photo.setBackgroundResource(R.drawable.f0510);
                    break;
                case 5:
                    if(position == 0)
                        holder.photo.setBackgroundResource(R.drawable.f0601);
                    else if(position == 1)
                        holder.photo.setBackgroundResource(R.drawable.f0602);
                    else if(position == 2)
                        holder.photo.setBackgroundResource(R.drawable.f0603);
                    else if(position == 3)
                        holder.photo.setBackgroundResource(R.drawable.f0604);
                    else if(position == 4)
                        holder.photo.setBackgroundResource(R.drawable.f0605);
                    else if(position == 5)
                        holder.photo.setBackgroundResource(R.drawable.f0606);
                    else if(position == 6)
                        holder.photo.setBackgroundResource(R.drawable.f0607);
                    else if(position == 7)
                        holder.photo.setBackgroundResource(R.drawable.f0608);
                    else if(position == 8)
                        holder.photo.setBackgroundResource(R.drawable.f0609);
                    else if(position == 9)
                        holder.photo.setBackgroundResource(R.drawable.f0610);
                    break;
                case 6:
                    if(position == 0)
                        holder.photo.setBackgroundResource(R.drawable.f0701);
                    else if(position == 1)
                        holder.photo.setBackgroundResource(R.drawable.f0702);
                    else if(position == 2)
                        holder.photo.setBackgroundResource(R.drawable.f0703);
                    else if(position == 3)
                        holder.photo.setBackgroundResource(R.drawable.f0704);
                    else if(position == 4)
                        holder.photo.setBackgroundResource(R.drawable.f0705);
                    else if(position == 5)
                        holder.photo.setBackgroundResource(R.drawable.f0706);
                    else if(position == 6)
                        holder.photo.setBackgroundResource(R.drawable.f0707);
                    else if(position == 7)
                        holder.photo.setBackgroundResource(R.drawable.f0708);
                    else if(position == 8)
                        holder.photo.setBackgroundResource(R.drawable.f0709);
                    else if(position == 9)
                        holder.photo.setBackgroundResource(R.drawable.f0710);
                    break;
                case 7:
                    if(position == 0)
                        holder.photo.setBackgroundResource(R.drawable.f0801);
                    else if(position == 1)
                        holder.photo.setBackgroundResource(R.drawable.f0802);
                    else if(position == 2)
                        holder.photo.setBackgroundResource(R.drawable.f0803);
                    else if(position == 3)
                        holder.photo.setBackgroundResource(R.drawable.f0804);
                    else if(position == 4)
                        holder.photo.setBackgroundResource(R.drawable.f0805);
                    else if(position == 5)
                        holder.photo.setBackgroundResource(R.drawable.f0806);
                    else if(position == 6)
                        holder.photo.setBackgroundResource(R.drawable.f0807);
                    else if(position == 7)
                        holder.photo.setBackgroundResource(R.drawable.f0808);
                    else if(position == 8)
                        holder.photo.setBackgroundResource(R.drawable.f0809);
                    else if(position == 9)
                        holder.photo.setBackgroundResource(R.drawable.f0810);
                    break;
                case 8:
                    if(position == 0)
                        holder.photo.setBackgroundResource(R.drawable.f0901);
                    else if(position == 1)
                        holder.photo.setBackgroundResource(R.drawable.f0902);
                    else if(position == 2)
                        holder.photo.setBackgroundResource(R.drawable.f0903);
                    else if(position == 3)
                        holder.photo.setBackgroundResource(R.drawable.f0904);
                    else if(position == 4)
                        holder.photo.setBackgroundResource(R.drawable.f0905);
                    else if(position == 5)
                        holder.photo.setBackgroundResource(R.drawable.f0906);
                    else if(position == 6)
                        holder.photo.setBackgroundResource(R.drawable.f0907);
                    else if(position == 7)
                        holder.photo.setBackgroundResource(R.drawable.f0908);
                    else if(position == 8)
                        holder.photo.setBackgroundResource(R.drawable.f0909);
                    else if(position == 9)
                        holder.photo.setBackgroundResource(R.drawable.f0910);
                    break;

                case 9:
                    if(position == 0)
                        holder.photo.setBackgroundResource(R.drawable.f1001);
                    else if(position == 1)
                        holder.photo.setBackgroundResource(R.drawable.f1002);
                    else if(position == 2)
                        holder.photo.setBackgroundResource(R.drawable.f1003);
                    else if(position == 3)
                        holder.photo.setBackgroundResource(R.drawable.f1004);
                    else if(position == 4)
                        holder.photo.setBackgroundResource(R.drawable.f1005);
                    else if(position == 5)
                        holder.photo.setBackgroundResource(R.drawable.f1006);
                    else if(position == 6)
                        holder.photo.setBackgroundResource(R.drawable.f1007);
                    else if(position == 7)
                        holder.photo.setBackgroundResource(R.drawable.f1008);
                    else if(position == 8)
                        holder.photo.setBackgroundResource(R.drawable.f1009);
                    else if(position == 9)
                        holder.photo.setBackgroundResource(R.drawable.f1010);
                    break;
            }

            holder.title.setText(mData.title);
            holder.likenum.setText(mData.likenum);
            holder.desc.setText(mData.desc);
            holder.loc1.setText(mData.loc1);
            holder.loc2.setText(mData.loc2);
            holder.call.setText(mData.call);

            return convertView;
        }
        public void addItem(String title, String likenum, String desc, String loc1, String loc2, String call){
            ListData2 addInfo = null;
            addInfo = new ListData2();
            addInfo.title = title;
            addInfo.likenum = likenum;
            addInfo.desc = desc;
            addInfo.loc1 = loc1;
            addInfo.loc2 = loc2;
            addInfo.call = call;

            //addInfo.back = back;

            mListData.add(addInfo);
        }
        public void remove(int position){
            mListData.remove(position);
            dataChange();
        }
        public void dataChange(){
            mAdapter.notifyDataSetChanged();
        }
    }
/////////////////////////////////////////////////////////
}
