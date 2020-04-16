package com.envisage.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class MainMenuScreen implements Screen {

    final Envisage game;
    OrthographicCamera camera;
    Texture envisageLogo;
    Texture actsOfWarLogo;
    Texture clickContinueLogo;

    public MainMenuScreen(final Envisage game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        envisageLogo = new Texture("envisage_logo.png");
        actsOfWarLogo = new Texture("acts_of_war_logo.png");
        clickContinueLogo = new Texture("click_continue_logo.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(envisageLogo, Gdx.graphics.getWidth() / 2f - envisageLogo.getWidth() / 2f, Gdx.graphics.getHeight() / 2f + envisageLogo.getHeight() / 2f);
        game.batch.draw(actsOfWarLogo, Gdx.graphics.getWidth() / 2f - actsOfWarLogo.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - envisageLogo.getHeight() / 2f);
        game.batch.draw(clickContinueLogo, Gdx.graphics.getWidth() / 2f - clickContinueLogo.getWidth() / 2f, 100);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game, "map..tmx"));
            dispose();
        }
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
        envisageLogo.dispose();
        actsOfWarLogo.dispose();
        clickContinueLogo.dispose();
    }
}
