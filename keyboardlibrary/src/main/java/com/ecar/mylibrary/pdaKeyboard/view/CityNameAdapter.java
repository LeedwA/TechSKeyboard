package com.ecar.mylibrary.pdaKeyboard.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ecar.mylibrary.R;

public class CityNameAdapter extends BaseAdapter implements OnClickListener {

    private Context context;
    private String[] provinces = {"皖", "苏", "赣", "浙", "豫", "鲁", "粤", "京",
            "津", "沪", "渝", "蒙", "新", "藏", "宁", "桂", "港", "澳", "黑", "吉",
            "辽", "晋", "冀", "青", "闽", "湘", "鄂", "琼", "甘", "陕", "黔", "云",
            "川", "台", "贵"};

    public CityNameAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public int getCount() {
        return provinces.length;
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
        if (position < 35) {
            city = (TextView) LayoutInflater.from(context).inflate(
                    R.layout.pub_item_city_name, null);
            city.setText(provinces[position]);
            city.setGravity(Gravity.CENTER);
        }
        city.setClickable(true);
        city.setOnClickListener(this);
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
