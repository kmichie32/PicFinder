package com.kevinmichie.picfinder.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kevinmichie.picfinder.Adapters.RestAdapter;
import com.kevinmichie.picfinder.Delegates.SearchFragmentDelegate;
import com.kevinmichie.picfinder.R;

import java.io.IOException;
import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;



public class SearchFragment extends Fragment {
    private static final String TAG = SearchFragment.class.getSimpleName();
    private FragmentSearchBinding b;
    private SearchFragmentDelegate mDelegate;

    public static SearchFragment newInstance() {

        Bundle args = new Bundle();

        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mDelegate = (SearchFragmentDelegate) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);


        b.searchButton.setOnClickListener(v -> {

            Log.d(TAG, "CLICK!");

            RestAdapter.getInstance()
                    .create(SearchApi.class)
                    .searchForImage("2688343-2891f1fe0c477ac2e59b83e53", b.searchEditText.getText().toString())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(jsonObjectResponse -> {

                        if (jsonObjectResponse.isSuccessful()) {

                            Log.d(TAG, jsonObjectResponse.toString());
                            ArrayList<String> imageUrls = new ArrayList<>();

                            JsonObject body = jsonObjectResponse.body();
                            JsonArray hits = body.getAsJsonArray("hits");

                            for (int i = 0; i < hits.size(); i++) {

                                JsonObject hitObject = hits.get(i).getAsJsonObject();
                                String imageUrl = hitObject.get("webformatURL").getAsString();
                                imageUrls.add(imageUrl);

                            }

                            mDelegate.onImagesFetched(imageUrls);

                        } else {

                            try {
                                Log.e(TAG, jsonObjectResponse.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                    }, error -> {

                        error.printStackTrace();


                    }, () -> {


                    });

        });


        return b.getRoot();
    }

    @Override
    public void onDetach() {
        mDelegate = null;
        super.onDetach();
    }
}
