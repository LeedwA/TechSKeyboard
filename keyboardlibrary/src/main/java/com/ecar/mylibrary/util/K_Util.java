package com.ecar.mylibrary.util;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

/*************************************
 功能：
 创建者： kim_tony
 创建日期：2017/5/4
 版权所有：深圳市亿车科技有限公司
 *************************************/

public class K_Util {
    @SuppressLint({"NewApi"})
    public static void hideKeyboard(Context context, TextView eText) {
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(eText.getWindowToken(), 0);
    }

    public static void showKeyboard(Context context, TextView eText) {
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.showSoftInput(eText, 2);
    }
}
