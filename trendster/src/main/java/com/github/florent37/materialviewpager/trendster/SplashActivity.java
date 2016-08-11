package com.github.florent37.materialviewpager.trendster;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import android.widget.ProgressBar;
import android.widget.TextView;
import com.github.florent37.materialviewpager.trendster.Items.Article;
import com.github.florent37.materialviewpager.trendster.util.Requests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

public class SplashActivity extends Activity {

    public static Article[] home_featured;
    public static Article[] lifestyle_featured;
    public static Article[] sport_featured;
    public static Article[] tech_featured;
    public static Article[] business_featured;

    public static Article[] lifestyle;
    public static Article[] sport;
    public static Article[] tech;
    public static Article[] business;
    private ProgressBar progressind;
    private TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final Activity act = this;
        Requests.activity = this;
        status = (TextView) findViewById(R.id.status);
        progressind = (ProgressBar)findViewById(R.id.progressBar);

        Thread one = new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        status.setText("Loading Featured Articles");
                    }
                });
                home_featured = Requests.getFeaturedArticles("8");
                lifestyle_featured = Requests.getFeaturedArticles("2");
                sport_featured = Requests.getFeaturedArticles("9");
                tech_featured = Requests.getFeaturedArticles("10");
                business_featured = Requests.getFeaturedArticles("3");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        status.setText("Loading Lifestyle Articles");
                    }
                });
                lifestyle = Requests.getCatagoryArticles("2");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        status.setText("Loading Sport Articles");
                    }
                });
                sport = Requests.getCatagoryArticles("9");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        status.setText("Loading Technology Articles");
                    }
                });
                tech = Requests.getCatagoryArticles("10");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        status.setText("Loading Business Articles");
                    }
                });
                business = Requests.getCatagoryArticles("3");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ActivityOptionsCompat options =
                                makeOptionsCompat(
                                        act
                                );
                        Intent intent = new Intent(act, MainActivity.class);
                        ActivityCompat.startActivity(act, intent, options.toBundle());
                        act.overridePendingTransition(R.anim.slide_up, R.anim.scale_down);

                    }
                });

            }
        };

        one.start();
    }


    public static ActivityOptionsCompat makeOptionsCompat(Activity fromActivity, Pair<View, String>... sharedElements) {
        ArrayList<Pair<View, String>> list = new ArrayList<>(Arrays.asList(sharedElements));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            list.add(Pair.create(fromActivity.findViewById(android.R.id.statusBarBackground), Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME));
            list.add(Pair.create(fromActivity.findViewById(android.R.id.navigationBarBackground), Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME));
        }

        //remove any views that are null
        for (ListIterator<Pair<View, String>> iter = list.listIterator(); iter.hasNext();) {
            Pair pair = iter.next();
            if (pair.first == null) iter.remove();
        }

        sharedElements = list.toArray(new Pair[list.size()]);
        return ActivityOptionsCompat.makeSceneTransitionAnimation(
                fromActivity,
                sharedElements
        );
    }

}
