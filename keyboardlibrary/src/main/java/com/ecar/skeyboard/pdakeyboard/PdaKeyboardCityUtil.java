package com.ecar.skeyboard.pdakeyboard;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.TextView;

import com.ecar.skeyboard.commonkeyboard.view.GroupCarNumView;
import com.ecar.skeyboard.pdakeyboard.view.CityPopupWindow;

/*************************************
 功能：
 创建者： kim_tony
 创建日期：2017/5/5
 版权所有：深圳市亿车科技有限公司
 *************************************/

public class PdaKeyboardCityUtil {

    CityPopupWindow cityPopupWindow;  //城市布局
    private GroupCarNumView mGroupCarNumView;//自定义车牌输入框


    boolean isVibrate = true;   //是否震动

    public PdaKeyboardCityUtil setVibrate(boolean vibrate) {
        cityPopupWindow.setVibrate(vibrate);
        isVibrate = vibrate;
        return this;
    }

    //    cityTextView  显示城市代号的textview     onSelectCity  选择后的回调  点击无车牌时会返回“”  onClicked  城市被点击后回调
    public PdaKeyboardCityUtil(final Activity context, final TextView cityTextView,
                               CityPopupWindow.OnSelectCity onSelectCity,
                               final CityPopupWindow.OnClicked onClicked) {
        cityTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPop(context);
                if (onClicked != null) {
                    onClicked.clicked();
                }
            }
        });
        cityPopupWindow = new CityPopupWindow(context, onSelectCity);
    }

    //弹城市选择框
    public void showPop(Activity activity) {
        cityPopupWindow.getmCityPop().setTouchable(true);
        cityPopupWindow.getmCityPop().setBackgroundDrawable(new ColorDrawable());
        cityPopupWindow.getmCityPop().showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
    }

    public void dismiss() {
        if (cityPopupWindow != null) {
            cityPopupWindow.getmCityPop().dismiss();
        }
    }


}
