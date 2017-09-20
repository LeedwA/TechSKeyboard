package com.ecar.skeyboard.commonkeyboard.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.ecar.skeyboard.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述:车牌号 组合控件
 * 1.点击回调 接口-->editText获取焦点
 * 2.设置车牌号api 对应显示imgview当做光标 。
 * 创建人:   happy
 * 创建时间: 2016/5/20 0020 上午 10:13
 * 修改人:   happy
 * 修改时间: 2016/5/20 0020 上午 10:13
 */
public class GroupCarNumView extends RelativeLayout implements View.OnClickListener {

    public static final int CAR_NUM_COUNT_NORMAL = 7; //普通车牌位数量

    public static final int CAR_NUM_COUNT_NEW_ENERGY = 8;//新能源车牌位数量

    private TextView mCharText8;
    private List<TextView> listTexts;
    private int size;

    private int index = 0;

    public GroupCarNumView(Context context) {
        this(context, null);
    }

    public GroupCarNumView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GroupCarNumView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_group_car_num, this);
        listTexts = new ArrayList<>();
        listTexts.add((TextView) findViewById(R.id.char_text1));
        listTexts.add((TextView) findViewById(R.id.char_text2));
        listTexts.add((TextView) findViewById(R.id.char_text3));
        listTexts.add((TextView) findViewById(R.id.char_text4));
        listTexts.add((TextView) findViewById(R.id.char_text5));
        listTexts.add((TextView) findViewById(R.id.char_text6));
        listTexts.add((TextView) findViewById(R.id.char_text7));
        mCharText8 = (TextView) findViewById(R.id.char_text8);
        mCharText8.setOnClickListener(this);
        size = listTexts.size();

        for (TextView textView : listTexts) {
            textView.setOnClickListener(this);
        }
        resetBg();
    }

    /***********
     * 点击事件
     ******************/

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.char_text1 || i == R.id.char_text2 || i == R.id.char_text3 || i == R.id.char_text4 || i == R.id.char_text5 || i == R.id.char_text6 || i == R.id.char_text7 || i == R.id.char_text8) {
            clickTextView((TextView) v);

        }
    }

    private void clickTextView(TextView v) {
        index = listTexts.indexOf(v);
        resetBg();
        onClickCallBack();
    }

    public void setClickTextView(int index) {
        this.index = index;
        resetBg();
        onClickCallBack();
    }

    private void onClickCallBack() {
        if (numClick != null) {
            numClick.onClickNum(index);
        }
    }

    /**
     * 切换为7位普通车牌
     */
    public void setNum4Seven() {
        if (listTexts.size() == CAR_NUM_COUNT_NEW_ENERGY) {
            mCharText8.setVisibility(GONE);
            mCharText8.setText("");
            listTexts.remove(CAR_NUM_COUNT_NEW_ENERGY - 1);
            size = listTexts.size();
            if (index == CAR_NUM_COUNT_NEW_ENERGY - 1) {//如果当前定位是最后一位
                index--;
            }
            resetBg();
        }
    }

    /**
     * 切换为8位新能源车牌
     */
    public void setNum4Eight() {
        if (listTexts.size() == CAR_NUM_COUNT_NORMAL) {
            mCharText8.setVisibility(VISIBLE);
            listTexts.add(mCharText8);
            size = listTexts.size();
            if (index == CAR_NUM_COUNT_NORMAL - 1 && !TextUtils.isEmpty(listTexts.get(index).getText())) {//如果当前定位是最后一位 且 当前最后一位有输入内容
                index++;
            }
            resetBg();
        }
    }

    /**
     * 重置输入框的背景框
     */
    private void resetBg() {
        int size = listTexts.size();
        for (int i = 0; i < size; i++) {
            if (i < index) {
                if (i == 0) {
                    listTexts.get(i).setBackgroundResource(R.drawable.parking_bind_bg_box_normal_left_fillet);
                } else {
                    listTexts.get(i).setBackgroundResource(R.drawable.parking_bind_bg_box_normal_left);
                }
            } else if (i > index) {
                if (i == listTexts.size() - 1) {
                    listTexts.get(i).setBackgroundResource(R.drawable.parking_bind_bg_box_normal_right_fillet);
                } else {
                    listTexts.get(i).setBackgroundResource(R.drawable.parking_bind_bg_box_normal_right);
                }
            }
        }
        if (index == 0) {
            listTexts.get(index).setBackgroundResource(R.drawable.parking_bind_bg_box_preess_left);
        } else if (index == listTexts.size() - 1) {
            listTexts.get(index).setBackgroundResource(R.drawable.parking_bind_bg_box_preess_right);
        } else {
            listTexts.get(index).setBackgroundResource(R.drawable.parking_bind_bg_box_preess);
        }
    }

    /**
     * 设置第一个输入框的内容
     */
    public void setFirstContent(String str) {
        listTexts.get(0).setText(str);
    }

    /***
     * 设置第二个输入框的内容
     * @param str
     */
    public void setTowContent(String str) {
        listTexts.get(1).setText(str);
    }

    /**
     * 设置整个输入框的内容
     *
     * @param str
     */
    public void setCarNum(String str) {
        if (!TextUtils.isEmpty(str)) {
            index = str.length() - 1;
            if (str.length() == CAR_NUM_COUNT_NORMAL) {
                if (listTexts.size() == CAR_NUM_COUNT_NORMAL) {
                    resetBg();
                } else {
                    setNum4Seven();
                }
            } else if (str.length() == CAR_NUM_COUNT_NEW_ENERGY) {
                setNum4Eight();
            } else {
                return;
            }
            for (int i = 0; i < str.length(); i++) {
                listTexts.get(i).setText(str.substring(i, i + 1));
            }
        }
    }

    /**
     * 设置数字
     */
    public void setContent(String str) {
        if (str != null && str.length() == 1) {
            listTexts.get(index).setText(str);
            if (index != size - 1) {//不是最后一个
                index++;
            }
            resetBg();
            onClickCallBack();
        }
    }

    /**
     * 删除
     **/
    public void delete() {
        //1. 获取当前的位置 内容 2.根据内容判断下一步编辑位置
        String currText = listTexts.get(index).getText().toString();
        //设置下一个 编辑位置 1.str不为空 直接清空 编辑位置不变 2.str 为空，表示已删除回到上一个位置进行编辑(为第一个位置则不变)
        if (!TextUtils.isEmpty(currText)) {
            listTexts.get(index).setText("");
        } else if (TextUtils.isEmpty(currText) && index != 0) {//为空，但不是第一个
            index--;
            listTexts.get(index).setText("");
        }
        resetBg();
        onClickCallBack();
    }

    /**
     * 获取所有输入框的内容
     ***/

    public String getAllContent() {
        String str = "";
        for (TextView textView : listTexts) {
            str += textView.getText().toString();
        }
        return str;
    }

    /**
     * 清空
     */
    public void clear() {
        for (int i = 1; i < size; i++) {
            listTexts.get(i).setText("");
        }
        index = 0;
        resetBg();
        onClickCallBack();
    }


    /**
     * 回调
     */
    public interface IGroupCarNumClick {
        void onClickNum(int index);
    }

    private IGroupCarNumClick numClick;

    public void setCarNumClick(IGroupCarNumClick numClick) {
        this.numClick = numClick;
    }
}
