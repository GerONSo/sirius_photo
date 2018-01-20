package com.example.user.siriusphotos.ui;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.siriusphotos.R;


public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder>{
    private Bitmap[] mDataset;
    private static View pictureAdapterView;
    private ImageView imageView;

    private static final int PHOTO=0;
    private static final int GALLERY=1;
    private static final int COLORIZER=2;
    private static final String[] names={"Colorizer","Own Effect","The Scream","The Starry Night","Water Lilies","Dogs Playing"};


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        View view;
        public ViewHolder(View v) {
            super(v);
            view=v;
            pictureAdapterView=v;
            imageView=view.findViewById(R.id.image);
            textView=view.findViewById(R.id.text);
        }
    }
    public PictureAdapter(Bitmap[] myDataset) {
        mDataset = myDataset;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false));
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.imageView.setImageBitmap(mDataset[position]);
        holder.textView.setText(names[position]);

    }
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
    void openCamera(){
        Toast.makeText(PictureAdapter.pictureAdapterView.getContext(), "Camera Opened", Toast.LENGTH_SHORT).show();
    }
    void openGallery(){
        Toast.makeText(PictureAdapter.pictureAdapterView.getContext(), "Gallery Opened", Toast.LENGTH_SHORT).show();
    }
    void openColorizer(){
        Toast.makeText(PictureAdapter.pictureAdapterView.getContext(), "Colorizer Opened", Toast.LENGTH_SHORT).show();
    }
}
