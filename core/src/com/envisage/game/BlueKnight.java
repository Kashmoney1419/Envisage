package com.envisage.game;

import com.badlogic.gdx.graphics.Texture;

public class BlueKnight extends Knight {

    public BlueKnight(MapStage mapStage, Player owner) {
        super(mapStage, owner);
    }

    @Override
    public void spawn() {
        setSprite(new Texture("blue_soldier.png"));
        spritePos(52, 24);
    }
}
