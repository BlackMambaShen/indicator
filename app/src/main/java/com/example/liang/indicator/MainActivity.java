package com.example.liang.indicator;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> dataList;
    private ViewPager vp_content;
    private LinearLayout ll_indicator;
    private int currentPosition=0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        vp_content.setAdapter(new PagerAdapter());
        for (int i = 0; i <dataList.size()-1 ; i++) {
            Button button=new Button(this);
            button.setText("我是猪"+i);
            button.setBackground(null);
            ll_indicator.addView(button);
        }

        vp_content.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                    if (currentPosition!=position){
                        ll_indicator.getChildAt(currentPosition).setBackgroundColor(Color.WHITE);
                    }
                    ll_indicator.getChildAt(position).setBackgroundColor(Color.RED);
                    currentPosition=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ll_indicator.getChildAt(0).setBackgroundColor(Color.RED);
    }

    private void initView() {
         vp_content = (ViewPager)findViewById(R.id.vp_content);
        ll_indicator = (LinearLayout)findViewById(R.id.ll_indicator);
    }

    private void initData() {
        dataList=new ArrayList<String>();
        for (int i = 0; i <10; i++) {
            dataList.add("我是猪"+i);
        }
    }

    class PagerAdapter extends android.support.v4.view.PagerAdapter{

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }

        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            TextView view=new TextView(getApplicationContext());
            view.setText(dataList.get(position));
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
