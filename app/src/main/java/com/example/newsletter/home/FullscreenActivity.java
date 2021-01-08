package com.example.newsletter.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Fade;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import com.example.newsletter.R2;
import com.example.newsletter.home.news.AllNewsActivity;
import com.example.newsletter.room.HomeDb;
import com.example.newsletter.utild.Config;
import com.example.newsletter.utild.Utility;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FullscreenActivity extends AppCompatActivity {


    private static final String TAG = "Home";
    @BindView(R2.id.fullscreen_content)
    TextView fullscreenContent;
    @BindView(R2.id.dummy_button)
    Button dummyButton;
    @BindView(R2.id.fullscreen_content_controls)
    LinearLayout fullscreenContentControls;
    @BindView(R2.id.news_list)
    RecyclerView newsList;
    @BindView(R2.id.container)
    FrameLayout container;
    private List<HomeDb> tagsName = new ArrayList<>();
    private HomeViewModel homeViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R2.layout.activity_fullscreen);
        ButterKnife.bind(this);
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        setUpObserver();

    }

    @SuppressLint("SetTextI18n")
    private void setUpObserver() {

        final TagsAdapter tagsAdapter = new TagsAdapter(tagsName, this);
        newsList.setAdapter(tagsAdapter);


        homeViewModel.getTags().observe(this, tagsResponse -> {
            if (tagsResponse != null) {
                tagsName.clear();
                tagsName.addAll(tagsResponse);
            }
            tagsAdapter.notifyDataSetChanged();
        });

        Utility.setPreference(this, Config.IS_FIRST_TIME, "no");
        Log.e(TAG, "setUpObserver: " + Utility.getPreference(this, Config.IS_FIRST_TIME));
    }


    private void toggleButton(boolean show) {
        Transition transition = new Fade();
        transition.setDuration(1000);
        transition.addTarget(R2.id.dummy_button);

        TransitionManager.beginDelayedTransition(container, transition);
        dummyButton.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @OnClick(R2.id.dummy_button)
    public void onClick() {

        homeViewModel.updateTags(tagsName);
        startActivity(new Intent(this, AllNewsActivity.class));
    }

    public static class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.ViewHolder> {

        private List<HomeDb> tagsName;
        private FullscreenActivity activity;
        private boolean isAnySelected = false;

        public TagsAdapter(List<HomeDb> tagsName, FullscreenActivity fullscreenActivity) {
            this.tagsName = tagsName;
            this.activity = fullscreenActivity;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R2.layout.news_tags_card, parent
                    , false);
            return new ViewHolder(view);
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            holder.dummyButton.setText(tagsName.get(position).getTagName());
            if (tagsName.get(position).isChecked()) {
                holder.dummyButton.setBackground(activity.getResources().getDrawable(R2.drawable.curved_button_white));
                holder.dummyButton.setTextColor(activity.getResources().getColor(R2.color.colorAccent));
            } else {
                holder.dummyButton.setBackground(activity.getResources().getDrawable(R2.drawable.curved_button_new));
                holder.dummyButton.setTextColor(activity.getResources().getColor(R2.color.colorAccent));
            }
            if (tagsName.get(position).isChecked()){
                isAnySelected = true;
            }
            holder.dummyButton.setOnClickListener(v -> {
                if (tagsName.get(position).isChecked()) {
                    tagsName.get(position).setChecked(false);
                } else {
                    isAnySelected = true;
                    tagsName.get(position).setChecked(true);
                }
                activity.toggleButton(isAnySelected);
                isAnySelected = false;
                notifyDataSetChanged();
            });
        }

        @Override
        public int getItemCount() {
            return tagsName.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R2.id.dummy_button)
            Button dummyButton;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}