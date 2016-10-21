package com.ecar.ecarskeyboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.ecar.mylibrary.KeyboardTouchListener;
import com.ecar.mylibrary.KeyboardUtil;

import urils.ecaray.com.ecarutils.Utils.ContlorKeyboard;


public class MainActivity extends AppCompatActivity {

    private RelativeLayout rootView;
    private ScrollView scrollView;
    private EditText normalEd1; //普通键盘
    private EditText specialEd1, specialEd2, specialEd3, specialEd4, specialEd5, specialEd6, specialEd7, specialEd8; //特殊键盘

    private KeyboardUtil keyboardUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rela);

        rootView = (RelativeLayout) findViewById(R.id.root_view);
        scrollView = (ScrollView) findViewById(R.id.sv_main);

        normalEd1 = (EditText) findViewById(R.id.normal_ed1);

        specialEd1 = (EditText) findViewById(R.id.special_ed1);
        specialEd2 = (EditText) findViewById(R.id.special_ed2);
        specialEd3 = (EditText) findViewById(R.id.special_ed3);
        specialEd4 = (EditText) findViewById(R.id.special_ed4);
        specialEd5 = (EditText) findViewById(R.id.special_ed5);
        specialEd6 = (EditText) findViewById(R.id.special_ed6);
        specialEd7 = (EditText) findViewById(R.id.special_ed7);
        specialEd8 = (EditText) findViewById(R.id.special_ed8);

        initMoveKeyBoard();

    }

    private void initMoveKeyBoard() {
        keyboardUtil = new KeyboardUtil(this, rootView, scrollView)
                .setRandom(true)//设置是否为随机键盘
                .setOtherEdittext(normalEd1) //不需要自定义的的键盘
                .setKeyBoardStateChangeListener(new KeyBoardStateListener())//监听键盘切换
                .setInputOverListener(new inputOverListener());//监听输入事件

        specialEd1.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_NUM, -1)); // 设置自定义键盘的ontouth事件
        specialEd2.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_NUM_FINISH, -1)); // 设置自定义键盘的ontouth事件
        specialEd3.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_NUM_POINT, -1)); // 设置自定义键盘的ontouth事件
        specialEd4.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_NUM_X, -1)); // 设置自定义键盘的ontouth事件
        specialEd5.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_NUM_NEXT, -1)); // 设置自定义键盘的ontouth事件
        specialEd6.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_NUM_ABC, -1)); // 设置自定义键盘的ontouth事件
        specialEd7.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_ABC, -1)); // 设置自定义键盘的ontouth事件
        specialEd8.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_SYMBOL, -1)); // 设置自定义键盘的ontouth事件

//        new ContlorKeyboard().controlKeyboardLayout(findViewById(R.id.root_view), specialEd3);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (keyboardUtil.isShow) {
                keyboardUtil.hideSystemKeyBoard();
                keyboardUtil.hideAllKeyBoard();
                keyboardUtil.hideKeyboardLayout();
            } else {
                return super.onKeyDown(keyCode, event);
            }
            return false;
        } else
            return super.onKeyDown(keyCode, event);
    }

    //监听键盘切换
    class KeyBoardStateListener implements KeyboardUtil.KeyBoardStateChangeListener {

        @Override
        public void KeyBoardStateChange(int state, EditText editText) {
//            System.out.println("state" + state);
//            System.out.println("editText" + editText.getText().toString());
        }
    }

    //监听输入事件
    class inputOverListener implements KeyboardUtil.InputFinishListener {

        @Override
        public void inputHasOver(int onclickType, EditText editText) {
//            System.out.println("onclickType" + onclickType);
//            System.out.println("editText" + editText.getText().toString());
        }
    }

}