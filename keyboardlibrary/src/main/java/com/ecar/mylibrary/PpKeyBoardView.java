package com.ecar.mylibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;

import java.util.ArrayList;

public class PpKeyBoardView extends KeyboardView {
    private Context mContext;
    private int rightType = 1;// 右下角


    private int keybordType ;// 键盘类型

    public void setKeybordType(int keybordType) {
        this.keybordType = keybordType;
    }

    public  void setmKeyBoard(Keyboard mKeyBoard) {
        this.mKeyBoard = mKeyBoard;
    }

    public  Keyboard mKeyBoard;

    public PpKeyBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public PpKeyBoardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
    }

    /**
     * 重新画一些按键
     */
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mKeyBoard==null){
            return;
        }
        ArrayList<Key>  keys = (ArrayList<Key>) mKeyBoard.getKeys();

        for (Key key : keys) {
            // 数字键盘的处理
            if (keybordType==KeyboardUtil.INPUTTYPE_NUM) {
                initRightType(key);
                drawNumSpecialKey(key, canvas);
            } else if (keybordType==KeyboardUtil.INPUTTYPE_ABC) {
                drawABCSpecialKey(key, canvas);
            } else if (keybordType==KeyboardUtil.INPUTTYPE_SYMBOL) {
                drawSymbolSpecialKey(key, canvas);
            }
        }
    }

    //数字键盘
    public void drawNumSpecialKey(Key key, Canvas canvas) {
        if (key.codes[0] == -5) {
            drawKeyBackground(R.drawable.btn_keyboard_key_num_delete, canvas, key);
        }

        // 顶部按键
        if (key.codes[0] == -3 && key.label == null) {
            drawKeyBackground(R.drawable.btn_keyboard_key_pull, canvas, key);
            drawText(canvas, key);
        }

        // 右下角的按键
        if (key.codes[0] == 0
                || key.codes[0] == 741741
                || key.codes[0] == 88
                || (key.codes[0] == -4 && key.label != null)
                || key.codes[0] == 46) {
            drawKeyBackground(R.drawable.btn_keyboard_key, canvas, key);
            drawText(canvas, key);
        }
    }

    //字母键盘特殊处理背景
    public void drawABCSpecialKey(Key key, Canvas canvas) {
        //TODO 待添加特殊处理
        if (key.codes[0] == -5) {
            drawKeyBackground(R.drawable.btn_keyboard_key_delete, canvas, key);
            drawText(canvas, key);
        }
        if (key.codes[0] == -1) {
            drawKeyBackground(R.drawable.btn_keyboard_key_shift, canvas, key);
            drawText(canvas, key);
        }
        if (key.codes[0] == 123123 || key.codes[0] == 789789) {
            drawKeyBackground(R.drawable.btn_keyboard_key_123, canvas, key);
            drawText(canvas, key);
        }
        if (key.codes[0] == 32) {
            drawKeyBackground(R.drawable.btn_keyboard_key_space, canvas, key);
        }

    }

    //标点键盘特殊处理背景
    public void drawSymbolSpecialKey(Key key, Canvas canvas) {
        //TODO 待添加特殊处理
        if (key.codes[0] == 123123 ||
                key.codes[0] == 456456) {
            drawKeyBackground(R.drawable.btn_keyboard_key_change, canvas, key);
            drawText(canvas, key);
        }

        if (key.codes[0] == -5) {
            drawKeyBackground(R.drawable.btn_keyboard_key_delete, canvas, key);
        }
    }

    public void drawKeyBackground(int drawableId, Canvas canvas, Key key) {
        Drawable npd = mContext.getResources().getDrawable(
                drawableId);
        int[] drawableState = key.getCurrentDrawableState();
        if (key.codes[0] != 0) {
            npd.setState(drawableState);
        }
        npd.setBounds(key.x, key.y, key.x + key.width, key.y
                + key.height);
        npd.draw(canvas);
    }

    public void initRightType(Key key) {
        if (key.codes[0] == 0) {
            rightType = 1;//0
        } else if (key.codes[0] == 88) {
            rightType = 2;//X
        } else if (key.codes[0] == 46) {
            rightType = 3; //点
        } else if (key.codes[0] == -4 && key.label.equals("完成")) {
            rightType = 4; //完成
        } else if (key.codes[0] == -4 && key.label.equals("下一项")) {
            rightType = 5; //next
        }
    }

    public int getRightType() {
        return this.rightType;
    }

    private void drawText(Canvas canvas, Key key) {
        Rect bounds = new Rect();
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        if (key.codes[0] == 46) {
            paint.setTextSize(70);
        } else {
            paint.setTextSize(40);
        }
        paint.setAntiAlias(true);
        // paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setColor(Color.BLACK);
        if (keybordType==KeyboardUtil.INPUTTYPE_NUM) {
            if (key.label != null) {
                paint.getTextBounds(key.label.toString(), 0, key.label.toString()
                        .length(), bounds);
                canvas.drawText(key.label.toString(), key.x + (key.width / 2),
                        (key.y + key.height / 2) + bounds.height() / 2, paint);
            } else if (key.codes[0] == -3) {
                key.icon.setBounds(key.x + 9 * key.width / 20, key.y + 3
                        * key.height / 8, key.x + 11 * key.width / 20, key.y + 5
                        * key.height / 8);
                key.icon.draw(canvas);
            } else if (key.codes[0] == -5) {
                key.icon.setBounds(key.x + (int) (0.4 * key.width), key.y + (int) (0.328
                        * key.height), key.x + (int) (0.6 * key.width), key.y + (int) (0.672
                        * key.height));
                key.icon.draw(canvas);
            }
        } else if (keybordType==KeyboardUtil.INPUTTYPE_ABC) {
            if (key.label != null) {
                paint.setColor(mContext.getResources().getColor(R.color.color_3c3c3c));
                paint.getTextBounds(key.label.toString(), 0, key.label.toString()
                        .length(), bounds);
                canvas.drawText(key.label.toString(), key.x + (key.width / 2),
                        (key.y + key.height / 2) + bounds.height() / 2, paint);
            }
        } else if (keybordType==KeyboardUtil.INPUTTYPE_SYMBOL) {
            paint.setColor(mContext.getResources().getColor(R.color.color_3c3c3c));
            paint.getTextBounds(key.label.toString(), 0, key.label.toString()
                    .length(), bounds);
            canvas.drawText(key.label.toString(), key.x + (key.width / 2),
                    (key.y + key.height / 2) + bounds.height() / 2, paint);
        }
    }
}
