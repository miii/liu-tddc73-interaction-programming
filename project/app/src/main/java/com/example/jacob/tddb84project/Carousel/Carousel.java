package com.example.jacob.tddb84project.Carousel;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jacob.tddb84project.R;

import java.util.ArrayList;
import java.util.List;

public class Carousel extends LinearLayout {

    private int itemsPerPage;
    private int currPage;

    private TextView titleText;
    private TextView pageText;
    private CarouselPageAdapter adapter;
    private List<View> items;

    public Carousel(AppCompatActivity context, String title) {
        // Construct with empty list
        this(context, title, new ArrayList<View>());
    }

    public Carousel(AppCompatActivity context, String title, List<View> items) {
        // Construct with default items per page
        this(context, title, items, 1);
    }

    public Carousel(AppCompatActivity context, String title, List<View> items, int itemsPerPage) {
        super(context);

        // Set context and item list
        this.items = items;
        this.itemsPerPage = itemsPerPage;

        // Get carousel view
        View view = context.getLayoutInflater().inflate(R.layout.carousel, null);
        view.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
        ));

        titleText = view.findViewById(R.id.carousel_title);
        titleText.setText(title);

        // Update page text
        pageText = view.findViewById(R.id.page);
        updatePageText();

        // Create ViewPager with adapter
        ViewPager vp = view.findViewById(R.id.carousel_viewpager);
        adapter = new CarouselPageAdapter(context.getSupportFragmentManager());
        vp.setAdapter(adapter);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                // Update current page
                currPage = i + 1;
                updatePageText();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        addView(view);
    }

    public void refresh() {
        // Notify adapter
        adapter.notifyDataSetChanged();
        // Update text
        updatePageText();
    }

    public void setTitle(String title) {
        titleText.setText(title);
    }

    public String getTitle() {
        return titleText.getText().toString();
    }

    public void setItems(List<View> i) {
        // Go to first page if list was reloaded
        currPage = 1;
        // Set new items
        items = i;
        // Notify adapter
        refresh();
    }

    public List<View> getItems() {
        return items;
    }

    public void setItemsPerPage(int n) {
        // Update and reload list
        itemsPerPage = n;
        setItems(items);
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public int getPageCount() {
        return (int) Math.ceil(items.size() * 1.0D / itemsPerPage);
    }

    private void updatePageText() {
        // Upper right corner text
        pageText.setText("Page " + currPage + "/" + getPageCount());
    }

    private class CarouselPageAdapter extends FragmentStatePagerAdapter {
        public CarouselPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // Use vertical LinearLayout as root layout for each page
            LinearLayout fragmentLayout = new LinearLayout(getContext());
            fragmentLayout.setOrientation(LinearLayout.HORIZONTAL);

            // Get subset of items for the specific page
            int startIndex = position * itemsPerPage;
            int endIndex = Math.min((position + 1) * itemsPerPage, items.size());
            List<View> subset = items.subList(startIndex, endIndex);

            // If last page and the items does not fill the remaining slots,
            // add dummy views to preserve layout
            if (subset.size() % itemsPerPage != 0) {
                subset = new ArrayList<>(subset);
                subset.add(new LinearLayout(getContext()));
            }

            for (View view : subset) {
                // Add each view to the root layout with the same widths
                fragmentLayout.addView(view);
                view.setLayoutParams(new LayoutParams(
                        LayoutParams.MATCH_PARENT,
                        LayoutParams.MATCH_PARENT,
                        1
                ));

            }

            // Create page fragment
            CarouselPageFragment fragment = new CarouselPageFragment();
            fragment.setView(fragmentLayout);

            return fragment;
        }

        @Override
        public int getCount() {
            return getPageCount();
        }
    }

}
