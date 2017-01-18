package com.askme.exploretoday;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class MyBaseAdapter extends BaseAdapter {
        
        ArrayList<ListData> myList = new ArrayList<ListData>();
        LayoutInflater inflater;
        Context context;
        public MyBaseAdapter(Context context, ArrayList<ListData> myList) {
                this.myList = myList;
                this.context = context;
                inflater = LayoutInflater.from(this.context);        // only context can also be used
        }
 
        @Override
        public int getCount() {
                return myList.size();
        }
 
        @Override
        public ListData getItem(int position) {
                return myList.get(position);
        }
 
        @Override
        public long getItemId(int position) {
                return 0;
        }
 
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
                MyViewHolder mViewHolder;
                
                if(convertView == null) {
                        convertView = inflater.inflate(R.layout.list_item, null);
                        mViewHolder = new MyViewHolder();
                        convertView.setTag(mViewHolder);
                } else {
                        mViewHolder = (MyViewHolder) convertView.getTag();
                }
                
                
                mViewHolder.tvTitle = detail(convertView, R.id.tvTitle, myList.get(position).getTitle());
                	if(mViewHolder.tvTitle.toString().isEmpty())
                		mViewHolder.tvTitle.setText("ERROR");
                return convertView;
        }
        
        // or you can try better way
        private TextView detail(View v, int resId, String text) {
                TextView tv = (TextView) v.findViewById(resId);
                tv.setText(text);
                return tv;
        }
        
        
        private class MyViewHolder {
                TextView tvTitle;
        }
 
}