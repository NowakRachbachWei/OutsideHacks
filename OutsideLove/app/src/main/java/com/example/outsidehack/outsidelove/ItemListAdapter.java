package com.example.outsidehack.outsidelove;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 7/24/16.
 */
public class ItemListAdapter extends BaseAdapter {

    ArrayList lister = new ArrayList();

    Context context;
    List<stageItem> listItems;


    /*

    public ItemListAdapter(Context context, ArrayList arrayList){
        this.lister = arrayList;
        this.context= context;
        layoutInflater = (LayoutInflater.from(this.context));
    }*/

    public ItemListAdapter(Context context, List<stageItem> items){
        this.context = context;
        this.listItems = items;
    }

    private class ViewHolder {
        TextView stageName, artistName, hypeText, turntText;
        ImageView itsLit;

/*
        public ViewHolder(View item){
            stageName = (TextView)item.findViewById(R.id.stageName);
            artistName = (TextView)item.findViewById(R.id.artistName);
            hypeText = (TextView)item.findViewById(R.id.hypeText);
            turntText = (TextView)item.findViewById(R.id.shuffleText);
            itsLit = (ImageView)item.findViewById(R.id.stageEmoji);
        }*/
    }

    @Override
    public int getCount(){
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listItems.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        LayoutInflater lInflator = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView== null){
            convertView = lInflator.inflate(R.layout.adapter_item, null);
            viewHolder = new ViewHolder();
            viewHolder.turntText = (TextView)convertView.findViewById(R.id.shuffleText);
            viewHolder.stageName = (TextView)convertView.findViewById(R.id.stageName);
            viewHolder.artistName = (TextView)convertView.findViewById(R.id.artistName);
            viewHolder.hypeText = (TextView)convertView.findViewById(R.id.hypeText);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        stageItem currentItem = (stageItem) getItem(position);

//        viewHolder.stageName.setText(currentItem.getStageName());
//        viewHolder.artistName.setText(currentItem.getArtistName());
//        viewHolder.turntText.setText(currentItem.getHypePercent());
//        viewHolder.hypeText.setText(currentItem.getHypePercent());

        return convertView;



    }


}
