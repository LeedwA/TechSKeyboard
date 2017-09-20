package com.ecar.skeyboard.commonkeyboard.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ecar.skeyboard.R;


/**
 * 类描述：车牌数字适配器
 * 创建人：Shirley
 */
public class CityDigitalAdapter extends BaseAdapter implements OnClickListener {

    private Context mContext;

    private String[] mNumList = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public CityDigitalAdapter(Context context) {
        super();
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mNumList.length;
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
        city = (TextView) LayoutInflater.from(mContext).inflate(R.layout.item_bind_carnum_ctiy_belong_check_text, null);
        city.setText(mNumList[position]);
        city.setGravity(Gravity.CENTER);
//        city.setBackgroundResource(R.color.bind_car_digital_color);
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
