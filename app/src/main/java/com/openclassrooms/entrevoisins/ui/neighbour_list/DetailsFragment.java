package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsFragment extends Fragment implements View.OnClickListener {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.details_fragment_adress_text)
    TextView adress;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.details_fragment_image)
    ImageView imageView;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.details_fragment_phone_text)
    TextView phone;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.details_fragment_facebook_text)
    TextView facebookLink;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.details_fragment_name)
    TextView name;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.details_fragment_profile_name)
    TextView profileName;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.details_fragment_description)
    TextView description;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.details_fragment_floating_button)
    FloatingActionButton floatingActionButton;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.details_fragment_back) ImageView back;

    private NeighbourApiService neighbourApiService;
    Neighbour neighbour;



    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        neighbourApiService = DI.getNeighbourApiService();

    }



    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        ButterKnife.bind(this,view);

        Bundle bundle = getArguments();

            if (bundle != null) {
                neighbour = (Neighbour) bundle.getSerializable("userSelected");
                name.setText(neighbour.getName());
                profileName.setText(neighbour.getName());
                adress.setText(neighbour.getAddress());
                phone.setText(neighbour.getPhoneNumber());
                facebookLink.setText("www.facebook.com/"+neighbour.getName());
                description.setText(neighbour.getAboutMe());

                setImageFavorite();

                Glide.with(view)
                        .load(neighbour.getAvatarUrl())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView);
            }

        floatingActionButton.setOnClickListener(this);

        back.setOnClickListener(v -> getActivity().finish());

        return view;
    }

    @Override
    public void onClick(View view) {
       setNeighbourFavorite();
    }


    private void setNeighbourFavorite() {
        if(neighbourApiService.isFavorite(neighbour)){

            neighbourApiService.removeFavorite(neighbour);


        }else {

            neighbourApiService.addFavorite(neighbour);


        }
        setImageFavorite();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void setImageFavorite() {
        if(neighbourApiService.isFavorite(neighbour)){

            floatingActionButton.setImageDrawable(getResources()
                    .getDrawable(R.drawable.ic_star_white_24dp));

        }else {

            floatingActionButton.setImageDrawable(getResources()
                    .getDrawable(R.drawable.ic_star_border_white_24dp));

        }

    }

}