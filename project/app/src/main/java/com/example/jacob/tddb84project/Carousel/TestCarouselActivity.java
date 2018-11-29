package com.example.jacob.tddb84project.Carousel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jacob.tddb84project.Carousel.BasicCarouselPageFragment;
import com.example.jacob.tddb84project.Carousel.Carousel;
import com.example.jacob.tddb84project.R;

import java.util.ArrayList;
import java.util.List;

public class TestCarouselActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Item 1
        TextView tv = new TextView(this);
        tv.setText("Page 1");

        // Item 2
        TextView tv2 = new TextView(this);
        tv2.setText("Page 2 med lite mer text");

        // Item 3
        ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.ic_android_black_24dp);
        iv.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));

        // View list
        List<View> list = new ArrayList<>();
        list.add(tv);
        list.add(tv2);
        list.add(iv);

        Carousel carousel = new Carousel(this, BasicCarouselPageFragment.class, "Recently added");
        carousel.setItems(list);
        carousel.setItemsPerPage(2); // Default is 1

        setContentView(carousel);
    }
}
