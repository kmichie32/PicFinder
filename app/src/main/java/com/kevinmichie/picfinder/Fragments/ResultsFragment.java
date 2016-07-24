package com.kevinmichie.picfinder.Fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kevinmichie.picfinder.Adapters.ImagesRecyclerAdapter;
import com.kevinmichie.picfinder.R;
import com.kevinmichie.picfinder.databinding.*;

import java.util.ArrayList;
import java.util.List;

public class ResultsFragment extends Fragment {

    private static final String IMAGE_URLS_KEY = "IMAGE_URLS_KEY";
    private List<String> mImageUrls;
    private FragmentResultsBinding b;
    private ImagesRecyclerAdapter mAdapter;

    public static ResultsFragment newInstance(ArrayList<String> imageUrls) {

        Bundle args = new Bundle();

        args.putStringArrayList(IMAGE_URLS_KEY, imageUrls);

        ResultsFragment fragment = new ResultsFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mImageUrls = getArguments().getStringArrayList(IMAGE_URLS_KEY);
        mAdapter = new ImagesRecyclerAdapter(mImageUrls);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        b = DataBindingUtil.inflate(inflater, R.layout.fragment_results, container, false);

        b.resultsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        b.resultsRecyclerView.setAdapter(mAdapter);

        return b.getRoot();

    }
}
