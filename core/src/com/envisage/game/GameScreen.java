package com.envisage.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;

public class GameScreen implements Screen {

    final Envisage game;
    final float unitScale = 1f / 32f;

    GameStage gameStage;
    TiledMap map;
    SpriteBatch rendererBatch;
    Texture minimap;
    Texture redSoldier;
    OrthographicCamera camera;
    OrthogonalTiledMapRenderer renderer;
    Vector3 hoverPos;

    String mapName;


    public GameScreen(final Envisage game, String mapName) {
        this.game = game;
        this.mapName = mapName;
        this.minimap = new Texture("map.png");
        this.redSoldier = new Texture("red_soldier.png");
        this.hoverPos = new Vector3();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 20, 21);
        camera.update();

        gameStage = new GameStage(this);
        Gdx.input.setInputProcessor(gameStage);
    }

    @Override
    public void show() {
        map = new TmxMapLoader().load(mapName);
        renderer = new OrthogonalTiledMapRenderer(map, unitScale);
        rendererBatch = (SpriteBatch) renderer.getBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameStage.update();

        camera.position.set(gameStage.cameraVector);
        camera.update();

        renderer.setView(camera);
        renderer.render();

        gameStage.draw();

        hoverPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(hoverPos);

        rendererBatch.begin();
        rendererBatch.draw(redSoldier, 5, 5, 1, 1); //TODO use 2d array to store unit locations and calculate movements
        rendererBatch.end();

        game.batch.begin();
        game.font.draw(game.batch, "Turn: " + gameStage.currentPlayer, 5, 299);
        game.font.draw(game.batch, "Round: " + gameStage.round, 5, 262);
        game.font.draw(game.batch, "X: " + (int) hoverPos.x + ", Y: " + (int) hoverPos.y, 5, 225);
        game.batch.draw(minimap, 1, 1, 192, 192);
        game.batch.end();
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
        dispose();
    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        gameStage.dispose();
    }
}