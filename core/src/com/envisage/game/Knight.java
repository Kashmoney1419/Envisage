package com.envisage.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;


public class Knight extends Unit {

    public Knight(final MapStage mapStage, final Player owner) {
        super(mapStage, owner);
        this.type = UnitType.Knight;
        this.currentHealth = 100f;
        this.totalHealth = 100;
        this.strength = 100;
        this.defence = 100;
        this.cost = 25;
        this.movementRange = 10;
        this.movesLeft = this.movementRange;
        final Knight knight = this;
        spawn();

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (mapStage.getCurrentPlayer() == owner) {
                    owner.setSelectedUnit(knight);
                    return true;
                } else {
                    return false;
                }
            }
        });

    }

    public void spawn() {
    }
}
