package com.pss.booking.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pss.booking.R;
import com.pss.booking.Utils.UserPefer;
import com.pss.booking.model.NavDrawerItem;

import java.util.Collections;
import java.util.List;

/**
 * Created by Ravi on 29/07/15.
 */


public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder> {
    List<NavDrawerItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;


    public NavigationDrawerAdapter(Context context, List<NavDrawerItem> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NavDrawerItem current = data.get(position);
        holder.title.setText(current.getTitle());
        UserPefer mUserPefer = new UserPefer(context);
        if (position == 9) {
            if (mUserPefer.isLoggedIn()) {
                holder.title.setText("Logout");
            }
        }
        if (current.isShowNotify()) {
            holder.image.setImageResource(current.getImageHighlight());
            holder.backgroundView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.title.setTextColor(Color.WHITE);
        } else {
            holder.image.setImageResource(current.getImage());
            holder.backgroundView.setBackgroundColor(Color.WHITE);
            holder.title.setTextColor(Color.BLACK);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        LinearLayout backgroundView;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            image = (ImageView) itemView.findViewById(R.id.imageView);
            backgroundView = (LinearLayout) itemView.findViewById(R.id.rowBackground);
        }
    }
}
