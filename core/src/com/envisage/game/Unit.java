package com.envisage.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Unit extends Actor {

    final float unitScale = 1f / 32f;

    Player owner;
    UnitType type;
    Sprite sprite;
    MapStage mapstage;
    ShapeRenderer shapeRenderer;

    float currentHealth;
    int totalHealth;
    int strength;
    int defence;
    int cost;
    int movementRange;
    int movesLeft;

    public Unit(MapStage mapStage, Player owner) {
        super();
        this.mapstage = mapStage;
        this.owner = owner;
        shapeRenderer = new ShapeRenderer();
        type = UnitType.none;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        shapeRenderer.setProjectionMatrix(mapstage.getViewport().getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(this.getX(), this.getY() + 1, 1, 1 / 5f);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(this.getX(), this.getY() + 1, currentHealth / totalHealth, 1 / 5f);
        shapeRenderer.end();
        batch.begin();
        sprite.draw(batch);
    }

    public void spritePos(float x, float y) {
        sprite.setPosition(x, y);
        sprite.setBounds(sprite.getX(), sprite.getY(), 1, 1);
        setBounds(sprite.getX(), sprite.getY(), 1, 1);
        scaleBy(unitScale);
    }

    public void moveUp() {
        if (movesLeft > 0) {
            spritePos(sprite.getX(), sprite.getY() + 1);
            movesLeft--;
        }
    }

    public void moveDown() {
        if (movesLeft > 0) {
            spritePos(sprite.getX(), sprite.getY() - 1);
            movesLeft--;
        }
    }

    public void moveRight() {
        if (movesLeft > 0) {
            spritePos(sprite.getX() + 1, sprite.getY());
            movesLeft--;
        }
    }

    public void moveLeft() {
        if (movesLeft > 0) {
            spritePos(sprite.getX() - 1, sprite.getY());
            movesLeft--;
        }
    }

    public Player getOwner() {
        return this.owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public float getHealth() {
        return this.currentHealth;
    }

    public void loseHealth(float health) {
        this.currentHealth -= health;
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

    public Sprite getSprite() {
        return this.sprite;
    }

    public void setSprite(Texture sprite) {
        this.sprite = new Sprite(sprite);
    }

    public UnitType getType() {
        return this.type;
    }

    public void setType(UnitType type) {
        this.type = type;
    }

    public enum UnitType {
        Knight, none
    }
}
