package com.example.dockedtoolbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.carousel.CarouselLayoutManager;
import com.google.android.material.carousel.CarouselSnapHelper;
import com.google.android.material.carousel.HeroCarouselStrategy;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView carouselRecyclerView = view.findViewById(R.id.carousel_recycler_view);

        carouselRecyclerView.setLayoutManager(new CarouselLayoutManager(new HeroCarouselStrategy()));

        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.classic);
        imageList.add(R.drawable.gourmet);
        imageList.add(R.drawable.perejil);

        CarouselAdapter adapter = new CarouselAdapter(imageList);
        carouselRecyclerView.setAdapter(adapter);

        CarouselSnapHelper snapHelper = new CarouselSnapHelper();
        snapHelper.attachToRecyclerView(carouselRecyclerView);
    }
}
