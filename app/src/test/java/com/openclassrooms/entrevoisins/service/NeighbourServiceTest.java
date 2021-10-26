package com.openclassrooms.entrevoisins.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void getNeighbourByIdWithSuccess() {
        Neighbour neighbourTo = service.getNeighbours().get(1);
        Neighbour neighbour2 = service.getNeighbours().get(2);
        assertEquals(neighbourTo, service.getNeighbourById(neighbourTo.getId()));
        assertNotEquals(neighbour2, service.getNeighbourById(neighbourTo.getId()));
    }

    @Test
    public void getFavoriteNeighbourWithSuccess() {
        Neighbour neighbour = service.getNeighbours().get(0);
        assertEquals(0,service.getFavoriteNeighbour().size());
        neighbour.setFavorite(true);
        assertEquals(1,service.getFavoriteNeighbour().size());
        assertTrue(service.getFavoriteNeighbour().contains(neighbour));
        neighbour.setFavorite(false);
        assertEquals(0,service.getFavoriteNeighbour().size());

    }
}
