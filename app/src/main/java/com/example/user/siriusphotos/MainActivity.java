package com.example.user.siriusphotos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public static final String RECYCLER_VIEW_FRAGMENT_TAG="recycler view";
    public static final String IMAGE_VIEW_FRAGMENT_TAG="image view";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Toolbar toolbar=findViewById(R.id.toolbar);
       //setSupportActionBar(toolbar);
        RecyclerViewFragment fragment=RecyclerViewFragment.newInstance();
        ImageViewFragment imageViewFragment=ImageViewFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.image_frame,imageViewFragment,IMAGE_VIEW_FRAGMENT_TAG)
                .replace(R.id.recycler_frame,fragment,RECYCLER_VIEW_FRAGMENT_TAG)
                .commit();
    }
}
