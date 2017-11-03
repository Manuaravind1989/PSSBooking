package com.pss.booking.Utils;

import android.util.Log;

import com.pss.booking.constants.callingCodeJson;
import com.pss.booking.model.DialCodeModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Manu on 10/2/2016.
 */
public class CommonUtils {

    public static ArrayList<DialCodeModel> getCallingCodes() {
        ArrayList<DialCodeModel> mCodeList = new ArrayList<DialCodeModel>();
        try {
            JSONArray arr = new JSONArray(callingCodeJson.callingCode);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject c = arr.getJSONObject(i);
                String name = c.getString("name");
                String dial_code = c.getString("dial_code");
                String code = c.getString("code");
                DialCodeModel mDialCodeModel = new DialCodeModel();
                mDialCodeModel.setName(c.getString("name"));
                mDialCodeModel.setDialCode(c.getString("dial_code"));
                mDialCodeModel.setCode(c.getString("code"));
                Log.e("Country Name  ====> ", name);
                mCodeList.add(mDialCodeModel);
            }
        } catch (Exception e) {

        }

        return mCodeList;
    }
}
