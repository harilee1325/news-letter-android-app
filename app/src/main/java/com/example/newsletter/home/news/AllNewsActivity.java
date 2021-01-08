package com.example.newsletter.home.news;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.newsletter.home.FullscreenActivity;
import com.example.newsletter.home.HomeViewModel;
import com.example.newsletter.home.news.ui.main.PlansPagerAdapter;
import com.example.newsletter.room.HomeDb;
import com.example.newsletter.utild.Config;
import com.example.newsletter.utild.Utility;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.newsletter.R2;


public class AllNewsActivity extends AppCompatActivity {

    private static final String TAG = "news letter";
    @BindView(R2.id.title)
    TextView title;
    @BindView(R2.id.tabs)
    TabLayout tabs;
    @BindView(R2.id.view_pager)
    ViewPager viewPager;
    @BindView(R2.id.fab)
    FloatingActionButton fab;
    private List<HomeDb> tagsName = new ArrayList<>();
    private HomeViewModel homeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R2.layout.activity_all_news);
        ButterKnife.bind(this);
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);

        setUpObserver();

        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }


    @SuppressLint("SetTextI18n")
    private void setUpObserver() {

        homeViewModel.getSelectedTags().observe(this, tagsResponse -> {
            if (tagsResponse != null) {
                Log.e(TAG, "setUpObserver: "+tagsResponse.size());
                tagsName.clear();
                tagsName.addAll(tagsResponse);
            }
        });
        for (HomeDb homeDb : tagsName) {
            Log.e(TAG, "setUpObserver: "+homeDb.getTagName() );
            tabs.addTab(tabs.newTab().setText(homeDb.getTagName()));
        }

        PlansPagerAdapter adapter = new PlansPagerAdapter
                (getSupportFragmentManager(), tabs.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        //Bonus Code : If your tab layout has more than 2 tabs then tab will scroll other wise they will take whole width of the screen
        if (tabs.getTabCount() == 2) {
            tabs.setTabMode(TabLayout.MODE_FIXED);
        } else {
            tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }
}