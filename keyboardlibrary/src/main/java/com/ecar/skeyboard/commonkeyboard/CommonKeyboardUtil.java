package com.ecar.skeyboard.commonkeyboard;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.ecar.skeyboard.R;
import com.ecar.skeyboard.commonkeyboard.view.CarKeyboardView;
import com.ecar.skeyboard.commonkeyboard.view.GroupCarNumView;
import com.ecar.skeyboard.util.DBPreferences;

import java.util.Arrays;
import java.util.List;

/**
 * +----------------------------------------------------------------------
 * |  说明     ：
 * +----------------------------------------------------------------------
 * | 创建者   :  kim_tony
 * +----------------------------------------------------------------------
 * | 时　　间 ：2017/9/20 02:04
 * +----------------------------------------------------------------------
 * | 版权所有: 北京市车位管家科技有限公司
 * +----------------------------------------------------------------------
 **/

public class CommonKeyboardUtil {
    private String[] mProvincesFuName = {"北京", "上海", "浙江", "苏州", "广东", "山东", "山西", "河北", "河南",
            "四川", "重庆", "辽宁", "吉林", "黑龙", "安徽", "湖北", "湖南", "江西", "福建", "陕西", "甘肃", "宁夏",
            "内蒙", "天津", "贵州", "云南", "广西", "海南", "青海", "新疆", "西藏"};

    private String[] mProvinces = {"京", "沪", "浙", "苏", "粤", "鲁", "晋", "冀", "豫",
            "川", "渝", "辽", "吉", "黑", "皖", "鄂", "湘", "赣", "闽", "陕", "甘", "宁",
            "蒙", "津", "贵", "云", "桂", "琼", "青", "新", "藏"};

    private List<String> mListProFuName;
    private PopupWindowHelper mHelper;
    private int mCarNumType = GroupCarNumView.CAR_NUM_COUNT_NORMAL;//车牌类型：默认普通7位车牌
    private GroupCarNumView mGroupCarNumView;//自定义车牌输入框
    private CarKeyboardView mCarKeyboardView;//自定义车牌键盘


    private boolean isVibrate = true;//是否震动

    public void setOnTextClicked(CarKeyboardView.OnTextListener onTextClicked) {
        this.onTextClicked = onTextClicked;

    }

    /**
     * @Override public void onText(String text) {
     * mGroupCarNumView.setContent(text);
     * <p>
     * }
     * @Override public void onSure() {
     * hidePopcity();
     * <p>
     * }
     * @Override public void onDelete() {
     * mGroupCarNumView.delete();
     * }
     **/

    CarKeyboardView.OnTextListener onTextClicked;

    Activity context;


    public int getmCarNumType() {
        return mCarNumType;

    }

    private void initData() {
        mListProFuName = Arrays.asList(mProvincesFuName);
        mGroupCarNumView.setCarNumClick(new GroupCarNumView.IGroupCarNumClick() {
            @Override
            public void onClickNum(int index) {
                showKeyboard();
                mCarKeyboardView.setKeyboard(index);
            }
        });
    }

    //  isVibrate是否震动
    public CommonKeyboardUtil(Activity activity, GroupCarNumView mGroupCarNumView, CarKeyboardView.OnTextListener onTextClicked) {
        this.context = activity;
        this.mGroupCarNumView = mGroupCarNumView;
        this.onTextClicked = onTextClicked;
        initData();
    }


    //  isVibrate是否震动
    public CommonKeyboardUtil(Activity activity, GroupCarNumView mGroupCarNumView, CarKeyboardView.OnTextListener onTextClicked, boolean isVibrate) {
        this.context = activity;
        this.mGroupCarNumView = mGroupCarNumView;
        this.onTextClicked = onTextClicked;
        initData();
        this.isVibrate = isVibrate;

    }


    //设置默认城市
    public void setDefaultProvince(int index) {
        DBPreferences.getDefault(context).setLocationCityProvince(
                mProvincesFuName[index]);
    }

    /****************************************
     方法描述：切换普通车牌和新能源
     @param  isNew  新能源车牌
     @return
     ****************************************/
    public void changeToNew(boolean isNew) {
        if (isNew) {
            mGroupCarNumView.setNum4Seven();
            mCarNumType = GroupCarNumView.CAR_NUM_COUNT_NORMAL;
        } else {
            mGroupCarNumView.setNum4Eight();
            mCarNumType = GroupCarNumView.CAR_NUM_COUNT_NEW_ENERGY;
        }
    }

    private void showKeyboard() {
        if (mHelper == null) {
            mHelper = new PopupWindowHelper(context);
            mHelper.setFocusableTouch(false, false);
            mCarKeyboardView = new CarKeyboardView(context);
            mCarKeyboardView.setOnTextItemOnClickListener(onTextClicked);
//            mCarKeyboardView.setOnTextItemOnClickListener(new CarKeyboardView.OnTextListener() {
//                @Override
//                public void onText(String text) {
//                    mGroupCarNumView.setContent(text);
//                    setSubBtnBg();
//                }
//
//                @Override
//                public void onSure() {
//                    hidePopcity();
//                    setSubBtnBg();
//                }
//
//                @Override
//                public void onDelete() {
//                    mGroupCarNumView.delete();
//                    setSubBtnBg();
//                }
//            });
        }
        if (!mHelper.isShowing()) {
            mHelper.showPopupWindow(mCarKeyboardView, context.getWindow().getDecorView(), 1.0f);
        }
        mCarKeyboardView.setVibrate(isVibrate);
    }

    public void hidePopcity() {
        if (mHelper != null) {
            mHelper.dismissPopupWindow();
        }
    }


    /****************************************
     方法描述：设置第一个和第二个字母
     @param    cityNum 默认城市
     @return
     ****************************************/
    public void setFirstTwoText(String cityNum) {
        mGroupCarNumView.setFirstContent(cityNum);
        String province = DBPreferences.getDefault(context).getLocationCityProvince();
        int indexOf = mListProFuName.indexOf(province);
        if (!TextUtils.isEmpty(province) && indexOf != -1) {
            mGroupCarNumView.setFirstContent(mProvinces[indexOf]);
        }
        if (DBPreferences.getDefault(context).getLocationCityName().equals(context.getString(R.string.default_city)) || TextUtils.isEmpty(DBPreferences.getDefault(//如果当前定位城市为深圳，或者没有定位到城市，第二位默认为深圳的车牌字母
                context).getLocationCityName())) {
            mGroupCarNumView.setTowContent(context.getString(R.string.default_car_code));
        }
    }

    //获取车牌号
    public String getCarnum() {
        return mGroupCarNumView.getAllContent();
    }


}
