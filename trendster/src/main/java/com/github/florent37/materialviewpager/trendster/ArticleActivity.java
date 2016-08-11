package com.github.florent37.materialviewpager.trendster;

import android.app.ActivityManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.florent37.materialviewpager.trendster.Items.Article;
import com.github.florent37.materialviewpager.trendster.fragment.adapter.viewholder.NewsViewHolder;
import com.squareup.picasso.Picasso;

public class ArticleActivity extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingtoolbar;
    private AppBarLayout appbarlayout;
    private Toolbar toolbar;
    public static Article article;

    private ImageView imageView;
    private TextView title;
    private TextView content;
    private TextView author;
    private TextView date;

    private String catagory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        imageView = (ImageView) findViewById(R.id.BigThumbnailImage);
        title = (TextView) findViewById(R.id.title);
        content = (TextView) findViewById(R.id.content);
        author = (TextView) findViewById(R.id.author);
        date = (TextView) findViewById(R.id.date);



        Picasso.with(this)
                .load(article.imageloc)
                .into(imageView);
        title.setText(article.title);


        content.setText(article.formattedString);

        author.setText(article.author);
        date.setText(article.date);
        appbarlayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        collapsingtoolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (article.catagory.equals("2")){
            catagory = "Lifestyle";
            collapsingtoolbar.setContentScrimColor(getResources().getColor(R.color.lifestyle));

            ActivityManager.TaskDescription taskDescription = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                taskDescription = new ActivityManager.TaskDescription(null, null, getResources().getColor(R.color.lifestyle));
                ((Activity)this).setTaskDescription(taskDescription);
            }
        }else if (article.catagory.equals("9")){
            catagory = "Sport";
            collapsingtoolbar.setContentScrimColor(getResources().getColor(R.color.sport));

            ActivityManager.TaskDescription taskDescription = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                taskDescription = new ActivityManager.TaskDescription(null, null, getResources().getColor(R.color.sport));
                ((Activity)this).setTaskDescription(taskDescription);
            }
        }else if (article.catagory.equals("10")){
            catagory = "Technology";
            collapsingtoolbar.setContentScrimColor(getResources().getColor(R.color.technology));

            ActivityManager.TaskDescription taskDescription = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                taskDescription = new ActivityManager.TaskDescription(null, null, getResources().getColor(R.color.technology));
                ((Activity)this).setTaskDescription(taskDescription);
            }
        }else if (article.catagory.equals("3")){
            catagory = "Business";
            collapsingtoolbar.setContentScrimColor(getResources().getColor(R.color.business));

            ActivityManager.TaskDescription taskDescription = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                taskDescription = new ActivityManager.TaskDescription(null, null, getResources().getColor(R.color.business));
                ((Activity)this).setTaskDescription(taskDescription);
            }
        }

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Time Roman.ttf");
        title.setTypeface(custom_font,Typeface.BOLD);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        appbarlayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d("STATE", state.name());
                if (state == State.COLLAPSED){

                    collapsingtoolbar.setTitle(catagory);
                }else {
                    collapsingtoolbar.setTitle("");
                }
            }
        });
    }
}
