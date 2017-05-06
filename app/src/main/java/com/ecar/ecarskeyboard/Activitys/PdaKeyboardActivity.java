package com.ecar.ecarskeyboard.Activitys;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.ecar.ecarskeyboard.R;
import com.ecar.mylibrary.pdaKeyboard.PdaKeyboardCityUtil;
import com.ecar.mylibrary.pdaKeyboard.PdaKeyboardNumUtil;
import com.ecar.mylibrary.pdaKeyboard.view.CityPopupWindow;
import com.ecar.mylibrary.util.K_Util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.orhanobut.logger.Logger.init;

/*************************************
 功能： pda输入键盘
 创建者： kim_tony
 创建日期：2017/5/4
 版权所有：深圳市亿车科技有限公司
 *************************************/

public class PdaKeyboardActivity extends Activity {

    private TextView normal_ed0;
    private PdaKeyboardNumUtil pdaKeyboardUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_pdakeyboard);
        initView();
    }


    private void initView() {
        //城市选择键盘
        normal_ed0 = (TextView) findViewById(R.id.normal_ed0);
        PdaKeyboardCityUtil pdaKeyboardCityUtil = new PdaKeyboardCityUtil(this, normal_ed0, new CityPopupWindow.OnSelectCity() {
            @Override
            public void getCityFromClick(String city) {
                normal_ed0.setText(TextUtils.isEmpty(city) ? "无" : city);
            }
        }, new CityPopupWindow.OnClicked() {
            @Override
            public void clicked() {
                pdaKeyboardUtil.hideKeyboard();
            }
        });
        //车牌号选择键盘
        KeyboardView keyboardView = (KeyboardView) findViewById(R.id.view_kbview);
        final EditText normal_ed1 = (EditText) findViewById(R.id.normal_ed1);
        pdaKeyboardUtil = new PdaKeyboardNumUtil(keyboardView, this, normal_ed1, new PdaKeyboardNumUtil.SlideView() {
            @Override
            public void hideView() {

            }
        }, false);

        normal_ed1.setText("99999999");

    }

}
