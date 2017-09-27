package choiyh1006.example.com.seouldal2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class foodPage extends Activity {
    private ListView mListView = null;
    private ListViewAdapter mAdapter = null;
    RelativeLayout food_lay;
    TextView title, cu_bon, cu_title, detail, dynamic;
    Typeface typeRe;
    Typeface typeBo;
    Typeface typeLi;
    String now_weather="";
   // phpDown task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_page);
//        task = new phpDown();
//        task.execute("http://52.8.57.228/open_bus_number.php?test=1");
        typeRe = Typeface.createFromAsset(getAssets(), "titi_regular.otf");
        typeBo = Typeface.createFromAsset(getAssets(), "titi_bold.otf");
        typeLi = Typeface.createFromAsset(getAssets(), "titi_light.otf");

        Intent intent= getIntent();
        now_weather = intent.getExtras().getString("now");

        food_lay = (RelativeLayout)findViewById(R.id.food_lay);
        dynamic = (TextView)findViewById(R.id.dynamic);
        title = (TextView)findViewById(R.id.title);
        cu_title = (TextView)findViewById(R.id.cu_title);
        cu_bon = (TextView)findViewById(R.id.cu_bon);
        detail = (TextView)findViewById(R.id.detail);
        title.setTypeface(typeBo);
        cu_title.setTypeface(typeRe);
        cu_bon.setTypeface(typeLi);
        detail.setTypeface(typeLi);
        dynamic.setTypeface(typeRe);

        dynamic.setText("Taste real Korea style");
        switch(now_weather){
            case "Clouds":
                cu_bon.setText("How about Bulgogi in cloudy day?");
                food_lay.setBackgroundResource(R.drawable.cu_bul);
                break;
            case "Clear":
                cu_bon.setText("How about \nChicken and Beer\n in Han-River\n in sunny day?");
                food_lay.setBackgroundResource(R.drawable.cu_ch);
                break;
            case "Rain":
                cu_bon.setText("How about \nMakGeolLi(Rice-wine) \nand Pajeon(Pancake) \nin rainy day?");
                food_lay.setBackgroundResource(R.drawable.cu_pa);
                break;
            case "Snow":
                cu_bon.setText("How about \nKalGukSu(Noodle Soup) \nin snowy day?");
                food_lay.setBackgroundResource(R.drawable.cu_kal);
                break;
            default:
                cu_bon.setText("How about \nBulgogi \nin cloudy day?");
                food_lay.setBackgroundResource(R.drawable.cu_bul);
                break;

        }
        String[] test = {"Chicken & Beer", "Pa-jeon&Mak-Geol-Li", "Whole Chicken with noodles", "Flour based food", "Pork belly","Bulgogi", "Naeng Myeon", "Dak-galbi", "Bibimbap", "Pat bing su" };

        mListView = (ListView) findViewById(R.id.mList);
        mAdapter = new ListViewAdapter(this);
        mListView.setAdapter(mAdapter);

        for(int i=0; i<10; i++)
            mAdapter.addItem(test[i]);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                Intent i2 = new Intent(foodPage.this, foodDetailPage.class);
                i2.putExtra("now", position);
                startActivity(i2);
                overridePendingTransition(R.anim.fade, R.anim.hold);
            }
        });

        setListViewHeightBasedOnChildren(mListView);
    }




    public void onClick(View v){
        switch(v.getId()){
            case R.id.back_btn:
                Intent intent = new Intent(foodPage.this, MainActivity.class);
                startActivity(intent);

                overridePendingTransition(R.anim.fade, R.anim.hold);
                finish();
                break;
            case R.id.food_lay:
                switch(now_weather){
                    case "Clouds":
                        Intent i3 = new Intent(foodPage.this, foodDetailPage.class);
                        i3.putExtra("now", 5);
                        startActivity(i3);
                        break;
                    case "Clear":
                        Intent i4 = new Intent(foodPage.this, foodDetailPage.class);
                        i4.putExtra("now", 0);
                        startActivity(i4);
                        break;
                    case "Rain":
                        Intent i5 = new Intent(foodPage.this, foodDetailPage.class);
                        i5.putExtra("now", 1);
                        startActivity(i5);
                        break;
                    case "Snow":
                        Intent i6 = new Intent(foodPage.this, foodDetailPage.class);
                        i6.putExtra("now", 2);
                        startActivity(i6);
                        break;
                    default:
                        Intent i7 = new Intent(foodPage.this, foodDetailPage.class);
                        i7.putExtra("now", 5);
                        startActivity(i7);
                        break;

                }
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

//    private class phpDown extends AsyncTask<String, Integer,String> {
//        @Override
//        protected String doInBackground(String... urls) {
//            StringBuilder jsonHtml = new StringBuilder();
//            try{
//                // 연결 url 설정
//                URL url = new URL(urls[0]);
//                // 커넥션 객체 생성
//                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//                // 연결되었으면.
//                if(conn != null){
//                    conn.setConnectTimeout(10000);
//                    conn.setUseCaches(false);
//                    // 연결되었음 코드가 리턴되면.
//                    if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
//                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//                        for(;;){
//                            // 웹상에 보여지는 텍스트를 라인단위로 읽어 저장.
//                            String line = br.readLine();
//                            if(line == null) break;
//                            // 저장된 텍스트 라인을 jsonHtml에 붙여넣음
//                            jsonHtml.append(line + "\n");
//                        }
//                        br.close();
//                    }
//                    conn.disconnect();
//                }
//            } catch(Exception ex){
//                ex.printStackTrace();
//            }
//            return jsonHtml.toString();
//
//        }
//
//        protected void onPostExecute(String str){
//            Log.d("str", str);
//            //showList(str);
//            //txtView.setText(str);
//        }
//    }
//    public void showList(String str){
//        String[] bus_number = new String[300];
//        int data_length = 0;
//
//        String[] data = str.split("_");
//        data_length = Integer.parseInt(data[0]);
//        for(int i=0; i<data_length; i++){
//            bus_number[i] = String.valueOf(data[i+1]);
//        }
//
//        String[] test = {"치킨", "파전&막걸리", "닭한마리 칼국수", "분식", "삼겹살","불고기", "냉면", "닭갈비", "비빔밥", "팥빙수" };
//
//        mListView = (ListView) findViewById(R.id.mList);
//        mAdapter = new ListViewAdapter(this);
//        mListView.setAdapter(mAdapter);
//
//        Drawable[] a = new Drawable[10];
//        a[0] = getResources().getDrawable(R.drawable.food1);
//        a[1] = getResources().getDrawable(R.drawable.food2);
//        a[2] = getResources().getDrawable(R.drawable.food3);
//        a[3] = getResources().getDrawable(R.drawable.food4);
//        a[4] = getResources().getDrawable(R.drawable.food5);
//        a[5] = getResources().getDrawable(R.drawable.food6);
//        a[6] = getResources().getDrawable(R.drawable.food7);
//        a[7] = getResources().getDrawable(R.drawable.food8);
//        a[8] = getResources().getDrawable(R.drawable.food9);
//        a[9] = getResources().getDrawable(R.drawable.food10);
//
//        for(int q=0; q<test.length; q++){
//                mAdapter.addItem(test[q], a[q]);
//        }
//
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
//
//            }
//        });
//
//    }
    //////////////////////////listvIew/////////////////////////////////
    private class ViewHolder{
        public TextView text;
        public RelativeLayout back;

    }
    private class ListViewAdapter extends BaseAdapter {
        private Context mContext = null;
        private ArrayList<ListData> mListData = new ArrayList<ListData>();

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
                convertView = inflater.inflate(R.layout.listview_item, null);

                holder.text = (TextView) convertView.findViewById(R.id.text);
                holder.back = (RelativeLayout) convertView.findViewById(R.id.layout);
                holder.text.setTypeface(typeRe);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }

            ListData mData = mListData.get(position);
            switch(position){
                case 0:
                    holder.back.setBackgroundResource(R.drawable.test1);
                    break;
                case 1:
                    holder.back.setBackgroundResource(R.drawable.test2);
                    break;
                case 2:
                    holder.back.setBackgroundResource(R.drawable.test3);
                    break;
                case 3:
                    holder.back.setBackgroundResource(R.drawable.test4);
                    break;
                case 4:
                    holder.back.setBackgroundResource(R.drawable.test5);
                    break;
                case 5:
                    holder.back.setBackgroundResource(R.drawable.test6);
                    break;
                case 6:
                    holder.back.setBackgroundResource(R.drawable.test7);
                    break;
                case 7:
                    holder.back.setBackgroundResource(R.drawable.test8);
                    break;
                case 8:
                    holder.back.setBackgroundResource(R.drawable.test9);
                    break;
                case 9:
                    holder.back.setBackgroundResource(R.drawable.test10);
                    break;
                default:
                    holder.back.setBackgroundResource(R.color.black);
                    break;
            }
//            if(mData.back != null){
//                //holder.back.setVisibility(View.VISIBLE);
//
//                holder.back.setBackgroundResource(mData.back);
//            }else{
//                holder.back.setVisibility(View.GONE);
//            }
            holder.text.setText(mData.title);

            return convertView;
        }
        public void addItem(String text){
            ListData addInfo = null;
            addInfo = new ListData();
            addInfo.title = text;
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
