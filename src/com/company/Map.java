package com.company;


import java.util.ArrayList;
import java.util.List;

public class Map {

    // az NxM-es pálya
    private Tile[][] tiles = new Tile[6][11];

    // lehetséges kezdőpontok tömbje
    private ArrayList<Tile> startPositions = new ArrayList<Tile>();

    // lehetséges alagutak pontjai
    private ArrayList<Tile> tunnelPositions;



    public ArrayList<Tile> getStartPositions() {
        return startPositions;
    }

    public void setStartPositions(ArrayList<Tile> sp) {
        startPositions = sp;
    }

    public ArrayList<Tile> getTunnelPositions() {
        return tunnelPositions;
    }

    public Tile getTileByIndex(int i, int k) {
        return tiles[i][k];
    }


    // Fájlból vagy bedrótozva betöltünk egy játékot
    public Map(int mapNumber) {

        // tiles változó feltoltese,  üres tile-okkal
        // 5X10-es pálya:
        for (int i = 1; i < 6; i++){
            for (int k = 1; k < 11; k++)
                tiles[i][k] = new Tile(i, k);
        }

        // ide pedig a tile feltöltése kéne
        // itt szedjük szét pályákra
        if (mapNumber == 1) {
            // első pálya
            Tile t1 = getTileByIndex(2,1);
            Tile t2 = getTileByIndex(5,1);

            t1.createRail(2, 1, 1);
            t2.createRail(5, 1, 1);

            ArrayList<Tile> sp = new ArrayList<>();
            sp.add(getTileByIndex(2,1));
            sp.add(getTileByIndex(5,1));
            setStartPositions(sp);
        }




        if (mapNumber == 2) {
            // második
        }

    }

}
