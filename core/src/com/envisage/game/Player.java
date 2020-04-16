package com.envisage.game;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.HashSet;

public class Player {

    String name;
    Side side;
    int bank;
    ArrayList<Unit> ownedUnits;
    HashSet<Resource> ownedResources;
    Unit selectedUnit;
    HashSet<Vector2> unitPositions;
    int[] prevCameraPosition;

    public Player(String name, Side side) {
        this.name = name;
        this.side = side;
        bank = 25;
        ownedUnits = new ArrayList<>();
        ownedResources = new HashSet<>();
        unitPositions = new HashSet<>();
        prevCameraPosition = new int[2];
    }

    public void harvestResources() {
        for (Resource resource : ownedResources) {
            this.bank += resource.getValue();
        }
    }

    public void updateUnitPositions() {
        unitPositions.clear();
        for (Unit unit : ownedUnits) {
            unitPositions.add(new Vector2(unit.getX(), unit.getY()));
        }
    }

    public void resetUnitMovement() {
        for (Unit unit : ownedUnits) {
            unit.setMovesLeft(unit.getMovementRange());
        }
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

    public int getBank() {
        return this.bank;
    }

    public Unit getSelectedUnit() {
        return this.selectedUnit;
    }

    public void setSelectedUnit(Unit unit) {
        this.selectedUnit = unit;
    }

    public int[] getPrevCameraPosition() {
        return this.prevCameraPosition;
    }

    public void setPrevCameraPosition(int[] cameraPosition) {
        this.prevCameraPosition = cameraPosition;
    }

    public HashSet<Vector2> getUnitPositions() {
        return this.unitPositions;
    }

    public void addUnit(Unit unit) {
        ownedUnits.add(unit);
        bank -= unit.getCost();
    }

    public void removeUnit(Unit unit) {
        ownedUnits.remove(unit);
    }

    public void addResource(Resource resource) {
        ownedResources.add(resource);
    }

    public void removeResource(Resource resource) {
        ownedResources.remove(resource);
    }
}
