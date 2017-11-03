package com.pss.booking.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pss.booking.R;

import java.util.ArrayList;

/**
 * Created by mdev3 on 10/13/16.
 */
public class ArrivalTimeAdapter extends RecyclerView.Adapter<ArrivalTimeAdapter.ViewHolder> {
    private ArrayList<String> itemList;

    public ArrivalTimeAdapter(ArrayList<String> itemList) {
        this.itemList = itemList;
    }

    @Override
    public ArrivalTimeAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_filter, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArrivalTimeAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_country.setText(itemList.get(i));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_country;

        public ViewHolder(View view) {
            super(view);

            tv_country = (TextView) view.findViewById(R.id.ItemTitle);
        }
    }

}
