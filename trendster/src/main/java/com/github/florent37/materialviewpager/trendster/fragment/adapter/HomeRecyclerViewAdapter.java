package com.github.florent37.materialviewpager.trendster.fragment.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.trendster.ArticleActivity;
import com.github.florent37.materialviewpager.trendster.Items.Article;
import com.github.florent37.materialviewpager.trendster.R;
import com.github.florent37.materialviewpager.trendster.fragment.adapter.viewholder.NewsViewHolder;
import com.github.florent37.materialviewpager.trendster.fragment.adapter.viewholder.TrendsterViewHolder;
import com.github.florent37.materialviewpager.trendster.fragment.adapter.viewholder.HomeViewHolder;
import com.squareup.picasso.Picasso;

/**
 * Created by cormac on 24/07/2016.
 */
public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<TrendsterViewHolder> {

    private static final int TYPE_TITLE = 0;
    private static final int TYPE_ARTICLE = 1;
    private static final int TYPE_SETTING = 2;
    private Activity activity;
    private Article[] data;

    public HomeRecyclerViewAdapter(Activity activity, Article[] data){
        this.activity = activity;
        this.data = data;
    }

    @Override
    public TrendsterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TITLE) {
            final View viewItem = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.list_item_blank_small, parent, false);
            return new HomeViewHolder(viewItem);
        }else if(viewType == TYPE_SETTING){
            final View viewItem = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.list_item_card_small, parent, false);
            return new HomeViewHolder(viewItem);
        }
        else {
            final View viewItem = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.list_item_card_big,parent,false);

            return new NewsViewHolder(viewItem);
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 4:
                return TYPE_TITLE;
            case 5:
                return TYPE_SETTING;
            default:
                return TYPE_ARTICLE;
        }
    }


    @Override
    public void onBindViewHolder(TrendsterViewHolder holder, int position) {
        if (position == 0){
            final Article article = data[position];

            NewsViewHolder newsholder = (NewsViewHolder) holder;
            newsholder.title.setText(article.title);
            newsholder.author.setText(article.author);
            newsholder.date.setText(article.date);
            newsholder.topHeader.setBackgroundColor(newsholder.itemView.getContext().getResources().getColor(R.color.lifestyle));
            Picasso.with(((NewsViewHolder) holder).itemView.getContext())
                    .load(article.imageloc)
                    .into(newsholder.imageView);

            newsholder.mainView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(activity, ArticleActivity.class);
                    ArticleActivity.article = article;
                    activity.startActivity(i);
                }
            });
        }else if (position == 1){
            final Article article = data[position];

            NewsViewHolder newsholder = (NewsViewHolder) holder;
            newsholder.title.setText(article.title);
            newsholder.author.setText(article.author);
            newsholder.date.setText(article.date);
            newsholder.topHeader.setBackgroundColor(newsholder.itemView.getContext().getResources().getColor(R.color.sport));
            Picasso.with(((NewsViewHolder) holder).itemView.getContext())
                    .load(article.imageloc)
                    .into(newsholder.imageView);

            newsholder.mainView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(activity, ArticleActivity.class);
                    ArticleActivity.article = article;
                    activity.startActivity(i);
                }
            });
        }else if (position == 2){
            final Article article = data[position];

            NewsViewHolder newsholder = (NewsViewHolder) holder;
            newsholder.title.setText(article.title);
            newsholder.author.setText(article.author);
            newsholder.date.setText(article.date);
            newsholder.topHeader.setBackgroundColor(newsholder.itemView.getContext().getResources().getColor(R.color.technology));
            Picasso.with(((NewsViewHolder) holder).itemView.getContext())
                    .load(article.imageloc)
                    .into(newsholder.imageView);

            newsholder.mainView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(activity, ArticleActivity.class);
                    ArticleActivity.article = article;
                    activity.startActivity(i);
                }
            });
        }else if (position == 3){
            final Article article = data[position];

            NewsViewHolder newsholder = (NewsViewHolder) holder;
            newsholder.title.setText(article.title);
            newsholder.author.setText(article.author);
            newsholder.date.setText(article.date);
            newsholder.topHeader.setBackgroundColor(newsholder.itemView.getContext().getResources().getColor(R.color.business));
            Picasso.with(((NewsViewHolder) holder).itemView.getContext())
                    .load(article.imageloc)
                    .into(newsholder.imageView);

            newsholder.mainView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(activity, ArticleActivity.class);
                    ArticleActivity.article = article;
                    activity.startActivity(i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }


}
