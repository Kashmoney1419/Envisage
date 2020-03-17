package com.envisage.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameStage extends Stage {

    final GameScreen gameScreen;

    MapData mapData;
    Vector3 cameraVector;
    Vector3 clickPos;
    int[] cameraPosition;

    Queue<Player> turnQueue = new Queue<>();
    String currentPlayer;
    Player player1;
    Player player2;
    int round;

    TextButton endTurnButton;

    public GameStage(final GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        initGame();
    }

    public void initGame() {
        mapData = new MapData("map_data.txt");
        clickPos = new Vector3();
        gameScreen.camera.unproject(clickPos);
        cameraPosition = new int[]{1, 0};
        round = 1;

        //*****PLAYERS*****
        player1 = new Player("Player 1", Side.red);
        Knight red = new Knight(player1);
        player1.addUnit(red);
        this.addActor(red);
        turnQueue.addLast(player1);

        player2 = new Player("Player 2", Side.blue);
        Knight blue = new Knight(player1);
        player2.addUnit(blue);
        this.addActor(blue);
        turnQueue.addLast(player2);

        //*****BUTTONS*****
        endTurnButton = new TextButton("End Turn", this.gameScreen.game.textButtonStyle);
        endTurnButton.setPosition(Gdx.graphics.getWidth() - endTurnButton.getWidth() - 1, 1);
        endTurnButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String name = turnQueue.removeFirst().name;
                if (name == "Player 1") {
                    turnQueue.addLast(player1);
                    cameraPosition[0] = 1;
                    cameraPosition[1] = 2;
                } else {
                    turnQueue.addLast(player2);
                    cameraPosition[0] = 1;
                    cameraPosition[1] = 0;
                    round++;
                }
            }
        });
        this.addActor(endTurnButton);
    }

    public void update() {
        currentPlayer = turnQueue.first().name;
        cameraVector = mapData.moveCamera(cameraPosition);
        handleInput();
    }

    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && cameraPosition[0] != 2) {
            cameraPosition[0] += 1;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && cameraPosition[0] != 0) {
            cameraPosition[0] -= 1;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && cameraPosition[1] != 2) {
            cameraPosition[1] += 1;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && cameraPosition[1] != 0) {
            cameraPosition[1] -= 1;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            gameScreen.game.close();
        }
        if (Gdx.input.isTouched()) {
            clickPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        }
    }
}
