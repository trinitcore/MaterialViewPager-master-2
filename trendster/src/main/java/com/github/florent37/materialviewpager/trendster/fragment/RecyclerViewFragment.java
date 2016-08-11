package com.github.florent37.materialviewpager.trendster.fragment;

import android.app.ActivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.github.florent37.materialviewpager.trendster.Items.Article;
import com.github.florent37.materialviewpager.trendster.R;
import com.github.florent37.materialviewpager.trendster.fragment.adapter.HomeRecyclerViewAdapter;
import com.github.florent37.materialviewpager.trendster.fragment.adapter.NewsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class RecyclerViewFragment extends Fragment {

    static final boolean GRID_LAYOUT = false;
    private static final int ITEM_COUNT = 10;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<Object> mContentItems = new ArrayList<>();
    private String type;
    private Article[] data;

    public static RecyclerViewFragment newInstance(String type, Article[] data) {
        return new RecyclerViewFragment().setParameters(type,data);
    }


    public RecyclerViewFragment(){

    }

    public RecyclerViewFragment setParameters(String type, Article[] data){
        this.type = type;
        this.data = data;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager;

        if (GRID_LAYOUT) {
            layoutManager = new GridLayoutManager(getActivity(), 2);
        } else {
            layoutManager = new LinearLayoutManager(getActivity());
        }
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        //Use this now
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());

        try {
            if (type.equals("Home")) {
                mAdapter = new HomeRecyclerViewAdapter(getActivity(), data);

            } else {
                mAdapter = new NewsRecyclerViewAdapter(getActivity(), data);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //mAdapter = new RecyclerViewMaterialAdapter();
        mRecyclerView.setAdapter(mAdapter);

        {
            for (int i = 0; i < ITEM_COUNT; ++i) {
                mContentItems.add(new Object());
            }
            mAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            // ...
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                if (type.equals("2")) {
                        ActivityManager.TaskDescription taskDescription = new ActivityManager.TaskDescription(null, null, getResources().getColor(R.color.lifestyle));
                        getActivity().setTaskDescription(taskDescription);
                } else if (type.equals("9")) {
                        ActivityManager.TaskDescription taskDescription = new ActivityManager.TaskDescription(null, null, getResources().getColor(R.color.sport));
                        getActivity().setTaskDescription(taskDescription);
                } else if (type.equals("10")) {
                        ActivityManager.TaskDescription taskDescription = new ActivityManager.TaskDescription(null, null, getResources().getColor(R.color.technology));
                        getActivity().setTaskDescription(taskDescription);
                } else if (type.equals("3")) {
                        ActivityManager.TaskDescription taskDescription = new ActivityManager.TaskDescription(null, null, getResources().getColor(R.color.business));
                        getActivity().setTaskDescription(taskDescription);
                } else if (type.equals("Home")){
                    try {
                        ActivityManager.TaskDescription taskDescription = new ActivityManager.TaskDescription(null, null, getResources().getColor(R.color.colorPrimary));
                        getActivity().setTaskDescription(taskDescription);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void onResume(){
        super.onResume();

    }

}
