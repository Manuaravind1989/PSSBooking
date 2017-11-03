package com.pss.booking.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pss.booking.R;
import com.pss.booking.Utils.DialogUtils;
import com.pss.booking.adapter.SubCategoryAdapter;
import com.pss.booking.callback.OnFragmentChangeListener;
import com.pss.booking.constants.ActivityConstants;
import com.pss.booking.constants.DialogConstants;
import com.pss.booking.httpManager.NetworkManager;
import com.pss.booking.httpManager.RequestType;
import com.pss.booking.httpManager.ServiceResponseListener;
import com.pss.booking.model.SubCategoryModel;

/**
 * Created by Manu on 12/16/2016.
 */
public class FragmentSubCategory extends Fragment implements ServiceResponseListener, AdapterView.OnItemClickListener, DialogUtils.OnDialogSelectedListener {
    int category_id = 0;
    MainActivity mActivity;
    private View rootView;
    private ListView mListView;
    NetworkManager mNetworkManager;
    OnFragmentChangeListener mOnFragmentChangeListener;
    SubCategoryAdapter mHotelAdapter;
    private int id_index = -1;
    DialogUtils mDialogUtils;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        mDialogUtils = new DialogUtils();
        mOnFragmentChangeListener = (MainActivity) getActivity();
        mNetworkManager = new NetworkManager(mActivity, this);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (category_id == 1) {
            mActivity.updateTitle(getString(R.string.nav_hotles),false);
        } else if (category_id == 2) {
            mActivity.updateTitle(getString(R.string.nav_travel),false);
        } else if (category_id == 3) {
            mActivity.updateTitle(getString(R.string.nav_renting),false);
        } else if (category_id == 4) {
            mActivity.updateTitle(getString(R.string.nav_industries),false);
        } else if (category_id == 5) {
            mActivity.updateTitle(getString(R.string.nav_hire),false);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(rootView!=null){
            return  rootView;
        }
        rootView = inflater.inflate(R.layout.fragment_subcatgory, container, false);
        mListView = (ListView) rootView.findViewById(R.id.listViewSubcategory);
        mNetworkManager.getSubCategory("" + category_id);
        // Inflate the layout for this fragment
        mListView.setOnItemClickListener(this);
        return rootView;
    }

    public void getcategoryId(int category_id) {
        this.category_id = category_id;
    }

    @Override
    public void onSuccessResponse(Object responseObject, String responseType) {
        if (responseType.equals(RequestType.SUBCATEGORY_TYPE)) {
            SubCategoryModel mSubCategoryModel = (SubCategoryModel) responseObject;
            if (mSubCategoryModel.getSuccess() == 1) {
                if (mSubCategoryModel.getSub_categories() != null) {
                    for (int k = 0; k < mSubCategoryModel.getSub_categories().size(); k++) {
                        Log.e("Title", "" + mSubCategoryModel.getSub_categories().get(k).getSub_category());
                    }

                    // prepareArrayLits();
                    mHotelAdapter = new SubCategoryAdapter(getActivity(), mSubCategoryModel.getSub_categories());
                    mListView.setAdapter(mHotelAdapter);
                } else if (mSubCategoryModel.getPosts() != null) {
                    for (int k = 0; k < mSubCategoryModel.getPosts().size(); k++) {
                        Log.e("Title", "" + mSubCategoryModel.getPosts().get(k).getSub_category());
                    }
                    String tempid = mSubCategoryModel.getPosts().get(0).getCategory();
                    if (category_id == 1) {
                        mOnFragmentChangeListener.onChangeListener(ActivityConstants.HOTELS_FRAGMENT, id_index);
                    } else if (category_id == 2) {
                        mOnFragmentChangeListener.onChangeListener(ActivityConstants.TRAVEL_FRAGMENT, id_index);

                    } else if (category_id == 3) {
                        //    mOnFragmentChangeListener.onChangeListener(ActivityConstants.HOTELS_FRAGMENT, id_index);

                    }
                }
            } else {
                mDialogUtils.okFunc(mActivity, mSubCategoryModel.getMsg(), "PSS", FragmentSubCategory.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);

            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        SubCategoryModel.SubCategoriesEntity mSubCategoryModel = (SubCategoryModel.SubCategoriesEntity) adapterView.getAdapter().getItem(i);
        mNetworkManager.getSubCategory("" + mSubCategoryModel.getId());
        id_index = Integer.valueOf(mSubCategoryModel.getId());
    }

    @Override
    public void onDialogClick(int selectedIndex, Object mObj, int dialogIndex) {

    }
}
