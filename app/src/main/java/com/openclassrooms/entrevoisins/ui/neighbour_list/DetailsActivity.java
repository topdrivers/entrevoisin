package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.openclassrooms.entrevoisins.R;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        configureFragment();
    }

    private void configureFragment() {

        // A - Get FragmentManager (Support) and Try to find existing instance of fragment in FrameLayout container
        DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager()
                .findFragmentById(R.id.frame_layout_details);

        if (detailsFragment == null) {

            detailsFragment = new DetailsFragment();
            Intent intent = this.getIntent();
            Bundle bundle = intent.getExtras();
            detailsFragment.setArguments(bundle);

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame_layout_details, detailsFragment)
                    .commit();

        }


    }
}