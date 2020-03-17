package com.envisage.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Knight extends Unit {

    public Knight(Player owner) {
        super(owner);
        this.type = UnitType.Knight;
        this.health = 100f;
        this.strength = 100;
        this.defence = 100;
        this.cost = 25;
        this.movementRange = 10;
        this.movesLeft = this.movementRange;
        spawn();
        initEventListener();
    }

    public void spawn() {
        if (owner.side == Side.red) {
            setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("red_soldier.png"))));
            setPosition(500, 500);
        } else {
            setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("blue_soldier.png"))));
        }
    }

    public void initEventListener() {
        this.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (owner.side == Side.red) {
                    setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("blue_soldier.png"))));
                } else {
                    setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("red_soldier.png"))));
                }
            }
        });
    }
}
