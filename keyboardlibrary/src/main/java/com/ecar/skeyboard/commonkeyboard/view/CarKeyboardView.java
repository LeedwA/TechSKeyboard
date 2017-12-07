package com.ecar.skeyboard.commonkeyboard.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecar.skeyboard.R;
import com.ecar.skeyboard.util.K_Util;


public class CarKeyboardView extends LinearLayout implements View.OnClickListener {

    private View viewKeyboardName;

    private View viewKeyboardNum;

    private TextView tv_1;

    private GridView gvNums;

    private CityCharAdapter charsAdapter;

    private int[] numRes = {R.id.tv_1, R.id.tv_2, R.id.tv_3, R.id.tv_4, R.id.tv_5, R.id.tv_6, R.id.tv_7, R.id.tv_8, R.id.tv_9,
            R.id.tv_10, R.id.tv_11, R.id.tv_12, R.id.tv_13, R.id.tv_14, R.id.tv_15, R.id.tv_16, R.id.tv_17, R.id.tv_18, R.id.tv_19,
            R.id.tv_20, R.id.tv_21, R.id.tv_22, R.id.tv_23, R.id.tv_24, R.id.tv_25, R.id.tv_26, R.id.tv_27, R.id.tv_28, R.id.tv_29,
            R.id.tv_30, R.id.tv_31, R.id.tx_car_city_delete};

    /**
     * 需要动态设置宽度的按钮
     */
    private int[] numResChange = {R.id.tv_19,
            R.id.tv_20, R.id.tv_21, R.id.tv_22, R.id.tv_23, R.id.tv_24, R.id.tv_25, R.id.tv_26, R.id.tv_27, R.id.tv_28, R.id.tv_29,
            R.id.tv_30, R.id.tv_31};
    private boolean isVibrate = true; //是否震动

    public CarKeyboardView(Context context) {
        super(context);
        initView();
    }

    public CarKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.pop_bind_car_city_belong_view, CarKeyboardView.this);

        viewKeyboardName = findViewById(R.id.rela_keyboard_name);
        viewKeyboardNum = findViewById(R.id.rela_keyboard_num_parent);
        findViewById(R.id.tx_num_sure).setOnClickListener(this);//确定按钮
        findViewById(R.id.tx_privince_sure).setOnClickListener(this);//确定按钮

        /**
         * 车牌汉字
         */
        for (int i = 0; i < numRes.length; i++) {
            findViewById(numRes[i]).setOnClickListener(this);
        }

        if (tv_1 == null) {
            tv_1 = (TextView) findViewById(R.id.tv_1);
        }
        tv_1.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if (tv_1.getWidth() == 0) {
                            return;
                        }
                        LinearLayout.LayoutParams para = new LinearLayout.LayoutParams(tv_1.getWidth(), LinearLayout.LayoutParams.WRAP_CONTENT);
                        para.rightMargin = K_Util.dip2px(getContext(), 5);
                        for (int i = 0; i < numResChange.length; i++) {
                            findViewById(numResChange[i]).setLayoutParams(para);
                        }
                        RelativeLayout.LayoutParams para2 = new RelativeLayout.LayoutParams(tv_1.getWidth(), RelativeLayout.LayoutParams.WRAP_CONTENT);
                        para2.rightMargin = K_Util.dip2px(getContext(), 5);
                        para2.addRule(RelativeLayout.ALIGN_TOP, R.id.ll_four);
                        para2.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.ll_four);
                        para2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                        findViewById(R.id.tx_car_city_delete).setLayoutParams(para2);
                        tv_1.getViewTreeObserver()
                                .removeGlobalOnLayoutListener(this);
                    }
                });

        /**
         * 数字
         */
        gvNums = (GridView) findViewById(R.id.gv_nums);
        CityDigitalAdapter numsAdapter = new CityDigitalAdapter(this.getContext());
        numsAdapter.setOnTextItemOnClickListener(
                new CityDigitalAdapter.OnTextItemOnClickListener() {
                    @Override
                    public void onTextItemOnClick(View view) {
                        setIText(view);
                    }
                });
        gvNums.setAdapter(numsAdapter);

        /**
         * 字母
         */
        GridView gvChars = (GridView) findViewById(R.id.gv_cars);//GriveView
        charsAdapter = new CityCharAdapter(this.getContext());
        charsAdapter.setOnTextItemOnClickListener(new CityCharAdapter.OnTextItemOnClickListener() {
            @Override
            public void onTextItemOnClick(View view) {
                setIText(view);
            }
        });
        gvChars.setAdapter(charsAdapter);

        setKeyboard(1);
    }

    @SuppressLint("NewApi")
    private void setIText(View view) {
        if (onTextListener != null) {
            String trim = ((TextView) view).getText().toString().trim();
            if (isVibrate)  //是否震动
            {
                this.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
            }
            if (!TextUtils.isEmpty(trim)) {
                onTextListener.onText(trim);
            } else {
                onTextListener.onDelete();
            }

        }
    }

    public void setKeyboard(int type) {
        if (type == 0) {//汉字
            viewKeyboardName.setVisibility(VISIBLE);
            viewKeyboardNum.setVisibility(GONE);
        } else if (type == 1) {//数字+字母
            viewKeyboardNum.setVisibility(VISIBLE);
            gvNums.setVisibility(View.VISIBLE);
            viewKeyboardName.setVisibility(GONE);
            charsAdapter.setPageType(CityCharAdapter.PAGE_TYPE_PROVINCE);
        } else if (type >= 2 && type < 6) {//数字+字母
            viewKeyboardNum.setVisibility(VISIBLE);
            gvNums.setVisibility(View.VISIBLE);
            viewKeyboardName.setVisibility(GONE);
            charsAdapter.setPageType(CityCharAdapter.PAGE_TYPE_NUM);
        } else if (type == 6) {//数字+字母
            viewKeyboardNum.setVisibility(VISIBLE);
            gvNums.setVisibility(View.VISIBLE);
            viewKeyboardName.setVisibility(GONE);
            charsAdapter.setPageType(CityCharAdapter.PAGE_TYPE_NUM_LAST);
        }
    }

    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tx_num_sure || i == R.id.tx_privince_sure) {
            if (onTextListener != null) {
                onTextListener.onSure();
            }

        } else {
            setIText(view);

        }
    }

    public void setVibrate(boolean isVibrate) {
        this.isVibrate = isVibrate;
    }


    /**
     * 接口 、属性、注入
     */
    public interface OnTextListener {

        void onText(String text);

        void onSure();

        void onDelete();
    }


    private OnTextListener onTextListener;

    public void setOnTextItemOnClickListener(OnTextListener onTextListener) {
        this.onTextListener = onTextListener;
    }


//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN)
////            initEditFocus();
//            return false;
//        return super.onTouchEvent(event);
//    }
//
//    @Override
//    public boolean onKey(View v, int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
//        }
//        return false;
//    }
}
