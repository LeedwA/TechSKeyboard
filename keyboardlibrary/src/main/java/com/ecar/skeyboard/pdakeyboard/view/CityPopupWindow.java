package com.ecar.skeyboard.pdakeyboard.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ecar.skeyboard.R;


/**
 * ===============================================
 * <p/>
 * 类描述:
 * <p/>
 * 创建人: Eric_Huang
 * <p/>
 * 创建时间: 2016/5/7 15:04
 * <p/>
 * 修改人:Eric_Huang
 * <p/>
 * 修改时间: 2016/5/7 15:04
 * <p/>
 * 修改备注:
 * <p/>
 * ===============================================
 */
public class CityPopupWindow {

    public PopupWindow mCityPop;
    private OnSelectCity mOnSecectCallback;
    private Context mContext;

    public CityPopupWindow(Context context, OnSelectCity callback) {
        mContext = context;
        mOnSecectCallback = callback;
        initCitySelector();
    }

    //初始化城市选择控件
    private void initCitySelector() {
        if (mCityPop == null) {
            final View view = View.inflate(mContext, R.layout.pub_pop_city_name, null);
            TextView tv_none_num = (TextView) view.findViewById(R.id.tv_none_num);

            tv_none_num.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnSecectCallback.getCityFromClick("");
                }
            });
            TextView tv_wj = (TextView) view.findViewById(R.id.tv_wj);//武警车牌
            tv_wj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnSecectCallback.getCityFromClick("WJ");
                }
            });
            GridView gvCityName = (GridView) view
                    .findViewById(R.id.gv_city_name_container);
            gvCityName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    mOnSecectCallback.getCityFromClick(((TextView) view).getText().toString().trim());
                }
            });
            CityNameAdapter cityNameAdapter = new CityNameAdapter(mContext);
            cityNameAdapter.setOnTextItemOnClickListener(new CityNameAdapter.OnTextItemOnClickListener() {
                @Override
                public void onTextItemOnClick(View city) {
                    mOnSecectCallback.getCityFromClick(((TextView) city).getText().toString()
                            .trim());
                }
            });
            gvCityName.setAdapter(cityNameAdapter);
            mCityPop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            mCityPop.setBackgroundDrawable(new BitmapDrawable());
            mCityPop.setFocusable(true);
        }
    }

    public PopupWindow getmCityPop() {
        return mCityPop;
    }

    //城市选择回调
    public interface OnSelectCity {
        void getCityFromClick(String city);
    }
    //城市初选回调
    public interface OnClicked {
        void clicked();
    }
}
