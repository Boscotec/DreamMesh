package com.boscotec.deezerm;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class HomeFragment extends Fragment {
    private static int currentPage = 0;
    private static final Integer[] images =
            {R.drawable.olamide, R.drawable.kingfisher, R.drawable.olamide,  R.drawable.flower, R.drawable.olamide, R.drawable.olamide};
    private ArrayList<Integer> slideImages = new ArrayList<>();

    public static HomeFragment newInstance() {
        Bundle bundle = new Bundle();
        HomeFragment fragment = new HomeFragment();
        //fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for(int i=0; i<images.length; i++) {
            slideImages.add(images[i]);
        }
        prepareData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    private List<Genre> genreList = new ArrayList<>();
    private void prepareData() {
        Genre genre1 = new Genre("Rap", R.drawable.flower);
        genreList.add(genre1);

        Genre genre2 = new Genre("Chart", R.drawable.olamide);
        genreList.add(genre2);

        Genre genre3 = new Genre("popular", R.drawable.kingfisher);
        genreList.add(genre3);

        Genre genre4 = new Genre("Summer", R.drawable.olamide);
        genreList.add(genre4);

        Genre genre5 = new Genre("Hello", R.drawable.flower);
        genreList.add(genre5);

        Genre genre6 = new Genre("Hi", R.drawable.olamide);
        genreList.add(genre6);

        Genre genre7 = new Genre("Template", R.drawable.kingfisher);
        genreList.add(genre7);

        Genre genre8 = new Genre("I dont know", R.drawable.flower);
        genreList.add(genre8);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ViewPager  mPager = view.findViewById(R.id.pager);
        mPager.setAdapter(new SlideAdapter(getContext(), slideImages));

        CircleIndicator indicator = view.findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        BrowseAdapter browseAdapter = new BrowseAdapter(getContext(), genreList);

        RecyclerView browseRecycler = view.findViewById(R.id.browse_recycler);
        browseRecycler.setHasFixedSize(true);
        browseRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        //browseRecycler.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        browseRecycler.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        browseRecycler.setAdapter(browseAdapter);


        RecyclerView pickRecycler = view.findViewById(R.id.picks_recycler);
        pickRecycler.setHasFixedSize(true);
        pickRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        //pickRecycler.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        pickRecycler.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        pickRecycler.setAdapter(browseAdapter);

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == images.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);
    }

    private class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        private GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int column = position % spanCount;

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;

                if (position < spanCount) {
                    outRect.top = spacing;
                }
                outRect.bottom = spacing;
            } else {
                outRect.left = column * spacing / spanCount;
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if (position >= spanCount) {
                    outRect.top = spacing;
                }
            }
        }
    }

    private int dpToPx(int dp) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics()));
    }
}