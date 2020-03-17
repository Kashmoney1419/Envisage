package com.envisage.game;

import java.util.ArrayList;

public class Player {

    String name;
    Side side;
    ArrayList<Unit> ownedUnits = new ArrayList<>();
    ArrayList<Unit> killedUnits = new ArrayList<>();

    public Player(String name, Side side) {
        this.name = name;
        this.side = side;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Side getSide() {
        return this.side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public void addUnit(Unit unit) {
        ownedUnits.add(unit);
    }

    public void removeUnit(Unit unit) {
        ownedUnits.remove(unit);
    }
}
