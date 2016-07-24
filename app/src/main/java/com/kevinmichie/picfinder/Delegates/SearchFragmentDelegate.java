package com.kevinmichie.picfinder.Delegates;

import java.util.ArrayList;

public interface SearchFragmentDelegate {

    void onImagesFetched(ArrayList<String> imageUrls);

}
