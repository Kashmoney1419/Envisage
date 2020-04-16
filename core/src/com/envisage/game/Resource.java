package com.envisage.game;

import com.badlogic.gdx.math.Vector2;

import java.util.HashSet;

public class Resource extends Tile {

    HashSet<Vector2> controlTiles;

    public Resource(int x, int y, TileType type, boolean walkable, int value) {
        super(x, y, type, walkable);
        this.value = value;
        controlTiles = new HashSet<>();
        setControlTiles();
    }

    public void setControlTiles() {
        controlTiles.add(new Vector2(x + 1, y + 1));
        controlTiles.add(new Vector2(x + 1, y));
        controlTiles.add(new Vector2(x + 1, y - 1));
        controlTiles.add(new Vector2(x, y + 1));
        controlTiles.add(new Vector2(x, y - 1));
        controlTiles.add(new Vector2(x - 1, y + 1));
        controlTiles.add(new Vector2(x - 1, y));
        controlTiles.add(new Vector2(x - 1, y - 1));
    }

    public HashSet<Vector2> getControlTiles() {
        return this.controlTiles;
    }

    public int getValue() {
        return this.value;
    }
}
