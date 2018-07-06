package com.boscotec.deezerm;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.List;

public class FlowFragment extends Fragment {
     private static final Integer[] images =
            {R.drawable.olamide, R.drawable.kingfisher, R.drawable.olamide,  R.drawable.flower, R.drawable.olamide, R.drawable.olamide};
    private ArrayList<Integer> slideImages = new ArrayList<>();

    private DiscreteScrollView itemPicker;
    private InfiniteScrollAdapter infiniteAdapter;

    public static FlowFragment newInstance() {
        return new FlowFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_flow, container, false);
    }

    private List<Genre> genreList = new ArrayList<>();
    private void prepareData() {
        Genre genre1 = new Genre("Rap", R.drawable.flower);
        genreList.add(genre1);

        Genre genre2 = new Genre("Chart", R.drawable.olamide);
        genreList.add(genre2);

        Genre genre3 = new Genre("popular", R.drawable.kingfisher);
        genreList.add(genre3);

        Genre genre4 = new Genre("Summer", R.drawable.kingfisher);
        genreList.add(genre4);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        itemPicker = view.findViewById(R.id.picker);
        itemPicker.setOrientation(DSVOrientation.HORIZONTAL);
        infiniteAdapter = InfiniteScrollAdapter.wrap(new FlowAdapter(getContext(), genreList));
        itemPicker.setAdapter(infiniteAdapter);
        itemPicker.setItemTransformer(new ScaleTransformer.Builder().setMinScale(0.8f).build());
    }
}