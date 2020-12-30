package com.sunday.mytest.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

import com.sunday.mytest.R;

public class InputEditView extends RelativeLayout {
    private static String TAG = InputEditView.class.getSimpleName();
    private boolean DEBUG = true;
    private int mInputIcon;
    private String mInputHint;
    private boolean mIsPassWord;
    private View mView;
    private ImageView mTitleImg;
    private EditText mEditInput;
    private int mEditInputHeight;
    private int mEditInputWidth;
    private ToggleButton mToggleBtnPassWordSwitch;
    private int mImageButtonPassWordSwitch;
    private int mInputViewBg;
    private Drawable mInputViewBgDrawable;
    private int mInputViewErrBg;
    private Drawable mInputViewErrBgDrawable;
    private static final int[] DEFAULT_ATTRS = new int[]{
            android.R.attr.height, android.R.attr.width
    };

    public InputEditView(Context context) {
        super(context);
        init(context,null);
    }

    public InputEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public InputEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    public InputEditView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }

    public void init(Context context, AttributeSet attrs){

        if(attrs == null){
            return;
        }

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.InputEditView);
        mInputIcon = typedArray.getResourceId(R.styleable.InputEditView_mInputIcon,R.drawable.icon_general_phone_n);
        mInputHint = typedArray.getString(R.styleable.InputEditView_mInputHint);
        mIsPassWord = typedArray.getBoolean(R.styleable.InputEditView_mIsPassWord,false);
        mInputViewBg = typedArray.getResourceId(R.styleable.InputEditView_mInputViewBg,R.drawable.inputview_background);
        mInputViewErrBg = typedArray.getResourceId(R.styleable.InputEditView_mInputViewErrBg,R.drawable.inputview_background);
        mEditInputHeight = (int)typedArray.getDimensionPixelSize(R.styleable.InputEditView_mEditInputHeight,40);
        mEditInputWidth = (int)typedArray.getDimensionPixelSize(R.styleable.InputEditView_mEditInputWidth,100);
        mInputViewBgDrawable = typedArray.getDrawable(R.styleable.InputEditView_mInputViewBg);
        mInputViewErrBgDrawable = typedArray.getDrawable(R.styleable.InputEditView_mInputViewErrBg);
        if(mIsPassWord){
            mImageButtonPassWordSwitch = typedArray.getResourceId(R.styleable.InputEditView_mImageButtonPassWordSwitch,R.drawable.icon_general_eye_s);
        }
        typedArray.recycle();

        mView = LayoutInflater.from(context).inflate(R.layout.customer_input_editview_layout,this,false);
        mView.setBackground(mInputViewBgDrawable);
        if(DEBUG){
           Log.d(TAG,"width = "+mEditInputWidth);
           Log.d(TAG,"height = "+mEditInputHeight);
        }
        mToggleBtnPassWordSwitch = (ToggleButton)mView.findViewById(R.id.password_imagebtn_switch);
        mTitleImg = (ImageView)mView.findViewById(R.id.inputview_icon);
        mTitleImg.setImageResource(mInputIcon);
        mEditInput = (EditText)mView.findViewById(R.id.editview_id);
        mEditInput.setHint(mInputHint);
        mEditInput.setHeight(mEditInputHeight);
        mEditInput.setWidth(mEditInputWidth);
        mEditInput.setInputType(mIsPassWord? InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD:InputType.TYPE_CLASS_PHONE);
        if(mIsPassWord){
            mToggleBtnPassWordSwitch.setVisibility(VISIBLE);
            mToggleBtnPassWordSwitch.setBackgroundResource(mImageButtonPassWordSwitch);
            mToggleBtnPassWordSwitch.setOnCheckedChangeListener(new PassWordDisplayOrHideOnClickListener());
        }else{
            mToggleBtnPassWordSwitch.setVisibility(GONE);
        }


        addView(mView);
    }

    public String getInputStr(){
        return mEditInput.getText().toString().trim();
    }
    class PassWordDisplayOrHideOnClickListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            if(isChecked){
                mEditInput.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else{
                mEditInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            mEditInput.setSelection(mEditInput.getText().length());
        }
    }
}
