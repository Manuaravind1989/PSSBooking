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


/**
 * Created by Manu on 10/1/2016.
 */
public class RentingAdapter extends BaseAdapter {

    public Activity context;
    public LayoutInflater inflater;
    String[] itemList;
    private int mPreviousPosition = 0;
    public RentingAdapter(Activity context, String[] itemList) {
        super();

        this.context = context;
        this.itemList = itemList;

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return itemList.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return itemList[position];
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

            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();


        holder.txtViewTitle.setText(itemList[position]);

        if (position > mPreviousPosition) {
         //   AnimationUtils.animteTransalte(convertView, true);
//            AnimationUtils.animateSunblind(holder, true);
//            AnimationUtils.animate1(holder, true);
//            AnimationUtils.animate(holder,true);
        } else {
          //  AnimationUtils.animteTransalte(convertView, false);
//            AnimationUtils.animateSunblind(holder, false);
//            AnimationUtils.animate1(holder, false);
//            AnimationUtils.animate(holder, false);
        }
        return convertView;
    }

    public static class ViewHolder {
        ImageView imgViewLogo;
        TextView txtViewTitle;
        TextView txtViewDescription;
    }

}

