package com.example.user.siriusphotos.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.siriusphotos.R;
import com.example.user.siriusphotos.utils.Query;
import com.example.user.siriusphotos.utils.RecyclerViewData;

import java.util.ArrayList;


public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {
    interface OnItemClickListner {
        void onItemClickListner(RecyclerViewData data);
    }

    private RecyclerViewFragment fragment;
    private OnItemClickListner call;
    private static final int PHOTO = 0;
    private static final int GALLERY = 1;
    private static final int COLORIZER = 2;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        View view;

        public ViewHolder(View v) {
            super(v);
            view = v;
            imageView = view.findViewById(R.id.image);
            textView = view.findViewById(R.id.text);
        }
    }


    public PictureAdapter(RecyclerViewFragment fragment, OnItemClickListner callback) {
        this.fragment = fragment;
        call = callback;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false));
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.imageView.setImageBitmap(fragment.getList().get(position).getImg());
        holder.textView.setText(fragment.getList().get(position).getNameVis());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call.onItemClickListner(fragment.getList().get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return fragment.getList().size();
    }

}
