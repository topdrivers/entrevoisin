package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.Utils.ItemClickSupport;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment {

    NeighbourApiService neighbourApiService;
    List<Neighbour> neighbourFavoriteList;
    MyNeighbourRecyclerViewAdapter myNeighbourRecyclerViewAdapter;
    RecyclerView recyclerView;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        neighbourApiService = DI.getNeighbourApiService();
    }

    private void initFavorite() {
        neighbourFavoriteList = neighbourApiService.getFavoriteNeighbours();
        myNeighbourRecyclerViewAdapter = new MyNeighbourRecyclerViewAdapter(neighbourFavoriteList);
        recyclerView.setAdapter(myNeighbourRecyclerViewAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        ButterKnife.bind(this,view);
        Context context = view.getContext();
        recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return view;

    }


    //Action click item FAVORITE recycler view

    private void configureOnClickFavoriteRecyclerView(){
        ItemClickSupport.addTo(recyclerView, R.layout.fragment_neighbour)
                .setOnItemClickListener((recyclerView, position, v) -> {

                    Neighbour neighbour = myNeighbourRecyclerViewAdapter.getNeighbour(position);

                    Intent myIntent = new Intent(getActivity(), DetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("userSelected", neighbour);
                    myIntent.putExtras(bundle);

                    FavoriteFragment.this.startActivity(myIntent);
                    Toast.makeText(getContext(), "You clicked on user : " + neighbour.getName(), Toast.LENGTH_SHORT).show();


                });
    }


    @Override
    public void onResume() {
        super.onResume();
        initFavorite();
        configureOnClickFavoriteRecyclerView();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    /**
     * Fired if the user clicks on a delete button
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        neighbourApiService.deleteNeighbour(event.neighbour);
        initFavorite();
    }

}