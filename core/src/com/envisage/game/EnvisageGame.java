package com.envisage.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.io.File;

public class EnvisageGame extends ApplicationAdapter {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Map map;
    private Player player1;
    private Player player2;
    private Vector3 clickPos = new Vector3();

    @Override
    public void create() {
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        map = new Map("Map", new Texture("map.png"), new File("map_data.txt"));
        player1 = new Player("Player 1", Side.red);
        player1.addUnit(new Unit("unit", player1, new Texture("red_soldier.png"), 100.0f,
                100, 100, 100, 10, blockToPixel(4), blockToPixel(24)));
        player1.selectedUnit = player1.ownedUnits.get(0);
        camera.setToOrtho(false, 1857, 1569);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(map.image, 0, 0);
        for (Unit unit : player1.ownedUnits) {
            batch.draw(unit.image, unit.posX, unit.posY);
        }
        batch.end();

        if (Gdx.input.isTouched()) {
            clickPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(clickPos);
            player1.selectedUnit.setPosX(clickPos.x);
            player1.selectedUnit.setPosY(clickPos.y);
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
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