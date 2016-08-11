package com.github.florent37.materialviewpager.trendster;

import android.app.Activity;
import android.app.ActivityManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.materialviewpager.MaterialAppCombatActivity;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.MaterialViewPagerAnimator;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.github.florent37.materialviewpager.trendster.Items.Article;
import com.github.florent37.materialviewpager.trendster.fragment.RecyclerViewFragment;
import com.github.florent37.materialviewpager.trendster.util.Requests;

public class MainActivity extends MaterialAppCombatActivity{

    private MaterialViewPager mViewPager;
    private Toolbar toolbar;

    private int index = 0;
    private Button right;
    private Button left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Requests.activity.finish();
                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
        mViewPager.activity = this;
        toolbar = mViewPager.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);

        final Article[] home_articles = new Article[]{SplashActivity.lifestyle[0],SplashActivity.sport[0],SplashActivity.tech[0],SplashActivity.business[0]};

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position % 5) {
                    case 1:

                        return RecyclerViewFragment.newInstance("2",SplashActivity.lifestyle);
                    case 2:

                        return RecyclerViewFragment.newInstance("9",SplashActivity.sport);
                    case 3:

                        return RecyclerViewFragment.newInstance("10",SplashActivity.tech);
                    case 4:

                        return RecyclerViewFragment.newInstance("3",SplashActivity.business);
                    default:
                        return RecyclerViewFragment.newInstance("Home",home_articles);
                }
            }

            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 5) {
                    case 0:
                        return "Home";
                    case 1:
                        return "Lifestyle";
                    case 2:
                        return "Sport";
                    case 3:
                        return "Technology";
                    case 4:
                        return "Business";
                }
                return "";
            }
        });

        left = (Button)findViewById(R.id.left);
        assert left != null;
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(0 == index)){
                    index--;
                    setmViewPager();
                }
            }
        });

        right = (Button)findViewById(R.id.right);
        assert right != null;
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(SplashActivity.home_featured.length-1 == index)){
                    index++;
                    setmViewPager();
                }
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());


            mViewPager.logoContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.notifyHeaderChanged();
                    Toast.makeText(getApplicationContext(), "Yes, the title is clickable", Toast.LENGTH_SHORT).show();
                }
            });
       /* mViewPager.animator.listener = new MaterialViewPagerAnimator.Listener(){
            public void ToolbarWillAppear(){
                left.setVisibility(View.INVISIBLE);
                right.setVisibility(View.INVISIBLE);
            }
            public void ToolbarWillDissapear(){
                left.setVisibility(View.VISIBLE);
                right.setVisibility(View.VISIBLE);
            }
        };*/
        setmViewPager();
    }

    void setmViewPager(){
        final TextView tx = (TextView) mViewPager.logoContainer.findViewById(R.id.title);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/HelveticaNeue.ttf");

        tx.setTypeface(custom_font);
        tx.setTypeface(tx.getTypeface(), Typeface.BOLD);

        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        tx.setText(SplashActivity.home_featured[index].title);
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.colorPrimary,
                                SplashActivity.home_featured[index].imageloc);
                    case 1:
                        tx.setText(SplashActivity.lifestyle_featured[index].title);
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.lifestyle,
                                SplashActivity.lifestyle_featured[index].imageloc);
                    case 2:
                        tx.setText(SplashActivity.sport_featured[index].title);
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.sport,
                                SplashActivity.sport_featured[index].imageloc);
                    case 3:
                        tx.setText(SplashActivity.tech_featured[index].title);
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.technology,
                                SplashActivity.tech_featured[index].imageloc);
                    case 4:
                        tx.setText(SplashActivity.business_featured[index].title);
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.business,
                                SplashActivity.business_featured[index].imageloc);
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });

    }

    @Override
    public void ToolbarWillAppear() {
        Log.d("Toolbar","--Appear--");
        left.setVisibility(View.INVISIBLE);
        right.setVisibility(View.INVISIBLE);
    }

    @Override
    public void ToolbarDidAppear() {
        Log.d("Toolbar","Appeared");
        setTitle("Trendster");
    }

    @Override
    public void ToolbarWillDissapear() {
        Log.d("Toolbar","--Disappear--");

        setTitle("");

    }

    @Override
    public void ToolbarDidDissappear() {
       Log.d("Toolbar","Disppeared");
        left.setVisibility(View.VISIBLE);
        right.setVisibility(View.VISIBLE);
    }
}
