package com.mobilesysteme.fatnessapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.mobilesysteme.fatnessapp.R;
import com.mobilesysteme.fatnessapp.TabStatusAdapter;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Toolbar toolbar = findViewById(R.id.toolbar);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager = findViewById(R.id.pager);

        setupToolbar(toolbar);
        setupTabLayout(tabLayout, viewPager);
    }

    /**
     * sets up the tabbed layout of the activity
     * @param tabLayout TabLayout element of current view
     * @param viewPager ViewPager2 element of current view
     */
    private void setupTabLayout(TabLayout tabLayout, ViewPager2 viewPager) {
        viewPager.setAdapter(new TabStatusAdapter(getSupportFragmentManager(), getLifecycle()));
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, true, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Nahrungsmittel");
                    break;
                case 1:
                    tab.setText("Rezepte");
                    break;
                default:
                    tab.setText("empty");
                    break;
            }
        });
        tabLayoutMediator.attach();
    }

    /**
     * sets up toolbar and title
     * @param toolbar Toolbar element of current view
     */
    private void setupToolbar(Toolbar toolbar) {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Nahrungsmittel hinzufügen");
    }
}
