package com.ecar.mylibrary.pdaKeyboard;

import android.app.Activity;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.ecar.mylibrary.util.K_Util;
import com.ecar.mylibrary.R;


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
    public boolean isnun = false;// 是否数据键盘
    public boolean isupper = false;// 是否大写
    Activity context;
    private EditText ed;
    private SlideView mSlideView;
    private boolean isFirst;


    public PdaKeyboardNumUtil(KeyboardView keyboardView, Activity ctx, EditText edit) {
        initKeyboard(ctx, edit, keyboardView);
    }

    public PdaKeyboardNumUtil(KeyboardView keyboardView, Activity ctx, EditText edit, SlideView slideView) {
        initKeyboard(ctx, edit, keyboardView);
        mSlideView = slideView;
    }

    //isFirstOpenKeyboard true:首次获取焦点打开键盘 false：不打开
    public PdaKeyboardNumUtil(KeyboardView keyboardView, Activity ctx, EditText edit, SlideView slideView,boolean isFirstOpenKeyboard) {
        isFirst=!isFirstOpenKeyboard;
        initKeyboard(ctx, edit, keyboardView);
        mSlideView = slideView;
    }

    public PdaKeyboardNumUtil(Activity ctx, EditText edit, KeyboardView keyboardView) {
        initKeyboard(ctx, edit, keyboardView);
    }

    private void initKeyboard(Activity ctx, EditText edit, KeyboardView keyboardView) {
        isFirst = true;
        this.context = ctx;
        this.ed = edit;
        kNum = new Keyboard(ctx, R.xml.number_keyboard);
        kLetter1 = new Keyboard(ctx, R.xml.letter_keyboard_1);
        kLetter2 = new Keyboard(ctx, R.xml.letter_keyboard_2);
        this.keyboardView = keyboardView;
        keyboardView.setKeyboard(kLetter1);
        keyboardView.setEnabled(true);
        keyboardView.setPreviewEnabled(true);
        keyboardView.setOnKeyboardActionListener(listener);

        ed.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    if (!isFirst) {
                        showKeyboard();
                    } else {
                        isFirst = false;
                        hideKeyboard();
                    }
                } else {
                    hideKeyboard();
                }
            }
        });
        ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showKeyboard();
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
                editable.insert(start, Character.toString((char) primaryCode));
            }
        }
    };

    public void showKeyboard() {
        K_Util.hideKeyboard(context, ed);
        int visibility = keyboardView.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            keyboardView.setVisibility(View.VISIBLE);
        }
    }

    public boolean isKeyboardShown() {
        return keyboardView.getVisibility() == View.VISIBLE;
    }

    public void hideKeyboard() {
        K_Util.hideKeyboard(context, ed);
        int visibility = keyboardView.getVisibility();
        if (visibility == View.VISIBLE) {
            keyboardView.setVisibility(View.GONE);
        }
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