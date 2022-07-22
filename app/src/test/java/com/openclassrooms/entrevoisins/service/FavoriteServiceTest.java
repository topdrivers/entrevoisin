package com.openclassrooms.entrevoisins.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class FavoriteServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    //add remove and get favorite list

    @Test
    public void getListFavNeighbourNotEmpty() {
        Neighbour neighbour = service.getNeighbours().get(0);
        service.addFavorite(neighbour);
        assertFalse(service.getFavoriteNeighbours().isEmpty());
    }

    @Test
    public void addNeighbourToFavoriteWithSuccess(){
        Neighbour neighbouradd = service.getNeighbours().get(0);
        service.addFavorite(neighbouradd);
        assertTrue(service.getFavoriteNeighbours().contains(neighbouradd));
    }

    @Test
    public void removeNeighbourToFavoriteWithSuccess(){
        Neighbour neighbourremove = service.getNeighbours().get(0);
        service.addFavorite(neighbourremove);
        assertTrue(service.getFavoriteNeighbours().contains(neighbourremove));
        service.removeFavorite((neighbourremove));
        assertFalse(service.getFavoriteNeighbours().contains(neighbourremove));
    }
}
