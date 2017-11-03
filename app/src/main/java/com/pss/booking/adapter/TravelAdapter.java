package com.pss.booking.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pss.booking.R;
import com.pss.booking.model.HotelModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Created by Manu on 10/1/2016.
 */
public class TravelAdapter extends BaseAdapter {

    public Activity context;
    public LayoutInflater inflater;
   // String[] itemList;
    private int mPreviousPosition = 0;
    List<HotelModel.PostsEntity> itemList;
    private ArrayList<HotelModel.PostsEntity> arraylist;
    public TravelAdapter(Activity context, List<HotelModel.PostsEntity> itemList) {
        super();

        this.context = context;
        this.itemList = itemList;
        this.arraylist = new ArrayList<HotelModel.PostsEntity>();
        this.arraylist.addAll(itemList);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.renting_row, null);

            holder.imgViewLogo = (ImageView) convertView.findViewById(R.id.imageView);
            holder.txtViewTitle = (TextView) convertView.findViewById(R.id.txtViewTitle);
            holder.txtViewDescription = (TextView) convertView.findViewById(R.id.txtViewDescription);
            holder.txtViewAmount = (TextView) convertView.findViewById(R.id.textViewAmount);

            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();


        holder.txtViewTitle.setText(itemList.get(position).getTitle());
        holder.txtViewDescription.setText(itemList.get(position).getDescription());
        holder.txtViewAmount.setText(itemList.get(position).getAmount());
        if (position > mPreviousPosition) {
          //  AnimationUtils.scaleY(convertView);
        //    AnimationUtils.animateSunblind(convertView, true);
//            AnimationUtils.animate1(holder, true);
//            AnimationUtils.animate(holder,true);
        } else {
           // AnimationUtils.scaleY(convertView);
         //  AnimationUtils.animateSunblind(convertView, false);
//            AnimationUtils.animate1(holder, false);
//            AnimationUtils.animate(holder, false);
        }

        return convertView;
    }

    public static class ViewHolder {
        ImageView imgViewLogo;
        TextView txtViewTitle;
        TextView txtViewDescription;
        TextView txtViewAmount;
    }
    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        itemList.clear();
        if (charText.length() == 0) {
            itemList.addAll(arraylist);
        } else {
            for (HotelModel.PostsEntity wp : arraylist) {
                if (wp.getTitle().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    itemList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}

