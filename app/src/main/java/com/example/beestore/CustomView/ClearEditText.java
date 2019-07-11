package com.example.beestore.CustomView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.example.beestore.R;



@SuppressLint("AppCompatCustomView")
public class ClearEditText extends EditText  {
    Drawable crossx,nonecrossx;
    Drawable drawable;
    Boolean visible=false;


    public ClearEditText(Context context) {
        super(context);
        khoiTao();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        khoiTao();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        khoiTao();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        khoiTao();
    }
    private void khoiTao()
    {
        crossx= ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_black_24dp).mutate();
        nonecrossx=ContextCompat.getDrawable(getContext(),android.R.drawable.screen_background_light_transparent).mutate();
        cauHinh();
    }
    private void cauHinh()
    {
        setInputType(InputType.TYPE_CLASS_TEXT);
        Drawable[] drawables=getCompoundDrawables();
        drawable=visible ? crossx :nonecrossx;
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]);
    }

    @Override
    //sự kiện click
    public boolean onTouchEvent(MotionEvent event) {
        if (MotionEvent.ACTION_DOWN==event.getAction() && event.getX() >= (getRight() - drawable.getBounds().width()));
       setText("");
        return super.onTouchEvent(event);

    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (lengthAfter ==0 && start ==0)
        {
           visible =false;
           cauHinh();
        }
        else
        {
            visible=true;
            cauHinh();
        }
    }
}
