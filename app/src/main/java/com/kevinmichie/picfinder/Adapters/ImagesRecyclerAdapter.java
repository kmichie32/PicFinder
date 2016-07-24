package com.kevinmichie.picfinder.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kevinmichie.picfinder.R;
import com.kevinmichie.picfinder.ViewHolders.ImageViewHolder;

import java.util.List;

public class ImagesRecyclerAdapter extends RecyclerView.Adapter<ImageViewHolder> {

    private final List<String> imageUrls;

    public ImagesRecyclerAdapter(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_image, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {

        holder.bindData(imageUrls.get(position));

    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }
}
