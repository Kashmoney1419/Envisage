package com.envisage.game;

import com.badlogic.gdx.graphics.Texture;

public class RedKnight extends Knight {

    public RedKnight(MapStage mapStage, Player owner) {
        super(mapStage, owner);
    }

    @Override
    public void spawn() {
        setSprite(new Texture("red_soldier.png"));
        spritePos(5, 24);
    }
}
