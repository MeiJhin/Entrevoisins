package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override

    public Neighbour getNeighbourById( long id){
        for (int i=0; i<neighbours.size(); i++) {
            if (neighbours.get(i).getId() == id) {
                return neighbours.get(i);

            }
        }

        return null;
    }

    @Override
    public List<Neighbour> getFavoriteNeighbour() {
        List<Neighbour> favourite = new ArrayList<Neighbour>();
        for (int i=0; i<neighbours.size(); i++) {
            if (neighbours.get(i).isFavorite()){
                favourite.add(neighbours.get(i));
            }

        }
        return favourite;
    }
}
