package com.example.newsletter.home.news;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.newsletter.R;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllNewsResult extends AppCompatActivity {

    @BindView(R.id.trees1)
    ImageView trees1;
    @BindView(R.id.car)
    ImageView car;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.pager)
    ViewPager pager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.all_result_activity);
        ButterKnife.bind(this);
        tabs.addTab(tabs.newTab().setText("tag1"));
        tabs.addTab(tabs.newTab().setText("tag1"));
        tabs.addTab(tabs.newTab().setText("tag1"));
        tabs.addTab(tabs.newTab().setText("tag1"));
        tabs.addTab(tabs.newTab().setText("tag1"));
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);
        ViewpagerHeader viewPagerHeader = findViewById(R.id.motionLayout);


        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addPage("tag1");
        adapter.addPage("tag2");
        adapter.addPage("tag3");
        adapter.addPage("tag4");
        adapter.addPage("tag5");

        pager.setAdapter(adapter);
        tabs.setupWithViewPager(pager);
        if (pager!=null){
            pager.addOnPageChangeListener(viewPagerHeader);
        }



    }
}
