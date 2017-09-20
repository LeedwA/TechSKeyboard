package com.ecar.ecarskeyboard.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.ecar.ecarskeyboard.R;
import com.ecar.skeyboard.randomkeyboard.KeyboardTouchListener;
import com.ecar.skeyboard.randomkeyboard.RandomKeyboardUtil;

/*************************************
 功能：  随机输入键盘
 创建者： kim_tony
 创建日期：2017/5/4
 版权所有：深圳市亿车科技有限公司
 *************************************/

public class RandomActivity extends Activity {
    private RelativeLayout rootView;
    private ScrollView scrollView;
    private EditText normalEd1; //普通键盘
    private EditText specialEd1, specialEd2, specialEd3, specialEd4, specialEd5, specialEd6, specialEd7, specialEd8; //特殊键盘
    private RandomKeyboardUtil keyboardUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_blank);
        initMoveKeyBoard();

    }

    private void initMoveKeyBoard() {
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


        keyboardUtil = new RandomKeyboardUtil(this, rootView, scrollView)
                .setRandom(true)//设置是否为随机键盘
                .setOtherEdittext(normalEd1) //不需要自定义的的键盘
                .setKeyBoardStateChangeListener(new KeyBoardStateListener())//监听键盘切换
                .setInputOverListener(new inputOverListener()).doneOnclick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(RandomActivity.this, "键盘", Toast.LENGTH_SHORT).show();
                    }
                });//监听输入事件


        keyboardUtil.hideKeyboardLayout(); //隐藏键盘

        specialEd1.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, RandomKeyboardUtil.INPUTTYPE_NUM, -1)); // 设置自定义键盘的ontouth事件
        specialEd2.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, RandomKeyboardUtil.INPUTTYPE_NUM_FINISH, -1)); // 设置自定义键盘的ontouth事件
        specialEd3.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, RandomKeyboardUtil.INPUTTYPE_NUM_POINT, -1)); // 设置自定义键盘的ontouth事件
        specialEd4.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, RandomKeyboardUtil.INPUTTYPE_NUM_X, -1)); // 设置自定义键盘的ontouth事件
        specialEd5.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, RandomKeyboardUtil.INPUTTYPE_NUM_NEXT, -1)); // 设置自定义键盘的ontouth事件
        specialEd6.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, RandomKeyboardUtil.INPUTTYPE_NUM_ABC, -1)); // 设置自定义键盘的ontouth事件
        specialEd7.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, RandomKeyboardUtil.INPUTTYPE_ABC, -1)); // 设置自定义键盘的ontouth事件
        specialEd8.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, RandomKeyboardUtil.INPUTTYPE_SYMBOL, -1)); // 设置自定义键盘的ontouth事件
    }


    //监听键盘切换
    class KeyBoardStateListener implements RandomKeyboardUtil.KeyBoardStateChangeListener {

        @Override
        public void KeyBoardStateChange(int state, EditText editText) {
//            System.out.println("state" + state);
//            System.out.println("editText" + editText.getText().toString());
        }

    }

    //监听输入事件
    class inputOverListener implements RandomKeyboardUtil.InputFinishListener {

        @Override
        public void inputHasOver(int onclickType, EditText editText) {
//            System.out.println("onclickType" + onclickType);
//            System.out.println("editText" + editText.getText().toString());
        }
    }
}
