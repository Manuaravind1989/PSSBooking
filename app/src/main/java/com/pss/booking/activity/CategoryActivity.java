package com.pss.booking.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.pss.booking.R;
import com.pss.booking.Utils.UserPefer;
import com.pss.booking.adapter.CategoryAdapter;
import com.pss.booking.httpManager.NetworkManager;
import com.pss.booking.httpManager.RequestType;
import com.pss.booking.httpManager.ServiceResponseListener;
import com.pss.booking.model.CategoryModel;

import java.util.ArrayList;

/**
 * Created by mdev3 on 10/19/16.
 */
public class CategoryActivity extends AppCompatActivity implements View.OnClickListener ,ServiceResponseListener {
    private ListView mListView;
    private ArrayList<CategoryModel> itemList;
    private CategoryAdapter mAdapter;
    private LinearLayout mLayout;
    NetworkManager networkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_layout);
        networkManager =  new NetworkManager(this, this);
        prepareArrayLits();
        mListView = (ListView) findViewById(R.id.listViewCategores);
        mLayout = (LinearLayout) findViewById(R.id.PssAds);
        mAdapter = new CategoryAdapter(CategoryActivity.this, itemList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(OnItemClick);
        mLayout.setOnClickListener(this);
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        networkManager.registerPushNotification(refreshedToken);
    }

    /* Method used to prepare the ArrayList,
     * Same way, you can also do looping and adding object into the ArrayList.
     */
    public void prepareArrayLits() {
        itemList = new ArrayList<CategoryModel>();

        AddObjectToList(R.drawable.hotel_bg, "HOTEL");
        AddObjectToList(R.drawable.transport_bg, "TRAVEL & TRANSPORT");
        AddObjectToList(R.drawable.renting_bg, "PRIVATE RENTING");
        AddObjectToList(R.drawable.flokirt_bg, "INDUSTRIES & EQUPMENT");
        AddObjectToList(R.drawable.hire_bg, "HIRE");


    }

    // Add one item into the Array List
    public void AddObjectToList(int image, String title) {
        CategoryModel bean = new CategoryModel();

        bean.setImage(image);
        bean.setName(title);
        itemList.add(bean);
    }

    AdapterView.OnItemClickListener OnItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent mSkipIntent = new Intent(CategoryActivity.this, MainActivity.class);

            mSkipIntent.putExtra("CATEGORY_TYPE", position);
            startActivity(mSkipIntent);
        }
    };

    @Override
    public void onClick(View view) {
        UserPefer userPefer = new UserPefer(this);
        if (userPefer.isLoggedIn()) {
            if (userPefer.getUserType().equals("private")) {
                Toast.makeText(CategoryActivity.this, "Private user cannot add the post", Toast.LENGTH_SHORT).show();
            } else {
                Intent mSkipIntent = new Intent(CategoryActivity.this, MainActivity.class);
                mSkipIntent.putExtra("CATEGORY_TYPE", 9);
                startActivity(mSkipIntent);
            }
        } else {
            Intent mSkipIntent = new Intent(CategoryActivity.this, MainActivity.class);
            mSkipIntent.putExtra("CATEGORY_TYPE", 8);
            startActivity(mSkipIntent);
        }
    }

    @Override
    public void onSuccessResponse(Object responseObject, String responseType) {
        if(responseType.equals(RequestType.PUSH_NOTIFICATION)){

        }
    }
}
