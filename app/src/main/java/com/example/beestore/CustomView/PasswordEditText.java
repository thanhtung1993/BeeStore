package com.example.beestore.CustomView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.example.beestore.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



@SuppressLint("AppCompatCustomView")
public class PasswordEditText  extends EditText {

    Drawable eye,eyeStrike;
    Boolean visible=false;//ẩn hiện trạng thái
    Boolean useTrike=false;
    Boolean useValidate=false;


    //làm mờ con mắt
    int ALPHA= (int) (255* .55f);

    //kiểm tra chũ hoa chữ thường số kí tự
    String MATCHER_PATTERN="((?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{6,20})";
    Pattern pattern;
    Matcher matcher;





    Drawable drawables;
    public PasswordEditText(Context context) {
        super(context);
        khoiTao(null);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        khoiTao(attrs);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        khoiTao(attrs);
    }

        public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        khoiTao(attrs);

       }

    private void khoiTao(AttributeSet attrs)

    {
        //biên dịch chuỗi matcherpatten
        this.pattern=Pattern.compile(MATCHER_PATTERN);
        if (attrs !=null)
        {
            TypedArray array=getContext().getTheme().obtainStyledAttributes(attrs,R.styleable.PasswordEditText,0,0);
            this.useTrike=array.getBoolean(R.styleable.PasswordEditText_useStrike,false);
            this.useValidate=array.getBoolean(R.styleable.PasswordEditText_useValidate,false);

        }
        eye= ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_black_24dp).mutate();
        eyeStrike=ContextCompat.getDrawable(getContext(),R.drawable.ic_visibility_off_black_24dp).mutate();

        if (this.useValidate)
        {
            setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {

                  //  Log.d("kiemtra",hasFocus+"");
                    if(!hasFocus) {
                        String chuoi = getText().toString();
                        TextInputLayout textInputLayout = (TextInputLayout) findViewById(v.getId()).getParent().getParent();
                        matcher = pattern.matcher(chuoi);
                        if(!matcher.matches()){
                            textInputLayout.setErrorEnabled(true);
                            textInputLayout.setError("Mật khẩu phải bao gồm 6 ký tự và một chữ hoa");
                        }else {
                            textInputLayout.setErrorEnabled(false);


                        }
                    }
                }
            });
        }
        caiDat();
    }
    //ấn vào hiện ra
    private void caiDat()
    {
        setInputType(InputType.TYPE_CLASS_TEXT | (visible ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD));
        //trả ra mảng drawable trái trên phải dưới
        Drawable[] drawable= getCompoundDrawables();
        //nếu người dùng khai báo usetrike và truyền vào trạng thái không được hiển thị
        drawables=useTrike && !visible ? eyeStrike :eye;
        drawables.setAlpha(ALPHA);
        setCompoundDrawablesWithIntrinsicBounds(drawable[0],drawable[1],drawables,drawable[3]) ;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Kiếm tra người dùng có nhấn vào màn hình không và tọa độ mắt bằng
if (event.getAction()==MotionEvent.ACTION_UP && event.getX()>=( getRight()-drawables.getBounds().width()) )
{
visible =!visible;
caiDat();
//kiểm tra màn hình
    invalidate();
}

        return super.onTouchEvent(event);
    }
}
