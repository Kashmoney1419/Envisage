package com.envisage.game;

import com.badlogic.gdx.graphics.Texture;

public class Unit {

    String name;
    Player owner;
    Texture image;
    float health;
    int strength;
    int defence;
    int cost;
    int movementRange;
    int movesLeft;
    float posX;
    float posY;

    public Unit(String name, Player owner, Texture image, float health, int strength, int defence, int cost, int movementRange, float posX, float posY) {
        this.name = name;
        this.owner = owner;
        this.image = image;
        this.health = health;
        this.strength = strength;
        this.defence = defence;
        this.cost = cost;
        this.movementRange = movementRange;
        this.movesLeft = movementRange;
        this.posX = posX;
        this.posY = posY;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getOwner() {
        return this.owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Texture getImage() {
        return this.image;
    }

    public void setImage(Texture image) {
        this.image = image;
    }

    public float getHealth() {
        return this.health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public int getStrength() {
        return this.strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDefence() {
        return this.defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getMovementRange() {
        return this.movementRange;
    }

    public void setMovementRange(int movementRange) {
        this.movementRange = movementRange;
    }

    public int getMovesLeft() {
        return this.movesLeft;
    }

    public void setMovesLeft(int movesLeft) {
        this.movesLeft = movesLeft;
    }

    public float getPosX() {
        return this.posX;
    }

    public void setPosX(float posX) {
        int block = (int) (posX / 32);
        this.posX = block * 32;
    }

    public float getPosY() {
        return this.posY;
    }

    public void setPosY(float posY) {
        int block = (int) (posY / 32);
        this.posY = block * 32;
    }
}
