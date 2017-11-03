package com.pss.booking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pss.booking.R;

/**
 * Created by mdev3 on 12/12/16.
 */

public class PostCategoryAdapter extends BaseAdapter {

    public Context context;
    public LayoutInflater inflater;
    String[]  itemArray;

    public PostCategoryAdapter(Context context, String[] itemArray) {
        super();

        this.context = context;
        this.itemArray = itemArray;

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return itemArray.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return itemArray[position];
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


        holder.txtViewTitle.setText(itemArray[position]);



        return convertView;
    }

    public static class ViewHolder {
        TextView txtViewTitle;


    }

}
