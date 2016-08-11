package com.github.florent37.materialviewpager.trendster.fragment.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.florent37.materialviewpager.trendster.ArticleActivity;
import com.github.florent37.materialviewpager.trendster.Items.Article;
import com.github.florent37.materialviewpager.trendster.R;
import com.github.florent37.materialviewpager.trendster.fragment.adapter.viewholder.NewsViewHolder;
import com.github.florent37.materialviewpager.trendster.fragment.adapter.viewholder.TrendsterViewHolder;
import com.squareup.picasso.Picasso;

/**
 * Created by cormac on 23/07/2016.
 */
public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<TrendsterViewHolder> {
    private final Activity activity;
    private Article[] data;

    public NewsRecyclerViewAdapter(FragmentActivity activity, Article[] data) {
        this.activity = activity;
        this.data = data;
    }

    @Override
    public TrendsterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View viewItem = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item_card_big,parent,false);
        return new NewsViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(TrendsterViewHolder holder, int position) {
        final Article article = data[position];

        NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
        newsViewHolder.topHeader.setVisibility(View.INVISIBLE);

        newsViewHolder.title.setText(article.title);
        newsViewHolder.author.setText(article.author);
        newsViewHolder.date.setText(article.date);
        Picasso.with(((NewsViewHolder) holder).itemView.getContext())
                .load(article.imageloc)
                .into(newsViewHolder.imageView);
        newsViewHolder.mainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, ArticleActivity.class);
                ArticleActivity.article = article;
                activity.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

}
