package com.ecar.skeyboard.commonkeyboard.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ecar.skeyboard.R;


/**
 * 类描述：车牌字母适配器
 * 创建人：Shirley
 */
public class CityCharAdapter extends BaseAdapter implements OnClickListener {

    public static final int PAGE_TYPE_PROVINCE = 1;

    public static final int PAGE_TYPE_NUM = 2;

    public static final int PAGE_TYPE_NUM_LAST = 3;

    private Context mContext;

    private int mViewHeight;

    private String[] provinces = {"Q", "W", "E", "R", "T", "Y", "U", "P", "港", "澳",
            "A", "S", "D", "F", "G", "H", "J", "K", "L", "学"," ",
            "Z", "X", "C", "V", "B", "N", "M"," "};

    public CityCharAdapter(Context context) {
        super();
        this.mContext = context;
    }

    public int mPageType = PAGE_TYPE_PROVINCE;

    public void setPageType(int pageType) {
        this.mPageType = pageType;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        int size = 0;
        if (mPageType == PAGE_TYPE_NUM) {
            size = provinces.length + 1;
        } else if (mPageType == PAGE_TYPE_PROVINCE) {
            size = provinces.length + 1;
        } else if (mPageType == PAGE_TYPE_NUM_LAST) {
            size = provinces.length + 1;
        }
        return size;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView city = null;
        if (mPageType == PAGE_TYPE_PROVINCE) {
             if (position == 20 || position == 28) {
                city = (TextView) LayoutInflater.from(mContext).inflate(R.layout.item_bind_carnum_ctiy_belong_check_text, null);
                city.setBackgroundResource(R.color.transparent);
                city.setVisibility(View.INVISIBLE);
            } else if (position == provinces.length) {
                 city = (TextView) LayoutInflater.from(mContext).inflate(
                         R.layout.item_bind_carnum_ctiy_belong_check_text, null);
                 Drawable drawable = mContext.getResources().getDrawable(
                         R.drawable.parking_bind_delete);
                 if (drawable != null) {
                     drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                     city.setCompoundDrawables(drawable, null, null, null);
                 }
                 city.setBackgroundResource(R.drawable.btn_bind_carnum_delete_bg);
                 city.setClickable(true);
                 city.setOnClickListener(this);
                 AbsListView.LayoutParams para = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT);
                 para.height = mViewHeight;
                 city.setLayoutParams(para);
            } else {
                 city = (TextView) LayoutInflater.from(mContext).inflate(R.layout.item_bind_carnum_ctiy_belong_check_text, null);
                 city.setText(provinces[position]);
                 city.setGravity(Gravity.CENTER);
                 city.setClickable(true);
                 city.setOnClickListener(this);
             }
        } else if (mPageType == PAGE_TYPE_NUM) {
            if (position == 20 || position == 28) {
                city = (TextView) LayoutInflater.from(mContext).inflate(R.layout.item_bind_carnum_ctiy_belong_check_text, null);
                city.setBackgroundResource(R.color.transparent);
                city.setVisibility(View.INVISIBLE);
            } else if (position == provinces.length) {
                city = (TextView) LayoutInflater.from(mContext).inflate(R.layout.item_bind_carnum_ctiy_belong_check_text, null);
                Drawable drawable = mContext.getResources().getDrawable(R.drawable.parking_bind_delete);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                city.setCompoundDrawables(drawable, null, null, null);
                city.setBackgroundResource(R.drawable.btn_bind_carnum_delete_bg);
                city.setClickable(true);
                city.setOnClickListener(this);
                AbsListView.LayoutParams para=new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT );
                para.height = mViewHeight;
                city.setLayoutParams(para);
            } else {
                city = (TextView) LayoutInflater.from(mContext).inflate(R.layout.item_bind_carnum_ctiy_belong_check_text, null);
//                if (provinces[position].equals("澳") || provinces[position].equals("港") ||
//                        provinces[position].equals("学")) {
//                    city.setText(provinces[position]);
//                    city.setGravity(Gravity.CENTER);
//                    city.setBackgroundResource(R.drawable.btn_bind_carnum_unclick_bg);
//                    city.setClickable(false);
//                } else {
                    city.setText(provinces[position]);
                    city.setGravity(Gravity.CENTER);
                    city.setClickable(true);
                    city.setOnClickListener(this);

//                }
            }
        } else if (mPageType == PAGE_TYPE_NUM_LAST) {
            if (position == 20 || position == 28) {
                city = (TextView) LayoutInflater.from(mContext).inflate(R.layout.item_bind_carnum_ctiy_belong_check_text, null);
                city.setBackgroundResource(R.color.transparent);
                city.setVisibility(View.INVISIBLE);
            } else if (position == provinces.length) {
                city = (TextView) LayoutInflater.from(mContext).inflate(R.layout.item_bind_carnum_ctiy_belong_check_text, null);
                Drawable drawable = mContext.getResources().getDrawable(R.drawable.parking_bind_delete);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                city.setCompoundDrawables(drawable, null, null, null);
                city.setBackgroundResource(R.drawable.btn_bind_carnum_delete_bg);
                city.setClickable(true);
                city.setOnClickListener(this);
                AbsListView.LayoutParams para=new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT );
                para.height = mViewHeight;
                city.setLayoutParams(para);
            } else {
                city = (TextView) LayoutInflater.from(mContext).inflate(R.layout.item_bind_carnum_ctiy_belong_check_text, null);
                city.setText(provinces[position]);
                city.setGravity(Gravity.CENTER);
                city.setClickable(true);
                city.setOnClickListener(this);
            }
        }

        if (mViewHeight == 0) {
            if (city != null) {
                city.measure(0,0);
                mViewHeight = city.getMeasuredHeight();
            }
        }
        return city;
    }

    public interface OnTextItemOnClickListener {
        void onTextItemOnClick(View city);
    }

    private OnTextItemOnClickListener onTextItemOnClickListener;

    public void setOnTextItemOnClickListener(
            OnTextItemOnClickListener onTextItemOnClickListener) {
        this.onTextItemOnClickListener = onTextItemOnClickListener;
    }

    @Override
    public void onClick(View v) {
        onTextItemOnClickListener.onTextItemOnClick(v);
    }


}
