package com.ecar.skeyboard.pdaKeyboard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.ecar.skeyboard.R;
import com.ecar.skeyboard.util.K_Util;


/**
 * 类描述: 自定义键盘工具类
 * 创建人: Eric_Huang
 * 创建时间: 2016/6/29 14:59
 * 修改人:Eric_Huang
 * 修改时间: 2016/6/29 14:59
 */
public class PdaKeyboardNumUtil {

    public static final int KEYCODE_UP = -7;
    public static final int KEYCODE_DOWN = -8;

    private KeyboardView keyboardView;
    private Keyboard kNum;// 数字键盘
    private Keyboard kLetter1;// 字母键盘1
    private Keyboard kLetter2;// 字母键盘2

    public Activity activity;

    public boolean isnun;// 是否数据键盘
    public boolean isUpper = true;// 是否大写
    public boolean isEnable = true;// 是否启用

    private EditText ed;
    private SlideView mSlideView;

    public PdaKeyboardNumUtil(KeyboardView keyboardView, Activity ctx, EditText edit) {
        initKeyboard(ctx, edit, keyboardView);
    }

    public PdaKeyboardNumUtil(KeyboardView keyboardView, Activity ctx, EditText edit, SlideView slideView) {
        initKeyboard(ctx, edit, keyboardView);
        mSlideView = slideView;
    }

    public PdaKeyboardNumUtil(Activity ctx, EditText edit, KeyboardView keyboardView) {
        initKeyboard(ctx, edit, keyboardView);
    }

    private void initKeyboard(final Activity ctx, EditText edit, KeyboardView keyboardView) {
        this.activity = ctx;
        this.ed = edit;
        kNum = new Keyboard(ctx, R.xml.number_keyboard);
        kLetter1 = new Keyboard(ctx, R.xml.letter_keyboard_1);
        kLetter2 = new Keyboard(ctx, R.xml.letter_keyboard_2);
        this.keyboardView = keyboardView;
        keyboardView.setKeyboard(kLetter1);
        keyboardView.setEnabled(true);
        keyboardView.setPreviewEnabled(true);
        keyboardView.setOnKeyboardActionListener(listener);
        ed.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (isEnable) {
                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            break;
                        case MotionEvent.ACTION_UP:
                            showKeyboard();
                            K_Util.setKeyBoardCursorNew(ctx, ed);
                            break;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

    }


    private KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {

        @Override
        public void swipeUp() {
        }

        @Override
        public void swipeRight() {
        }

        @Override
        public void swipeLeft() {
        }

        @Override
        public void swipeDown() {
        }

        @Override
        public void onText(CharSequence text) {
        }

        @Override
        public void onRelease(int primaryCode) {
        }

        @Override
        public void onPress(int primaryCode) {
        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            Editable editable = ed.getText();
            int start = ed.getSelectionStart();
            if (primaryCode == Keyboard.KEYCODE_DONE) {// 完成
                if (mSlideView != null)
                    mSlideView.hideView();
                hideKeyboard();
            } else if (primaryCode == Keyboard.KEYCODE_DELETE) {// 回退
                if (editable != null && editable.length() > 0) {
                    if (start > 0) {
                        editable.delete(start - 1, start);
                    }
                }
            } else if (primaryCode == Keyboard.KEYCODE_SHIFT) {// 大小写切换
//                changeKey();
                keyboardView.setKeyboard(kLetter2);

            } else if (primaryCode == Keyboard.KEYCODE_MODE_CHANGE) {// 数字键盘切换
                if (isnun) {
                    isnun = false;
                    keyboardView.setKeyboard(kLetter1);
                } else {
                    isnun = true;
                    keyboardView.setKeyboard(kNum);
                }
            } else if (primaryCode == KEYCODE_UP) {// 字母键盘上一页
                keyboardView.setKeyboard(kLetter1);
            } else if (primaryCode == KEYCODE_DOWN) {// 字母键盘下一页
                keyboardView.setKeyboard(kLetter2);
            } else if (primaryCode == 57419) { // go left
                if (start > 0) {
                    ed.setSelection(start - 1);
                }
            } else if (primaryCode == 57421) { // go right
                if (start < ed.length()) {
                    ed.setSelection(start + 1);
                }
            } else {
                String text = Character.toString((char) primaryCode);
                editable.insert(start, isUpper ? text.toUpperCase() : text);
            }
        }
    };

    //是否大写  true大写
    public PdaKeyboardNumUtil setUpper(boolean upper) {
        this.isUpper = upper;
        return this;
    }

    //判断是否启用  enable true 启用
    public void setEnable(boolean enable) {
        isEnable = enable;
        hideKeyboard();
    }

    //清空输入框内容
    @SuppressLint("NewApi")
    public void clearFocus() {
        this.ed.setText("");
        this.ed.clearFocus();
        this.ed.setSelected(false);
        this.ed.setShowSoftInputOnFocus(false);
        this.ed.setClickable(false);
        this.ed.setFocusable(false);


    }

    //隐藏/显示输入框  isShow true显示
    public void showEdit(boolean isShow) {
        this.ed.setVisibility(isShow ? View.VISIBLE : View.INVISIBLE);
    }


    //重置键盘（设为字母首页）
    @SuppressLint("NewApi")
    public void resetKeyboard() {
        keyboardView.setKeyboard(kLetter1);
        isnun = false;
        isEnable = true;
        this.ed.setFocusable(true);
        this.ed.setClickable(true);
        this.ed.setFocusable(true);
        this.ed.setFocusableInTouchMode(true);
        this.ed.requestFocus();
    }

    public void showKeyboard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            keyboardView.setVisibility(View.VISIBLE);
        }
        K_Util.hideKeyboard(activity, ed);
    }

    public boolean isKeyboardShown() {
        return keyboardView.getVisibility() == View.VISIBLE;
    }

    public void hideKeyboard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.VISIBLE) {
            keyboardView.setVisibility(View.GONE);
        }
        K_Util.hideKeyboard(activity, ed);

    }

    private boolean isword(String str) {
        String wordstr = "abcdefghijklmnopqrstuvwxyz";
        if (wordstr.indexOf(str.toLowerCase()) > -1) {
            return true;
        }
        return false;
    }


    public interface SlideView {
        void hideView();
    }

}