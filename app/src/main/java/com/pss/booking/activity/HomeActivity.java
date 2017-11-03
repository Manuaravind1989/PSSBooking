package com.pss.booking.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.pss.booking.R;


/**
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mPrivateButton, mBussinessButton, mOrganisationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        mPrivateButton = (Button) findViewById(R.id.PrivateButton);
        mBussinessButton = (Button) findViewById(R.id.BussinessButton);
        mOrganisationButton = (Button) findViewById(R.id.OrganizationButton);
        mPrivateButton.setOnClickListener(this);
        mBussinessButton.setOnClickListener(this);
        mOrganisationButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String userType ="";
        switch (v.getId()){
            case R.id.PrivateButton:{
                userType = "private";
            }
            break;
            case R.id.BussinessButton:{
                userType = "bussiness";
            }
            break;
            case R.id.OrganizationButton:{
                userType = "organization";
            }
            break;
        }
        Intent i = new Intent(HomeActivity.this, SignupActivity.class);
        i.putExtra("USERTYPE",userType);
        startActivity(i);
    }
}
