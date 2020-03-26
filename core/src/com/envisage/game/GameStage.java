package com.envisage.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class GameStage extends Stage {

    final Envisage game;
    final MapStage mapStage;

    InputMultiplexer inputMultiplexer;

    TextButton endTurnButton;
    TextButton spawnKnightButton;

    public GameStage(final Envisage game, final MapStage mapStage) {
        super();

        this.game = game;
        this.mapStage = mapStage;

        inputMultiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
        inputMultiplexer.addProcessor(this);

        endTurnButton = new TextButton("End Turn", this.game.getTextButtonStyle());
        endTurnButton.setTransform(true);
        endTurnButton.setSize(100, 30);
        endTurnButton.setPosition(Gdx.graphics.getWidth() - endTurnButton.getWidth() - 1, 1);
        endTurnButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mapStage.endTurn();
            }
        });

        spawnKnightButton = new TextButton("Knight (10)", this.game.getTextButtonStyle());
        spawnKnightButton.setTransform(true);
        spawnKnightButton.setSize(100, 30);
        spawnKnightButton.setPosition(Gdx.graphics.getWidth() - spawnKnightButton.getWidth() - 1, endTurnButton.getHeight() + 2);
        spawnKnightButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mapStage.spawnKnight(mapStage.currentPlayer);
            }
        });

        this.addActor(endTurnButton);
        this.addActor(spawnKnightButton);
    }

}
