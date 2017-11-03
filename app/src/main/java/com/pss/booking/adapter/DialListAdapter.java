package com.pss.booking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pss.booking.R;
import com.pss.booking.model.DialCodeModel;

import java.util.ArrayList;


/**
 * Created by Manu on 10/2/2016.
 */
public class DialListAdapter extends BaseAdapter {
    public Context context;
    public LayoutInflater inflater;
    ArrayList<DialCodeModel> itemList;

    public DialListAdapter(Context context, ArrayList<DialCodeModel> itemList) {
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
            convertView = inflater.inflate(R.layout.diallist_item, null);


            holder.txtViewTitle = (TextView) convertView.findViewById(R.id.textViewCountry);
            holder.txtViewDialCode = (TextView) convertView.findViewById(R.id.textViewDialCode);
            holder.txtViewCode = (TextView) convertView.findViewById(R.id.textViewCode);

            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();


        holder.txtViewTitle.setText(itemList.get(position).getName());
        holder.txtViewDialCode.setText(itemList.get(position).getDialCode());
        holder.txtViewCode.setText(itemList.get(position).getCode());


        return convertView;
    }

    public static class ViewHolder {
        TextView txtViewTitle;
        TextView txtViewDialCode;
        TextView txtViewCode;

    }


}
