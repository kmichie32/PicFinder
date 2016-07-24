package com.kevinmichie.picfinder.Activities;

import android.app.SearchManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.kevinmichie.picfinder.Delegates.SearchFragmentDelegate;
import com.kevinmichie.picfinder.Fragments.ResultsFragment;
import com.kevinmichie.picfinder.Fragments.SearchFragment;
import com.kevinmichie.picfinder.R;

import java.util.ArrayList;


public class PictureFinderActivity extends AppCompatActivity implements SearchFragmentDelegate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar my_toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(my_toolbar);

        getSupportActionBar().setTitle(R.string.my_toolbar_title);
        getSupportActionBar().setIcon(R.drawable.ic_toolbar);

        if (savedInstanceState == null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.search_image_content_frame_layout, SearchFragment.newInstance())
                    .commit();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_1:
                Toast.makeText(PictureFinderActivity.this, "You Clicked Option 1", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_2:
                Toast.makeText(PictureFinderActivity.this, "You Clicked Option 2", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onImagesFetched(ArrayList<String> imageUrls) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.search_image_content_frame_layout, ResultsFragment.newInstance(imageUrls))
                .addToBackStack(null)
                .commit();
    }
}
