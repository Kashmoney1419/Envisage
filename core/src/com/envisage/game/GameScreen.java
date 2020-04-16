package com.envisage.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen implements Screen {

    final Envisage game;
    final float unitScale = 1f / 32f;

    GameStage gameStage;
    MapStage mapStage;
    TiledMap map;
    Texture minimap;
    OrthographicCamera camera;
    OrthogonalTiledMapRenderer renderer;
    Vector3 hoverPos;

    String mapName;

    public GameScreen(final Envisage game, String mapName) {
        this.game = game;
        this.mapName = mapName;
        this.minimap = new Texture("map.png");
        this.hoverPos = new Vector3();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 20, 21);
        camera.update();

        Gdx.input.setInputProcessor(new InputMultiplexer());
    }

    @Override
    public void show() {
        map = new TmxMapLoader().load(mapName);
        renderer = new OrthogonalTiledMapRenderer(map, unitScale);

        mapStage = new MapStage(this.game, new FitViewport(camera.viewportWidth, camera.viewportHeight, camera));
        gameStage = new GameStage(this.game, mapStage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapStage.getViewport().apply();
        mapStage.update();

        camera.position.set(mapStage.getCameraVector());
        camera.update();

        renderer.setView(camera);
        renderer.render();

        mapStage.act(Gdx.graphics.getDeltaTime());
        mapStage.draw();

        gameStage.getViewport().apply();
        gameStage.act(Gdx.graphics.getDeltaTime());
        gameStage.draw();

        hoverPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(hoverPos);

        game.batch.begin();
        game.screenFont.draw(game.batch, "Turn: " + mapStage.currentPlayer.getName(), 1, 200);
        game.screenFont.draw(game.batch, "Bank: " + mapStage.currentPlayer.getBank(), 1, 175);
        game.screenFont.draw(game.batch, "X: " + (int) hoverPos.x + ", Y: " + (int) hoverPos.y, 1, 150);
        game.batch.draw(minimap, 1, 1, 128, 128);
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
        mapStage.dispose();
    }
}