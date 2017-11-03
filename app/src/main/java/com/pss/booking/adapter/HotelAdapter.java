package com.pss.booking.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.pss.booking.R;
import com.pss.booking.Utils.AnimationUtils;
import com.pss.booking.model.HotelModel;

import java.util.List;

/**
 * Created by mdev3 on 10/13/16.
 */
public class HotelAdapter extends BaseAdapter {

    public Context context;
    public LayoutInflater inflater;
    List<HotelModel.PostsEntity> itemList;

    int mPreviousPosition =-1;
    public HotelAdapter(Context context, List<HotelModel.PostsEntity> itemList) {
        super();

        this.context = context;
        this.itemList = itemList;

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
            convertView = inflater.inflate(R.layout.hotel_list_item, null);
            holder.txtViewTitle = (TextView) convertView.findViewById(R.id.textViewtitle);
            holder.mBGImage = (ImageView) convertView.findViewById(R.id.imageViewView);
            holder.txtViewLocation = (TextView) convertView.findViewById(R.id.LocationAdress);
            holder.txtViewTiming = (TextView) convertView.findViewById(R.id.TimingLabel);
            holder.txtViewDiscount = (TextView) convertView.findViewById(R.id.DiscountLabel);
            holder.txtViewOldAmount = (TextView) convertView.findViewById(R.id.OldPrice);
            holder.txtViewNewAmount = (TextView) convertView.findViewById(R.id.NewPrice);
            holder.mRatingBar = (RatingBar) convertView.findViewById(R.id.RatingBar);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        holder.txtViewTitle.setText(itemList.get(position).getTitle() + " - " + itemList.get(position).getSub_title());
        holder.txtViewLocation.setText(itemList.get(position).getLocation());
        holder.txtViewTiming.setText(itemList.get(position).getTime_start() + "-" + itemList.get(position).getTime_end());
        holder.txtViewDiscount.setText(itemList.get(position).getDiscount() + "%");
        if (position % 2 == 0)
            holder.mBGImage.setImageResource(R.drawable.images1);
        else
            holder.mBGImage.setImageResource(R.drawable.images2);
        double discount = 0;
        if (!itemList.get(position).getDiscount().equals("")) {
            discount = Integer.valueOf(itemList.get(position).getDiscount());
        }

        if (discount > 0) {
            Log.e("Discount","kkk"+itemList.get(position).getTitle());
            holder.txtViewDiscount.setVisibility(View.VISIBLE);
            holder.txtViewOldAmount.setPaintFlags(holder.txtViewOldAmount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.txtViewOldAmount.setVisibility(View.VISIBLE);
        } else {
            Log.e("No Discount","ggg"+itemList.get(position).getTitle());
            holder.txtViewDiscount.setVisibility(View.GONE);
            holder.txtViewOldAmount.setVisibility(View.GONE);
        }
        holder.txtViewOldAmount.setText(itemList.get(position).getAmount());
        holder.txtViewNewAmount.setText("" + getPercentageValue(Double.valueOf(itemList.get(position).getAmount()), discount));
        Log.e("Rating ===>",itemList.get(position).getRating());
        if(!itemList.get(position).getRating().equals(""))
        holder.mRatingBar.setRating(Float.valueOf(itemList.get(position).getRating()));
        if (position > mPreviousPosition) {
            //         AnimationUtils.animateScatter(convertView, true);
            AnimationUtils.animteTransalte(convertView, true);
//            AnimationUtils.animate1(holder, true);
//            AnimationUtils.animate(holder,true);
        } else {
            //          AnimationUtils.animateScatter(convertView, false);
            AnimationUtils.animteTransalte(convertView, false);
//            AnimationUtils.animate1(holder, false);
//            AnimationUtils.animate(holder, false);
        }
        mPreviousPosition = position;
        return convertView;
    }
    private double getPercentageValue(double oldAmount, double percent) {
        double newAmount = oldAmount - (oldAmount * percent / 100);
        return newAmount;
    }
    public static class ViewHolder {
        TextView txtViewTitle;
        ImageView mBGImage;
        TextView txtViewLocation;
        TextView txtViewTiming;
        TextView txtViewDiscount;
        TextView txtViewOldAmount;
        TextView txtViewNewAmount;
        RatingBar mRatingBar;

    }
}