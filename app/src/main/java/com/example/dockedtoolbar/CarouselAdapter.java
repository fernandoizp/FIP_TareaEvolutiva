package com.example.dockedtoolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder> {

    private final List<Integer> imageResIds;

    public CarouselAdapter(List<Integer> imageResIds) {
        this.imageResIds = imageResIds;
    }

    @NonNull
    @Override
    public CarouselViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.carousel_item, parent, false);
        return new CarouselViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarouselViewHolder holder, int position) {
        holder.imageView.setImageResource(imageResIds.get(position));
    }

    @Override
    public int getItemCount() {
        return imageResIds.size();
    }

    static class CarouselViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        CarouselViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.carousel_image_view);
        }
    }
}
