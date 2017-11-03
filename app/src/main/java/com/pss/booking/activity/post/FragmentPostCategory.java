package com.pss.booking.activity.post;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pss.booking.R;
import com.pss.booking.Utils.DialogUtils;
import com.pss.booking.Utils.UserPefer;
import com.pss.booking.activity.MainActivity;
import com.pss.booking.adapter.PostCategoryAdapter;
import com.pss.booking.callback.OnFragmentChangeListener;
import com.pss.booking.constants.ActivityConstants;

/**
 * Created by mdev3 on 12/12/16.
 */

public class FragmentPostCategory extends Fragment {
    MainActivity mActivity;
    DialogUtils mDialogUtils;
    OnFragmentChangeListener mOnFragmentChangeListener;
    View rootView;
    UserPefer mUserPefer;
    private ListView mListCategory;
    String[] arrOfCategories = {"HOTELS","TRAVEL & TRANSPORT", "PRIVATE RENTING","INDUSTRIES & EQUIPMENTS","HIRE"};
    PostCategoryAdapter mPostCategoryAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        mDialogUtils = new DialogUtils();

        mOnFragmentChangeListener = (MainActivity) getActivity();
        mUserPefer = new UserPefer(mActivity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(rootView!=null){
            return rootView;
        }
        rootView = inflater.inflate(R.layout.fragment_post_category, container, false);

        initUI();
        setListners();
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        mActivity.updateTitle(getString(R.string.nav_postAdd),false);
    }

    private void initUI(){
        mListCategory = (ListView)rootView.findViewById(R.id.ListCategory);
        mListCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // BeneificiaryListModel.BeneficiaryListEntity selItem = (BeneificiaryListModel.BeneficiaryListEntity) parent.getAdapter().getItem(position);


                mOnFragmentChangeListener.onChangeListener(ActivityConstants.POST_SUBCATEGORY, ""+(position+1));
            }
        });
    }
    private void setListners(){
        mPostCategoryAdapter = new PostCategoryAdapter(getActivity(), arrOfCategories);
        mListCategory.setAdapter(mPostCategoryAdapter);
    }

}
