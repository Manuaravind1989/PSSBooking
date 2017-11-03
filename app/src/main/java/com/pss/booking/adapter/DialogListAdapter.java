package com.pss.booking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pss.booking.R;

;

/**
 * Created by Manu on 10/1/2016.
 */
public class DialogListAdapter extends BaseAdapter {
    public Context context;
    public LayoutInflater inflater;
    String[] itemList;

    public DialogListAdapter(Context context, String[] itemList) {
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
            convertView = inflater.inflate(R.layout.dialog_list_item, null);


            holder.txtViewTitle = (TextView) convertView.findViewById(R.id.dialog_textview);

            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();


        holder.txtViewTitle.setText(itemList[position]);


        return convertView;
    }

    public static class ViewHolder {
        TextView txtViewTitle;

    }

}