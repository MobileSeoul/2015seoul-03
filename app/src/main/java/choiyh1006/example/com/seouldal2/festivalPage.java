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


public class festivalPage extends Activity {
    private ListView mListView = null;
    private ListViewAdapter mAdapter = null;

    TextView title, dynamic;
    Typeface typeRe;
    Typeface typeBo;
    Typeface typeLi;

    // phpDown task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festival_page);
        //        task = new phpDown();
//        task.execute("http://52.8.57.228/open_bus_number.php?test=1");
        typeRe = Typeface.createFromAsset(getAssets(), "titi_regular.otf");
        typeBo = Typeface.createFromAsset(getAssets(), "titi_bold.otf");
        typeLi = Typeface.createFromAsset(getAssets(), "titi_light.otf");

        dynamic = (TextView)findViewById(R.id.dynamic);
        title = (TextView)findViewById(R.id.title);

        title.setTypeface(typeBo);
        dynamic.setTypeface(typeRe);

        dynamic.setText("Dynamic night of Seoul");
        String[] test = {"Jeong-dong Culture Night" , "The Seoul Bamdokkaebi Night Market", "Namsan Mountain Goblins Halloween", "Han River lights of Fall event ", "Lotte World Halloween Party"};

        mListView = (ListView) findViewById(R.id.mList);
        mAdapter = new ListViewAdapter(this);
        mListView.setAdapter(mAdapter);

        for(int i=0; i<test.length; i++)
            mAdapter.addItem(test[i]);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                Intent i2 = new Intent(festivalPage.this, festivalDetailPage.class);
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
                Intent intent = new Intent(festivalPage.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade, R.anim.hold);
                finish();
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
                    holder.back.setBackgroundResource(R.drawable.list_festival_1);
                    break;
                case 1:
                    holder.back.setBackgroundResource(R.drawable.list_festival_2);
                    break;
                case 2:
                    holder.back.setBackgroundResource(R.drawable.list_festival_3);
                    break;
                case 3:
                    holder.back.setBackgroundResource(R.drawable.list_festival_4);
                    break;
                case 4:
                    holder.back.setBackgroundResource(R.drawable.list_festival_5);
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

