package com.github.florent37.materialviewpager.trendster.fragment.adapter.viewholder;

import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.florent37.carpaccio.controllers.ImageViewController;
import com.github.florent37.materialviewpager.trendster.R;

/**
 * Created by cormac on 24/07/2016.
 */
public class NewsViewHolder extends TrendsterViewHolder {
    public TextView title;
    public TextView author;
    public TextView date;
    public ImageView imageView;
    public RelativeLayout topHeader;
    public RelativeLayout mainView;

    public NewsViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        author = (TextView) itemView.findViewById(R.id.author);
        date = (TextView) itemView.findViewById(R.id.date);
        topHeader = (RelativeLayout) itemView.findViewById(R.id.topHeader);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
        mainView = (RelativeLayout) itemView.findViewById(R.id.mainView);

        Typeface custom_font = Typeface.createFromAsset(itemView.getContext().getAssets(),  "fonts/Time Roman.ttf");
        title.setTypeface(custom_font,Typeface.BOLD);
    }
}