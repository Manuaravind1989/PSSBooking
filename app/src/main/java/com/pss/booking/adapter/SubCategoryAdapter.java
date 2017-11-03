package com.pss.booking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pss.booking.R;
import com.pss.booking.Utils.AnimationUtils;
import com.pss.booking.model.SubCategoryModel;

import java.util.List;

/**
 * Created by Manu on 12/16/2016.
 */
public class SubCategoryAdapter extends BaseAdapter {
    public Context context;
    public LayoutInflater inflater;
    List<SubCategoryModel.SubCategoriesEntity> itemList;

    int mPreviousPosition =-1;
    public SubCategoryAdapter(Context context, List<SubCategoryModel.SubCategoriesEntity> itemList) {
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

        DialListAdapter.ViewHolder holder;
        if (convertView == null) {
            holder = new DialListAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.category_row, null);


            holder.txtViewTitle = (TextView) convertView.findViewById(R.id.categoryLabel);


            convertView.setTag(holder);
        } else
            holder = (DialListAdapter.ViewHolder) convertView.getTag();


        holder.txtViewTitle.setText(itemList.get(position).getSub_category());



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

    public static class ViewHolder {
        TextView txtViewTitle;


    }

}
