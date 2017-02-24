package com.company;



public class Map {

    // az NxM-es pálya
    private Tile[][] tiles;

    // lehetséges kezdőpontok tömbje
    private Tile[] startPositions;

    // lehetséges alagutak pontjai
    private Tile[] tunnelPositions;



    public Tile[] getStartPositions() {
        return startPositions;
    }



}
