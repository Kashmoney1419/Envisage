package com.envisage.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapImageLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen implements Screen {

    final Envisage game;
    final float unitScale = 1f/32f;

    Stage stage;
    TiledMap map;
    TiledMapTileLayer objectLayer;
    TiledMapImageLayer imageLayer;
    OrthographicCamera camera;
    OrthogonalTiledMapRenderer renderer;

    Vector3 clickPos = new Vector3();

    public GameScreen(final Envisage game, String mapName) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 30, 30);
        camera.update();

        map = new TmxMapLoader().load(mapName);
        renderer = new OrthogonalTiledMapRenderer(map, unitScale);

        stage = new Stage();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        renderer.setView(camera);
        renderer.render();

        clickPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(clickPos);
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
    }

    int blockToPixel(int block) {
        return block * 32;
    }

    int pixelToBlock(float pixel) {
        return (int) (pixel % 32);
    }
}