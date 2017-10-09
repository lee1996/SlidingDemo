package com.example.leet.slidingdemo;

import android.icu.text.DateFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.TransitionRes;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.lang.reflect.Field;

/**
 * Created by leet on 17-10-9.
 */

public class BaseActivity extends AppCompatActivity implements SlidingPaneLayout.PanelSlideListener {
    public final static String TAG=BaseActivity.class.getCanonicalName();
    FrameLayout mContainer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initSwipeFinish();
        super.onCreate(savedInstanceState);
    }
    public void initSwipeFinish(){
        SlidingPaneLayout slidingPaneLayout=new SlidingPaneLayout(this);
        try {
            Field f_overHang=SlidingPaneLayout.class.getDeclaredField("mOverhangSize");
            f_overHang.setAccessible(true);
            f_overHang.set(slidingPaneLayout,0);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        slidingPaneLayout.setPanelSlideListener(this);
        slidingPaneLayout.setSliderFadeColor(getResources().getColor(android.R.color.transparent));
        View leftview=new View(this);
        leftview.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        slidingPaneLayout.addView(leftview,0);
        ViewGroup decorView= (ViewGroup) getWindow().getDecorView();
        ViewGroup decorChild= (ViewGroup) decorView.getChildAt(0);
        decorChild.setBackgroundColor(getResources().getColor(android.R.color.white));
        decorView.removeView(decorChild);
        decorView.addView(slidingPaneLayout);
        slidingPaneLayout.addView(decorChild,1);
    }
    @Override
    public void onPanelSlide(View panel, float slideOffset) {

    }

    @Override
    public void onPanelOpened(View panel) {
        finish();
        this.overridePendingTransition(0,R.anim.out);
    }

    @Override
    public void onPanelClosed(View panel) {


    }
}
