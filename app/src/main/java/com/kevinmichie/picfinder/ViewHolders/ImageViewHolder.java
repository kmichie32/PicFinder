package com.kevinmichie.picfinder.ViewHolders;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.kevinmichie.picfinder.databinding.HolderImageBinding;

public class ImageViewHolder extends RecyclerView.ViewHolder {

    private final HolderImageBinding b;

    public ImageViewHolder(View itemView) {
        super(itemView);
        b = DataBindingUtil.bind(itemView);
    }

    public void bindData(String imageURL) {

        Glide.with(b.imageView.getContext())
                .load(imageURL)
                .fitCenter()
                .centerCrop()
                .into(b.imageView);
    }
}
