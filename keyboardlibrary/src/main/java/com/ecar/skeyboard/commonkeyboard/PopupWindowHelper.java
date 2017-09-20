package com.ecar.skeyboard.commonkeyboard;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.ecar.skeyboard.R;


/**
 * 创建者： 陆先俊
 * 创建时间： 2015/11/20 11:03
 * 公司： 深圳掌舵科技有限公司
 * 邮箱： CarlLu0712@163.com
 * 描述：泡泡窗体弹出的帮助类
 */
public class PopupWindowHelper {

    private PopupWindow mPopupWindow;

    private Activity mActivity;

    /**
     * 初始化窗体帮助类，操作：创建popupWindow对象，设置其样式
     * param activity activity类型的上下文
     */
    public PopupWindowHelper(Activity activity) {
        mActivity = activity;
        setPopupStyle();
    }

    /**
     * 设置window窗体的透明度
     * param alpha 透明度的值
     */
    private void setWindowAlpha(float alpha) {
        WindowManager.LayoutParams params = mActivity.getWindow().getAttributes();
        params.alpha = alpha;
        mActivity.getWindow().setAttributes(params);
    }

    /**
     * 设置popupWindow的样式
     */
    private void setPopupStyle() {
        if (mPopupWindow == null) {
            //创建popup窗体对象
            mPopupWindow = new PopupWindow();
            mPopupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
            mPopupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
            //设置弹出窗体需要软键盘，
            mPopupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
            //再设置模式，和Activity的一样，覆盖，调整大小。
            mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            //设置焦点，背景，动画，以及外部点击消失
            mPopupWindow.setFocusable(true);
            mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.setAnimationStyle(R.style.bottom_pop_window_anim_style);

            //给窗体设置消失监听
            mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    //当窗体消失后让主界面恢复为完全不透明
                    setWindowAlpha(1.0f);
                }
            });
        }
    }

    /**
     * 设置弹出框的高度
     * param height 高度
     */
    public void setPopupWindowHeight(int height) {
        if (mPopupWindow != null) {
            mPopupWindow.setHeight(height);
        }
    }

    /**
     * 设置默认的高度
     */
    public void setPopupWindowDefaultHeight() {
        if (mPopupWindow != null) {
            mPopupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        }
    }

    /**
     * 显示弹出窗体
     * param showView 需要显示出来的view
     * param rootView 需要依附的根view
     */
    public void showPopupWindow(View showView, View rootView) {
        //设置窗体弹出显示的view
        mPopupWindow.setContentView(showView);
        //判断窗体是否是显示的
        if (mPopupWindow.isShowing()) {
            //显示就隐藏
            mPopupWindow.dismiss();
        } else {
            mPopupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
            setWindowAlpha(0.6f);
        }
    }


    /**
     * 显示弹出窗体
     * param showView 需要显示出来的view
     * param rootView 需要依附的根view
     */
    public void showPopupWindow(View showView, View rootView, float alpha) {
        //设置窗体弹出显示的view
        mPopupWindow.setContentView(showView);
        //判断窗体是否是显示的
        if (mPopupWindow.isShowing()) {
            //显示就隐藏
            mPopupWindow.dismiss();
        } else {
            mPopupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
            setWindowAlpha(alpha);
        }
    }


    public void setFocusableTouch(boolean focusable, boolean touchable) {
        mPopupWindow.setFocusable(focusable);
        mPopupWindow.setOutsideTouchable(touchable);
        mPopupWindow.setAnimationStyle(-1);
    }

    public boolean isShowing() {
        return mPopupWindow.isShowing();
    }

    /**
     * 让popupWindow消失
     */
    public void dismissPopupWindow() {
        mPopupWindow.dismiss();
    }
}
