package com.boscotec.deezerm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class SlideAdapter extends PagerAdapter {

    private ArrayList<Integer> images;
    private Context context;

    public SlideAdapter(Context context, ArrayList<Integer> images) {
        this.context = context;
        this.images=images;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public @NonNull Object instantiateItem(@NonNull ViewGroup view, int position) {
        View mImageLayout = LayoutInflater.from(context).inflate(R.layout.slide, view, false);
        ImageView mImage = mImageLayout.findViewById(R.id.image);
        mImage.setImageResource(images.get(position));
        view.addView(mImageLayout, 0);
        return mImageLayout;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}