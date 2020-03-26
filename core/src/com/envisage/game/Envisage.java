package com.envisage.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Envisage extends Game {

    public SpriteBatch batch;
    public BitmapFont screenFont;
    public BitmapFont buttonFont;
    public TextureAtlas textureAtlas;
    public Skin skin;
    public TextButton.TextButtonStyle textButtonStyle;


    public void create() {
        batch = new SpriteBatch();

        screenFont = new BitmapFont(Gdx.files.internal("text_button_skin/default.fnt"));
        screenFont.getData().setScale(1.33f);
        screenFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        buttonFont = new BitmapFont(Gdx.files.internal("text_button_skin/default.fnt"));
        buttonFont.getData().setScale(1f);
        buttonFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        textureAtlas = new TextureAtlas(Gdx.files.internal("text_button_skin/uiskin.atlas"));

        skin = new Skin();
        skin.addRegions(textureAtlas);

        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = buttonFont;
        textButtonStyle.up = skin.getDrawable("default-round");
        textButtonStyle.down = skin.getDrawable("default-round-down");

        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        screenFont.dispose();
        buttonFont.dispose();
        textureAtlas.dispose();
        skin.dispose();
        this.getScreen().dispose();
    }

    public void close() {
        Gdx.app.exit();
    }

    public TextButton.TextButtonStyle getTextButtonStyle() {
        return this.textButtonStyle;
    }
}
