package com.pss.booking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pss.booking.R;
import com.pss.booking.model.CategoryModel;

import java.util.ArrayList;

/**
 * Created by mdev3 on 10/19/16.
 */
public class CategoryAdapter extends BaseAdapter {
    public Context context;
    public LayoutInflater inflater;
    ArrayList<CategoryModel> itemList;

    public CategoryAdapter(Context context, ArrayList<CategoryModel> itemList) {
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
            convertView = inflater.inflate(R.layout.category_item_row, null);

holder.imageView = (ImageView)convertView.findViewById(R.id.imageViewView);
            holder.txtViewTitle = (TextView) convertView.findViewById(R.id.textViewtitle);

            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();


        holder.txtViewTitle.setText(itemList.get(position).getName());
        holder.imageView.setImageResource(itemList.get(position).getImage());



        return convertView;
    }

    public static class ViewHolder {
        TextView txtViewTitle;
        ImageView imageView;

    }
}
