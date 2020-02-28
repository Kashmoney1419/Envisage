package com.envisage.game;

public class Tile {

    TileType type;
    boolean walkable;
    int value;

    public Tile(TileType type, boolean walkable, int value) {
        this.type = type;
        this.walkable = walkable;
        this.value = value;
    }

    public TileType getType() {
        return this.type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public boolean getWalkable() {
        return this.walkable;
    }

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
