package com.envisage.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import java.io.File;

public class GameScreen implements Screen {

    final Envisage game;
    OrthographicCamera camera;
    Map map;
    Player player1;
    Player player2;
    Vector3 clickPos = new Vector3();

    public GameScreen(final Envisage game) {
        this.game = game;
        map = new Map("Map", new Texture("map.png"), new File("map_data.txt"));
        camera = new OrthographicCamera();
        player1 = new Player("Player 1", Side.red);
        player1.addUnit(new Unit("unit", player1, new Texture("red_soldier.png"), 100.0f,
                100, 100, 100, 10, blockToPixel(4), blockToPixel(24)));
        player1.selectedUnit = player1.ownedUnits.get(0);
        camera.setToOrtho(false, 1857, 1569);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(map.image, 0, 0);
        for (Unit unit : player1.ownedUnits) {
            game.batch.draw(unit.image, unit.posX, unit.posY);
        }
        game.batch.end();

        if (Gdx.input.isTouched()) {
            clickPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(clickPos);
            player1.selectedUnit.setPosX(clickPos.x);
            player1.selectedUnit.setPosY(clickPos.y);
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        player1.dispose();
    }

    public int blockToPixel(int block) {
        return block * 32;
    }

    public int pixelToBlock(float pixel) {
        return (int) (pixel % 32);
    }
}