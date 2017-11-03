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
import com.pss.booking.activity.MainActivity;
import com.pss.booking.adapter.PostSubCategoryAdapter;
import com.pss.booking.callback.OnFragmentChangeListener;
import com.pss.booking.constants.ActivityConstants;
import com.pss.booking.httpManager.NetworkManager;
import com.pss.booking.httpManager.RequestType;
import com.pss.booking.httpManager.ServiceResponseListener;
import com.pss.booking.model.SubCategoryModel;

/**
 * Created by mdev3 on 12/12/16.
 */

public class FragmentPostSubCategory extends Fragment implements ServiceResponseListener {
    MainActivity mActivity;
    DialogUtils mDialogUtils;
    OnFragmentChangeListener mOnFragmentChangeListener;
    View rootView;
    PostSubCategoryAdapter mPostSubCategoryAdapter;
    private ListView mListSubCategory;
    private NetworkManager networkManager;
    private String mCategoryId ="0";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        mDialogUtils = new DialogUtils();
        networkManager = new NetworkManager(mActivity, this);
        mOnFragmentChangeListener = (MainActivity) getActivity();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(rootView!=null){
            return rootView;
        }
        rootView = inflater.inflate(R.layout.fragment_post_subcategory, container, false);
        networkManager.getSubCategory(mCategoryId);
        initUI();
        setListners();
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        mActivity.updateTitle(getString(R.string.nav_postAdd),false);
    }

    private void initUI() {
        mListSubCategory = (ListView) rootView.findViewById(R.id.ListSubCategory);
        mListSubCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SubCategoryModel.SubCategoriesEntity selItem = (SubCategoryModel.SubCategoriesEntity) parent.getAdapter().getItem(position);
                ItemCategoryModel mItemCategoryModel = new ItemCategoryModel();
                mItemCategoryModel.setCategoryid(mCategoryId);
                mItemCategoryModel.setSubcategoryid(selItem.getId());
                mItemCategoryModel.setSubCategoryName(selItem.getSub_category());
                mOnFragmentChangeListener.onChangeListener(ActivityConstants.POST_ADD, mItemCategoryModel);
            }
        });
    }

    private void setListners() {

    }

    @Override
    public void onSuccessResponse(Object responseObject, String responseType) {
        if (responseType.equals(RequestType.SUBCATEGORY_TYPE)) {
            SubCategoryModel mSubCategoryModel = (SubCategoryModel)responseObject;
            mPostSubCategoryAdapter = new PostSubCategoryAdapter(getActivity(),  mSubCategoryModel.getSub_categories());
            mListSubCategory.setAdapter(mPostSubCategoryAdapter);
        }
    }

    public void getCategoryId(String id){
        this.mCategoryId = id;
    }

    public class ItemCategoryModel{
        String categoryid="";

        public String getSubcategoryid() {
            return subcategoryid;
        }

        public void setSubcategoryid(String subcategoryid) {
            this.subcategoryid = subcategoryid;
        }

        public String getCategoryid() {
            return categoryid;
        }

        public void setCategoryid(String categoryid) {
            this.categoryid = categoryid;
        }

        String subcategoryid ="";

        public String getSubCategoryName() {
            return subCategoryName;
        }

        public void setSubCategoryName(String subCategoryName) {
            this.subCategoryName = subCategoryName;
        }

        String subCategoryName = "";
    }
}
