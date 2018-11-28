package com.example.jacob.tddb84project.Carousel;

import android.support.v4.app.Fragment;
import android.view.View;

import java.util.List;

public abstract class CarouselPageFragment extends Fragment {

    private List<View> items;

    public void setItems(List<View> i) {
        items = i;
    }

    protected List<View> getItems() {
        return items;
    }

}
