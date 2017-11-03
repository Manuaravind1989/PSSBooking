package com.pss.booking.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pss.booking.R;


/**
 * Created by Manu on 10/1/2016.
 */
public class IntroPagerAdapter extends PagerAdapter {
    // Declare Variables
    Context context;
    String[] content;

    LayoutInflater inflater;

    public IntroPagerAdapter(Context context, String[] content) {
        this.context = context;
        this.content = content;

    }

    @Override
    public int getCount() {
        return content.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        // Declare Variables
        TextView txtcontent;


        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.intro_pager_item, container,
                false);

        // Locate the TextViews in viewpager_item.xml
        txtcontent = (TextView) itemView.findViewById(R.id.intro_content);


        // Capture position and set to the TextViews
        //  txtcontent.setText(getR);


        // Add viewpager_item.xml to ViewPager
        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((RelativeLayout) object);

    }
}