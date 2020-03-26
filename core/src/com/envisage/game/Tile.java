package com.envisage.game;

public class Tile {

    TileType type;
    boolean walkable;
    float x, y;
    int value;

    public Tile(int x, int y, TileType type, boolean walkable) {
        this.type = type;
        this.walkable = walkable;
        this.x = (float) x;
        this.y = (float) y;
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

    public float getX() { return x; }

    public float getY() { return y; }
}
