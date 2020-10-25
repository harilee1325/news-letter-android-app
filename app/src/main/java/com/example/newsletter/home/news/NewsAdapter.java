package com.example.newsletter.home.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsletter.R;
import com.example.newsletter.model.NewsModel;
import com.example.newsletter.utild.Utility;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends ListAdapter<NewsModel.News, NewsAdapter.ViewHolder> {


    private final Context context;

    public NewsAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    private static final DiffUtil.ItemCallback<NewsModel.News> DIFF_CALLBACK
            = new DiffUtil.ItemCallback<NewsModel.News>() {
        @Override
        public boolean areItemsTheSame(@NonNull NewsModel.News oldItem, @NonNull NewsModel.News newItem) {
            return Objects.equals(oldItem.getHeadline(), newItem.getHeadline());
        }

        @Override
        public boolean areContentsTheSame(@NonNull NewsModel.News oldItem, @NonNull NewsModel.News newItem) {
            return oldItem.getHeadline().equals(newItem.getHeadline());
        }

    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        NewsModel.News news = getItem(position);
        holder.author.setText(news.getAuthor());
        holder.desc.setText(news.getDesc());
        holder.title.setText(news.getHeadline());
        holder.time.setText(news.getTime());
        Utility.setImage(news.getImage(), context ,holder.newsImage);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.news_image)
        ImageView newsImage;
        @BindView(R.id.author)
        TextView author;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.desc)
        TextView desc;
        @BindView(R.id.time)
        TextView time;
        public ViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
